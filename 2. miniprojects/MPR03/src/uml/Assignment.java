package uml;

import java.time.LocalDate;

public class Assignment extends ObjectPlusPlus {

    private final Case aCase;
    private final Employee employee;
    private final Manager manager;
    private final LocalDate date;
    private AssignmentStatus caseStatus;

    public Assignment(Case aCase, Employee employee, Manager manager, LocalDate date) {
        if (aCase == null) throw new IllegalArgumentException("Case is required!");
        if (employee == null) throw new IllegalArgumentException("Employee is required!");
        if (manager == null) throw new IllegalArgumentException("Manager is required!");
        if (date == null) throw new IllegalArgumentException("Date is required!");

        this.aCase = aCase;
        this.employee = employee;
        this.manager = manager;
        this.date = date;
        this.caseStatus = AssignmentStatus.ASSIGNED;

        aCase.addAssignment(this);
        employee.addAssignment(this);
        manager.addManagedAssignment(this);
    }

    public Case getCase() {
        return aCase;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Manager getManager() {
        return manager;
    }

    public LocalDate getDate() {
        return date;
    }

    public AssignmentStatus getCaseStatus() {
        return caseStatus;
    }

    public void changeStatus(AssignmentStatus caseStatus) {
        if (caseStatus == null) throw new IllegalArgumentException("Assignment status is required!");
        this.caseStatus = caseStatus;
    }


    @Override
    public String toString() {
        return "[ASSIGNMENT] case=" + aCase.getTitle() +
                ", employee=" + employee.getFirstName() + " " + employee.getSurname() +
                ", manager=" + manager.getFirstName() + " " + manager.getSurname() +
                ", date=" + date +
                ", caseStatus=" + caseStatus;
    }
}
