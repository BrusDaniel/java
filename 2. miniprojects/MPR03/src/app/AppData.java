package app;

import account.MayorAccountData;
import account.UserAccount;

import java.io.Serializable;
import java.util.List;

public class AppData implements Serializable {

    private final List<UserAccount> accounts;
    private final MayorAccountData mayorData;

    public AppData(List<UserAccount> accounts, MayorAccountData mayorData) {
        if (accounts == null) throw new IllegalArgumentException("Accounts are required!");
        if (mayorData == null) throw new IllegalArgumentException("Mayor data is required!");

        this.accounts = accounts;
        this.mayorData = mayorData;
    }

    public List<UserAccount> getAccounts() {
        return accounts;
    }

    public MayorAccountData getMayorData() {
        return mayorData;
    }
}