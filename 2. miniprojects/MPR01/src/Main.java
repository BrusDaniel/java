import java.io.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        Case financial = new Case("250 zł fee returning", LocalDate.of(2026, 04, 11));
        Case other = new Case("Residence certificate", LocalDate.of(2026, 03, 11), "gib me residence pls");
        other.addAttachment(new Attachment(1, "Very important information"));
        System.out.println(financial);
        System.out.println(other + "\nDays passed: " + other.getDaysCaseExists());

        System.out.println("\nnot saved extent");

        Case.showExtent();

        final String fileName = "cases.bin";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            Case.writeExtent(out);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Case.readExtent(in);
        }

        System.out.println("\nsaved extent");

        Case.showExtent();
    }
}