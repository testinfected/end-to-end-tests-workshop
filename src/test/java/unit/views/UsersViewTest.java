package unit.views;

import org.junit.Test;
import org.w3c.dom.Element;
import workshop.UserAccount;

import java.io.IOException;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testinfected.hamcrest.dom.DomMatchers.*;
import static org.testinfected.hamcrest.dom.HasSelector.hasSelector;
import static unit.support.ViewRenderer.render;

public class UsersViewTest {

    @Test
    public void displaysAllGivenAccounts() throws IOException {
        Collection<UserAccount> users = asList(
                new UserAccount("18", "Captain", "America"),
                new UserAccount("23", "Black", "Widow"));
        Element view = render("users").with(users).asDom();

        assertThat(view, hasSelector("tr[id^='user-']", hasSize(2)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void displaysAccountDetailsInColumns() throws IOException {
        Collection<UserAccount> users = asList(new UserAccount("23", "Black", "Widow"));
        Element view = render("users").with(users).asDom();

        assertThat(view, hasSelector("tr#user-23 td", matches(
                hasText("23"), hasText("Black"), hasText("Widow"))));
    }
}
