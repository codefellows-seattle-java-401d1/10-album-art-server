import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CodeFellowsCourses {
    public static String main(String[] args) {
        try {
            String url = "https://www.codefellows.org/courses/code-401/advanced-software-development-in-full-stack-javascript/";
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
