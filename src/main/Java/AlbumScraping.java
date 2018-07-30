import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AlbumScraping {

    public static String albumScrape(String query) {
        try {
            String url = "https://www.discogs.com/search/?q=" + query + "&type=all";
            Document doc = Jsoup.connect(url).get();
            Elements albumArt = doc.select(".thumbnail_center");
            Element album = albumArt.get(0);
            Element image = album.child(0);
            String src = image.attr("data-src");

            System.out.println("src:" + src);
            return src;

        } catch(IOException e){
            e.printStackTrace();
        }
        return "default";
    }
}

