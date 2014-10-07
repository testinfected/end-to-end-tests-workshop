package workshop.views;

import com.vtence.molecule.templating.JMustacheRenderer;
import org.junit.Test;
import org.w3c.dom.Element;
import workshop.UserAccount;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.vtence.molecule.support.TemplateRenderer.render;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testinfected.hamcrest.dom.DomMatchers.*;
import static org.testinfected.hamcrest.dom.HasSelector.hasSelector;
import static workshop.support.HtmlDocument.toElement;

public class UsersViewTest {

    JMustacheRenderer mustache = new JMustacheRenderer().fromDir(new File("src/main/webapp/views")).extension("html");

    @Test
    public void displaysAllGivenAccounts() throws IOException {
        Collection<UserAccount> users = new ArrayList<>();
        users.add(new UserAccount("18", "Captain", "America"));
        users.add(new UserAccount("23", "Black", "Widow"));
        Element body = toElement(render("users").with(users).asString(mustache));

        assertThat(body, hasSelector("tr[id^='user-']", hasSize(2)));
    }

    @Test
    public void displaysAccountDetails() throws IOException {
        Collection<UserAccount> users = new ArrayList<>();
        users.add(new UserAccount("23", "Black", "Widow"));
        Element body = toElement(render("users").with(users).asString(mustache));

        assertThat(body, hasUniqueSelector("tr#user-23", anElement(
                hasSelector("td", hasText("Black")),
                hasSelector("td", hasText("Widow")))));
    }
}
