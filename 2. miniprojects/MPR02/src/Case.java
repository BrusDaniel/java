import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Case implements Serializable {
    private final int id;
    private final String title;
    private final LocalDate receivedDate;
    private String description;
    private static String footer =
            "—\nUrząd Gminy\nul. Centralna 1,\n00-000, Miasto";
    private static int nextId = 1;
    private static List<Case> extent = new ArrayList<>();
    private final List<Attachment> attachments = new ArrayList<>();
    private final List<CaseApplicant> caseApplicants = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();

    public static String getFooter() {
        return footer;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Case(String title, LocalDate receivedDate) {
        this.id = nextId++;
        if (title == null) throw new IllegalArgumentException("Provide case title!");
        this.title = title;
        if (receivedDate == null) throw new IllegalArgumentException("Provide the date of the case receiving!");
        this.receivedDate = receivedDate;
        this.description = null;
        extent.add(this);
    }

    public Case(String title, LocalDate receivedDate, String description) {
        this(title, receivedDate);
        this.description = description;
    }

    public long getDaysCaseExists() {
        return LocalDate.now().toEpochDay() - receivedDate.toEpochDay();
    }

    public Attachment createAttachment(String name, String content) {
        Attachment attachment = new Attachment(name, content);
        attachments.add(attachment);
        return attachment;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public static void deleteCase(Case aCase) {
        if (aCase == null) return;
        aCase.attachments.clear();
        extent.remove(aCase);
    }

    public void addCaseApplicant(CaseApplicant caseApplicant) {
        if (caseApplicant == null) throw new IllegalArgumentException("Association cannot be empty!");
        if (!caseApplicants.contains(caseApplicant)) caseApplicants.add(caseApplicant);
    }

    public List<CaseApplicant> getCaseApplicants() {
        return caseApplicants;
    }

    public void addEmployee(Employee newEmployee) {
        if(!employees.contains(newEmployee)) {
            employees.add(newEmployee);
            newEmployee.addCase(this);
        }
    }

    public static void writeExtent(ObjectOutputStream out) throws IOException {
        out.writeObject(extent);
        out.writeInt(nextId);
    }

    public static void readExtent(ObjectInputStream in) throws IOException, ClassNotFoundException {
        extent = (List<Case>) in.readObject();
        nextId = in.readInt();
    }

    public static void showExtent() {
        for (Case aCase : extent) System.out.println(aCase);
    }

    @Override
    public String toString() {
        String info = "[CASE] id=" + id + ", title=" + title + ", received date=" + receivedDate +
                ", description=" + (description != null ? "'" + description + "'" : "(empty)");
        if (!attachments.isEmpty()) info += "[ATTACHMENTS]:\n";
        for (Attachment attachment : attachments) info += ("   " + attachment.getName() + "\n");
        return info;
    }

    public class Attachment implements Serializable {
        private final int id;
        private final String name;
        private final String content;
        private static int nextId = 1;

        private Attachment(String name, String content) {
            this.id = nextId++;
            if (name == null) throw new IllegalArgumentException("Name cannot be empty!");
            this.name = name;
            if (content == null) throw new IllegalArgumentException("Content cannot be empty!");
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getContent() {
            return content;
        }

        public Case getCase() {
            return Case.this;
        }

        @Override
        public String toString() {
            return "[ATTACHMENT] id" + id + ", content: " + content + "from case: " + Case.this.getTitle();
        }

    }
}
