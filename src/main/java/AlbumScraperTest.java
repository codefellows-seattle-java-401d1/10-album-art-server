import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class AlbumScraperTest {

    @org.junit.jupiter.api.Test
    void getAlbumArtTest() {
        String query = "Beastie Boys";
        String actual ="";
        String expected = "https://img.discogs.com/jR0e9VlZJK0i6EaE52ws7lVZ3Ic=/300x300/smart/filters" +
                ":strip_icc():format(jpeg):mode_rgb():quality(40)/discogs-images/A-10783-1449311945-7861.jpeg.jpg";
        try {
            String url = " https://www.discogs.com/search/?q=" + query + "&type=all";
            Document doc = Jsoup.connect(url).get();

            //search for the album covers
            Elements albumCovers = doc.select(".thumbnail_center");
            Element span = albumCovers.get(0);
            Element img = span.child(0);
            String src = img.attr("data-src");
            System.out.println(src);
            actual = src;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }

        assertEquals(expected,actual);
    }
    //album art for not found not working - tried debugging for almost 2 hours with Molly. Calling it good.
}