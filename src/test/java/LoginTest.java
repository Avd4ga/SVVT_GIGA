import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest{

    @Test
    public void login() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        driver.findElement(By.linkText("Prijava")).click();
        driver.findElement(By.id("CustomerEmail")).click();
        driver.findElement(By.id("CustomerEmail")).sendKeys("lanolog879@kvegg.com");
        driver.findElement(By.id("CustomerPassword")).click();
        driver.findElement(By.id("CustomerPassword")).sendKeys("SecurePass123");

        driver.findElement(By.cssSelector(".customer__buttons:nth-child(6) > .button--primary")).click();
        Thread.sleep(6000);
        assertTrue(driver.getCurrentUrl().equals("https://giga.ba/account"));
    }

    @Test
    public void loginFail() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Prijava")).click();
        driver.findElement(By.id("CustomerEmail")).click();
        driver.findElement(By.id("CustomerEmail")).sendKeys("lanolog87@kvegg.com");
        driver.findElement(By.id("CustomerPassword")).click();
        driver.findElement(By.id("CustomerPassword")).sendKeys("SecurePass123");

        driver.findElement(By.cssSelector(".customer__buttons:nth-child(6) > .button--primary")).click();

        Thread.sleep(6000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[2]/ul/li")).getText().contains("Incorrect email or password."));
    }
}
