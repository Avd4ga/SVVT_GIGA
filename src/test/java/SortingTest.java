import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortingTest extends BaseTest {

    @Test
    public void sortByPriceAscending() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(".cards-grid__item:nth-child(2) > .cards-grid__link")).click();
        Thread.sleep(1000);

        WebElement sort = driver.findElement(By.name("sort_by"));
        Select order = new Select(sort);
        order.selectByIndex(4);
        Thread.sleep(1000);

        // this is only a trigger to refresh the page
        WebElement minimumPrice = driver.findElement(By.name("filter.v.price.gte"));
        minimumPrice.clear();
        minimumPrice.sendKeys("0");
        Thread.sleep(2000);

        List<WebElement> prices = driver.findElements(By.cssSelector(".price__regular .price-item.price-item--regular"));
        double previousPrice = 0;
        for (WebElement price : prices) {
            String priceText = price.getAttribute("innerText"); // for some reason, getText() returns an empty string here
            double priceValue = Double.parseDouble(priceText);
            System.out.println(priceValue);
            assertTrue(priceValue >= previousPrice, "Price found less than " + previousPrice + ": " + priceValue);
            previousPrice = priceValue;
        }

    }

    // test fails because there is an actual bug in the application
    @Test
    public void sortAlphabetically() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"shopify-section-template--16504047698096__cards-grid\"]/div/div/div/div[1]/a")).click();
        Thread.sleep(1000);

        WebElement sort = driver.findElement(By.name("sort_by"));
        Select order = new Select(sort);
        order.selectByIndex(2);
        Thread.sleep(1000);

        // this is only a trigger to refresh the page
        WebElement minimumPrice = driver.findElement(By.name("filter.v.price.gte"));
        minimumPrice.clear();
        minimumPrice.sendKeys("0");
        Thread.sleep(2000);

        List<WebElement> names = driver.findElements(By.cssSelector("h3.card__title.h5 > a.full-unstyled-link"));
        String previousName = "";
        for (WebElement name : names) {
            String nameText = name.getAttribute("innerText");
            System.out.println(nameText);
            assertTrue(nameText.compareTo(previousName) >= 0, "Name found less than " + previousName + ": " + nameText);
            previousName = nameText;
        }

    }
}
