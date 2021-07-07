import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

class MyHttpServer {


    public static final int PORT = 6789;

    public static void main(String argv[]) {
        try (ServerSocket socket = new ServerSocket(PORT)) {
            serve(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serve(ServerSocket socket) throws IOException {
        System.out.println("Listening on http://localhost:" + PORT);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("waiting for request...");
            Socket connectionSocket = socket.accept();
            BufferedReader in;
            BufferedWriter out = null;

            try {
                in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                out = new BufferedWriter((new OutputStreamWriter(connectionSocket.getOutputStream())));
                handleRequest(in, out);
                HTTPRequest request = new HTTPRequest(in);
            } catch (Exception e) {
                if (out != null) {
                    HTTPResponse response = new HTTPResponse(500, "Internal Server Error");
                    response.send(out);
                }
            }

            System.out.println("closed request.");
        }

    }

    public static void handleRequest (BufferedReader in, BufferedWriter out) {
        HTTPRequest request = new HTTPRequest(in);
        HTTPResponse response;

        try {
            String body = route(request);
            response = new HTTPResponse(200, body);
        } catch (FileNotFoundException e) {
            response = new HTTPResponse(404, "Error processing file " + request.path);
            e.printStackTrace();
        } catch (Exception e) {
            response = new HTTPResponse(500, "Internal server error");
        }
        response.send(out);
        System.out.println("closed request");
    }

    public static String route (HTTPRequest request) throws IOException {
        HTTPStaticFileReader reader = new HTTPStaticFileReader(request.path);
        if (request.path.startsWith("/search")) {
            String query = request.getParams().get("query");
            String url = AlbumScraping.albumScrape(query);

            Map<String, String> albums = new HashMap<>();
            albums.put("IMG_SRC", url);
            reader = new FileReader("/albums.html", albums);
        }
        return reader.getContents();
    }

}