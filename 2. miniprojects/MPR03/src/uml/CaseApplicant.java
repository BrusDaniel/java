package uml;

import java.time.LocalDate;

public class CaseApplicant extends ObjectPlusPlus {
    private final int id;
    private final Case aCase;
    private final Applicant applicant;
    private final LocalDate dateOfSubmission;
    private static int nextId = 1;

    public CaseApplicant(Case aCase, Applicant applicant, LocalDate dateOfSubmission) {
        super();
        if (aCase == null) throw new IllegalArgumentException("Case is required!");
        if (applicant == null) throw new IllegalArgumentException("Applicant is required!");
        if (dateOfSubmission == null) throw new IllegalArgumentException("Date is required!");

        this.id = nextId++;
        this.aCase = aCase;
        this.applicant = applicant;
        this.dateOfSubmission = dateOfSubmission;

        aCase.addCaseApplicant(this);
        applicant.addCaseApplicant(this);
    }

    public int getId() {
        return id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public Case getCase() {
        return aCase;
    }

    public LocalDate getDateOfSubmission() {
        return dateOfSubmission;
    }

    @Override
    public String toString() {
        return "[CASE APPLIED] " + dateOfSubmission;
    }
}
