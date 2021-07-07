import java.util.Map;

public class FileReader extends HTTPStaticFileReader {
    private Map<String, String> albums;

    public FileReader(String template, Map<String, String> albums) {
        super(template);
        this.albums = albums;
    }

    public String replaceSymbol (String symbol) {
        if (this.albums.containsKey(symbol)) {
            return this.albums.get(symbol);
        }
        return super.replaceSymbol(symbol);
    }
}


