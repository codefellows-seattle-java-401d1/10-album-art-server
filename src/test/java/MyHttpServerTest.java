import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyHttpServerTest {

    @Test
    void route() {
        String path = "/index.html";
        String examineReader="";
        HTTPStaticFileReader reader = new HTTPStaticFileReader(path);
//        if (path.startsWith("/search")) {
//            String query = path.queryParams().get("query");
//            String url = AlbumScraper.getAlbumArtURL(query);
//
//            Map<String, String> locals = new HashMap<>();
//            locals.put("IMG_SRC", url);
//            reader = new TemplateFileReader("/cover.html", locals);
//        }
        try {
            examineReader = reader.getContents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean actual = examineReader.contains("action");
        Boolean expected = true;

        assertEquals(expected, actual);
    }
}