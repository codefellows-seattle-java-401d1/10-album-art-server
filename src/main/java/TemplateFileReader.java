
import java.util.Map;

public class TemplateFileReader extends HTTPStaticFileReader {
    private Map<String, String> locals;

    public TemplateFileReader(String template, Map<String, String> locals) {
        super(template);
        this.locals = locals;
    }

    public String replaceSymbol(String symbol) {
        if (this.locals.containsKey(symbol)) {
            return this.locals.get(symbol);
        }
        return super.replaceSymbol(symbol);
    }
}
