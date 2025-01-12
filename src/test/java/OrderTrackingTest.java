import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTrackingTest extends BaseTest {

    @Test
    public void invalidOrderNumber() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement chatBot = driver.findElement(By.id("Avada-FAQ_WidgetTrigger"));
        chatBot.click();
        Thread.sleep(1000);

        WebElement orderTrackDiv = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[4]/div"));
        orderTrackDiv.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/div[2]/div/input")).sendKeys("123456");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/div[4]/div/input")).sendKeys(EMAIL);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/button")).click();
        Thread.sleep(1000);

        WebElement alertText = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/div[5]/span"));

        System.out.println(alertText.getText());
        assertTrue(alertText.getText().contains("Ne možemo naći nijednu narudžbu"));

    }

    @Test
    public void invalidEmail () throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement chatBot = driver.findElement(By.id("Avada-FAQ_WidgetTrigger"));
        chatBot.click();
        Thread.sleep(1000);

        WebElement orderTrackDiv = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[4]/div"));
        orderTrackDiv.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/div[2]/div/input")).sendKeys("123456");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/div[4]/div/input")).sendKeys("abcd1234xyz");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/button")).click();
        Thread.sleep(2000);

        WebElement alertText = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/div[4]/span"));

        System.out.println(alertText.getText());
        assertTrue(alertText.getText().contains("Email is invalid"));

    }

    @Test
    public void invalidPhoneNumber() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement chatBot = driver.findElement(By.id("Avada-FAQ_WidgetTrigger"));
        chatBot.click();
        Thread.sleep(1000);

        WebElement orderTrackDiv = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[4]/div"));
        orderTrackDiv.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/div[3]/label[2]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/div[2]/div/input")).sendKeys("123456");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/div[4]/div/div/div/div/div/input")).sendKeys("000000000000000000000");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/button")).click();
        Thread.sleep(2000);

        WebElement alertText = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[2]/div/div/div/div[4]/div/div[2]/span"));

        System.out.println(alertText.getText());
        assertTrue(alertText.getText().contains("Telefonski broj je netačan."));

    }
}
