package workshop.activities;

import workshop.Account;
import workshop.WebApplication;

public class UserManagement {
    private final WebApplication app;

    public UserManagement(WebApplication app) {
        this.app = app;
    }

    public void noUserAccountMatches(String query) {
        app.toSearchPage().searchFor(query).showsNoResult();
    }

    public void accountsExistMatching(String term, Account... accounts) {
        for (Account account : accounts) {
            app.toSearchPage().searchFor(term).displaysAccount(account.id, account.firstName, account.lastName);
        }
    }
}
