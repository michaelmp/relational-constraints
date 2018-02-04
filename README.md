# Bean Validation 2.0 Patterns

Demonstrates patterns using _groups_ and _payload_ constraint attributes in Java Bean Validation 2.0 (JSR-380), released in August 2017 for Java 8.

See: [Bean Validation specification](http://beanvalidation.org/2.0/spec/)

## Vanilla Pattern

[RequestTest.java](src/test/java/mmp/relational_constraints/example/domain/objects/RequestTest.java) demonstrates how to access the payload information from `javax.validation.ConstraintViolation` validation results.

```java
Set<ConstraintViolation<Request>> violations = validator.validate(request, InterlibraryLoan.class);
assertFalse(violations.stream().anyMatch(v -> v.getConstraintDescriptor().getPayload().contains(CanLoan.class)));
```

## Relational Pattern

I added a helper class, [RelationChecker.java](src/test/java/mmp/relational_constraints/example/RelationChecker.java), that wraps the payload inspection.
[RelationCheckerTest.java](src/test/java/mmp/relational_constraints/example/RelationCheckerTest.java) demonstrates how to check for the flagging of a payload, in the context of a group, for the validated object.

```java
assertTrue(checker.checkRelation(request, CanLoan.class, InterlibraryLoan.class));
```

Here, the `Request request` object contains a cascading `@Valid Patron` object.

You can then pattern your code around the question:
> "Is a constrained **relation** (Can Loan) between two objects (Request and Patron) present in the context of some **action** or event (Interlibrary Loan)?"

## Property Pattern

This could also be patterned as constrained **properties** about an object.
> "Does an object possess a constrained **property** in the context of some **action** or event."

**Thoughts?**
