import java.io.Serializable;

public class Attachment implements Serializable {
    private final int id;
    private final String content;
    public Attachment(int id, String content) {
        this.id = id;
        this.content = content;
    }
    @Override
    public String toString() {
        return "[ATTACHMENT]: id" + id + ", content: " + content;
    }
}