import java.util.HashMap;
import java.util.Map;

public class StringButchering {

    public static void main(String[] args) {
       Map<String, String> params = getParams("/search?query=Tom null");
        System.out.println("q: " + params.get("q"));
    }

        public static Map<String, String> getParams(String url){
        Map<String, String> params = new HashMap<>();
            String example = "";


            if (!url.contains("?")) {
            return params;
        }


        String querystring = url.split("\\?")[1];
        String[] pairs = querystring.split("&");
        for (String pair : pairs) {

            String[] cells = pair.split("=");
            String key = cells[0];
            String value = "";
            if (cells.length >= 2) {
                value = cells[1];
            }
            params.put(key, value);

        }

        return params;
    }
}

