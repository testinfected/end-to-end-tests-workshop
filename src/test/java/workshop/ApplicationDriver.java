package workshop;

import java.io.File;

public class ApplicationDriver {

    private final Example example = new Example(new File("src/main/webapp"));
    private final WebApplication app;

    private String email = "admin";
    private String password = "admin";

    private User user;

    public ApplicationDriver() {
        this.app = new WebApplication(example);
        this.user = new User(app);
    }

    public void start() throws Exception {
        app.start();
    }

    public void stop() throws Exception {
        app.stop();
    }

    public void havingRegisteredAccounts(Account... accounts) {
        for (Account account : accounts) {
            example.addUserAccount(new UserAccount(account.id, account.firstName, account.lastName));
        }
    }

    public void searchShowsNoResult(String term) {
        user.loginAs(email, password).accounts().noUserAccountMatches(term);
    }

    public void searchDisplaysResults(String term, Account... accounts) {
        user.loginAs(email, password).accounts().accountsExistMatching(term, accounts);
    }
}
