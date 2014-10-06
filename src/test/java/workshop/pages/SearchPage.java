package workshop.pages;

import com.objogate.wl.web.AsyncWebDriver;
import org.openqa.selenium.By;

public class SearchPage {

    private final AsyncWebDriver browser;

    public SearchPage(AsyncWebDriver browser) {
        this.browser = browser;
    }

    public UsersPage searchFor(String query) {
        browser.element(By.id("query")).type(query);
        browser.element(By.tagName("button")).click();
        return new UsersPage(browser);
    }
}
