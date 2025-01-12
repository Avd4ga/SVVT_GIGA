import chromedriver.ChromeDriverBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// this test class will be use my personal chrome profile
// so it does not extend BaseTest class
// and it will not have the same setup and teardown methods

// path to the chrome profile needs to be changed when running on another machine

public class UserInfoTest  {

    protected final String BASE_URL = "https://giga.ba/";

    protected static WebDriver driver;

    protected static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {

        String driver_home = "src/main/resources/driver/chromedriver.exe";
        ChromeOptions chrome_options = new ChromeOptions();
        chrome_options.addArguments("--window-size=1076,645");
        chrome_options.addArguments("--user-data-dir=C:/Users/amirl/AppData/Local/Google/Chrome/User Data");

        driver = new ChromeDriverBuilder()
                .build(chrome_options,driver_home);
        js = (JavascriptExecutor) driver;

    }

    @Test
    public void editUserAddress() throws InterruptedException {

        driver.get(BASE_URL + "account");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement addressViewBtn = driver.findElement(By.xpath("/html/body/main/div/div/div/div[2]/div[2]/div/a"));
        addressViewBtn.click();
        Thread.sleep(1000);

        String addressFirst = driver.findElement(By.xpath("/html/body/main/div/div/div/div[3]/div/div[2]/div/div[1]/ul/li[2]/span[2]")).getText();
        String cityFirst = driver.findElement(By.xpath("/html/body/main/div/div/div/div[3]/div/div[2]/div/div[1]/ul/li[3]/span[2]")).getText();
        String countryFirst = driver.findElement(By.xpath("/html/body/main/div/div/div/div[3]/div/div[2]/div/div[1]/ul/li[4]/span[2]")).getText();
        String zipFirst = driver.findElement(By.xpath("/html/body/main/div/div/div/div[3]/div/div[2]/div/div[1]/ul/li[5]/span[2]")).getText();

        WebElement editBtn = driver.findElement(By.xpath("/html/body/main/div/div/div/div[3]/div/div[2]/div/div[2]/button[1]"));
        editBtn.click();
        Thread.sleep(1000);

        WebElement addressInput = driver.findElement(By.xpath("//*[@id=\"AddressAddress1_9563875442864\"]"));
        addressInput.clear();
        addressInput.sendKeys("Test Address 123");
        Thread.sleep(500);

        WebElement cityInput = driver.findElement(By.xpath("//*[@id=\"AddressCity_9563875442864\"]"));
        cityInput.clear();
        cityInput.sendKeys("Sarajevo");
        Thread.sleep(500);

        Select countrySelect = new Select(driver.findElement(By.xpath("//*[@id=\"AddressCountry_9563875442864\"]")));
        countrySelect.selectByValue("Bosnia And Herzegovina");
        Thread.sleep(500);

        WebElement zipInput = driver.findElement(By.xpath("//*[@id=\"AddressZip_9563875442864\"]"));
        zipInput.clear();
        zipInput.sendKeys("71000");
        Thread.sleep(500);


        WebElement saveBtn = driver.findElement(By.xpath("/html/body/main/div/div/div/div[3]/div/div[1]/div/form/div[12]/button[1]"));
        saveBtn.click();
        Thread.sleep(1000);

        String addressSecond = driver.findElement(By.xpath("/html/body/main/div/div/div/div[3]/div/div[2]/div/div[1]/ul/li[2]/span[2]")).getText();
        String citySecond = driver.findElement(By.xpath("/html/body/main/div/div/div/div[3]/div/div[2]/div/div[1]/ul/li[3]/span[2]")).getText();
        String countrySecond = driver.findElement(By.xpath("/html/body/main/div/div/div/div[3]/div/div[2]/div/div[1]/ul/li[4]/span[2]")).getText();
        String zipSecond = driver.findElement(By.xpath("/html/body/main/div/div/div/div[3]/div/div[2]/div/div[1]/ul/li[5]/span[2]")).getText();

        assertNotEquals(addressFirst, addressSecond);
        assertNotEquals(cityFirst, citySecond);
        assertEquals(countryFirst, countrySecond);
        assertEquals(zipFirst, zipSecond);

    }

    @Test
    public void userInfoFetchOnOrder() throws InterruptedException {

        driver.get(BASE_URL + "account");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        String userAddress = driver.findElement(By.xpath("/html/body/main/div/div/div/div[2]/div[2]/div/ul/li[2]/span[1]")).getText();
        String userCity = driver.findElement(By.xpath("/html/body/main/div/div/div/div[2]/div[2]/div/ul/li[2]/span[2]")).getText();
        String userZip = driver.findElement(By.xpath("/html/body/main/div/div/div/div[2]/div[2]/div/ul/li[2]/span[3]")).getText();
        String userCountry = driver.findElement(By.xpath("/html/body/main/div/div/div/div[2]/div[2]/div/ul/li[2]/span[4]")).getText();

        driver.findElement(By.xpath("/html/body/main/div/div/div/div[2]/div[1]/div/div/a")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/main/section/div/ul/li[4]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/main/section/div/div/div[3]/div/ul/li[1]/div")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/main/section[1]/section/div/div[2]/div[2]/div/div[7]/product-form/form/div/div[2]/div/shopify-accelerated-checkout/shopify-buy-it-now-button/button")).click();
        Thread.sleep(1000);

        String orderAddress = driver.findElement(By.name("address1")).getAttribute("value");
        String orderCity = driver.findElement(By.name("city")).getAttribute("value");
        String orderZip = driver.findElement(By.name("postalCode")).getAttribute("value");
        String orderCountry = driver.findElement(By.name("countryCode")).getAttribute("innerText");

        assertEquals(userAddress, orderAddress);
        assertEquals(userCity, orderCity);
        assertEquals(userZip, orderZip);
        assertEquals(userCountry, orderCountry);

    }

    @AfterAll
    public static void tearDown() {

        if (driver != null) {
            driver.quit();
        }

    }
}
