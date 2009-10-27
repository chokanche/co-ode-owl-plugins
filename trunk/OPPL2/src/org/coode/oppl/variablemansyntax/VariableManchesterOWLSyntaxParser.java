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
package org.coode.oppl.variablemansyntax;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.coode.manchesterowlsyntax.ManchesterOWLSyntax;
import org.coode.manchesterowlsyntax.ManchesterOWLSyntaxEditorParser;
import org.coode.manchesterowlsyntax.ManchesterOWLSyntaxTokenizer;
import org.coode.manchesterowlsyntax.ManchesterOWLSyntaxTokenizer.Token;
import org.coode.oppl.syntax.OPPLParser;
import org.semanticweb.owl.expression.OWLEntityChecker;
import org.semanticweb.owl.expression.ParserException;
import org.semanticweb.owl.model.AxiomType;
import org.semanticweb.owl.model.OWLAxiom;
import org.semanticweb.owl.model.OWLClassAxiom;
import org.semanticweb.owl.model.OWLConstant;
import org.semanticweb.owl.model.OWLDataProperty;
import org.semanticweb.owl.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owl.model.OWLDataPropertyExpression;
import org.semanticweb.owl.model.OWLDescription;
import org.semanticweb.owl.model.OWLDisjointClassesAxiom;
import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLIndividualAxiom;
import org.semanticweb.owl.model.OWLLogicalAxiom;
import org.semanticweb.owl.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owl.model.OWLObjectPropertyAxiom;
import org.semanticweb.owl.model.OWLObjectPropertyExpression;
import org.semanticweb.owl.model.OWLPropertyDomainAxiom;
import org.semanticweb.owl.model.OWLPropertyExpression;
import org.semanticweb.owl.model.OWLPropertyRange;
import org.semanticweb.owl.model.OWLPropertyRangeAxiom;
import org.semanticweb.owl.model.OWLSubPropertyAxiom;
import org.semanticweb.owl.model.OWLUnaryPropertyAxiom;

/**
 * @author Luigi Iannone
 * 
 */
