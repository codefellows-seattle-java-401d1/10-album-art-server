package main.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class DiscogParser {
    public static String Scraper(String name){
        try {
            name.replace(" ","+");
            name.replace("%20","+");
            Document file = Jsoup.connect("https://discogs.com/search/?q=" + name + "&type=all").get();
            System.out.println((file).title());
            Elements images = file.select("div a span img");
            String imgUrl = images.first().attributes().get("data-src");
            System.out.println(imgUrl);
            return imgUrl;
        }catch(IOException e){
            System.out.println("Bad query");
            return "";
        }
    }
}
