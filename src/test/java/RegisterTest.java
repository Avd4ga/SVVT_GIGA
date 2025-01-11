import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest extends BaseTest{


    @Test
    public void registration() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        Thread.sleep(2000);
        {
            WebElement element = driver.findElement(By.linkText("Prijava"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".header__account__register > span")).click();
        driver.findElement(By.id("RegisterForm-FirstName")).click();
        driver.findElement(By.id("RegisterForm-FirstName")).sendKeys("test");
        String name=driver.findElement(By.id("RegisterForm-FirstName")).getText();
        driver.findElement(By.id("RegisterForm-LastName")).click();
        driver.findElement(By.id("RegisterForm-LastName")).sendKeys("user");
        String last=driver.findElement(By.id("RegisterForm-LastName")).getText();
        String AccountName=name+" "+last;
        driver.findElement(By.id("RegisterForm-email")).click();
        driver.findElement(By.id("RegisterForm-email")).sendKeys("kalemab337@sfxeur.com");
        driver.findElement(By.id("RegisterForm-password")).sendKeys("SecurePass123");
        driver.findElement(By.cssSelector(".button--arrow:nth-child(1)")).click();
        driver.manage().window().maximize();
        Thread.sleep(60000);
        driver.findElement(By.cssSelector(".icon-account")).click();
        driver.findElement(By.id("RegisterForm-password")).click();
        assertEquals(driver.findElement(By.className("header__account__name")).getText(), AccountName);
    }

    @Test
    public void registrationFail() throws InterruptedException {
        driver.get(baseUrl);
        //driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        driver.findElement(By.cssSelector(".header__account__register > span")).click();
        driver.findElement(By.id("RegisterForm-FirstName")).click();
        driver.findElement(By.id("RegisterForm-FirstName")).sendKeys("test");
        driver.findElement(By.id("RegisterForm-LastName")).click();
        driver.findElement(By.id("RegisterForm-LastName")).sendKeys("user");
        driver.findElement(By.id("RegisterForm-email")).click();
        driver.findElement(By.id("RegisterForm-email")).sendKeys("avdovladavic@gmail.com");
        driver.findElement(By.id("RegisterForm-password")).click();
        driver.findElement(By.id("RegisterForm-password")).sendKeys("Metak2003");
        driver.findElement(By.cssSelector(".button--arrow:nth-child(1)")).click();
        Thread.sleep(3000);
        //assertThat(driver.findElement(By.id("RegisterForm-email-error")).getText(), is("Ova e-adresa već je povezana s računom."));
        assertEquals(driver.findElement(By.id("RegisterForm-email-error")).getText(), "Ova e-adresa već je povezana s računom.");
    }
}
