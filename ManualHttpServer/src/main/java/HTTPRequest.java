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
                this.path = "/index.html";
            }
        } catch (IOException e) {
            System.out.println("Error parsing HTTP request: " + this.path);
        }
    }

    public Map<String, String> queryParams() {
        Map<String, String> params = new HashMap<>();
        if (this.path.contains("?")) {
            String queryString = this.path.split("\\?")[1];
            String[] pairs = queryString.split("&");

            for (String param : pairs) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];

                String value = "";
                if (keyValue.length >= 1) {
                    value = keyValue[1];
                }
                params.put(key, value);
            }
        }
        return params;
    }
}
