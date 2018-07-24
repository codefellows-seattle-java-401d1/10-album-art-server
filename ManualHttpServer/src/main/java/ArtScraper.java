package main.java;

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
        try {
            String url = "https://www.discogs.com/search/q=" + query + "&type=all";
            Document doc = Jsoup.connect(url).get();
            Elements albumArt = doc.select(".thumbnail_center");
            Element span = albumArt.get(0);
            Element img = span.child(0);
            String src = img.attr("data-src");
            System.out.println(src);

        } catch (IOException e) {

        }

        return src;
    }

}
