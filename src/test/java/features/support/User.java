package features.support;

import features.activities.UserManagement;

public class User {
    private final WebApplication app;
    private final UserManagement users;

    public User(WebApplication app) {
        this.app = app;
        this.users = new UserManagement(app);
    }

    public User loginAs(String email, String password) {
        app.toHomePage();
        app.loginAs(email, password);
        return this;
    }

    public UserManagement accounts() {
        return users;
    }
}
