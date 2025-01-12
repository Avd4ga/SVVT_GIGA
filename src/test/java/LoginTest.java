import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest{

    @Test
    public void login() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Prijava")).click();
        Thread.sleep(1000);

        // the undetected-driver is replacing the @ sign with driver location, so we input the email in this way
        String[] emailParts = EMAIL.split("@");
        driver.findElement(By.id("CustomerEmail")).sendKeys(emailParts[0] + Keys.chord(Keys.SHIFT, "2") + emailParts[1]);
        Thread.sleep(1000);

        driver.findElement(By.id("CustomerPassword")).sendKeys(PASSWORD);
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(".customer__buttons:nth-child(6) > .button--primary")).click();

        // wait until captcha is manually solved
        Thread.sleep(15000);
        assertEquals("https://giga.ba/account", driver.getCurrentUrl());
    }

    @Test
    public void loginFail() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        Thread.sleep(2000);

        driver.findElement(By.linkText("Prijava")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("CustomerEmail")).sendKeys("example" + Keys.chord(Keys.SHIFT, "2") + "gmail.com");
        Thread.sleep(1000);

        driver.findElement(By.id("CustomerPassword")).sendKeys("SecurePass123");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(".customer__buttons:nth-child(6) > .button--primary")).click();

        Thread.sleep(6000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[2]/ul/li")).getText().contains("Incorrect email or password."));
    }
}
