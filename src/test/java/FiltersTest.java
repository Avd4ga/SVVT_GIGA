import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FiltersTest extends BaseTest{

    @Test
    public void filters() throws InterruptedException {

        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector(".cards-grid__item:nth-child(2) > .cards-grid__link")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("#Facet-3-template--16504047435952__product-grid .list-menu__item:nth-child(2) > .facet-checkbox")).click();
        Thread.sleep(2000);

        assertTrue(driver.findElement(By.xpath("//*[@id=\"product-grid\"]")).getText().contains("Rasprodano"));

    }

    @Test
    public void filtersPrice() throws InterruptedException {

        driver.get(baseUrl);
        driver.manage().window().maximize();

        String inputPrice = "68";

        WebElement secondGridItem = driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cards-grid__item:nth-child(2) > .cards-grid__link")));
        secondGridItem.click();
        Thread.sleep(2000);

        WebElement priceFilterInput = driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Filter-Cijena-LTE\"]")));
        priceFilterInput.clear();
        priceFilterInput.sendKeys(inputPrice);
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
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector(".cards-grid__item:nth-child(2) > .cards-grid__link")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("#Facet-1-template--16504047435952__product-grid .list-menu__item:nth-child(1) > .facet-checkbox")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("#Facet-3-template--16504047435952__product-grid .list-menu__item:nth-child(2) > .facet-checkbox")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(".active-facets-desktop .active-facets__button-remove > span")).click();
        Thread.sleep(1000);

        assertNotEquals("https://giga.ba/collections/gaming-setovi?sort_by=best-selling&filter.v.price.gte=0.00&filter.v.price.lte=400.00", driver.getCurrentUrl());

    }
}
