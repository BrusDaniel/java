package account;

import java.io.Serializable;

public class UserAccount implements Serializable {

    private final String login;
    private final String password;
    private final AccountType accountType;
    private final Object owner;

    public UserAccount(String login, String password, AccountType accountType, Object owner) {
        if (login == null) throw new IllegalArgumentException("Login is required!");
        if (password == null) throw new IllegalArgumentException("Password is required!");
        if (accountType == null) throw new IllegalArgumentException("Account type is required!");
        if (owner == null) throw new IllegalArgumentException("Owner is required!");

        this.login = login;
        this.password = password;
        this.accountType = accountType;
        this.owner = owner;
    }

    public String getLogin() {
        return login;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Object getOwner() {
        return owner;
    }
}