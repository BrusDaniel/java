package app;

import account.AccountType;
import account.MayorAccountData;
import account.UserAccount;
import gui.MyFrame;
import uml.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AppData appData;

        try {
            appData = DataStore.load();
            System.out.println("Data loaded from file.");
        } catch (Exception e) {
            System.out.println("No saved data found. Creating test data.");

            try {
                appData = createTestData();
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        }

        AppData finalAppData = appData;

        SwingUtilities.invokeLater(() -> {
            MyFrame frame = new MyFrame(
                    finalAppData.getAccounts(),
                    finalAppData.getMayorData()
            );

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    try {
                        DataStore.save(finalAppData);
                        System.out.println("Data saved.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            frame.showLoginPanel();
            frame.setVisible(true);
        });
    }

    private static AppData createTestData() throws Exception {
        List<UserAccount> accounts = new ArrayList<>();
        MayorAccountData mayorData = new MayorAccountData();

        Applicant applicant1 = new Applicant(
                "Anna",
                "Nowak",
                "12345678901",
                LocalDate.of(1998, 5, 20)
        );

        Applicant applicant2 = new Applicant(
                "Piotr",
                "Kowalski",
                "98765432109",
                LocalDate.of(1990, 3, 12)
        );

        Applicant applicant3 = new Applicant(
                "Ewa",
                "Zielinska",
                "55544433322",
                LocalDate.of(1985, 9, 3)
        );

        Applicant applicant4 = new Applicant(
                "Tomasz",
                "Wojcik",
                "11122233344",
                LocalDate.of(1979, 11, 18)
        );

        Document doc1 = new Document(
                "ID card",
                "ABC123",
                LocalDate.of(2020, 1, 10),
                "Poland"
        );

        Document doc2 = new Document(
                "Passport",
                "PASS777",
                LocalDate.of(2021, 6, 15),
                "Poland",
                "Valid until 2031"
        );

        Document doc3 = new Document(
                "Driving licence",
                "DRV555",
                LocalDate.of(2022, 4, 1),
                "Poland"
        );

        Document doc4 = new Document(
                "Residence certificate",
                "RES888",
                LocalDate.of(2023, 2, 20),
                "Poland"
        );

        Document doc5 = new Document(
                "Tax certificate",
                "TAX2024",
                LocalDate.of(2024, 1, 5),
                "Poland"
        );

        Document doc6 = new Document(
                "Property document",
                "PROP444",
                LocalDate.of(2019, 7, 7),
                "Poland"
        );

        Document doc7 = new Document(
                "Business register document",
                "BUS901",
                LocalDate.of(2022, 10, 30),
                "Poland"
        );

        applicant1.addDocumentQualif(doc1);
        applicant1.addDocumentQualif(doc2);

        applicant2.addDocumentQualif(doc3);
        applicant2.addDocumentQualif(doc5);

        applicant3.addDocumentQualif(doc4);
        applicant3.addDocumentQualif(doc6);

        applicant4.addDocumentQualif(doc7);

        Case case1 = new Case(
                "Building permit",
                LocalDate.now().minusDays(14),
                "Application for building permit"
        );

        Case case2 = new Case(
                "Tax correction",
                LocalDate.now().minusDays(8),
                "Application for tax correction"
        );

        Case case3 = new Case(
                "Tree cutting permit",
                LocalDate.now().minusDays(4),
                "Application for tree cutting permit"
        );

        Case case4 = new Case(
                "Business activity registration",
                LocalDate.now().minusDays(20),
                "Application for registering business activity"
        );

        Case case5 = new Case(
                "Residence confirmation",
                LocalDate.now().minusDays(2),
                "Application for residence confirmation"
        );

        case1.createAttachment("building_plan.pdf", "Building plan content");
        case1.createAttachment("ownership_statement.txt", "Ownership statement content");
        case2.createAttachment("tax_form.pdf", "Tax correction form content");
        case3.createAttachment("tree_photo.jpg", "Photo evidence content");
        case4.createAttachment("business_form.pdf", "Business registration form content");
        case5.createAttachment("residence_form.pdf", "Residence confirmation form content");

        new CaseApplicant(case1, applicant1, LocalDate.now().minusDays(14));
        new CaseApplicant(case1, applicant2, LocalDate.now().minusDays(13));
        new CaseApplicant(case2, applicant2, LocalDate.now().minusDays(8));
        new CaseApplicant(case3, applicant3, LocalDate.now().minusDays(4));
        new CaseApplicant(case3, applicant1, LocalDate.now().minusDays(3));
        new CaseApplicant(case4, applicant4, LocalDate.now().minusDays(20));
        new CaseApplicant(case5, applicant3, LocalDate.now().minusDays(2));

        Employee employee1 = new Employee(
                "Jan",
                "Kowalski",
                LocalDate.of(2020, 1, 10),
                6000
        );

        Employee employee2 = new Employee(
                "Maria",
                "Wisniewska",
                LocalDate.of(2021, 2, 15),
                6200
        );

        Employee employee3 = new Employee(
                "Kamil",
                "Lewandowski",
                LocalDate.of(2019, 9, 1),
                5800
        );

        Employee employee4 = new Employee(
                "Olga",
                "Kaminska",
                LocalDate.of(2022, 3, 7),
                5700
        );

        employee1.addInspector("PJATK", 6500);
        employee2.addSpecialist("Tax service", List.of("Tax certificate", "Finance course"), 6700);
        employee3.addReferent(5800);
        employee4.addSpecialist("Administration", List.of("Office procedures certificate"), 6000);

        Manager manager1 = new Manager(
                employee1,
                "ADMINISTRACJA",
                List.of("Managing administrative cases", "Document verification"),
                LocalDate.of(2024, 1, 1)
        );

        Manager manager2 = new Manager(
                employee2,
                "FINANSOWY",
                List.of("Managing financial cases", "Tax documentation control"),
                LocalDate.of(2024, 2, 1)
        );

        mayorData.addManager(manager1);
        mayorData.addManager(manager2);

        new Assignment(case1, employee1, manager1, LocalDate.now().minusDays(13));
        new Assignment(case2, employee2, manager1, LocalDate.now().minusDays(7));
        new Assignment(case3, employee3, manager1, LocalDate.now().minusDays(3));
        new Assignment(case4, employee4, manager2, LocalDate.now().minusDays(18));
        new Assignment(case5, employee2, manager2, LocalDate.now().minusDays(1));

        Department department1 = new Department(
                DepartmentName.ADMINISTRATION,
                List.of("Verify documents carefully", "Use official templates")
        );

        Department department2 = new Department(
                DepartmentName.FINANCE,
                List.of("Check tax documents", "Verify payment deadlines")
        );

        department1.addEmployee(employee1);
        department1.addEmployee(employee3);
        department1.setHead(manager1);

        department2.addEmployee(employee2);
        department2.addEmployee(employee4);
        department2.setHead(manager2);

        ExternalAgent agent1 = new ExternalAgent(
                "IT Support Company",
                "1234567890",
                "contact@itsupport.pl",
                "Warszawa, ul. Techniczna 5"
        );

        ExternalAgent agent2 = new ExternalAgent(
                "Document Archive Service",
                "9876543210",
                "office@archive.pl",
                "Krakow, ul. Archiwalna 12"
        );

        new Contract(
                manager1,
                agent1,
                "IT support for municipal office system",
                LocalDate.of(2024, 1, 15),
                LocalDate.of(2024, 12, 31)
        );

        new Contract(
                manager2,
                agent2,
                "Document archive maintenance",
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 9, 30)
        );

        accounts.add(new UserAccount("applicant", "123", AccountType.APPLICANT, applicant1));
        accounts.add(new UserAccount("applicant2", "123", AccountType.APPLICANT, applicant2));
        accounts.add(new UserAccount("applicant3", "123", AccountType.APPLICANT, applicant3));
        accounts.add(new UserAccount("applicant4", "123", AccountType.APPLICANT, applicant4));

        accounts.add(new UserAccount("employee", "123", AccountType.EMPLOYEE, employee1));
        accounts.add(new UserAccount("employee2", "123", AccountType.EMPLOYEE, employee2));
        accounts.add(new UserAccount("employee3", "123", AccountType.EMPLOYEE, employee3));
        accounts.add(new UserAccount("employee4", "123", AccountType.EMPLOYEE, employee4));

        accounts.add(new UserAccount("manager", "123", AccountType.MANAGER, manager1));
        accounts.add(new UserAccount("manager2", "123", AccountType.MANAGER, manager2));

        accounts.add(new UserAccount("mayor", "123", AccountType.MAYOR, mayorData));

        return new AppData(accounts, mayorData);
    }
}