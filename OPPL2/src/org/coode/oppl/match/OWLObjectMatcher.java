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
package org.coode.oppl.match;

import org.coode.oppl.variablemansyntax.ConstraintSystem;
import org.coode.oppl.variablemansyntax.Variable;
import org.coode.oppl.variablemansyntax.bindingtree.Assignment;
import org.coode.oppl.variablemansyntax.bindingtree.BindingNode;
import org.semanticweb.owl.model.OWLObject;

/**
 * @author Luigi Iannone
 * 
 */
public abstract class OWLObjectMatcher<T extends OWLObject> {
	private final ConstraintSystem constraintSystem;
	private final BindingNode bindings;

	/**
	 * @param constraintSystem
	 */
	public OWLObjectMatcher(BindingNode bindings,
			ConstraintSystem constraintSystem) {
		if (constraintSystem == null) {
			throw new NullPointerException(
					"The constraint system canno be null");
		}
		if (bindings == null) {
			throw new NullPointerException("The bindings cannot be null");
		}
		this.constraintSystem = constraintSystem;
		this.bindings = bindings;
	}

	protected void replace(Variable variable, T value) {
		if (variable.getType().isCompatibleWith(value)) {
			this.getBindings().addAssignment(new Assignment(variable, value));
		}
	}

	protected boolean isAssigned(Variable variable) {
		return this.bindings.getAssignmentValue(variable) != null;
	}

	protected boolean canReplace(Variable variable, T value) {
		return !this.isAssigned(variable)
				|| this.bindings.getAssignmentValue(variable).equals(value);
	}

	/**
	 * @return the bindings
	 */
	public BindingNode getBindings() {
		return this.bindings;
	}

	/**
	 * @return the constraintSystem
	 */
	public ConstraintSystem getConstraintSystem() {
		return this.constraintSystem;
	}
}
