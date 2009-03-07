package org.coode.annotate;

import org.apache.log4j.Logger;
import org.coode.annotate.prefs.AnnotationTemplatePrefs;
import org.protege.editor.owl.model.OWLModelManager;
import org.semanticweb.owl.model.*;

import java.io.IOException;
import java.net.URI;
import java.util.*;

/*
* Copyright (C) 2007, University of Manchester
*
* Modifications to the initial code base are copyright of their
* respective authors, or their employers as appropriate.  Authorship
* of the modifications may be determined from the ChangeLog placed at
* the end of this file.
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.

* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.

* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

/**
 * Author: Nick Drummond<br>
 * http://www.cs.man.ac.uk/~drummond/<br><br>
 * <p/>
 * The University Of Manchester<br>
 * Bio Health Informatics Group<br>
 * Date: Aug 16, 2007<br><br>
 */
public class TemplateModel {

    private static final Logger logger = Logger.getLogger(TemplateModel.class.getName());

    public static final String TEXT = "text";
    public static final String ENTITY = "entity";
    public static final String MULTILINE = "multiline";

    private final java.util.List<TemplateRow> cList = new ArrayList<TemplateRow>();

    private final AnnotationComponentComparator comparator = new AnnotationComponentComparator();

    private final Set<TemplateModelListener> listeners = new HashSet<TemplateModelListener>();

    private final OWLModelManager mngr;

    private OWLEntity currentEntity;

    private OWLOntologyChangeListener ontChangeListener = new OWLOntologyChangeListener(){
        public void ontologiesChanged(List<? extends OWLOntologyChange> list) throws OWLException {
            handleOntologyChanges(list);
        }
    };


    public TemplateModel(OWLModelManager mngr) throws IOException {
        this.mngr = mngr;

        mngr.addOntologyChangeListener(ontChangeListener);
    }


    public String getComponentType(URI uri){
        Set<String> params = AnnotationTemplatePrefs.getInstance().getParams(uri);

        // the order of these will determine precedence if multiple component types are specified
        if (params.contains(ENTITY)){
            return ENTITY;
        }
        else if (params.contains(MULTILINE)){
            return MULTILINE;
        }
        else {// default to text (params.contains(TEXT)){
            return TEXT;
        }
    }

    public Set<OWLAnnotationAxiom> getAnnotations(OWLEntity entity) {
        Set<OWLAnnotationAxiom> annotations = new HashSet<OWLAnnotationAxiom>();
        for (OWLOntology ont : mngr.getActiveOntologies()){
            annotations.addAll(entity.getAnnotationAxioms(ont));
        }
        return annotations;
    }


    public Set<OWLOntology> getOntologiesContainingAnnotation(TemplateRow templateRow) {
        Set<OWLOntology> onts = new HashSet<OWLOntology>();
        OWLAxiom ax = templateRow.getAxiom();
        if (ax != null) {
        	for (OWLOntology ont : mngr.getActiveOntologies()){
        		if (ont.containsAxiom(ax)){
        			onts.add(ont);
        		}
        	}
        }
        return onts;
    }

    public OWLModelManager getOWLModelManager() {
        return mngr;
    }

    public OWLEntity getEntity() {
        return currentEntity;
    }

    private void handleOntologyChanges(List<? extends OWLOntologyChange> changes) {
        for (OWLOntologyChange change : changes){
            if (change.isAxiomChange() &&
                change.getAxiom() instanceof OWLEntityAnnotationAxiom &&
                ((OWLEntityAnnotationAxiom)change.getAxiom()).getSubject().equals(currentEntity)){
                refresh();
                return;
            }
        }
    }

    private void refresh(){
        setEntity(currentEntity);
    }

    public void setEntity(OWLEntity entity) {
        currentEntity = entity;
        cList.clear();

        if (entity != null){
            Set<OWLAnnotationAxiom> annots = getAnnotations(entity);
            Set<URI> usedURIs = new HashSet<URI>();
            for (OWLAnnotationAxiom annot : annots){
                final URI annotationURI = annot.getAnnotation().getAnnotationURI();
                if (AnnotationTemplatePrefs.getInstance().getURIList().contains(annotationURI)){
                    usedURIs.add(annotationURI);
                    cList.add(new TemplateRow(annot, this));
                }
            }

            for (URI uri : AnnotationTemplatePrefs.getInstance().getURIList()){
                if (!usedURIs.contains(uri)){
                    cList.add(new TemplateRow(entity, uri, this));
                }
            }

            Collections.sort(cList, comparator);
        }

        notifyStructureChanged();
    }

    public List<TemplateRow> getRows() {
        return Collections.unmodifiableList(cList);
    }

    public void addRow(URI uri) {
        cList.add(new TemplateRow(currentEntity, uri, this));
        Collections.sort(cList, comparator);
        notifyStructureChanged();
    }

    public void removeRow(TemplateRow c) {
        c.setValue(null);
        cList.remove(c);
        notifyStructureChanged();
    }

    private void notifyStructureChanged() {
        for (TemplateModelListener l : listeners){
            l.modelStructureChanged();
        }
    }

    public void addModelListener(TemplateModelListener l){
        listeners.add(l);
    }

    public void removeModelListener(TemplateModelListener l){
        listeners.remove(l);
    }

    /**
     * Called by the rows when they make changes
     * (so that the table can manage itself without having to refresh completely)
     * This implementation disables the ontology change listeners temporarily
     *
     * @param changes the set of changes to apply
     */
    public void requestApplyChanges(List<OWLOntologyChange> changes) {
        if (!changes.isEmpty()){
            mngr.removeOntologyChangeListener(ontChangeListener);
            mngr.applyChanges(changes);
            mngr.addOntologyChangeListener(ontChangeListener);
        }
    }

    public void dispose() {
        mngr.removeOntologyChangeListener(ontChangeListener);
        listeners.clear();
    }

    class AnnotationComponentComparator implements Comparator<TemplateRow> {

        public int compare(TemplateRow c1, TemplateRow c2) {
            URI uri1 = c1.getURI();
            URI uri2 = c2.getURI();
            for (URI uri : AnnotationTemplatePrefs.getInstance().getURIList()){
                if (uri.equals(uri1)){
                    return -1;
                }
                if (uri.equals(uri2)){
                    return 1;
                }
            }
            return 0;
        }
    }
}
