import java.util.Map;

public class FileReader extends HTTPStaticFileReader {
    private Map<String, String> Albums;

    public FileReader(String template, Map<String, String> Albums) {
        super(template);
        this.Albums = Albums;
    }

    public String replaceSymbol (String symbol) {
        if (this.Albums.containsKey(symbol)) {
            return this.Albums.get(symbol);
        }
        return super.replaceSymbol(symbol);
    }
}


