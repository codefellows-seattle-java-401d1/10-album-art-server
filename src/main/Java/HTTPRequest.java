import java.io.BufferedReader;
import java.io.BufferedWriter;
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
        String requestLine = inFromClient.readLine();
        this.path = requestLine.split(" ")[1];

        if (this.path.equals("/")) {
            this.path = "/index.html";
        }
    } catch (IOException e) {
            System.out.println("Error parsing HTTP request " + this.path);
        }
    }

    public Map<String, String> getParams() {
        Map<String, String> query = new HashMap<>();

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
                query.put(key, value);
            }
        }
        return query;
    }
}
