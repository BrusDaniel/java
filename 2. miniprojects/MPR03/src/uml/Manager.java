package uml;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {

    private final String departmentName;
    private final LocalDate appointmentDate;
    private LocalDate dismissalDate = null;
    private final List<Assignment> managedAssignments = new ArrayList<>();
    private final List<String> responsibility = new ArrayList<>();
    private final List<Contract> contracts = new ArrayList<>();
    //non-trivial case
    private boolean suspended = false;
    private int passwordAttempts = 0;
    private static final int MAX_PASSWORD_ATTEMPTS = 3;

    public Manager(Employee oldEmployee, String departmentName, List<String> responsibility, LocalDate appointmentDate) {
        super(oldEmployee.getFirstName(), oldEmployee.getMiddleName(), oldEmployee.getSurname(), oldEmployee.getHireDate(), oldEmployee.getSalary());
        if (departmentName == null) throw new IllegalArgumentException("Department name is required!");
        if (appointmentDate == null) throw new IllegalArgumentException("Appointment date is required!");

        this.departmentName = departmentName;
        this.appointmentDate = appointmentDate;
        this.setPosition(oldEmployee.getPosition());
        if (responsibility != null) this.responsibility.addAll(responsibility);
    }

    public Manager(Employee oldEmployee, String departmentName, LocalDate appointmentDate) {
        this(oldEmployee, departmentName, null, appointmentDate);
    }

    public String getDepartmentName() {
        return departmentName;
    }
    public List<String> getResponsibility() {
        return List.copyOf(responsibility);
    }
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
    public LocalDate getDismissalDate() {
        return dismissalDate;
    }
    public List<Assignment> getManagedAssignments() {
        return List.copyOf(managedAssignments);
    }
    public List<Contract> getContracts() {return List.copyOf(contracts);}
    public void addResponsibility(String responsibility) {
        if (responsibility == null) throw new IllegalArgumentException("Responsibility is required!");
        this.responsibility.add(responsibility);
    }
    public static int getMaxPasswordAttempts() {return MAX_PASSWORD_ATTEMPTS;}
    public int getPasswordAttempts() {return passwordAttempts;}
    public boolean isSuspended() {return suspended;}


    void addManagedAssignment(Assignment assignment) {
        if (assignment == null) throw new IllegalArgumentException("Assignment is required!");
        if (!managedAssignments.contains(assignment)) {
            managedAssignments.add(assignment);
        }
    }

    void addContract(Contract contract) {
        if (contract == null) throw new IllegalArgumentException("Contract is required!");
        contracts.add(contract);
    }

    public void dismiss(LocalDate dismissalDate) {
        if (dismissalDate == null) throw new IllegalArgumentException("Dismissal date is required!");
        this.dismissalDate = dismissalDate;
    }

    @Override
    public String businessCard() {
        return super.businessCard() +
                "\n Manager of " + departmentName + " department";
    }


    public void suspend() {
        suspended = true;
    }

    public void unfreeze() {
        suspended = false;
        resetPasswordAttempts();
    }

    public void resetPasswordAttempts() {
        passwordAttempts = 0;
    }

    public boolean attemptDocumentAccess(String enteredPassword, String correctPassword) {
        if (suspended) throw new IllegalStateException("Manager is suspended!");


        if (enteredPassword == null) throw new IllegalArgumentException("Entered password is required!");
        if (correctPassword == null) throw new IllegalArgumentException("Correct password is required!");

        if (enteredPassword.equals(correctPassword)) {
            resetPasswordAttempts();
            return true;
        }

        passwordAttempts++;
        return false;
    }

    @Override
    public String toString() {
        return "[MANAGER] " + super.toString() +
                ", departmentName='" + departmentName + '\'' +
                ", responsibility=" + responsibility +
                ", appointmentDate=" + appointmentDate +
                ", dismissalDate=" + dismissalDate +
                ", suspended=" + suspended +
                ", passwordAttempts=" + passwordAttempts;
    }
}