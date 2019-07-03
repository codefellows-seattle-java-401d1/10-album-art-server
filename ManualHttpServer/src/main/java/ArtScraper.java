import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class ArtScraper {
    public static void main(String[] args) {
        Art("cartoons");
    }
    public static String Art(String query) {
        String notFound = "/Users/ahmedosman/Desktop/401/labs/lab10-ahmed/10-album-art-server/ManualHttpServer/static/no-image-found.jpg";
        String src = notFound;
        try {
            String url = "https://www.discogs.com/search/?q=" + query + "&type=all";
            Document doc = Jsoup.connect(url).get();
            Elements albumArt = doc.select(".thumbnail_center");
            Element span = albumArt.get(0);
            Element img = span.child(0);
            src = img.attr("data-src");
            System.out.println(src);
            return src;
        } catch (IOException e) {
            return notFound;
        }

    }
}
