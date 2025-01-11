import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest{



    @Test
    public void seleniumAddCartSingle() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().setSize(new Dimension(1936, 1048));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".collection-product-card:nth-child(4) .link")).click();
        {
            WebElement element = driver.findElement(By.name("add"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        String name=driver.findElement(By.xpath("//*[@id=\"ProductInfo-template--16504047861936__main\"]/div[3]/h1")).getText();

        driver.findElement(By.name("add")).click();
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"CartPopup\"]/div/div[1]/div/h2")).getText().contains("1"));
        assertTrue(driver.findElement(By.xpath("//*[@id=\"CartPopup\"]/div/div[2]/div[4]")).getText().contains(name));
    }

    @Test
    public void seleniumAddCartMultiple() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().setSize(new Dimension(1936, 1048));
        driver.findElement(By.cssSelector(".collection-product-card:nth-child(4) .link")).click();
        driver.findElement(By.cssSelector(".lb-qty-selector-tier-content:nth-child(1) > .lb-qty-selector-discount-info-wrapper")).click();
        driver.findElement(By.cssSelector(".lb-qty-selector-atc-button")).click();
        Thread.sleep(2000);
        {
            WebElement element = driver.findElement(By.linkText("Plaćanje • 1,431.00"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"CartPopup\"]/div/div[1]/div/h2")).getText().contains("10"));
    }

    @Test
    public void seleniumAddCartOutofStock() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();

        // Navigate to the page
        driver.findElement(By.cssSelector(".cards-grid__item:nth-child(2) > .cards-grid__link")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#Facet-3-template--16504047435952__product-grid .list-menu__item:nth-child(2) > .facet-checkbox")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".collection-product-card:nth-child(1) .link")).click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nonClickableElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/section[1]/section/div/div[2]/div[2]/div/div[7]/lb-vd-qty-selector/button")));
        nonClickableElement.click();
        driver.findElement(By.xpath("/html/body/div[1]/sticky-header/header/div[1]/div/div[2]/div/a")).click();
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"CartPopup\"]/div/div[1]/div")).getText().contains("Cart • 0"));
    }
}
