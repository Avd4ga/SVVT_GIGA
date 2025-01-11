import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends BaseTest{

    @Test
    public void search() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.id("Search-In-Modal-1")).click();
        driver.findElement(By.id("Search-In-Modal-1")).sendKeys("laptop");
        driver.findElement(By.cssSelector(".icon-search")).click();
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"product-grid\"]/ul/li[1]/div/div[2]/div/h3/a")).getText().toLowerCase().contains("laptop"));

    }

    @Test
    public void searchNotExist() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.id("Search-In-Modal-1")).click();
        driver.findElement(By.id("Search-In-Modal-1")).sendKeys("xyz123");
        driver.findElement(By.cssSelector(".icon-search")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"shopify-section-template--16504047894704__main\"]/div/div[1]/div/div[2]/predictive-search/div/form/p")).getText().contains("Nema rezultata"));
    }

    @Test
    public void searchSuggestion() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.id("Search-In-Modal-1")).click();
        driver.findElement(By.id("Search-In-Modal-1")).sendKeys("lap");
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"predictive-search-results-groups-wrapper\"]/div")).getText().toLowerCase().contains("lap"));
    }
}
