import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Document implements Serializable {
    private final int id;
    private final String type;
    private final String numberAndSeries;
    private final LocalDate issueDate;
    private final String country;
    private String additionalInformation;
    private final Map<Integer, Applicant> applicantsQualif = new TreeMap<>();
    private final List<Applicant> applicants = new ArrayList<>();
    private static List<Document> extent = new ArrayList<>();
    private static int nextId = 1;


    public Document(String type, String numberAndSeries, LocalDate issueDate, String country) {
        this.id = nextId++;
        if (type == null) throw new IllegalArgumentException("Type cannot be empty!");
        this.type = type;
        if (numberAndSeries == null) throw new IllegalArgumentException("Number and/or series cannot be empty!");
        this.numberAndSeries = numberAndSeries;
        if (issueDate == null) throw new IllegalArgumentException("Issue date cannot be empty!");
        this.issueDate = issueDate;
        if (country == null) throw new IllegalArgumentException("Country cannot be empty!");
        this.country = country;
        extent.add(this);
    }

    public Document(String type, String numberAndSeries, LocalDate issueDate, String country, String additionalInformation) {
        this(type, numberAndSeries, issueDate, country);
        this.additionalInformation = additionalInformation;
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public int getId() {
        return id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public String getCountry() {
        return country;
    }

    public String getNumberAndSeries() {
        return numberAndSeries;
    }

    public String getType() {
        return type;
    }

    public Map<Integer, Applicant> getAplicantsQualif() {
        return applicantsQualif;
    }

    public void addApplicant(Applicant newApplicant) {
        if(!applicants.contains(newApplicant)) {
            applicants.add(newApplicant);
            newApplicant.addDocument(this);
        }
    }

    public void addApplicantQualif(Applicant newApplicant) {
        if (newApplicant == null) throw new IllegalArgumentException("Applicant cannot be empty");
        applicantsQualif.putIfAbsent(newApplicant.getId(), newApplicant);
    }

    public Applicant findApplicantQualif(int id) throws Exception {
        if (applicantsQualif.get(id) == null) throw new Exception("Unable to find applicant id " + id);
        return applicantsQualif.get(id);
    }

    public static void writeExtent(ObjectOutputStream out) throws IOException {
        out.writeObject(extent);
        out.writeInt(nextId);
    }

    public static void readExtent(ObjectInputStream in) throws IOException, ClassNotFoundException {
        extent = (List<Document>) in.readObject();
        nextId = in.readInt();
    }

    public static void showExtent() {
        for (Document document : extent) System.out.println(document);
    }

    @Override
    public String toString() {
        return "[DOCUMENT] " +
                "type='" + type + '\'' +
                ", numberAndSeries='" + numberAndSeries + '\'' +
                ", issueDate=" + issueDate +
                ", country='" + country + '\'' +
                ", additionalInformation='" + additionalInformation + '\'';
    }
}
