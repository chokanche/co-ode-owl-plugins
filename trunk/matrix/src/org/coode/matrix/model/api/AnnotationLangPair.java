package org.coode.matrix.model.api;

import java.net.URI;
import java.util.ArrayList;
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
 * Author: drummond<br>
 * http://www.cs.man.ac.uk/~drummond/<br><br>
 * <p/>
 * The University Of Manchester<br>
 * Bio Health Informatics Group<br>
 * Date: Nov 17, 2008<br><br>
 */
public class AnnotationLangPair implements ColumnFilterPair<URI, String> {

    private List pair = new ArrayList();

    public AnnotationLangPair(URI uri, String lang) {
        pair.add(uri);
        pair.add(lang);
    }


    public URI getColumnObject() {
        return (URI)pair.get(0);
    }


    public String getFilterObject() {
        return (String)pair.get(1);
    }


    public int hashCode() {
        return pair.hashCode();
    }


    public boolean equals(Object o) {
        return o instanceof AnnotationLangPair && pair.equals(o);
    }
}
