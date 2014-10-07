package unit.support;

import com.vtence.molecule.support.TemplateRenderer;
import com.vtence.molecule.templating.JMustacheRenderer;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;

import static unit.support.HtmlDocument.toElement;

public class ViewRenderer {

    private final JMustacheRenderer mustache = new JMustacheRenderer().fromDir(new File("src/main/webapp/views")).extension("html");
    private final TemplateRenderer renderer;

    public static ViewRenderer render(String template) {
        return new ViewRenderer(new TemplateRenderer(template));
    }

    public ViewRenderer(TemplateRenderer renderer) {
        this.renderer = renderer;
    }

    public ViewRenderer with(Object context) {
        renderer.with(context);
        return this;
    }

    public Element asDom() {
        return toElement(asString());
    }

    private String asString() {
        try {
            return renderer.asString(mustache);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
