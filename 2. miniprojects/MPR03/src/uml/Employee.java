package uml;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;


public class Employee extends Person {

    private final LocalDate hireDate;
    @Nullable
    private LocalDate fireDate;
    private float salary;
    protected static final String POSITION_EMPLOYEE = "employee";
    protected static final String POSITION_REFERENT = "referent";
    protected static final String POSITION_INSPECTOR = "inspector";
    protected static final String POSITION_SPECIALIST = "specialist";
    private String position = POSITION_EMPLOYEE;
    private final List<Assignment> assignments = new ArrayList<>();


    public Employee(String firstName, @Nullable String middleName, String surname, LocalDate hireDate, float salary) {

        super(firstName, middleName, surname);

        if (hireDate == null) throw new IllegalArgumentException("Hire date is required!");
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative!");

        this.hireDate = hireDate;
        this.salary = salary;
    }
    public Employee(String firstName, String surname, LocalDate hireDate, float salary) {
        this(firstName, null, surname, hireDate, salary);
    }

    public LocalDate getHireDate() {
        return hireDate;
    }
    public float getSalary() {
        return salary;
    }
    public @Nullable LocalDate getFireDate() {
        return fireDate;
    }
    protected void setFireDate(@Nullable LocalDate fireDate) {this.fireDate = fireDate;}
    public void setSalary(float salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative!");
        this.salary = salary;
    }
    public List<Assignment> getAssignments() {
        return List.copyOf(assignments);
    }

    public void fire(LocalDate fireDate, Employee newEmployee) {
        if (fireDate == null) throw new IllegalArgumentException("Fire date is required!");
        if (newEmployee == null) throw new IllegalArgumentException("Replacement employee is required!");

        setFireDate(fireDate);

        List<Assignment> oldAssingments = new ArrayList<>(assignments);

        for (Assignment assignment : oldAssingments) {
            if (assignment.getCaseStatus() == AssignmentStatus.ASSIGNED) {
                assignment.changeStatus(AssignmentStatus.TRANSFERRED);
                new Assignment(assignment.getCase(), newEmployee, assignment.getManager(), fireDate);
            }
        }
    }

    public static float countLabourCosts() throws Exception {
        float sum = 0;

        for (ObjectPlus object : ObjectPlus.getExtent(Employee.class)) {
            Employee employee = (Employee) object;

            if (employee.getFireDate() == null) {
                sum += employee.getSalary();
            }
        }

        return sum;
    }


    void addAssignment(Assignment assignment) {
        if (assignment == null) throw new IllegalArgumentException("Assignment is required!");
        if (!assignments.contains(assignment)) {
            assignments.add(assignment);
        }
    }

    @Override
    public String businessCard() {
        return "Employee of Urzad Gminy" +
                "\n Name: " + super.getFirstName()  +
                "\n Surname: " + super.getSurname() +
                "\n Position: " + position;
    }

    @Override
    public String toString() {
        return "[EMPLOYEE] id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                (getMiddleName() != null ? ", middleName='" + getMiddleName() + '\'' : "") +
                ", surname='" + getSurname() + '\'' +
                ", hireDate=" + hireDate +
                ", fireDate=" + (fireDate != null ? fireDate : "(still employed)");
    }



    //INHERITANCE - POSITION ASPECT: Manager extends Employee
    //INHERITANCE - ROLE ASPECT:

    public void addReferent(float salary) {
        removeCurrentPosition();
        Referent referent = new Referent(salary);
        this.addLink(POSITION_REFERENT, POSITION_EMPLOYEE, referent);
        setSalary(salary);
        setPosition(POSITION_REFERENT);
    }

    public void addInspector(String almaMater, float salary) {
        removeCurrentPosition();
        Inspector inspector = new Inspector(almaMater, salary);
        this.addLink(POSITION_INSPECTOR, POSITION_EMPLOYEE, inspector);
        setSalary(salary);
        setPosition(POSITION_INSPECTOR);
    }

    public void addSpecialist(String area, List<String> certificates, float salary) {
        removeCurrentPosition();
        Specialist specialist = new Specialist(area, certificates, salary);
        this.addLink(POSITION_SPECIALIST, POSITION_EMPLOYEE, specialist);
        setSalary(salary);
        setPosition(POSITION_SPECIALIST);
    }

    private void removeCurrentPosition() {
        String [] positions = {POSITION_REFERENT, POSITION_INSPECTOR, POSITION_SPECIALIST};
        for (String position : positions) {
            try {
                ObjectPlusPlus[] links = this.getLinks(position);

                for (ObjectPlusPlus link : links) {
                    this.removeLink(position, POSITION_EMPLOYEE, link);
                }
            } catch (Exception ignored) {}
        }
        setPosition(POSITION_EMPLOYEE);
    }

    public String getAlmaMater() throws Exception {
        try {
            ObjectPlusPlus[] obj = this.getLinks(POSITION_INSPECTOR);
            return ((Inspector) obj[0]).getAlmaMater();
        } catch (Exception e) {
            throw new Exception("The employee is not an inspector!");
        }
    }

    public String getArea() throws Exception {
        try {
            ObjectPlusPlus[] obj = this.getLinks(POSITION_SPECIALIST);
            return ((Specialist) obj[0]).getArea();
        } catch (Exception e) {
            throw new Exception("The employee is not a specialist!");
        }
    }

    public List<String> getCertificates() throws Exception {
        try {
            ObjectPlusPlus[] obj = this.getLinks(POSITION_SPECIALIST);
            return ((Specialist) obj[0]).getCertificates();
        } catch (Exception e) {
            throw new Exception("The employee is not a specialist!");
        }
    }

    public String getPosition() {
        return position;
    }
    protected void setPosition(String position) {
        this.position = position;
    }


    public class Referent extends ObjectPlusPlus {
        private Referent(float salary) {
            if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative!");
        }
    }


    public class Inspector extends ObjectPlusPlus {
        private final String almaMater;

        private Inspector(String almaMater, float salary) {

            if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative!");

            if (almaMater == null) throw new IllegalArgumentException("Inspector must be a bachelor!");
            this.almaMater = almaMater;

        }

        public String getAlmaMater() {
            return almaMater;
        }
    }


    public class Specialist extends ObjectPlusPlus {
        private final List<String> certificates = new ArrayList<>();
        private final String area;

        public Specialist(String area, List<String> certificates, float salary) {

            if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative!");
            if (area == null) throw new IllegalArgumentException("Area is required!");
            if (certificates == null) throw new IllegalArgumentException(
                    "Specialist must be a bachelor and optionally have certificates!");

            this.area = area;
            this.certificates.addAll(certificates);
        }

        public String getArea() {
            return area;
        }

        public List<String> getCertificates() {
            return List.copyOf(certificates);
        }

        public void addCertificate (String certificate) {
            if (certificate == null) throw new IllegalArgumentException("Certificate is required!");
            this.certificates.add(certificate);
        }
    }
}
