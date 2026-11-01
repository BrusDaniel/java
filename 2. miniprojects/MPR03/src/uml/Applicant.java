package uml;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Applicant extends Person {
    private final String pESEL;
    private final LocalDate birthDate;
    private final List<CaseApplicant> caseApplicants = new ArrayList<>();

    public Applicant(String firstName, String middleName, String surname, String pESEL, LocalDate birthDate) {
        super(firstName, middleName, surname);
        if (pESEL == null) throw new IllegalArgumentException("PESEL is required!");
        if (birthDate == null) throw new IllegalArgumentException("Birth date is required!");
        this.pESEL = pESEL;
        this.birthDate = birthDate;
    }

    public Applicant(String firstName, String surname, String pESEL, LocalDate birthDate) {
        this(firstName, null, surname, pESEL, birthDate);
    }

    public String getpESEL() {
        return pESEL;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public List<CaseApplicant> getCaseApplicants() {
        return List.copyOf(caseApplicants);
    }


    void addCaseApplicant(CaseApplicant caseApplicant) {
        if (caseApplicant == null) throw new IllegalArgumentException("Case applicant is required!");
        if (!caseApplicants.contains(caseApplicant)) caseApplicants.add(caseApplicant);
    }

    public void addDocumentQualif(Document doc) {
        if (doc == null) throw new IllegalArgumentException("Document is required!");
        addLink("documentQualif", "applicantQualif", doc, doc.getNumberAndSeries());
    }

    public Document findDocumentQualif(String numberAndSeries) throws Exception {
        if (numberAndSeries == null) throw new IllegalArgumentException("Number and series is required!");
        return (Document) getLinkedObject("documentQualif", numberAndSeries);
    }

    public List<Document> getDocuments() {
        List<Document> result = new ArrayList<>();

        try {
            ObjectPlusPlus[] documents = getLinks("documentQualif");

            for (ObjectPlusPlus document : documents) {
                result.add((Document) document);
            }
        } catch (Exception ignored) {
        }

        return List.copyOf(result);
    }

    public Document findDocumentById(int id) throws Exception {
        for (Document document : getDocuments()) {
            if (document.getId() == id) {
                return document;
            }
        }

        throw new Exception("Document not found!");
    }

    @Override
    public String businessCard() {
        return "Not Employee!";
    }

    @Override
    public String toString() {
        return "[APPLICANT] PESEL=" + pESEL + ", name='" + getFirstName() + " "
                + getSurname() + "', birth date=" + birthDate;
    }
}
