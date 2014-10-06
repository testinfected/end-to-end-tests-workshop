package workshop.pages;

import com.objogate.wl.web.AsyncWebDriver;
import org.openqa.selenium.By;

import static org.hamcrest.Matchers.equalTo;

public class UsersPage {
    private final AsyncWebDriver browser;

    public UsersPage(AsyncWebDriver browser) {
        this.browser = browser;
    }

    public void showsNoResult() {
        browser.element(By.tagName("td")).assertDoesNotExist();
    }

    public void displaysAccount(String id, String firstName, String lastName) {
        browser.element(By.cssSelector("#user-" + id + " .id")).assertText(equalTo(id));
        browser.element(By.cssSelector("#user-" + id + " .first-name")).assertText(equalTo(firstName));
        browser.element(By.cssSelector("#user-" + id + " .last-name")).assertText(equalTo(lastName));
    }
}
