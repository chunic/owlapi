/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.semanticweb.owlapi.metrics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

/** Counts the number of "hidden" GCIs in an ontology imports closure. A GCI is
 * regarded to be a "hidden" GCI if it is essentially introduce via an
 * equivalent class axiom and a subclass axioms where the LHS of the subclass
 * axiom is nameed. For example, A equivalentTo p some C, A subClassOf B results
 * in a "hidden" GCI.
 * 
 * @author Matthew Horridge, The University Of Manchester, Bio-Health
 *         Informatics Group, Date: 13-Aug-2007 */
public class HiddenGCICount extends IntegerValuedMetric {
    /** @param owlOntologyManager
     *            manager to use */
    public HiddenGCICount(OWLOntologyManager owlOntologyManager) {
        super(owlOntologyManager);
    }

    @Override
    protected void disposeMetric() {}

    @Override
    protected boolean isMetricInvalidated(List<? extends OWLOntologyChange> changes) {
        for (OWLOntologyChange chg : changes) {
            if (chg.isAxiomChange()) {
                if (chg.getAxiom() instanceof OWLEquivalentClassesAxiom
                        || chg.getAxiom() instanceof OWLSubClassOfAxiom) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected Integer recomputeMetric() {
        Set<OWLClass> processed = new HashSet<OWLClass>();
        Set<OWLClass> result = new HashSet<OWLClass>();
        for (OWLOntology ont : getOntologies()) {
            for (OWLClass cls : ont.getClassesInSignature()) {
                if (!processed.contains(cls)) {
                    processed.add(cls);
                } else {
                    continue;
                }
                boolean foundEquivalentClassesAxiom = false;
                boolean foundSubClassAxiom = false;
                for (OWLOntology o : getOntologies()) {
                    if (!foundEquivalentClassesAxiom) {
                        foundEquivalentClassesAxiom = !o.getEquivalentClassesAxioms(cls)
                                .isEmpty();
                    }
                    if (!foundSubClassAxiom) {
                        foundSubClassAxiom = !o.getSubClassAxiomsForSubClass(cls)
                                .isEmpty();
                    }
                    if (foundSubClassAxiom && foundEquivalentClassesAxiom) {
                        result.add(cls);
                        break;
                    }
                }
            }
        }
        return result.size();
    }

    @Override
    public String getName() {
        return "Hidden GCI Count";
    }
}
