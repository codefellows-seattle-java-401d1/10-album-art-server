import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class HTTPStaticFileReader {
    private String path;

    public HTTPStaticFileReader(HTTPRequest request) {
        this.path = request.path;
    }

    public String getContents() throws IOException {
        String result = "";

        // Load a source from a file
        // https://stackoverflow.com/questions/15749192/how-do-i-load-a-file-from-resource-folder
        String filepath = "/Users/sooz/codefellows/401Java/Labs/10-album-art-server/src/main/resources/static/" +
                this.path;

        //ClassLoader classLoader = getClass().getClassLoader();
        //String fullFilepath = classLoader.getResource(filepath).getFile();

        File file = new File(filepath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result += processLine(line);
            }
            return result;
        }
    }

    // accepts a line and either returns the plain line, or
    // detects the template syntax {{SYMBOL_STUFF}} and replaces that portion
    // of the line with corresponding content for "SYMBOL_STUFF"
    private String processLine(String line) {
        if (!line.contains("{{")) {
            return line;
        }

        //Help from Amy Cohen and GB (Timothy) Busch code review
        //Using JSON reader from lab 8 HTTP as example
        String[] words = line.split("\\{\\{");
        String first = words[0];
        String rest = words[1];

        words = rest.split("\\}\\}");
        String symbol = words[0];
        String last = words[1];

        String content = replaceSymbol(symbol);

        return first + content + last;
    }

    public String replaceSymbol(String symbol){
        String content = "---";
        if (symbol.equals("IMG_SRC")) {
            content = AlbumScraper.getAlbumArt(symbol);
            return content;
        } else {
            String filepath = "/Users/sooz/codefellows/401Java/Labs/10-album-art-server/src/main/resources/static/not" +
                    "-found.html";
            return filepath;
        }
    }
}