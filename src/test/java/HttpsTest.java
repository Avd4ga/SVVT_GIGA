import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpsTest extends BaseTest {

    @Test
    public void https() {

        driver.get(BASE_URL);
        driver.manage().window().maximize();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https"));

    }

    @Test
    public void http() {

        driver.get(BASE_URL.replace("https", "http"));
        driver.manage().window().maximize();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https"));

        Cookie cookie = new Cookie("cookie", "value");

    }

}
