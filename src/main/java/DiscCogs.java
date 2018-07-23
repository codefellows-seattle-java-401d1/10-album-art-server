import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DiscCogs {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.discogs.com/").get();
        System.out.println(doc.title());
        Elements albumCover = doc.select("#thumbnail_center");
        for (Element img : albumCover) {
        }
    }
}