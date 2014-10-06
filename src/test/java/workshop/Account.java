package workshop;

public class Account {

    public String id;
    public String firstName;
    public String lastName;

    public static Account account(String id, String firstName, String lastName) {
        return new Account(id, firstName, lastName);
    }

    public Account(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
