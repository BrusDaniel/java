import java.io.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        Employee e1 = new Employee("Jozef", "Pilsudzki");
        Employee e2 = new Employee("Roman", "Dmowski");

        Applicant a1 = new Applicant(548334653, "Jan", "Kowalski", LocalDate.of(1989, 7, 12));
        Applicant a2 = new Applicant(920505222, "Maria", "Katarzyna", "Nowak", LocalDate.of(2002, 5, 7));

        Case c1 = new Case("Residence certificate", LocalDate.of(2026, 4, 1));
        Case c2 = new Case("Waste fee complaint", LocalDate.of(2026, 1, 13), "Incorrect fee deposed");

        Document d1 = new Document("Birth certificate", "KK1122334", LocalDate.of(1989, 7, 20), "PL");

        System.out.println();
        System.out.println("REGULAR ASSOCIATION");
        e1.addCase(c1);
        e1.addCase(c2);
        e2.addCase(c2);

        System.out.println("   e1's cases: " + e1.getCases());
        System.out.println("   c1's employees: " + c1.getEmployees());
        System.out.println("   c2's employees: " + c2.getEmployees());



        System.out.println("\nASSOCIATION WITH ATTRIBUTE");
        CaseApplicant ca1 = new CaseApplicant(c1, a1, LocalDate.of(2026, 1, 17));

        System.out.println("   case c1 submission: " + ca1);


        System.out.println("\nCOMPOSITION");
        c1.createAttachment("ID scan", "Scan");
        c2.createAttachment("Payment confirmation", "200zł paid through Bank PEKAO cc");

        //Case.Attachment c3 = new Case.Attachment("Payment confirmation", "200zł paid through Bank PEKAO cc");

        System.out.println("   attachments from c1: " + c1.getAttachments());


        System.out.println("\nQUALIFIED ASSOCIATION");

        d1.addApplicantQualif(a1);
        d1.addApplicantQualif(a2);

        int id = a2.getId();

        System.out.println("   is it Ms. Nowak? --> " + d1.findApplicantQualif(id));


    }
}