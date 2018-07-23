import java.util.HashMap;
import java.util.Map;

public class StringButchering {
    public static void main(String[] args) {
    Map<String, String> params = getParams();
        System.out.println("q: " + params.get("q"));

    }

    public static Map<String, String> getParams () {
        Map<String, String> params = new HashMap<>();
        String example = "https://www.discogs.com/search/?q=thrice+vheissu&type=all";

        //urls are not required to have query params
        if (!example.contains("?")) {
            return params;
        }
        String queryString = example.split("\\?")[1];
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            System.out.println(pair);

            String[] cells = pair.split("=");
            String key = cells[0];
            String value = "";

            //params aren't required to have a value.
            //keys can be present by themselves.
            if (cells.length >= 2) {
                value = cells[1];
            }
            params.put(key, value);
        }
        return params;
    }
}
