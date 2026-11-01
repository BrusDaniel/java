package uml;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;


public class Case extends ObjectPlusPlus {
    private final int id;
    private final String title;
    private final LocalDate receivedDate;
    @Nullable
    private String description;
    private static String footer =
            "—\nUrząd Gminy\nul. Centralna 1,\n00-000, Miasto";
    private final List<Attachment> attachments = new ArrayList<>();
    private final List<CaseApplicant> caseApplicants = new ArrayList<>();
    private final List<Assignment> assignments = new ArrayList<>();
    private static int nextId = 1;

    public Case(String title, LocalDate receivedDate) {
        super();
        if (title == null) throw new IllegalArgumentException("Title is required!");
        if (receivedDate == null) throw new IllegalArgumentException("Date is required!");

        this.id = nextId++;
        this.title = title;
        this.receivedDate = receivedDate;
    }

    public Case(String title, LocalDate receivedDate, @Nullable String description) {
        this(title, receivedDate);
        this.description = description;
    }

    public static String getFooter() {
        return footer;
    }
    public String getTitle() {
        return title;
    }
    public LocalDate getReceivedDate() {
        return receivedDate;
    }
    public @Nullable String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }
    public List<CaseApplicant> getCaseApplicants() {return List.copyOf(caseApplicants);}
    public List<Attachment> getAttachments() {return List.copyOf(attachments);}
    public List<Assignment> getAssignments() {return List.copyOf(assignments);}

    public long getDaysCaseExists() {
        return LocalDate.now().toEpochDay() - receivedDate.toEpochDay();
    }


    public Attachment createAttachment(String name, String content) {
        Attachment attachment = new Attachment(name, content);
        attachments.add(attachment);
        return attachment;
    }

    void addCaseApplicant(CaseApplicant caseApplicant) {
        if (caseApplicant == null) throw new IllegalArgumentException("Association is required!");
        if (!caseApplicants.contains(caseApplicant)) caseApplicants.add(caseApplicant);
    }

    void addAssignment(Assignment assignment) {
        if (assignment == null) throw new IllegalArgumentException("Assignment is required!");
        if (!assignments.contains(assignment)) {
            assignments.add(assignment);
        }
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

            if (name == null) throw new IllegalArgumentException("Name is required!");
            if (content == null) throw new IllegalArgumentException("Content is required!");
            if (content.length() > 1000) throw new IllegalArgumentException("Content cannot be longer than 1000 characters!");

            this.id = nextId++;
            this.name = name;
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
            return "[ATTACHMENT] id=" + id + ", name=" + name +
                    ", content=" + content + " (case=" + Case.this.getTitle() + ")";        }

    }
}
