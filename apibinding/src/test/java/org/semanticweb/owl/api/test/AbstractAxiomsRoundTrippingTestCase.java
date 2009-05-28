package org.semanticweb.owl.api.test;

import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLAxiom;
import org.semanticweb.owl.model.OWLOntologyChangeException;

import java.util.Set;
import java.util.HashSet;
/*
 * Copyright (C) 2009, University of Manchester
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
 * Author: Matthew Horridge<br>
 * The University of Manchester<br>
 * Information Management Group<br>
 * Date: 28-May-2009
 */
public abstract class AbstractAxiomsRoundTrippingTestCase extends AbstractRoundTrippingTest {

    private Set<OWLAxiom> axioms = new HashSet<OWLAxiom>();


    final protected OWLOntology createOntology() {
        try {
            OWLOntology ont = getOWLOntology("Ont");
            axioms.clear();
            axioms.addAll(createAxioms());
            getManager().addAxioms(ont, axioms);
            return ont;
        } catch (OWLOntologyChangeException e) {
            throw new RuntimeException(e);
        }
    }

    public void testRDFXML() throws Exception {
        super.testRDFXML();
    }

    public void testOWLXML() throws Exception {
        super.testOWLXML();
    }

    public void testFunctionalSyntax() throws Exception {
        super.testFunctionalSyntax();
    }

    public void testTurtle() throws Exception {
        super.testTurtle();
    }

    public void testManchesterOWLSyntax() throws Exception {
        super.testManchesterOWLSyntax();
    }

    protected abstract Set<? extends OWLAxiom> createAxioms();
}