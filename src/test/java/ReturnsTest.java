import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReturnsTest extends BaseTest {

    @Test
    public void fileTooLarge() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement returnsLink = driver.findElement(By.xpath("//*[@id=\"shopify-section-sections--16504047927472__footer\"]/footer/div[1]/div/div/div[7]/ul/li[3]/a"));
        js.executeScript("arguments[0].scrollIntoView();", returnsLink);
        Thread.sleep(1000);

        returnsLink.click();
        Thread.sleep(1000);

        assertTrue(driver.getCurrentUrl().contains("povrat-robe"));

        WebElement formBtn = driver.findElement(By.xpath("/html/body/main/section[2]/div/div/a"));
        js.executeScript("arguments[0].scrollIntoView();", formBtn);
        Thread.sleep(1000);

        formBtn.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[1]/form/div[1]/textarea")).sendKeys("Logitech laptop mouse");
        Thread.sleep(500);

        driver.findElement(By.id("nameLastName")).sendKeys("John Doe");
        Thread.sleep(500);

        driver.findElement(By.id("email")).sendKeys(EMAIL);
        Thread.sleep(500);

        driver.findElement(By.id("adress")).sendKeys("Test Address 123");
        Thread.sleep(500);

        driver.findElement(By.id("phoneNumber")).sendKeys("061 061 061");
        Thread.sleep(500);

        driver.findElement(By.id("returnReason")).sendKeys("Product is not working");
        Thread.sleep(500);

        driver.findElement(By.id("bankAccount")).sendKeys("000000000000000000");
        Thread.sleep(500);

        driver.findElement(By.id("billNumber")).sendKeys("123456789");
        Thread.sleep(500);

        driver.findElement(By.id("dateOfPurchase")).sendKeys("01.01.2021");
        Thread.sleep(500);

        driver.findElement(By.id("dateAndPlace")).sendKeys("Sarajevo, 01.01.2021");
        Thread.sleep(500);

        driver.findElement(By.id("imagePath")).sendKeys(Paths.get("src/main/resources/test_image.jpg").toAbsolutePath().toString());

        WebElement signature = driver.findElement(By.id("signature"));
        js.executeScript("arguments[0].click();", signature);
        Thread.sleep(500);

        driver.findElement(By.xpath("/html/body/div[1]/form/button")).click();

        assertTrue(driver.getPageSource().contains("File too large"), "File exceeds 10MB but it was uploaded");
        Thread.sleep(2000);

    }

    @Test
    public void duplicateRequest() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement returnsLink = driver.findElement(By.xpath("//*[@id=\"shopify-section-sections--16504047927472__footer\"]/footer/div[1]/div/div/div[7]/ul/li[3]/a"));
        js.executeScript("arguments[0].scrollIntoView();", returnsLink);
        Thread.sleep(1000);

        returnsLink.click();
        Thread.sleep(1000);

        assertTrue(driver.getCurrentUrl().contains("povrat-robe"));

        WebElement formBtn = driver.findElement(By.xpath("/html/body/main/section[2]/div/div/a"));
        js.executeScript("arguments[0].scrollIntoView();", formBtn);
        Thread.sleep(1000);

        formBtn.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[1]/form/div[1]/textarea")).sendKeys("Logitech laptop mouse");
        Thread.sleep(500);

        driver.findElement(By.id("nameLastName")).sendKeys("John Doe");
        Thread.sleep(500);

        driver.findElement(By.id("email")).sendKeys(EMAIL);
        Thread.sleep(500);

        driver.findElement(By.id("adress")).sendKeys("Test Address 123");
        Thread.sleep(500);

        driver.findElement(By.id("phoneNumber")).sendKeys("061 061 061");
        Thread.sleep(500);

        driver.findElement(By.id("returnReason")).sendKeys("Product is not working");
        Thread.sleep(500);

        driver.findElement(By.id("bankAccount")).sendKeys("000000000000000000");
        Thread.sleep(500);

        driver.findElement(By.id("billNumber")).sendKeys("123456789");
        Thread.sleep(500);

        driver.findElement(By.id("dateOfPurchase")).sendKeys("01.01.2021");
        Thread.sleep(500);

        driver.findElement(By.id("dateAndPlace")).sendKeys("Sarajevo, 01.01.2021");
        Thread.sleep(500);

        WebElement signature = driver.findElement(By.id("signature"));
        js.executeScript("arguments[0].click();", signature);
        Thread.sleep(500);

        driver.findElement(By.xpath("/html/body/div[1]/form/button")).click();

        Thread.sleep(3000);
        assertTrue(driver.getPageSource().contains("Zahtjev za povrat već postoji s unesenim računom!"), "Duplicate request was not detected");
    }

}
