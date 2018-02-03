package mmp.relational_constraints.example;

import javax.validation.Validation;
import javax.validation.Validator;

public class RelationChecker {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Validates that {@code object} has no {@code relation} payload for the
	 * single {@code context} validation group.
	 * 
	 * FIXME: {@code context} can be a variable argument.
	 */
	public boolean checkRelation(Object object, Class<? extends Relation> relation, Class<? extends Action> context) {
		return !validator.validate(object, context).stream()
				.anyMatch(violation -> violation.getConstraintDescriptor().getPayload().contains(relation));
	}

}
