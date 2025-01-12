import chromedriver.ChromeDriverBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Random;

public class BaseTest {

    // the undetected-driver is replacing the @ sign with driver location, so we input the email in this way
    protected final String EMAIL = "gefofa5405" + Keys.chord(Keys.SHIFT, "2") + "pariag.com";

    protected final String PASSWORD = "gefofa5405";

    protected final String BASE_URL = "https://giga.ba/";

    protected static WebDriver driver;
    protected static JavascriptExecutor js;
    protected static WebDriverWait driverWait;

    @BeforeAll
    public static void setUp() {

        /*ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--window-size=1024,768");
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        // options.addArguments("--headless");
        //driver = new ChromeDriver(options);*/

        String driver_home = "src/main/resources/driver/chromedriver.exe";
        ChromeOptions chrome_options = new ChromeOptions();
        chrome_options.addArguments("--window-size=1076,645");

        driver = new ChromeDriverBuilder()
                .build(chrome_options,driver_home);
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
        /*driver = new ChromeDriverBuilder()
                .build("your driver home");*/
    }

    @AfterAll
    public static void tearDown() {

        if (driver != null) {
            driver.quit();
        }

    }

}
