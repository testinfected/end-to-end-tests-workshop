package features.pages;

import com.objogate.wl.web.AsyncWebDriver;
import org.openqa.selenium.By;

public class LoginPage {
    private final AsyncWebDriver browser;

    public LoginPage(AsyncWebDriver browser) {
        this.browser = browser;
    }

    public SearchPage loginAs(String email, String password) {
        browser.element(By.id("email")).type(email);
        browser.element(By.id("password")).type(password);
        browser.element(By.tagName("button")).click();
        return new SearchPage(browser);
    }
}
