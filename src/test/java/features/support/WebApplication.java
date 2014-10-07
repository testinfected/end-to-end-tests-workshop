package features.support;

import com.objogate.wl.UnsynchronizedProber;
import com.objogate.wl.web.AsyncWebDriver;
import com.vtence.molecule.WebServer;
import features.pages.LoginPage;
import features.pages.SearchPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import workshop.Example;

public class WebApplication {
    public static final int PORT = 9999;

    private final AsyncWebDriver browser;
    private final WebServer server;
    private final Example app;

    public WebApplication(Example app) {
        this.app = app;
        this.browser = new AsyncWebDriver(new UnsynchronizedProber(), new FirefoxDriver());
        this.server = WebServer.create(PORT);
    }

    public void start() throws Exception {
        startServer();
    }

    public void stop() throws Exception {
        stopServer();
        stopBrowser();
    }

    private void startServer() throws Exception {
        app.run(server);
    }

    private void stopBrowser() {
        browser.quit();
    }

    private void stopServer() throws Exception {
        server.stop();
    }

    public WebApplication toHomePage() {
        browser.navigate().to(url("/login"));
        return this;
    }

    public SearchPage toSearchPage() {
        browser.navigate().to(url("/search"));
        return new SearchPage(browser);
    }

    public SearchPage loginAs(String email, String password) {
        LoginPage login = new LoginPage(browser);
        return login.loginAs(email, password);
    }

    private String url(String path) {
        return "http://localhost:" + PORT + path;
    }
}