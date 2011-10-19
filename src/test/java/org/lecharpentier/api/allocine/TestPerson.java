package org.lecharpentier.api.allocine;

import junit.framework.TestCase;

import org.junit.Test;
import org.lecharpentier.api.allocine.Person.PersonFactory;

public class TestPerson extends TestCase {
	PersonFactory pf;

	@Override
	protected void setUp() throws Exception {
		pf = new PersonFactory();
	}

	@Test
	public void testNormalFormedString() {
		pf.create();
		pf.parseString("John Doe");
		Person p = pf.validate();
		assertEquals("John", p.getFirstName());
		assertEquals("Doe", p.getName());
	}

	@Test
	public void testLongNameString() {
		pf.create();
		pf.parseString("John A. Doe");
		Person p = pf.validate();
		assertEquals("John A.", p.getFirstName());
		assertEquals("Doe", p.getName());
	}

	@Test
	public void testVeryLongNameString() {
		pf.create();
		pf.parseString("Albus Perceval Wulfric Brian Dumbledore");
		Person p = pf.validate();
		assertEquals("Albus Perceval Wulfric Brian", p.getFirstName());
		assertEquals("Dumbledore", p.getName());
	}

	@Test
	public void testComposedFirstName() {
		pf.create();
		pf.parseString("Pierre-Louis Smith");
		Person p = pf.validate();
		assertEquals("Pierre-Louis", p.getFirstName());
		assertEquals("Smith", p.getName());
	}

	@Test
	public void testComposedLastName() {
		pf.create();
		pf.parseString("Pierre-Louis A. Smith-Doe");
		Person p = pf.validate();
		assertEquals("Pierre-Louis A.", p.getFirstName());
		assertEquals("Smith-Doe", p.getName());
	}
}
