import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Case implements Serializable {
    private static List<Case> extent = new ArrayList<>();
    private int id;
    private static int nextId = 1;
    private String title;
    private LocalDate receivedDate;
    private String description;
    private List<Attachment> attachments = new ArrayList<>();
    private static String footer =
            "—\nUrząd Gminy\nul. Centralna 1,\n00-000, Miasto";

    public Case (String title, LocalDate receivedDate) {
        this.id = nextId++;
        if (title == null) throw new IllegalArgumentException("Provide case title!");
        this.title = title;
        if (receivedDate == null) throw new IllegalArgumentException("Provide the date of the case receiving!");
        this.receivedDate = receivedDate;
        extent.add(this);
    }

    public Case(String title, LocalDate receivedDate, String description) {
        this(title, receivedDate);
        this.description = description;
    }

    public long getDaysCaseExists() {
        return LocalDate.now().toEpochDay() - receivedDate.toEpochDay();
    }
    public static void showExtent() {
        for (Case c : extent) System.out.println(c);
    }

    public void addAttachment(Attachment a) {
        if (a == null) throw new IllegalArgumentException("Attachment cannot be empty!");
        attachments.add(a);
    }

    public static void writeExtent(ObjectOutputStream out) throws IOException {
        out.writeObject(extent);
        out.writeInt(nextId);
    }

    public static void readExtent(ObjectInputStream in) throws IOException, ClassNotFoundException {
        extent = (List<Case>) in.readObject();
        nextId = in.readInt();
    }

    public String toString () {
        return "[CASE] id=" + id + ", title=" + title + ", receivedDate=" + receivedDate + "\n" +
                "description=" + (description != null ? "'" + description + "'" : "(empty)") + "\n" +
                "attachments=" + attachments + "\n" +
                footer + "\n";
    }

}
