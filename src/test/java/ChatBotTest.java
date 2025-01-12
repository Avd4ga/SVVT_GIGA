import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChatBotTest extends BaseTest {

    @Test
    public void searchTest() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement chatBot = driver.findElement(By.id("Avada-FAQ_WidgetTrigger"));
        chatBot.click();
        Thread.sleep(1000);

        WebElement search = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[5]/div/div[1]/div/input"));

        search.click();
        Thread.sleep(1000);

        // when creating an instance of search input, the code throws stale element exception, so we do it this way
        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[1]/div/div[2]/div/div/input")).sendKeys("k");
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[1]/div/div[2]/div/div/input")).sendKeys("a");
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[1]/div/div[2]/div/div/input")).sendKeys("k");
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[1]/div/div[2]/div/div/input")).sendKeys("o");
        Thread.sleep(2000);

        List<WebElement> titles = driver.findElements(By.xpath("//p[contains(@style, 'font-weight: 500')]"));
        List<WebElement> descriptions = driver.findElements(By.xpath("//p[contains(@style, 'font-weight: 400')]"));

        for (int i = 0; i < titles.size(); i++) {
            assertTrue(titles.get(i).getText().toLowerCase().contains("kako") || descriptions.get(i).getText().toLowerCase().contains("kako"));
        }

    }

    @Test
    public void navigationTest() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement chatBot = driver.findElement(By.id("Avada-FAQ_WidgetTrigger"));
        chatBot.click();
        Thread.sleep(1000);

        WebElement questionDiv1 = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[5]/div/div[2]/nav[1]/div"));
        String questionTitle1 = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[5]/div/div[2]/nav[1]/div/div[1]/span")).getText();

        questionDiv1.click();
        Thread.sleep(1000);

        String answerTitle1 = driver.findElement(By.xpath("//p[contains(@style, 'font-weight: 500')]")).getText();
        System.out.println("Question title: " + questionTitle1);
        System.out.println("Answer title: " + answerTitle1);
        assertEquals(questionTitle1, answerTitle1);

        WebElement backButton = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[1]/div/div[1]/div[1]/button"));
        backButton.click();
        Thread.sleep(1000);

        WebElement questionDiv2 = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[5]/div/div[2]/nav[2]/div"));
        String questionTitle2 = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[5]/div/div[2]/nav[2]/div/div[1]/span")).getText();

        questionDiv2.click();
        Thread.sleep(1000);

        String answerTitle2 = driver.findElement(By.xpath("//p[contains(@style, 'font-weight: 500')]")).getText();
        System.out.println("Question title: " + questionTitle2);
        System.out.println("Answer title: " + answerTitle2);
        assertEquals(questionTitle2, answerTitle2);

        backButton = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[1]/div/div[1]/div[1]/button"));
        backButton.click();
        Thread.sleep(1000);

        WebElement expandButton = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[3]/div/div/div/div[3]/button[2]"));
        expandButton.click();
        Thread.sleep(1000);

        WebElement instagram = driver.findElement(By.xpath("//*[@id=\"Avada_Faq_Popup-Container\"]/div/div[2]/div/div[3]/div/div/div/div[3]/div[4]/a"));
        // don't open in new tab
        js.executeScript("arguments[0].setAttribute('target', '_self');", instagram);
        instagram.click();
        Thread.sleep(5000);

        assertEquals(driver.getCurrentUrl(), "https://www.instagram.com/giga.doo/");
        driver.navigate().back();
        Thread.sleep(2000);

        assertEquals(driver.getCurrentUrl(), BASE_URL);

    }
}
