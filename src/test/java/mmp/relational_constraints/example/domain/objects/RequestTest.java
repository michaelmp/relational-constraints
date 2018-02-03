package mmp.relational_constraints.example.domain.objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import mmp.relational_constraints.example.domain.actions.DocumentDelivery;
import mmp.relational_constraints.example.domain.actions.InterlibraryLoan;
import mmp.relational_constraints.example.domain.relations.CanLoan;

public class RequestTest {

	Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testCanLoanForInterlibraryLoan_5_loans() {
		Patron patron = new Patron();
		patron.setLoans(5);
		Request request = new Request();
		request.setPatron(patron);
		Set<ConstraintViolation<Request>> violations = validator.validate(request, InterlibraryLoan.class);
		assertFalse(
				violations.stream().anyMatch(v -> v.getConstraintDescriptor().getPayload().contains(CanLoan.class)));
	}

	@Test
	public void testCanLoanForInterlibraryLoan_6_loans() {
		Patron patron = new Patron();
		patron.setLoans(6);
		Request request = new Request();
		request.setPatron(patron);
		Set<ConstraintViolation<Request>> violations = validator.validate(request, InterlibraryLoan.class);
		assertTrue(violations.stream().anyMatch(v -> v.getConstraintDescriptor().getPayload().contains(CanLoan.class)));
	}

	@Test
	public void testCanLoanForDocumentDelivery_6_loans() {
		Patron patron = new Patron();
		patron.setLoans(6);
		Request request = new Request();
		request.setPatron(patron);
		Set<ConstraintViolation<Request>> violations = validator.validate(request, DocumentDelivery.class);
		assertFalse(
				violations.stream().anyMatch(v -> v.getConstraintDescriptor().getPayload().contains(CanLoan.class)));
	}

}
