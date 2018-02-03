package mmp.relational_constraints.example.domain.objects;

import javax.validation.Valid;

public class Request {

	@Valid
	private Book book;
	@Valid
	private Patron patron;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

}
