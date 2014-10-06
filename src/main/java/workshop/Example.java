package workshop;

import com.vtence.molecule.WebServer;
import com.vtence.molecule.http.MimeTypes;
import com.vtence.molecule.middlewares.FileServer;
import com.vtence.molecule.middlewares.StaticAssets;
import com.vtence.molecule.routing.DynamicRoutes;
import com.vtence.molecule.templating.JMustacheRenderer;
import com.vtence.molecule.templating.Templates;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Example {
    private static final Object NO_CONTEXT = null;
    private static final String HTML_UTF8 = MimeTypes.HTML + "; charset=UTF-8";

    private final File webroot;
    private final Collection<UserAccount> users = new ArrayList<>();

    public Example(File webroot) {
        this.webroot = webroot;
    }

    public void addUserAccount(UserAccount user) {
        users.add(user);
    }

    public void run(WebServer server) throws IOException {
        final Templates views = new Templates(
                new JMustacheRenderer().fromDir(new File(webroot, "views")).extension("html"));

        server.add(new StaticAssets(new FileServer(new File(webroot, "assets")), "/favicon.ico", "/images", "/css", "/fonts"))
              .start(new DynamicRoutes() {{

                  get("/login").to((request, response) -> {
                      response.contentType(MimeTypes.HTML);
                      response.body(views.named("login").render(NO_CONTEXT));
                  });

                  post("login").to((request, response) -> response.redirectTo("/search"));

                  get("/search").to((request, response) -> {
                      response.contentType(HTML_UTF8);
                      response.body(views.named("search").render(NO_CONTEXT));
                  });

                  get("/users").to((request, response) -> {
                      response.contentType(HTML_UTF8);
                      String query = request.parameter("query");
                      Collection<UserAccount> matching = new ArrayList<>();
                      for (UserAccount user : users) {
                          if (user.getFirstName().contains(query) || user.getLastName().contains(query))
                              matching.add(user);
                      }
                      response.body(views.named("users").render(matching));
                  });

                  get("/").to((request, response) -> response.body("Yep, it works!"));
              }});
    }

    private static final int PORT = 0;
    private static final int WEB_ROOT = 1;

    private static int port(String[] args) {
        return Integer.parseInt(args[PORT]);
    }

    private static File webroot(String[] args) {
        return new File(args[WEB_ROOT]);
    }

    public static void main(String[] args) throws IOException {
        WebServer server = WebServer.create("0.0.0.0", port(args));
        Example example = new Example(webroot(args));
        example.run(server);
        System.out.println("Started at " + server.uri());
    }
}

