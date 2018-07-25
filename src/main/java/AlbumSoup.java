import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AlbumSoup {
    public static void main(String[] args) {
        fetchAlbum("Beaches and Canyons");
    }

    public static String fetchAlbum(String query) {
        try{
            String url = "https://www.discogs.com/search/?q=" + query + "&type=all";
            Document doc = Jsoup.connect(url).get();
            Elements albumImg = doc.select(".thumbnail_center");
            Element span = albumImg.get(0);
            Element img = span.child(0);
            String source = img.attr("data-src");
            System.out.println(source);

            return source;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "default";
    }
}
