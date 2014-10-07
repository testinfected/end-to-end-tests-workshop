package features;

import features.support.ApplicationDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static features.support.Account.account;

public class SearchFeature {

    ApplicationDriver application = new ApplicationDriver();

    @Before
    public void
    startApplication() throws Exception {
            application.start();
        }

    @After
    public void
    stopApplication() throws Exception {
            application.stop();
        }

    @Test
    public void
    searchingForAnUnknownAccount() throws Exception {
        application.havingRegisteredAccounts(account("1", "Tony", "Stark"));
        application.searchShowsNoResult("Hulk");
    }

    @Test public void
    searchingAndFindingAnAccount() throws Exception {
        application.havingRegisteredAccounts(account("1", "Tony", "Stark"));
        application.searchDisplaysResults("Tony", account("1", "Tony", "Stark"));
    }

    @Test public void
    searchingForAllAccounts() throws Exception {
        application.havingRegisteredAccounts(account("1", "Tony", "Stark"), account("2", "Bruce", "Banner"));
        application.searchDisplaysResults("", account("1", "Tony", "Stark"), account("2", "Bruce", "Banner"));
    }
}
