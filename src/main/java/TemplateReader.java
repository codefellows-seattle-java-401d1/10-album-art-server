import java.util.Map;

public class TemplateFileReader extends HTTPStaticFileReader {
    private Map<String, String> foundArt;

    public TemplateFileReader(HTTPRequest template, Map<String, String> foundArt) {
        super(template);
        this.foundArt = foundArt;
    }

    public String replaceSymbol(String symbol) {
        if (this.foundArt.containsKey(symbol)) {
            return this.foundArt.get(symbol);
        }
        return super.replaceSymbol(symbol);
    }
}
