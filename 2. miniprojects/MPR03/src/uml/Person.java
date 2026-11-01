package uml;

import org.jetbrains.annotations.Nullable;

public abstract class Person extends ObjectPlusPlus {
    //had an interaction with the Hall
    private final int id;
    private final String firstName;
    @Nullable
    private final String middleName;
    private final String surname;
    private static int nextId = 1;

    protected Person(String firstName, @Nullable String middleName, String surname) {

        super();

        if (firstName == null) throw new IllegalArgumentException("First name is required!");
        if (surname == null) throw new IllegalArgumentException("Surname is required!");

        this.id = nextId++;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
    }

    protected Person(String firstName, String surname) {
        this(firstName, null, surname);
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public @Nullable String getMiddleName() {
        return middleName;
    }
    public String getSurname() {
        return surname;
    }

    public abstract String businessCard();
}
