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
package org.coode.oae.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.core.ui.util.InputVerificationStatusChangedListener;
import org.protege.editor.core.ui.util.VerifiedInputEditor;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.description.OWLExpressionParserException;
import org.protege.editor.owl.ui.clsdescriptioneditor.ExpressionEditor;
import org.protege.editor.owl.ui.frame.AbstractOWLFrameSectionRowObjectEditor;
import org.semanticweb.owl.model.OWLException;

import uk.ac.manchester.mae.Constants;
import uk.ac.manchester.mae.evaluation.FormulaModel;

/**
 * @author Luigi Iannone
 * 
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Apr 4, 2008
 */
public class OWLCalculationsFormulaEditor extends
		AbstractOWLFrameSectionRowObjectEditor<FormulaModel> implements
		VerifiedInputEditor, InputVerificationStatusChangedListener {
	private OWLEditorKit owlEditorKit;
	private FormulaModel formulaModel;
	private ExpressionEditor<FormulaModel> editor;
	private JTextField nameTextField = new JTextField();
	private JPanel mainPanel = new JPanel(new BorderLayout());
	private Set<InputVerificationStatusChangedListener> listeners = new HashSet<InputVerificationStatusChangedListener>();

	/**
	 * @param owlEditorKit
	 * @param formulaAnnotationURIs
	 */
	public OWLCalculationsFormulaEditor(OWLEditorKit owlEditorKit) {
		this.owlEditorKit = owlEditorKit;
		this.init();
	}

	private void init() {
		this.editor = new ExpressionEditor<FormulaModel>(this.owlEditorKit,
				new OWLCalculationsExpressionChecker(this.owlEditorKit));
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.setBorder(ComponentFactory.createTitledBorder("Name"));
		namePanel.add(this.nameTextField);
		// will go away when the completer will be removed from the expression
		// editor
		this.removeKeyListeners();
		// Now add the appropriate auto-completer
		new FormulaCompleter(this.owlEditorKit, this.editor, this.editor
				.getExpressionChecker());
		this.editor.setPreferredSize(new Dimension(50, 200));
		this.editor.addStatusChangedListener(this);
		JScrollPane editorPane = ComponentFactory.createScrollPane(this.editor);
		JPanel editorPanel = new JPanel(new BorderLayout());
		editorPanel.setBorder(ComponentFactory.createTitledBorder("Formula: "));
		editorPanel.add(editorPane);
		this.nameTextField.getDocument().addDocumentListener(
				new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						this.updateURI();
					}

					/**
					 * 
					 */
					private void updateURI() {
						URI anUri = null;
						try {
							anUri = new URI(
									Constants.FORMULA_NAMESPACE_URI_STRING
											+ OWLCalculationsFormulaEditor.this.nameTextField
													.getText());
						} catch (URISyntaxException e1) {
							anUri = null;
						} finally {
							if (OWLCalculationsFormulaEditor.this.formulaModel != null) {
								OWLCalculationsFormulaEditor.this.formulaModel
										.setFormulaURI(anUri);
							}
							OWLCalculationsFormulaEditor.this.handleChange();
						}
					}

					public void insertUpdate(DocumentEvent e) {
						this.updateURI();
					}

					public void removeUpdate(DocumentEvent e) {
						this.updateURI();
					}
				});
		this.mainPanel.add(namePanel, BorderLayout.NORTH);
		this.mainPanel.add(editorPanel, BorderLayout.CENTER);
	}

	public void clear() {
		this.formulaModel = null;
	}

	public void dispose() {
		this.listeners.clear();
	}

	public FormulaModel getEditedObject() {
		return this.formulaModel;
	}

	@Override
	public Set<FormulaModel> getEditedObjects() {
		return Collections.singleton(this.getEditedObject());
	}

	public void setFormula(FormulaModel formulaModel) {
		this.nameTextField.setText(formulaModel.getFormulaURI().toString());
		String formulaString;
		formulaString = formulaModel
				.render(this.owlEditorKit.getModelManager());
		this.editor.setText(formulaString);
	}

	public void addStatusChangedListener(
			InputVerificationStatusChangedListener listener) {
		this.listeners.add(listener);
		this.notifyListener(listener);
	}

	public void removeStatusChangedListener(
			InputVerificationStatusChangedListener listener) {
		this.listeners.remove(listener);
	}

	public JComponent getEditorComponent() {
		return this.mainPanel;
	}

	private void removeKeyListeners() {
		KeyListener[] keyListeners = this.editor.getKeyListeners();
		for (KeyListener keyListener : keyListeners) {
			this.editor.removeKeyListener(keyListener);
		}
	}

	/**
	 * @param listener
	 */
	private void notifyListener(InputVerificationStatusChangedListener listener) {
		try {
			boolean valid = this.formulaModel != null ? this.formulaModel
					.getFormulaURI() != null : false;
			listener.verifiedStatusChanged(valid);
		} catch (RuntimeException e) {
			listener.verifiedStatusChanged(false);
		}
	}

	public void handleChange() {
		for (InputVerificationStatusChangedListener listener : this.listeners) {
			this.notifyListener(listener);
		}
	}

	public void verifiedStatusChanged(boolean newState) {
		this.formulaModel = null;
		if (newState) {
			try {
				this.formulaModel = this.editor.createObject();
				URI anUri = new URI(Constants.FORMULA_NAMESPACE_URI_STRING
						+ OWLCalculationsFormulaEditor.this.nameTextField
								.getText());
				this.formulaModel.setFormulaURI(anUri);
			} catch (OWLExpressionParserException e) {
				e.printStackTrace();
			} catch (OWLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				this.formulaModel.setFormulaURI(null);
			}
		}
		this.handleChange();
	}
}