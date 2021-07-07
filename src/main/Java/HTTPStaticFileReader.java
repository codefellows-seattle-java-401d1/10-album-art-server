import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class HTTPStaticFileReader {
    private String path;

    public HTTPStaticFileReader(String request) {
        this.path = request;
    }

    public String getContents() throws IOException {
        String result = "";

        String filepath = "/Users/greg/codefellows/401/labs/10-album-art-server/src/main/resources/Static"
                + this.path;

        File file = new File(filepath);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result += processLine(line);
            }
            return result;
        }
    }

    private String processLine(String line) {
        if (!line.contains("{{")) {
            return line;
        }

        String[]cells = line.split("\\{\\{");
        String first = cells[0];
        String theRest = cells[1];

        cells = theRest.split("\\}\\}");
        String symbol = cells[0];
        String last = cells[1];

        String content = replaceSymbol(symbol);

        return first + content + last;
    }

    public String replaceSymbol (String symbol) {
        String content = "";
        if (symbol.equals("RANDOM_JSON_QUOTE")) {
        } else if (symbol.equals("TIMESTAMP")) {
            content = currentTimestamp();
        }
        return content;
    }

    public String currentTimestamp() {
        Date date = new Date();
        return date.toString();
    }
}



