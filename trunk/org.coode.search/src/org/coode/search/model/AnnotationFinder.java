package org.coode.search.model;

import org.semanticweb.owl.model.*;
import org.protege.editor.owl.model.OWLModelManager;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
import java.net.URI;
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
 * Author: drummond<br>
 * http://www.cs.man.ac.uk/~drummond/<br><br>
 * <p/>
 * The University Of Manchester<br>
 * Bio Health Informatics Group<br>
 * Date: Mar 18, 2008<br><br>
 */
public class AnnotationFinder {

    public Set<OWLAxiom> getAnnotationAxioms(URI uri, String str, Set<OWLOntology> onts) {
        return getAnnotationAxioms(Collections.singleton(uri), str, onts);
    }

    public Set<OWLAxiom> getAnnotationAxioms(Set<URI> uris, String str, Set<OWLOntology> onts) {
        Set<OWLAxiom> annotAxioms = new HashSet<OWLAxiom>();
        Pattern p = null;
        try{
            if (str != null){
                p = Pattern.compile(str);
            }
        }
        catch (PatternSyntaxException e){
            e.printStackTrace();
        }
        for (OWLOntology ont : onts){
            for (OWLEntityAnnotationAxiom ax : ont.getAxioms(AxiomType.ENTITY_ANNOTATION)){
                if (uris.contains(ax.getAnnotation().getAnnotationURI())){
                    if (p != null){
                        String annotValue = ax.getAnnotation().getAnnotationValueAsConstant().getLiteral();
                        Matcher matcher = p.matcher(annotValue);
                        if (matcher.matches()){
                            annotAxioms.add(ax);
                        }
                    }
                    else{
                        annotAxioms.add(ax);
                    }
                }
            }
        }
        return annotAxioms;
    }
}