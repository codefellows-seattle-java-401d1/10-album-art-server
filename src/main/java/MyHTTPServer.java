
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.BufferedReader;


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
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedWriter outToClient = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));

            HTTPRequest request = new HTTPRequest(inFromClient);

            HTTPResponse response;
            try {
                String body = route(request);
                response = new HTTPResponse(200, body);
            } catch (FileNotFoundException e) {
                response = new HTTPResponse(404, "Could not find " + request.path);
            } catch (Exception e){
                response = new HTTPResponse(500, "Internal server error");
            }
             response.send(outToClient);

             System.out.println("closed request.");
        }
    }

    private static String route(HTTPRequest request) throws IOException {
        String body = "";
        HTTPStaticFileReader reader = new HTTPStaticFileReader(request.path);
        //System.out.println("Path " + request.path);

        if (request.path.startsWith("/search")) {
            String querySearch = request.queryParams().get("query");
            String url = AlbumScraper.getAlbumArt(querySearch);

            Map<String, String> locals = new HashMap<>();
            locals.put("IMG_SRC", url);
            reader = new TemplateFileReader("/cover.html", locals);
            String examineReader =  reader.getContents();
            return examineReader;
        } else {
            HTTPStaticFileReader file = new HTTPStaticFileReader(request.path);
            body = file.getContents();
        }
        return body;
    }
    
}