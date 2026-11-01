import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Applicant implements Serializable {
    private final int id;
    private final int pESEL;
    private final String firstName;
    private final String middleName;
    private final String surname;
    private final LocalDate birthDate;
    private final List<Document> documents = new ArrayList<>();
    private final List<CaseApplicant> caseApplicants = new ArrayList<>();
    private static List<Applicant> extent = new ArrayList<>();
    private static int nextId = 1;


    public Applicant(int pESEL, String firstName, String surname, LocalDate birthDate) {
        this(pESEL, firstName, null, surname, birthDate);
    }

    public Applicant(int pESEL, String firstName, String middleName, String surname, LocalDate birthDate) {
        this.id = nextId++;
        if (pESEL <= 0) throw new IllegalArgumentException("Provide PESEL!");
        this.pESEL = pESEL;
        if (firstName == null) throw new IllegalArgumentException("Provide first name!");
        this.firstName = firstName;
        if (surname == null) throw new IllegalArgumentException("Provide surname!");
        this.surname = surname;
        this.middleName = middleName;
        if (birthDate == null) throw new IllegalArgumentException("Provide birth date!");
        this.birthDate = birthDate;
        extent.add(this);
    }

    public int getId() {
        return id;
    }

    public int getpESEL() {
        return pESEL;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void addCaseApplicant(CaseApplicant caseApplicant) {
        if (caseApplicant == null) throw new IllegalArgumentException("Association cannot be empty!");
        if (!caseApplicants.contains(caseApplicant)) caseApplicants.add(caseApplicant);
    }

    public List<CaseApplicant> getCaseApplicants() {
        return caseApplicants;
    }

    public void addDocument(Document newDocument) {
        if(!documents.contains(newDocument)) {
            documents.add(newDocument);
            newDocument.addApplicant(this);
        }
    }

    public static void writeExtent(ObjectOutputStream out) throws IOException {
        out.writeObject(extent);
        out.writeInt(nextId);
    }

    public static void readExtent(ObjectInputStream in) throws IOException, ClassNotFoundException {
        extent = (List<Applicant>) in.readObject();
        nextId = in.readInt();
    }

    public static void showExtent() {
        for (Applicant applicant : extent) System.out.println(applicant);
    }

    @Override
    public String toString() {
        return "[APPLICANT] PESEL=" + pESEL + ", name='" + firstName
                + (middleName != null ? " " + middleName : "")
                + " " + surname + "', birth date=" + birthDate;
    }
}