@SuppressWarnings("unchecked")
public class VariableManchesterOWLSyntaxParser extends
		ManchesterOWLSyntaxEditorParser {
	private static final List<AxiomType<? extends OWLPropertyRangeAxiom<? extends OWLPropertyExpression<?, ? extends OWLPropertyRange>, ?>>> RANGE_AXIOMTYPES = Arrays
			.asList(AxiomType.OBJECT_PROPERTY_RANGE,
					AxiomType.DATA_PROPERTY_RANGE);
	private static final List<AxiomType<? extends OWLPropertyDomainAxiom<? extends OWLPropertyExpression<?, ? extends OWLPropertyRange>>>> DOMAIN_AXIOMTYPES = Arrays
			.asList(AxiomType.DATA_PROPERTY_DOMAIN,
					AxiomType.OBJECT_PROPERTY_DOMAIN);
	private static final List<AxiomType<? extends OWLSubPropertyAxiom<? extends OWLPropertyExpression<?, ? extends OWLPropertyRange>>>> SUBPROPERTY_AXIOMTYPES = Arrays
			.asList(AxiomType.SUB_DATA_PROPERTY, AxiomType.SUB_OBJECT_PROPERTY);
	private static final List<AxiomType<? extends OWLUnaryPropertyAxiom<? extends OWLPropertyExpression<?, ? extends OWLPropertyRange>>>> FUNCTIONAL_AXIOMTYPES = Arrays
			.asList(AxiomType.FUNCTIONAL_DATA_PROPERTY,
					AxiomType.FUNCTIONAL_OBJECT_PROPERTY);
	private static final List<AxiomType<? extends OWLLogicalAxiom>> DISJOINTWITH_AXIOMTYPES = Arrays
			.asList(AxiomType.DISJOINT_CLASSES,
					AxiomType.DISJOINT_DATA_PROPERTIES,
					AxiomType.DISJOINT_OBJECT_PROPERTIES);
	private static final List<AxiomType<? extends OWLLogicalAxiom>> EQUIVALENTTO_AXIOMTYPES = Arrays
			.asList(AxiomType.EQUIVALENT_CLASSES,
					AxiomType.EQUIVALENT_DATA_PROPERTIES,
					AxiomType.EQUIVALENT_OBJECT_PROPERTIES);
	private static final List<AxiomType<? extends OWLIndividualAxiom>> AXIOM_TYPES = Arrays
			.asList(AxiomType.CLASS_ASSERTION,
					AxiomType.DATA_PROPERTY_ASSERTION,
					AxiomType.OBJECT_PROPERTY_ASSERTION,
					AxiomType.DATA_PROPERTY_ASSERTION,
					AxiomType.SAME_INDIVIDUAL, AxiomType.DIFFERENT_INDIVIDUALS,
					AxiomType.NEGATIVE_DATA_PROPERTY_ASSERTION,
					AxiomType.NEGATIVE_OBJECT_PROPERTY_ASSERTION);
	private static final Set<AxiomType<?>> CLASS_AXIOMTYPES = new HashSet<AxiomType<?>>(
			Arrays.asList(AxiomType.SUBCLASS, AxiomType.EQUIVALENT_CLASSES,
					AxiomType.DISJOINT_CLASSES));
	private static final Set<AxiomType<?>> OBJECTPROPERTY_AXIOMTYPES = new HashSet<AxiomType<?>>(
			Arrays.asList(AxiomType.ANTI_SYMMETRIC_OBJECT_PROPERTY,
					AxiomType.DISJOINT_OBJECT_PROPERTIES,
					AxiomType.FUNCTIONAL_OBJECT_PROPERTY,
					AxiomType.SYMMETRIC_OBJECT_PROPERTY,
					AxiomType.SUB_OBJECT_PROPERTY,
					AxiomType.OBJECT_PROPERTY_DOMAIN,
					AxiomType.OBJECT_PROPERTY_RANGE,
					AxiomType.INVERSE_FUNCTIONAL_OBJECT_PROPERTY,
					AxiomType.INVERSE_OBJECT_PROPERTIES,
					AxiomType.IRREFLEXIVE_OBJECT_PROPERTY,
					AxiomType.REFLEXIVE_OBJECT_PROPERTY,
					AxiomType.TRANSITIVE_OBJECT_PROPERTY,
					AxiomType.EQUIVALENT_OBJECT_PROPERTIES));
	private static final Set<AxiomType<?>> DATAPROPERTY_AXIOMTYPES = new HashSet<AxiomType<?>>(
			Arrays.asList(AxiomType.DISJOINT_DATA_PROPERTIES,
					AxiomType.FUNCTIONAL_DATA_PROPERTY,
					AxiomType.SUB_DATA_PROPERTY,
					AxiomType.DATA_PROPERTY_DOMAIN,
					AxiomType.DATA_PROPERTY_RANGE,
					AxiomType.EQUIVALENT_DATA_PROPERTIES));
	private static final Set<AxiomType<?>> ASSERTION_AXIOMTYPES = new HashSet<AxiomType<?>>(
			Arrays.asList(AxiomType.CLASS_ASSERTION,
					AxiomType.DATA_PROPERTY_ASSERTION,
					AxiomType.OBJECT_PROPERTY_ASSERTION,
					AxiomType.SAME_INDIVIDUAL, AxiomType.DIFFERENT_INDIVIDUALS,
					AxiomType.NEGATIVE_DATA_PROPERTY_ASSERTION,
					AxiomType.NEGATIVE_OBJECT_PROPERTY_ASSERTION));
	private static final String COLON = ":";
	private static final String OPEN_PAR = "(";
	private static final String CLOSE_PAR = ")";
	private static final String EOF = "<EOF>";

	protected static class ManchesterOWLSyntaxVariableTokeizer extends
			ManchesterOWLSyntaxTokenizer {
		protected ManchesterOWLSyntaxVariableTokeizer(String s) {
			super(s);
			this.delims.remove('?');
		}
	}

	private ConstraintSystem constraintSystem;
	private Map<String, Set<AxiomType>> tokenAxiomTypesMap = new HashMap<String, Set<AxiomType>>();
	private VariableShortFormEntityChecker owlEntityChecker;

	public VariableManchesterOWLSyntaxParser(String s, ConstraintSystem cs) {
		super(cs.getDataFactory(), s);
		this.constraintSystem = cs;
		setOWLEntityChecker(OPPLParser.getOPPLFactory().getOWLEntityChecker());
		// super.setOWLEntityChecker(this.owlEntityChecker);
		setupTokenAximoTypeMap();
	}

	@Override
	protected ManchesterOWLSyntaxTokenizer getTokenizer(String s) {
		return new ManchesterOWLSyntaxVariableTokeizer(s);
	}

	private static final String key(ManchesterOWLSyntax o) {
		return o.toString().toLowerCase();
	}

	private static final Set<AxiomType> singleton(AxiomType t) {
		return Collections.singleton(t);
	}

	private void setupTokenAximoTypeMap() {
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.SUBCLASS_OF),
				singleton(AxiomType.SUBCLASS));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.EQUIVALENT_TO),
				new HashSet<AxiomType>(EQUIVALENTTO_AXIOMTYPES));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.DISJOINT_WITH),
				new HashSet<AxiomType>(DISJOINTWITH_AXIOMTYPES));
		// For n-ary disjoint classes axioms
		// FIXME missing toLowerCase
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.DISJOINT_CLASSES),
				singleton(AxiomType.DISJOINT_CLASSES));
		// With or without (above) colon
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.DISJOINT_CLASSES)
				+ ":", singleton(AxiomType.DISJOINT_CLASSES));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.ANTI_SYMMETRIC),
				singleton(AxiomType.ANTI_SYMMETRIC_OBJECT_PROPERTY));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.FUNCTIONAL),
				new HashSet<AxiomType>(FUNCTIONAL_AXIOMTYPES));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.SYMMETRIC),
				singleton(AxiomType.SYMMETRIC_OBJECT_PROPERTY));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.SUB_PROPERTY_OF),
				new HashSet<AxiomType>(SUBPROPERTY_AXIOMTYPES));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.DOMAIN),
				new HashSet<AxiomType>(DOMAIN_AXIOMTYPES));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.RANGE),
				new HashSet<AxiomType>(RANGE_AXIOMTYPES));
		this.tokenAxiomTypesMap.put(
				key(ManchesterOWLSyntax.INVERSE_FUNCTIONAL),
				singleton(AxiomType.INVERSE_FUNCTIONAL_OBJECT_PROPERTY));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.INVERSE_OF),
				singleton(AxiomType.INVERSE_OBJECT_PROPERTIES));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.IRREFLEXIVE),
				singleton(AxiomType.IRREFLEXIVE_OBJECT_PROPERTY));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.REFLEXIVE),
				singleton(AxiomType.REFLEXIVE_OBJECT_PROPERTY));
		this.tokenAxiomTypesMap.put(key(ManchesterOWLSyntax.TRANSITIVE),
				singleton(AxiomType.TRANSITIVE_OBJECT_PROPERTY));
	}

	@Override
	public OWLConstant parseConstant() throws ParserException {
		OWLConstant toReturn = null;
		Token tok = getToken();
		Variable variable = this.constraintSystem.getVariable(tok.getToken());
		if (variable != null
				&& variable.getType().equals(VariableType.CONSTANT)) {
			this.consumeToken();
			toReturn = getDataFactory().getOWLUntypedConstant(
					variable.getName());
		}
		if (toReturn == null) {
			toReturn = super.parseConstant();
		}
		return toReturn;
	}

	public OWLAxiom parseAxiom() throws ParserException {
		OWLAxiom axiom = null;
		String tok;
		Set<AxiomType> axiomTypes = new HashSet<AxiomType>();
		Set<AxiomType> tokenAxiomTypes;
		Set<String> generatedElements = new HashSet<String>();
		List<Token> axiomTokens = getTokens();
		Iterator<Token> tokenIt = axiomTokens.iterator();
		StringBuilder debugStringBuilder = new StringBuilder();
		while (tokenIt.hasNext()) {
			tok = tokenIt.next().getToken();
			debugStringBuilder.append(tok + " ");
			String lowerCase = tok.toLowerCase();
			tokenAxiomTypes = this.tokenAxiomTypesMap.get(lowerCase);
			if (tokenAxiomTypes != null) {
				axiomTypes.addAll(tokenAxiomTypes);
			} else if (lowerCase.startsWith("!")) {
				generatedElements.add(tok);
			}
		}
		reset();
		if (axiomTypes.isEmpty()) {
			axiomTypes.addAll(AXIOM_TYPES);
		}
		Iterator<AxiomType> it = axiomTypes.iterator();
		boolean found = false;
		AxiomType axiomType;
		Comparator<ParserException> peComparator = new Comparator<ParserException>() {
			public int compare(ParserException anException,
					ParserException anotherException) {
				return anException.getColumnNumber()
						- anotherException.getColumnNumber();
			}
		};
		Set<ParserException> exceptions = new HashSet<ParserException>();
		while (!found && it.hasNext()) {
			axiomType = it.next();
			this.owlEntityChecker.generate(generatedElements);
			do {
				try {
					reset();
					axiom = this.parseAxiom(axiomType);
					found = axiom != null;
					if (!found) {
						this.owlEntityChecker.discard();
					}
				} catch (ParserException e) {
					exceptions.add(e);
					this.owlEntityChecker.discard();
				}
			} while (!found && this.owlEntityChecker.hasAlternatives());
		}
		if (!found) {
			ParserException bestTryExcpetion = exceptions.isEmpty() ? new ParserException(
					peekToken(), getTokenPos(), getTokenRow(), getTokenCol())
					: Collections.max(exceptions, peComparator);
			throw bestTryExcpetion;
		}
		return axiom;
	}

	@Override
	public OWLClassAxiom parseClassAxiom() throws ParserException {
		if (compareICColon(peekToken(), ManchesterOWLSyntax.DISJOINT_CLASSES)) {
			return parseDisjointClasses();
		} else {
			return super.parseClassAxiom();
		}
	}

	@Override
	public OWLDisjointClassesAxiom parseDisjointClasses()
			throws ParserException {
		String section = this.consumeToken();
		// FIXME bogus check
		if (!compareICColon(section, ManchesterOWLSyntax.DISJOINT_CLASSES)) {
			this.throwException(DISJOINT_CLASSES,
					ManchesterOWLSyntax.DISJOINT_CLASSES.toString());
		}
		Set<OWLDescription> descriptions = this.parseDescriptionList();
		return getDataFactory().getOWLDisjointClassesAxiom(descriptions);
	}

	/**
	 * @param axiom
	 * @param axiomType
	 * @return
	 * @throws ParserException
	 */
	private OWLAxiom parseAxiom(AxiomType axiomType) throws ParserException {
		OWLAxiom axiom = null;
		if (CLASS_AXIOMTYPES.contains(axiomType)) {
			axiom = parseClassAxiom();
			checkEOF();
		} else if (OBJECTPROPERTY_AXIOMTYPES.contains(axiomType)) {
			axiom = parseObjectPropertyAxiom();
			checkEOF();
		} else if (DATAPROPERTY_AXIOMTYPES.contains(axiomType)) {
			axiom = parseDataPropertyAxiom();
			checkEOF();
		} else if (ASSERTION_AXIOMTYPES.contains(axiomType)) {
			axiom = parseAssertion();
			checkEOF();
		}
		return axiom;
	}

	private void checkEOF() throws ParserException {
		if (!peekToken().equals(EOF)) {
			this.throwException(EOF);
		}
	}

	private boolean compareICColon(String tok, ManchesterOWLSyntax toCompare) {
		return tok.equalsIgnoreCase(toCompare.toString())
				|| tok.equalsIgnoreCase(toCompare.toString() + COLON);
	}

	private boolean compareIC(String tok, ManchesterOWLSyntax toCompare) {
		return tok.equalsIgnoreCase(toCompare.toString());
	}

	private boolean equals(String tok, ManchesterOWLSyntax toCompare) {
		return tok.equals(toCompare.toString());
	}

	@Override
	public OWLObjectPropertyAxiom parseObjectPropertyAxiom()
			throws ParserException {
		String tok = peekToken();
		if (compareICColon(tok, ManchesterOWLSyntax.FUNCTIONAL)) {
			// TODO verify whether only functional properties need the open and
			// close check
			this.consumeToken();
			checkOpen();
			OWLObjectPropertyExpression prop = this
					.parseObjectPropertyExpression();
			checkClose();
			return getDataFactory().getOWLFunctionalObjectPropertyAxiom(prop);
		} else if (compareICColon(tok, ManchesterOWLSyntax.TRANSITIVE)) {
			this.consumeToken();
			consumeColon();
			return getDataFactory().getOWLTransitiveObjectPropertyAxiom(
					this.parseObjectPropertyExpression());
		} else if (compareICColon(tok, ManchesterOWLSyntax.SYMMETRIC)) {
			this.consumeToken();
			consumeColon();
			return getDataFactory().getOWLSymmetricObjectPropertyAxiom(
					this.parseObjectPropertyExpression());
		} else if (compareICColon(tok, ManchesterOWLSyntax.REFLEXIVE)) {
			this.consumeToken();
			consumeColon();
			return getDataFactory().getOWLReflexiveObjectPropertyAxiom(
					this.parseObjectPropertyExpression());
		} else if (compareICColon(tok, ManchesterOWLSyntax.IRREFLEXIVE)) {
			this.consumeToken();
			consumeColon();
			return getDataFactory().getOWLIrreflexiveObjectPropertyAxiom(
					this.parseObjectPropertyExpression());
		}
		// FIXME copy paste mistake here
		else if (compareICColon(tok, ManchesterOWLSyntax.ANTI_SYMMETRIC)) {
			this.consumeToken();
			consumeColon();
			return getDataFactory().getOWLAntiSymmetricObjectPropertyAxiom(
					this.parseObjectPropertyExpression());
		} else if (isObjectPropertyName(tok)) {
			OWLObjectPropertyExpression property = this
					.parseObjectPropertyExpression();
			tok = this.consumeToken();
			if (compareIC(tok, ManchesterOWLSyntax.EQUIVALENT_TO)) {
				Set<OWLObjectPropertyExpression> equivalentProperties = parseObjectPropertyList();
				equivalentProperties.add(property);
				return getDataFactory().getOWLEquivalentObjectPropertiesAxiom(
						equivalentProperties);
			} else if (compareIC(tok, ManchesterOWLSyntax.DISJOINT_WITH)) {
				Set<OWLObjectPropertyExpression> disjointProperties = parseObjectPropertyList();
				disjointProperties.add(property);
				return getDataFactory().getOWLDisjointObjectPropertiesAxiom(
						disjointProperties);
			} else if (compareIC(tok, ManchesterOWLSyntax.SUB_PROPERTY_OF)) {
				OWLObjectPropertyExpression superProperty = this
						.parseObjectPropertyExpression();
				return getDataFactory().getOWLSubObjectPropertyAxiom(property,
						superProperty);
			} else if (compareIC(tok, ManchesterOWLSyntax.DOMAIN)) {
				OWLDescription domain = parseDescription();
				return getDataFactory().getOWLObjectPropertyDomainAxiom(
						property, domain);
			} else if (compareIC(tok, ManchesterOWLSyntax.RANGE)) {
				OWLDescription range = parseDescription();
				return getDataFactory().getOWLObjectPropertyRangeAxiom(
						property, range);
			} else if (compareIC(tok, ManchesterOWLSyntax.INVERSE_FUNCTIONAL)) {
				checkOpen();
				OWLObjectPropertyExpression prop = this
						.parseObjectPropertyExpression();
				checkClose();
				return getDataFactory()
						.getOWLInverseFunctionalObjectPropertyAxiom(prop);
			} else if (compareIC(tok, ManchesterOWLSyntax.INVERSE_OF)) {
				checkOpen();
				OWLObjectPropertyExpression prop = this
						.parseObjectPropertyExpression();
				checkClose();
				return getDataFactory().getOWLInverseObjectPropertiesAxiom(
						property, prop);
			} else {
				this.throwException(ManchesterOWLSyntax.EQUIVALENT_TO
						.toString(), ManchesterOWLSyntax.SUB_PROPERTY_OF
						.toString(), ManchesterOWLSyntax.INVERSE.toString(),
						ManchesterOWLSyntax.INVERSE_FUNCTIONAL.toString());
			}
		}
		return null;
	}

	private void consumeColon() {
		String colon = peekToken();
		if (colon.equals(COLON)) {
			this.consumeToken();
		}
	}

	private void checkClose() throws ParserException {
		String close = this.consumeToken();
		if (!close.equals(CLOSE_PAR)) {
			this.throwException(CLOSE_PAR);
		}
	}

	private void checkOpen() throws ParserException {
		String open = this.consumeToken();
		if (!open.equals(OPEN_PAR)) {
			this.throwException(OPEN_PAR);
		}
	}

	private OWLAxiom parseAssertion() throws ParserException {
		String tok = peekToken();
		if (equals(tok, ManchesterOWLSyntax.NOT)) {
			this.consumeToken();
			// FIXME probably useless instruction, or missing assignment
			peekToken();
			OWLAxiom negatedTargetAssertion = parseAssertion();
			if (negatedTargetAssertion instanceof OWLDataPropertyAssertionAxiom) {
				OWLDataPropertyAssertionAxiom dataPropertyAssertionAxiom = (OWLDataPropertyAssertionAxiom) negatedTargetAssertion;
				return getDataFactory()
						.getOWLNegativeDataPropertyAssertionAxiom(
								dataPropertyAssertionAxiom.getSubject(),
								dataPropertyAssertionAxiom.getProperty(),
								dataPropertyAssertionAxiom.getObject());
			} else if (negatedTargetAssertion instanceof OWLObjectPropertyAssertionAxiom) {
				OWLObjectPropertyAssertionAxiom objectPropertyAssertionAxiom = (OWLObjectPropertyAssertionAxiom) negatedTargetAssertion;
				return getDataFactory()
						.getOWLNegativeObjectPropertyAssertionAxiom(
								objectPropertyAssertionAxiom.getSubject(),
								objectPropertyAssertionAxiom.getProperty(),
								objectPropertyAssertionAxiom.getObject());
			} else {
				this.throwException("object property assertion",
						"data property assertion");
			}
		} else if (isIndividualName(tok)) {
			OWLIndividual individual = this.parseIndividual();
			tok = peekToken();
			if (equals(tok, ManchesterOWLSyntax.SAME_AS)) {
				Set<OWLIndividual> sameIndividuals = parseIndividualList();
				sameIndividuals.add(individual);
				return getDataFactory().getOWLSameIndividualsAxiom(
						sameIndividuals);
			} else if (equals(tok, ManchesterOWLSyntax.DIFFERENT_FROM)) {
				Set<OWLIndividual> differentIndividuals = parseIndividualList();
				differentIndividuals.add(individual);
				return getDataFactory().getOWLDifferentIndividualsAxiom(
						differentIndividuals);
			} else if (isDataPropertyName(tok)) {
				OWLDataPropertyExpression dataProperty = parseDataProperty();
				OWLConstant filler = parseConstant();
				return getDataFactory().getOWLDataPropertyAssertionAxiom(
						individual, dataProperty, filler);
			} else if (isObjectPropertyName(tok)) {
				OWLObjectPropertyExpression objectProperty = this
						.parseObjectPropertyExpression();
				OWLIndividual filler = this.parseIndividual();
				return getDataFactory().getOWLObjectPropertyAssertionAxiom(
						individual, objectProperty, filler);
			}
			// FIXME these strings are from the manchester OWL syntax: check
			// whether they are the onl valid form
			else if (tok.equals("InstanceOf") || tok.equals("types")) {
				this.consumeToken();
				OWLDescription description = parseDescription();
				return getDataFactory().getOWLClassAssertionAxiom(individual,
						description);
			}
		}
		return null;
	}

	@Override
	public OWLDescription parseDescription() throws ParserException {
		// try {
		return parseIntersection();
		// } catch (ParserException e) {
		// Token t = getLastToken();
		// throw new ParserException(t.getToken(), t.getPos(), t.getRow(), t
		// .getCol(), e.isClassNameExpected(), e
		// .isObjectPropertyNameExpected(), e
		// .isDataPropertyNameExpected(),
		// e.isIndividualNameExpected(), e.isDatatypeNameExpected(), e
		// .getExpectedKeywords());
		// // System.out
		// // .println("VariableManchesterOWLSyntaxParser.parseDescription() "
		// // + e.getLineNumber()
		// // + " "
		// // + e.getColumnNumber()
		// // + " current token: "
		// // + getTokenRow()
		// // + " "
		// // + getTokenCol());
		// // throw e;
		// }
	}

	private OWLAxiom parseDataPropertyAxiom() throws ParserException {
		String tok = peekToken();
		if (equals(tok, ManchesterOWLSyntax.FUNCTIONAL)) {
			this.consumeToken();
			checkOpen();
			OWLDataPropertyExpression prop = parseDataProperty();
			checkClose();
			return getDataFactory().getOWLFunctionalDataPropertyAxiom(prop);
		} else if (isDataPropertyName(tok)) {
			OWLDataPropertyExpression dataProperty = getOWLDataProperty(tok);
			tok = this.consumeToken();
			if (compareIC(tok, ManchesterOWLSyntax.SUB_PROPERTY_OF)) {
				OWLDataProperty anotherDataProperty = parseDataProperty();
				return getDataFactory().getOWLSubDataPropertyAxiom(
						dataProperty, anotherDataProperty);
			} else if (compareIC(tok, ManchesterOWLSyntax.DISJOINT_WITH)) {
				OWLDataProperty anotherDataProperty = parseDataProperty();
				Set<OWLDataPropertyExpression> properties = new HashSet<OWLDataPropertyExpression>();
				properties.add(dataProperty);
				properties.add(anotherDataProperty);
				return getDataFactory().getOWLDisjointDataPropertiesAxiom(
						properties);
			} else if (compareIC(tok, ManchesterOWLSyntax.EQUIVALENT_TO)) {
				OWLDataProperty anotherDataProperty = parseDataProperty();
				Set<OWLDataPropertyExpression> properties = new HashSet<OWLDataPropertyExpression>();
				properties.add(dataProperty);
				properties.add(anotherDataProperty);
				return getDataFactory().getOWLEquivalentDataPropertiesAxiom(
						properties);
			} else if (compareIC(tok, ManchesterOWLSyntax.DOMAIN)) {
				return getDataFactory().getOWLDataPropertyDomainAxiom(
						dataProperty, parseDescription());
			} else if (compareIC(tok, ManchesterOWLSyntax.RANGE)) {
				return getDataFactory().getOWLDataPropertyRangeAxiom(
						dataProperty, parseDataRange(true));
			}
			this.throwException(SUB_PROPERTY_OF, EQUIVALENT_TO, DISJOINT_WITH,
					DOMAIN, RANGE);
		}
		return null;
	}

	@Override
	public final void setOWLEntityChecker(OWLEntityChecker owlEntityChecker) {
		this.owlEntityChecker = new VariableShortFormEntityChecker(
				owlEntityChecker, this.constraintSystem);
		super.setOWLEntityChecker(this.owlEntityChecker);
	}
}
