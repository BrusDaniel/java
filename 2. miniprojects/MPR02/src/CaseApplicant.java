import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CaseApplicant implements Serializable {
    private final int id;
    private final Case aCase;
    private final Applicant applicant;
    private final LocalDate dateOfSubmission;
    private static List<CaseApplicant> extent = new ArrayList<>();
    private static int nextId = 1;

    public CaseApplicant(Case aCase, Applicant applicant, LocalDate dateOfSubmission) {
        this.id = nextId++;
        if (aCase == null) throw new IllegalArgumentException("Case cannot be empty!");
        this.aCase = aCase;
        if (applicant == null) throw new IllegalArgumentException("Applicant cannot be empty!");
        this.applicant = applicant;
        if (dateOfSubmission == null) throw new IllegalArgumentException("Date cannot be empty!");
        this.dateOfSubmission = dateOfSubmission;

        aCase.addCaseApplicant(this);
        applicant.addCaseApplicant(this);
        extent.add(this);
    }

    public int getId() {
        return id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public Case getaCase() {
        return aCase;
    }

    public LocalDate getDateOfSubmission() {
        return dateOfSubmission;
    }

    public static void writeExtent(ObjectOutputStream out) throws IOException {
        out.writeObject(extent);
        out.writeInt(nextId);
    }

    public static void readExtent(ObjectInputStream in) throws IOException, ClassNotFoundException {
        extent = (List<CaseApplicant>) in.readObject();
        nextId = in.readInt();
    }

    public static void showExtent() {
        for (CaseApplicant caseApplicant : extent) System.out.println(caseApplicant);
    }

    @Override
    public String toString() {
        return "[CASE APPLIED] " + dateOfSubmission;
    }
}
