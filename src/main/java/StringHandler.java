import java.util.HashMap;
import java.util.Map;

public class StringHandler {
    public static void main(String[] args) {
        Map<String, String> params = getParams("/search?query=Cone Toaster");
        System.out.println(params);
    }

    public static Map<String, String> getParams(String url) {
        System.out.println(url);
        Map<String, String> params = new HashMap<>();
        if (!url.contains("?")) {
            return params;
        }
        String query = url.split("\\?")[1];
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            System.out.println(pair);
            String[] arrayOfStrings = pair.split("=");
            String key = arrayOfStrings[0];
            String value = "";
            if (arrayOfStrings.length >= 2) {
                value = arrayOfStrings[1];
            }
            params.put(key, value);
        }
        return params;
    }
}