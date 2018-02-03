package mmp.relational_constraints.example;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mmp.relational_constraints.example.domain.actions.DocumentDelivery;
import mmp.relational_constraints.example.domain.actions.InterlibraryLoan;
import mmp.relational_constraints.example.domain.objects.Patron;
import mmp.relational_constraints.example.domain.objects.Request;
import mmp.relational_constraints.example.domain.relations.CanLoan;

public class RelationCheckerTest {

	RelationChecker checker;
	
	@Before
	public void setup() {
		checker = new RelationChecker();
	}
	
	@Test
	public void testCanLoanForInterlibraryLoan_5_loans() {
		Patron patron = new Patron();
		patron.setLoans(5);
		Request request = new Request();
		request.setPatron(patron);
		assertTrue(checker.checkRelation(request, CanLoan.class, InterlibraryLoan.class));
	}

	@Test
	public void testCanLoanForInterlibraryLoan_6_loans() {
		Patron patron = new Patron();
		patron.setLoans(6);
		Request request = new Request();
		request.setPatron(patron);
		assertFalse(checker.checkRelation(request, CanLoan.class, InterlibraryLoan.class));
	}

	@Test
	public void testCanLoanForDocumentDelivery_6_loans() {
		Patron patron = new Patron();
		patron.setLoans(6);
		Request request = new Request();
		request.setPatron(patron);
		assertTrue(checker.checkRelation(request, CanLoan.class, DocumentDelivery.class));
	}

}
