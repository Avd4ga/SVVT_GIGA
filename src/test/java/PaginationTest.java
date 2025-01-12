import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaginationTest extends BaseTest {

    @Test
    public void nextPage() throws InterruptedException {

        driver.get(BASE_URL + "/collections");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        List<WebElement> firstPageTitles = driver.findElements(By.xpath("//li[contains(@class, 'collections-grid__item')]//span"));
        List<String> firstPageTitlesText = new ArrayList<>();
        for (WebElement title : firstPageTitles) {
            firstPageTitlesText.add(title.getText());
        }
        String firstPageUrl = driver.getCurrentUrl();

        WebElement nextPageButton = driver.findElement(By.xpath("/html/body/main/section/div/div[2]/nav/ul/li[6]/a"));
        js.executeScript("arguments[0].scrollIntoView();", nextPageButton);
        nextPageButton.click();
        Thread.sleep(2000);

        List<WebElement> secondPageTitles = driver.findElements(By.xpath("//li[contains(@class, 'collections-grid__item')]//span"));
        List<String> secondPageTitlesText = new ArrayList<>();
        for (WebElement title : secondPageTitles) {
            secondPageTitlesText.add(title.getText());
        }
        String secondPageUrl = driver.getCurrentUrl();

        for (int i = 0; i < firstPageTitlesText.size(); i++) {
            System.out.println("First page title: " + firstPageTitlesText.get(i));
            System.out.println("Second page title: " + secondPageTitlesText.get(i));
            assertNotEquals(firstPageTitlesText.get(i), secondPageTitlesText.get(i));
        }

        assertNotEquals(firstPageUrl, secondPageUrl);

    }

    @Test
    public void goToSpecificPage() throws InterruptedException {

        driver.get(BASE_URL + "/collections");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        List<WebElement> firstPageTitles = driver.findElements(By.xpath("//li[contains(@class, 'collections-grid__item')]//span"));
        List<String> firstPageTitlesText = new ArrayList<>();
        for (WebElement title : firstPageTitles) {
            firstPageTitlesText.add(title.getText());
        }
        String firstPageUrl = driver.getCurrentUrl();

        WebElement thirdPageButton = driver.findElement(By.xpath("/html/body/main/section/div/div[2]/nav/ul/li[3]/a"));
        js.executeScript("arguments[0].scrollIntoView();", thirdPageButton);
        thirdPageButton.click();
        Thread.sleep(2000);

        List<WebElement> thirdPageTitles = driver.findElements(By.xpath("//li[contains(@class, 'collections-grid__item')]//span"));
        List<String> thirdPageTitlesText = new ArrayList<>();
        for (WebElement title : thirdPageTitles) {
            thirdPageTitlesText.add(title.getText());
        }
        String thirdPageUrl = driver.getCurrentUrl();

        for (int i = 0; i < firstPageTitlesText.size(); i++) {
            System.out.println("First page title: " + firstPageTitlesText.get(i));
            System.out.println("Third page title: " + thirdPageTitlesText.get(i));
            assertNotEquals(firstPageTitlesText.get(i), thirdPageTitlesText.get(i));
        }

        assertNotEquals(firstPageUrl, thirdPageUrl);
        assertTrue(thirdPageUrl.contains("page=3"));
    }

    @Test
    public void previousPage() throws InterruptedException {

        driver.get(BASE_URL + "/collections?page=3");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        List<WebElement> thirdPageTitles = driver.findElements(By.xpath("//li[contains(@class, 'collections-grid__item')]//span"));
        List<String> thirdPageTitlesText = new ArrayList<>();
        for (WebElement title : thirdPageTitles) {
            thirdPageTitlesText.add(title.getText());
        }
        String thirdPageUrl = driver.getCurrentUrl();

        WebElement previousPageButton = driver.findElement(By.xpath("/html/body/main/section/div/div[2]/nav/ul/li[1]/a"));
        js.executeScript("arguments[0].scrollIntoView();", previousPageButton);
        previousPageButton.click();
        Thread.sleep(2000);

        List<WebElement> secondPageTitles = driver.findElements(By.xpath("//li[contains(@class, 'collections-grid__item')]//span"));
        List<String> secondPageTitlesText = new ArrayList<>();
        for (WebElement title : secondPageTitles) {
            secondPageTitlesText.add(title.getText());
        }
        String secondPageUrl = driver.getCurrentUrl();

        for (int i = 0; i < thirdPageTitlesText.size(); i++) {
            System.out.println("Third page title: " + thirdPageTitlesText.get(i));
            System.out.println("Second page title: " + secondPageTitlesText.get(i));
            assertNotEquals(thirdPageTitlesText.get(i), secondPageTitlesText.get(i));
        }

        assertNotEquals(thirdPageUrl, secondPageUrl);
        assertTrue(secondPageUrl.contains("page=2"));

    }
}
