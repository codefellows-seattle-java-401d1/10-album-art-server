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
            Elements courseTitles = doc.select("h3");

            for (Element title : courseTitles) {
                System.out.println(title.text());
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return "default";
    }
}

