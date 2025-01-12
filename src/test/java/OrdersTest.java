import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdersTest extends BaseTest {

    @Test
    public void orderItemInfo() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/main/section[1]/div/div/div/div[1]/a")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/main/section/div/div/div[3]/div/ul/li[3]/div/a")).click();
        Thread.sleep(1000);

        String productTitle = driver.findElement(By.xpath("/html/body/main/section[1]/section/div/div[2]/div[2]/div/div[3]/h1")).getText();
        String productPrice = driver.findElement(By.xpath("/html/body/main/section[1]/section/div/div[2]/div[2]/div/div[4]/div[1]/dl/div[2]/dd[1]/span")).getText();

        System.out.println("Product title: " + productTitle);
        System.out.println("Product price: " + productPrice);

        driver.findElement(By.xpath("/html/body/main/section[1]/section/div/div[2]/div[2]/div/div[7]/product-form/form/div/div[2]/div/shopify-accelerated-checkout/shopify-buy-it-now-button/button")).click();
        Thread.sleep(1000);

        String orderTitle = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div[2]/div[2]/div/aside/div/section/div/div[1]/section/div[2]/div[2]/div/div[2]/div/p")).getText();
        String orderPrice = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div[2]/div[2]/div/aside/div/section/div/section/div[2]/div[2]/div[3]/div[2]/div/div/strong")).getText().split(" ")[1];

        System.out.println("Order title: " + orderTitle);
        System.out.println("Order price: " + orderPrice);

        assertEquals(productTitle, orderTitle);
        assertEquals(productPrice, orderPrice);

    }

    @Test
    public void deliveryPrice() throws InterruptedException {

        driver.get(BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(3000);

        driver.findElement(By.xpath("/html/body/main/section[1]/div/div/div/div[1]/a")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/main/section/div/div/div[3]/div/ul/li[3]/div/a")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/main/section[1]/section/div/div[2]/div[2]/div/div[7]/product-form/form/div/div[2]/div/shopify-accelerated-checkout/shopify-buy-it-now-button/button")).click();
        Thread.sleep(1000);

        driver.findElement(By.name("address1")).sendKeys("Francuske revolucije bb");
        Thread.sleep(1000);
        driver.findElement(By.name("postalCode")).sendKeys("71000");
        Thread.sleep(1000);
        driver.findElement(By.name("city")).sendKeys("Sarajevo" + Keys.ENTER);
        Thread.sleep(5000);

        String productPrice = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div[2]/div[2]/div/aside/div/section/div/section/div[2]/div[2]/div[1]/div[2]/span"))
                .getText()
                .split(" ")[1]
                .trim();

        String deliveryPrice = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div[2]/div[2]/div/aside/div/section/div/section/div[2]/div[2]/div[2]/div[2]/span"))
                .getText()
                .split(" ")[1]
                .trim();

        String totalPrice = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div[2]/div[2]/div/aside/div/section/div/section/div[2]/div[2]/div[3]/div[2]/div/div/strong"))
                .getText()
                .split(" ")[1]
                .trim();

        double productPriceValue = Double.parseDouble(productPrice);
        double deliveryPriceValue = Double.parseDouble(deliveryPrice);
        double totalPriceValue = Double.parseDouble(totalPrice);

        System.out.println("Product price: " + productPriceValue);
        System.out.println("Delivery price: " + deliveryPriceValue);
        System.out.println("Total price: " + totalPriceValue);

        assertEquals(totalPriceValue, productPriceValue + deliveryPriceValue);

    }
}
