import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationTest extends BaseTest{

    @Test
    public void navigationInternal() throws InterruptedException {

        driver.get(baseUrl);
        driver.manage().window().maximize();

        Thread.sleep(1000);

        WebElement aboutUs = driver.findElement(By.xpath("//*[@id=\"shopify-section-sections--16504047927472__footer\"]/footer/div[1]/div/div/div[5]/ul/li[1]/a"));
        js.executeScript("arguments[0].scrollIntoView();", aboutUs);
        Thread.sleep(1000);
        aboutUs.click();

        assertEquals(baseUrl + "pages/o-nama", driver.getCurrentUrl());
        Thread.sleep(1000);

        WebElement logo = driver.findElement(By.xpath("//*[@id=\"shopify-section-sections--16504047960240__header\"]/sticky-header/header/div[1]/div/a"));
        logo.click();

        assertEquals(baseUrl, driver.getCurrentUrl());
    }

    @Test
    public void navigationExternal() throws InterruptedException {

        driver.get(baseUrl);
        driver.manage().window().maximize();

        Thread.sleep(1000);

        WebElement instagram = driver.findElement(By.xpath("//*[@id=\"shopify-section-sections--16504047927472__footer\"]/footer/div[2]/div/div/div/div[3]/ul/li[2]/a"));
        js.executeScript("arguments[0].scrollIntoView();", instagram);
        Thread.sleep(1000);

        // don't open in new tab
        js.executeScript("arguments[0].setAttribute('target', '_self');", instagram);
        instagram.click();

        assertEquals("https://www.instagram.com/giga.doo/", driver.getCurrentUrl());

        Thread.sleep(4000);

        driver.navigate().back();
        Thread.sleep(1000);
        assertEquals(baseUrl, driver.getCurrentUrl());
    }
}
