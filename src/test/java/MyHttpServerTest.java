import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyHttpServerTest {

    @BeforeEach

    HTTPRequest request = "https://img.discogs.com/40VgAD5aipkblycrQSI0-w07IE4=/300x300/smart/filters:strip_icc():format(jpeg):mode_rgb():quality(40)/discogs-images/A-84839-1473273545-9408.jpeg.jpg";
    public static String route() throws IOException {
        HTTPStaticFileReader reader = new HTTPStaticFileReader(request.path);
        if (request.path.startsWith("/search")) {
            String query = request.queryParams().get("query");
            String url = AlbumScraper.getAlbumArtURL(query);

            Map<String, String> locals = new HashMap<>();
            locals.put("IMG_SRC", url);
            reader = new TemplateFileReader("/cover.html", locals);
        }
        String examineReader = reader.getContents();
        return examineReader;
    }

    @Test
    void route() {
        try {
            String actual = route(request);
            String expected = "https://img.discogs.com/40VgAD5aipkblycrQSI0-w07IE4=/300x300/smart/filters:strip_icc():format(jpeg):mode_rgb():quality(40)/discogs-images/A-84839-1473273545-9408.jpeg.jpg";
        assertEquals(expected, actual);
        } catch (IOException e) {

        }
    }
}