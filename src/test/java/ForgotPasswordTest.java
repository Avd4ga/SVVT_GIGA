import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForgotPasswordTest extends BaseTest {

    @Test
    public void forgotPasswordValidInput() throws InterruptedException {

        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Prijava")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Zaboravljena lozinka?")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("RecoverEmail")).sendKeys("lanolog879@kvegg.com");
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/main/section/div/div/div/div/div[2]/div[1]/div[2]/form/div[2]/button")).click();
        Thread.sleep(4000);

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Poslali smo vam e-poštu s poveznicom za ažuriranje lozinke."));

    }

    @Test
    public void forgotPasswordInvalidInput() throws InterruptedException {

        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Prijava")).click();
        Thread.sleep(1000);

        driver.findElement(By.linkText("Zaboravljena lozinka?")).click();
        Thread.sleep(1000);

        WebElement email = driver.findElement(By.id("RecoverEmail"));
        email.sendKeys("abc1234xyz");

        driver.findElement(By.xpath("/html/body/main/section/div/div/div/div/div[2]/div[1]/div[2]/form/div[2]/button")).click();
        Thread.sleep(2000);

        String error = email.getAttribute("validationMessage");
        System.out.println(error);

        assertTrue(error.contains("Please include an '@' in the email address."));

    }

}
