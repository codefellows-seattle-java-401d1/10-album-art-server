import java.io.*;
import java.net.*;

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
            HTTPStaticFileReader file = new HTTPStaticFileReader(request);

            try {
                int statusCode = 200;
                String body = file.getContents();
                HTTPResponse response = new HTTPResponse(statusCode, body);
                response.send(outToClient);
            } catch (FileNotFoundException e) {
                HTTPResponse response = new HTTPResponse(404, "Could not find " + request.path);
                response.send(outToClient);
            } catch (IOException e) {
               HTTPResponse response = new HTTPResponse(500, "Internal server error");
               response.send(outToClient);
            }

            System.out.println("closed request.");
        }
    }
}
/*
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

            BufferedReader in;
            BufferedWriter out = null;
            try {
                Socket request = socket.accept();
                in = new BufferedReader(new InputStreamReader(request.getInputStream(connectionSocket.getInputStream())));
//            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                out = new BufferedWriter((new OutputStreamWriter(connectionSocket.getOutputStream())));
//            BufferedWriter outToClient = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
                handleRequest(in, out);
//            HTTPRequest request = new HTTPRequest(inFromClient);
//            HTTPResponse response;
            } catch (Exception e) {
                if (out != null) {
                    HTTPResponse response = new HTTPResponse(500, "Internal server error");
                    response.send(out);
                }
            }
        }

        public static void handleRequest(BufferedReader in, BufferedWriter out) {
            HTTPRequest request = new HTTPRequest(in);
            HTTPResponse response;

            try {
                String body = route(request);
                response = new HTTPResponse(200, body);
            } catch (FileNotFoundException e) {
                response = new HTTPResponse(404, "Error processing file " + request.path);
//            } catch (Exception e) {
//                response = new HTTPResponse(500, "Internal server error");
            }
            response.send(out);
            System.out.println("closed request.");
        }
    }

    private static String route(HTTPRequest request) throws IOException {
        HTTPStaticFileReader reader = new HTTPStaticFileReader(request.path);
        if (request.path.startsWith("/search")) {
            String query = request.queryParams().get("query");
            String url = AlbumScraper.getAlbumArtURL(query);

            Map<String, String> locals = new HashMap<>();
            locals.put("IMG_SRC", url);
            reader = new TemplateFileReader("cover.html", locals);
//        } else {
//        HTTPStaticFileReader file = new HTTPStaticFileReader(request);
//        body = file.getContents();
        }
        return reader.getContents();
    }
}
*/