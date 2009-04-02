/**
 * Copyright (C) 2008, University of Manchester
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
package org.coode.patterns.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import org.coode.oppl.protege.ProtegeOPPLFactory;
import org.coode.oppl.syntax.OPPLParser;
import org.coode.patterns.PatternManager;
import org.coode.patterns.protege.ProtegePatternModelFactory;
import org.coode.patterns.syntax.PatternParser;
import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.owl.ui.frame.OWLFrameSectionRow;
import org.protege.editor.owl.ui.framelist.OWLFrameList2;
import org.protege.editor.owl.ui.view.AbstractActiveOntologyViewComponent;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyChange;

/**
 * @author Luigi Iannone
 * 
 * Jul 3, 2008
 */
public class PatternOntologyView extends AbstractActiveOntologyViewComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8110091764534100865L;
	private OWLFrameList2<OWLOntology> list;
	private PatternManager patternManager;

	/**
	 * @see org.protege.editor.owl.ui.view.AbstractActiveOntologyViewComponent#disposeOntologyView()
	 */
	@Override
	protected void disposeOntologyView() {
		if (this.list != null) {
			this.list.dispose();
		}
		if (this.patternManager != null) {
			this.getOWLEditorKit().getModelManager()
					.removeOntologyChangeListener(this.patternManager);
		}
	}

	/**
	 * @see org.protege.editor.owl.ui.view.AbstractActiveOntologyViewComponent#initialiseOntologyView()
	 */
	@Override
	protected void initialiseOntologyView() throws Exception {
		this.setLayout(new BorderLayout());
		OPPLParser.setOPPLFactory(new ProtegeOPPLFactory(this
				.getOWLModelManager()));
		this.list = new OWLFrameList2<OWLOntology>(this.getOWLEditorKit(),
				new PatternOntologyFrame(this.getOWLEditorKit(), true, true,
						true)) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4280726052980983935L;

			@Override
			public void handleDelete() {
				int[] selIndices = this.getSelectedIndices();
				List<OWLOntologyChange> changes = new ArrayList<OWLOntologyChange>();
				for (int selIndex : selIndices) {
					Object val = this.getModel().getElementAt(selIndex);
					if (val instanceof OWLFrameSectionRow) {
						OWLFrameSectionRow<?, ?, ?> row = (OWLFrameSectionRow<?, ?, ?>) val;
						changes
								.addAll(changes.size(), row
										.getDeletionChanges());
					}
				}
				for (OWLOntologyChange change : changes) {
					PatternOntologyView.this.getOWLEditorKit()
							.getModelManager().applyChange(change);
				}
			}
		};
		this.list.setRootObject(this.getOWLEditorKit().getModelManager()
				.getActiveOntology());
		this.list.setCellRenderer(new PatternCellRenderer(this
				.getOWLEditorKit()));
		JScrollPane listPane = ComponentFactory.createScrollPane(this.list);
		PatternParser.setPatternModelFactory(new ProtegePatternModelFactory(
				this.getOWLModelManager()));
		this.patternManager = PatternManager.getInstance(this.getOWLEditorKit()
				.getModelManager().getOWLOntologyManager());
		this.getOWLEditorKit().getModelManager().addOntologyChangeListener(
				this.patternManager);
		this.add(listPane);
	}

	/**
	 * @see org.protege.editor.owl.ui.view.AbstractActiveOntologyViewComponent#updateView(org.semanticweb.owl.model.OWLOntology)
	 */
	@Override
	protected void updateView(OWLOntology activeOntology) throws Exception {
		this.list.setRootObject(activeOntology);
	}
}
