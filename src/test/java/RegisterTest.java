import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest extends BaseTest {

    @Test
    public void registration() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".header__account__register > span")).click();

        driver.findElement(By.id("RegisterForm-FirstName")).sendKeys("test");
        String firstName = driver.findElement(By.id("RegisterForm-FirstName")).getText();

        driver.findElement(By.id("RegisterForm-LastName")).sendKeys("user");
        String lastName = driver.findElement(By.id("RegisterForm-LastName")).getText();

        String accountName = firstName + " " + lastName;

        driver.findElement(By.id("RegisterForm-email")).sendKeys("example@gmail.com");

        driver.findElement(By.id("RegisterForm-password")).sendKeys("SecurePass123");

        driver.findElement(By.cssSelector(".button--arrow:nth-child(1)")).click();
        driver.manage().window().maximize();

        Thread.sleep(30000);
        driver.findElement(By.cssSelector(".icon-account")).click();
        driver.findElement(By.id("RegisterForm-password")).click();

        assertEquals(driver.findElement(By.className("header__account__name")).getText(), accountName);
    }

    @Test
    public void registrationFail() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();

        driver.findElement(By.cssSelector(".header__account__register > span")).click();

        driver.findElement(By.id("RegisterForm-FirstName")).sendKeys("test");

        driver.findElement(By.id("RegisterForm-LastName")).sendKeys("user");

        driver.findElement(By.id("RegisterForm-email")).sendKeys("avdovladavic@gmail.com");

        driver.findElement(By.id("RegisterForm-password")).sendKeys("Metak2003");

        driver.findElement(By.cssSelector(".button--arrow:nth-child(1)")).click();
        Thread.sleep(3000);
        //assertThat(driver.findElement(By.id("RegisterForm-email-error")).getText(), is("Ova e-adresa već je povezana s računom."));
        assertEquals(driver.findElement(By.id("RegisterForm-email-error")).getText(), "Ova e-adresa već je povezana s računom.");
    }
}
