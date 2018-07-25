import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AlbumScraperTest {

    @Test
    void getAlbumArtURL() {
        String query = "andrew lloyd webber";
        String actual = "";
        try {
            String url = " https://www.discogs.com/search/?q=" + query + "&type=all";
            Document doc = Jsoup.connect(url).get();

            Elements albumCovers = doc.select(".thumbnail_center");
            Element span = albumCovers.get(0);
            Element img = span.child(0);
            String src = img.attr("data-src");
            System.out.println(src);
            actual =  src;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
        String expected = "https://img.discogs.com/40VgAD5aipkblycrQSI0-w07IE4=/300x300/smart/filters:strip_icc():format(jpeg):mode_rgb():quality(40)/discogs-images/A-84839-1473273545-9408.jpeg.jpg";
        assertEquals(expected, actual);
    }
}