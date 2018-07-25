import org.junit.jupiter.api.Test;

import java.util.Map;


class StringButcheringTest {

    @Test
    void getParams() {
        String url = "/search?query=Tom null";
        Map<String, String> params = StringButchering.getParams(url);
        System.out.println("q: " + params.get("q"));
    }
}