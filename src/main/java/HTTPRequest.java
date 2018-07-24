import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HTTPRequest {
    public String path;

    public HTTPRequest(BufferedReader inFromClient) {
        processRequest(inFromClient);
    }

    public void processRequest(BufferedReader inFromClient) {
        try {
            // peel off the first GET/POST PATH line
            // "GET /home.html HTTP/1.1"
            // ["GET", "/home.html", "HTTP/1.1"][1]
            String requestLine = inFromClient.readLine();
            this.path = requestLine.split(" ")[1];

            if (this.path.equals("/")) {
                this.path = "/Users/yutani/codefellows/401/10-album-art-server/src/main/resources/static/index.html";
            }
        } catch (IOException e) {
            System.out.println("Error parsing HTTP request: " + this.path);
        }
    }

    public Map<String, String> getParams () {
        Map<String, String> queryParams = new HashMap<>();

        if (this.path.contains("?")) {
            String getPath = this.path.split("\\?")[1];
            String[] pairs = getPath.split("&");

            for (String pair : pairs) {
                String[] cells = pair.split("=");
                String key = cells[0];

                String value = "";

                if (cells.length >= 2) {
                    value = cells[1];
                }
                queryParams.put(key, value);
            }
        }
        return queryParams;
    }
}
