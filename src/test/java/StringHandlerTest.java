import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StringHandlerTest {

    @Test
    void getParams() {
        String url = "/search?query=Beaches";
        Map<String, String> params = StringHandler.getParams(url);
        System.out.println("q: " + params.get("q"));
    }
}