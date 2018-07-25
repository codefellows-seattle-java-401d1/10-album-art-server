import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HTTPRequestTest {

    @Test
    //Test idea from lecture on Tuesday at 1:58pm
    void queryParamsWithKnownEntity() {
        String url = "/search/?q=andrew lloyd webber";
        Map<String, String> params = new HashMap<>();

        if (url.contains("?")) {
            String queryString = url.split("\\?")[1];
            String[] pairs = queryString.split("&");

            for (String param : pairs) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];

                String value = "";
                if (keyValue.length >= 1) {
                    value = keyValue[1];
                }
                params.put(key, value);
            }
        }
        String actual = params.get("q");
        String expected = "andrew lloyd webber";
        assertEquals(expected, actual);
    }
}