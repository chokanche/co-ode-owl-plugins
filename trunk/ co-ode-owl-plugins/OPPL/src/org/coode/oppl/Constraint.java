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
package org.coode.oppl;

import java.io.StringWriter;

import org.coode.oppl.variablemansyntax.ConstraintSystem;
import org.coode.oppl.variablemansyntax.Variable;
import org.semanticweb.owl.model.OWLObject;

import uk.ac.manchester.cs.owl.mansyntaxrenderer.ManchesterOWLSyntaxObjectRenderer;

/**
 * @author Luigi Iannone
 * 
 */
public class Constraint {
	protected Variable variable;
	protected OWLObject expression;

	/**
	 * @param variable
	 * @param expression
	 */
	public Constraint(Variable variable, OWLObject expression) {
		this.variable = variable;
		this.expression = expression;
	}

	/**
	 * @return the variable
	 */
	public Variable getVariable() {
		return this.variable;
	}

	/**
	 * @return the expression
	 */
	public OWLObject getExpression() {
		return this.expression;
	}

	public <O> O accept(ConstraintVisitor<O> visitor) {
		return visitor.visit(this);
	}

	public String toString(ConstraintSystem constraintSystem) {
		StringWriter writer = new StringWriter();
		ManchesterOWLSyntaxObjectRenderer renderer = new ManchesterOWLSyntaxObjectRenderer(
				writer);
		renderer.setShortFormProvider(new SimpleVariableShortFormProvider(
				constraintSystem));
		this.expression.accept(renderer);
		return this.variable.getName() + " != " + writer.toString();
	}
}