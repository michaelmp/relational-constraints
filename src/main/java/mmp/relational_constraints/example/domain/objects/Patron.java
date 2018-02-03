package mmp.relational_constraints.example.domain.objects;


import javax.validation.constraints.Max;

import mmp.relational_constraints.example.domain.actions.InterlibraryLoan;
import mmp.relational_constraints.example.domain.relations.CanLoan;

public class Patron {

	public static final long MAX_LOANS = 5;

	@Max(value = Patron.MAX_LOANS, message = "Too many books already on loan.", groups = InterlibraryLoan.class, payload = CanLoan.class)
	private Integer loans;

	public Integer getLoans() {
		return loans;
	}

	public void setLoans(Integer loans) {
		this.loans = loans;
	}

}
