import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {
    private final int id;
    private final String firstName;
    private final String surname;
    private static int nextId = 1;
    private static List<Employee> extent = new ArrayList<>();
    private final List<Case> cases = new ArrayList<>();

    public Employee(String firstName, String surname) {
        this.id = nextId++;
        this.firstName = firstName;
        this.surname = surname;
        extent.add(this);
    }

    public void addCase(Case newCase) {
        if(!cases.contains(newCase)) {
            cases.add(newCase);
            newCase.addEmployee(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<Case> getCases() {
        return cases;
    }

    public static void writeExtent(ObjectOutputStream out) throws IOException {
        out.writeObject(extent);
        out.writeInt(nextId);
    }

    public static void readExtent(ObjectInputStream in) throws IOException, ClassNotFoundException {
        extent = (List<Employee>) in.readObject();
        nextId = in.readInt();
    }

    public static void showExtent() {
        for (Employee employee : extent) System.out.println(employee);
    }

    @Override
    public String toString() {
        return "[EMPLOYEE] " +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'';
    }
}
