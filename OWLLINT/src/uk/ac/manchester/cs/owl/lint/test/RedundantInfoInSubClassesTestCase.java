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
package uk.ac.manchester.cs.owl.lint.test;

import org.semanticweb.owl.lint.Lint;
import org.semanticweb.owl.lint.LintReport;

import uk.ac.manchester.cs.owl.lint.LintManagerFactory;
import uk.ac.manchester.cs.owl.lint.examples.RedundantInfoInSubClassesLintPattern;

/**
 * @author Luigi Iannone
 * 
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Feb 14, 2008
 */
public class RedundantInfoInSubClassesTestCase extends LintTestCase {
	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.manchester.cs.owl.lint.test.LintTestCase#getPhysicalOntologyURI()
	 */
	@Override
	protected String getPhysicalOntologyURI() {
		return "file:./lint/src/test/ontologies/RedundantInfoInSubClasses.owl";
	}

	@Override
	public void testDetected() throws Exception {
		LintReport detected = this.lint.detected(this.ontologies);
		assertTrue(
				"Lint does not detect anything and it really shoud not happen",
				!detected.getAffectedOntologies().isEmpty());
		System.out.println(detected);
	}

	@Override
	protected Lint createLint() {
		return LintManagerFactory.getLintManager().getLintFactory().createLint(
				new RedundantInfoInSubClassesLintPattern());
	}
}