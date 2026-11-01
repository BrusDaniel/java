package uml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.Nullable;

public class Department extends ObjectPlusPlus {

    private final DepartmentName name;
    private final List<String> rules = new ArrayList<>();

    private static final String ROLE_EMPLOYEE = "employee";
    private static final String ROLE_DEPARTMENT = "department";
    private static final String ROLE_HEAD = "head";
    private static final String ROLE_HEADED_DEPARTMENT = "headedDepartment";

    private static final int MAX_EMPLOYEES = 25;

    public Department(DepartmentName name, List<String> rules) {
        super();

        if (name == null) throw new IllegalArgumentException("Department name is required!");

        this.name = name;

        if (rules != null) {
            this.rules.addAll(rules);
        }
    }

    public Department(DepartmentName name) {
        this(name, null);
    }

    public DepartmentName getName() {
        return name;
    }

    public List<String> getRules() {
        return List.copyOf(rules);
    }

    public void addRule(String rule) {
        if (rule == null) throw new IllegalArgumentException("Rule is required!");
        rules.add(rule);
    }

    public void removeRule(String rule) {
        if (rule == null) throw new IllegalArgumentException("Rule is required!");
        rules.remove(rule);
    }

    public void addEmployee(Employee employee) throws Exception {
        if (employee == null) throw new IllegalArgumentException("Employee is required!");
        if (isLink(ROLE_EMPLOYEE, employee)) return;
        if (getEmployees().size() >= MAX_EMPLOYEES) throw new IllegalStateException("Department cannot have more than " + MAX_EMPLOYEES + " employees!");


        try {
            employee.getLinks(ROLE_DEPARTMENT);
            throw new IllegalStateException("Employee already belongs to another department!");
        } catch (Exception e) {
            if (e instanceof IllegalStateException) {
                throw e;
            }
        }

        addLink(ROLE_EMPLOYEE, ROLE_DEPARTMENT, employee);
    }

    public void removeEmployee(Employee employee) {
        if (employee == null) throw new IllegalArgumentException("Employee is required!");

        if (!isLink(ROLE_EMPLOYEE, employee)) {
            return;
        }

        if (Objects.equals(getHead(), employee)) {
            removeHead();
        }

        removeLink(ROLE_EMPLOYEE, ROLE_DEPARTMENT, employee);
    }

    public List<Employee> getEmployees() {
        List<Employee> result = new ArrayList<>();

        try {
            ObjectPlusPlus[] employees = getLinks(ROLE_EMPLOYEE);

            for (ObjectPlusPlus employee : employees) {
                result.add((Employee) employee);
            }
        } catch (Exception ignored) {
        }

        return List.copyOf(result);
    }

    public void setHead(Manager manager) {
        if (manager == null) throw new IllegalArgumentException("Manager is required!");

        removeHead();
        addLink(ROLE_HEAD, ROLE_HEADED_DEPARTMENT, manager);
    }

    public void removeHead() {
        try {
            ObjectPlusPlus[] heads = getLinks(ROLE_HEAD);

            for (ObjectPlusPlus head : heads) {
                removeLink(ROLE_HEAD, ROLE_HEADED_DEPARTMENT, head);
            }
        } catch (Exception ignored) {
        }
    }

    @Nullable
    public Manager getHead() {
        try {
            ObjectPlusPlus[] heads = getLinks(ROLE_HEAD);
            return (Manager) heads[0];
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        Manager head = getHead();

        return "[DEPARTMENT] name=" + name +
                ", rules=" + rules +
                ", head=" + (head != null
                ? head.getFirstName() + " " + head.getSurname()
                : "(no head)");
    }
}