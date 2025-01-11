import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;

    protected static String baseUrl;

    protected static JavascriptExecutor js;

    protected static WebDriverWait driverWait;

    @BeforeAll
    public static void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1024,768");
        options.addArguments("--incognito");
        options.addArguments("--incognito");

        // Additional options to avoid detection
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        // options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        baseUrl = "https://giga.ba/";
        js = (JavascriptExecutor) driver;
    }

    @AfterAll
    public static void tearDown() {

        if (driver != null) {
            driver.quit();
        }

    }

}
