package org.coode.matrix.model.api;

import org.semanticweb.owl.model.OWLObject;
import org.coode.jtreetable.TreeTableCellRenderer;
import org.coode.jtreetable.TreeTableModelAdapter;

import javax.swing.*;

import sun.jvm.hotspot.code.CodeBlob;

import java.util.List;
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
 * Date: Jul 6, 2007<br><br>
 */
public interface TreeMatrixModel<R extends OWLObject> extends MatrixModel<R> {

    /**
     * Convenience method for retrieving the tree.
     * @return a JTree
     */
    JTree getTree();

    /**
     * Get the tree table cell renderer
     * @return a TreeTableCellRenderer
     */
    TreeTableCellRenderer getTreeRenderer();

    /**
     * Get the model required by JTable and to access all TableModel methods directly.
     * Most of the time, you should be able to interact directly with the matrix model
     * @return a TableModel implementation for the treetable
     */
    TreeTableModelAdapter getTreeTableModelAdapter();
}