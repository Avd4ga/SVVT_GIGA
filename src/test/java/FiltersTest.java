import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class FiltersTest extends BaseTest{

    @Test
    public void filters() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".cards-grid__item:nth-child(2) > .cards-grid__link")).click();
        driver.findElement(By.cssSelector("#Facet-3-template--16504047435952__product-grid .list-menu__item:nth-child(2) > .facet-checkbox")).click();
        Thread.sleep(2000);
        {
            WebElement element = driver.findElement(By.cssSelector(".facets__range:nth-child(3) > .field__range:nth-child(1)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".facets__range:nth-child(3) > .field__range:nth-child(1)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".facets__range:nth-child(3) > .field__range:nth-child(1)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }


        Thread.sleep(2000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"product-grid\"]")).getText().contains("Rasprodano"));

    }

    @Test
    public void filtersPrice() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();

        String inputPrice = "68";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement secondGridItem = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cards-grid__item:nth-child(2) > .cards-grid__link")));
        secondGridItem.click();

        WebElement priceFilterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Filter-Cijena-LTE\"]")));
        priceFilterInput.clear();
        priceFilterInput.sendKeys(inputPrice);
        priceFilterInput.sendKeys(Keys.RETURN);
        Thread.sleep(2000);

        List<WebElement> prices = driver.findElements(By.cssSelector(".price__regular .price-item.price-item--regular"));
        for (WebElement price : prices) {
            double priceValue = Double.parseDouble(price.getText());
            assertTrue(priceValue <= Double.parseDouble(inputPrice), "Price found greater than " + inputPrice + ": " + priceValue);
        }
    }

    @Test
    public void filtersClearFilters() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().setSize(new Dimension(1936, 1048));
        driver.findElement(By.cssSelector(".cards-grid__item:nth-child(2) > .cards-grid__link")).click();
        driver.findElement(By.cssSelector("#Facet-1-template--16504047435952__product-grid .list-menu__item:nth-child(1) > .facet-checkbox")).click();
        driver.findElement(By.cssSelector("#Facet-3-template--16504047435952__product-grid .list-menu__item:nth-child(2) > .facet-checkbox")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("#Details-3-template--16504047435952__product-grid .facets__reset"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".active-facets-desktop .active-facets__button-remove > span")).click();
        Thread.sleep(2000);
        assertNotEquals("https://giga.ba/collections/gaming-setovi?sort_by=best-selling&filter.v.price.gte=0.00&filter.v.price.lte=400.00", driver.getCurrentUrl());
    }
}
