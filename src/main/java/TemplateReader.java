import java.util.Map;

class TemplateFileReader extends HTTPStaticFileReader {
    private Map<String, String> locals;

    public TemplateFileReader(String template, Map<String, String> lcoals) {
        super(template);
        this.locals = lcoals;
    }

    public String replaceSymbol(String symbol) {
        if (this.locals.containsKey(symbol)) {
            return this.locals.get(symbol);
        }
        return super.replaceSymbol(symbol);
    }
}
