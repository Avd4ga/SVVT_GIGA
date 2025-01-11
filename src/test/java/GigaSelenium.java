import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GigaSelenium {

    private static WebDriver driver;
    private static String baseUrl;
    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Avdo1\\OneDrive\\Desktop\\Avdo\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1024,768");
        options.addArguments("--incognito");

        // Additional options to avoid detection
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");


       // options.addArguments("--headless");

        driver = new ChromeDriver(options);
        baseUrl = "https://giga.ba/";
    }


    @Test
    public void registration() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        Thread.sleep(2000);
        {
            WebElement element = driver.findElement(By.linkText("Prijava"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".header__account__register > span")).click();
        driver.findElement(By.id("RegisterForm-FirstName")).click();
        driver.findElement(By.id("RegisterForm-FirstName")).sendKeys("test");
        String name=driver.findElement(By.id("RegisterForm-FirstName")).getText();
        driver.findElement(By.id("RegisterForm-LastName")).click();
        driver.findElement(By.id("RegisterForm-LastName")).sendKeys("user");
        String last=driver.findElement(By.id("RegisterForm-LastName")).getText();
        String AccountName=name+" "+last;
        driver.findElement(By.id("RegisterForm-email")).click();
        driver.findElement(By.id("RegisterForm-email")).sendKeys("kalemab337@sfxeur.com");
        driver.findElement(By.id("RegisterForm-password")).sendKeys("SecurePass123");
        driver.findElement(By.cssSelector(".button--arrow:nth-child(1)")).click();
        driver.manage().window().maximize();
        Thread.sleep(60000);
        driver.findElement(By.cssSelector(".icon-account")).click();
        driver.findElement(By.id("RegisterForm-password")).click();
        assertEquals(driver.findElement(By.className("header__account__name")).getText(), AccountName);
    }

    @Test
    public void registrationFail() throws InterruptedException {
        driver.get("https://giga.ba/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        driver.findElement(By.cssSelector(".header__account__register > span")).click();
        driver.findElement(By.id("RegisterForm-FirstName")).click();
        driver.findElement(By.id("RegisterForm-FirstName")).sendKeys("test");
        driver.findElement(By.id("RegisterForm-LastName")).click();
        driver.findElement(By.id("RegisterForm-LastName")).sendKeys("user");
        driver.findElement(By.id("RegisterForm-email")).click();
        driver.findElement(By.id("RegisterForm-email")).sendKeys("avdovladavic@gmail.com");
        driver.findElement(By.id("RegisterForm-password")).click();
        driver.findElement(By.id("RegisterForm-password")).sendKeys("Metak2003");
        driver.findElement(By.cssSelector(".button--arrow:nth-child(1)")).click();
        Thread.sleep(3000);
        assertThat(driver.findElement(By.id("RegisterForm-email-error")).getText(), is("Ova e-adresa već je povezana s računom."));
    }

    @Test
    public void login() throws InterruptedException {
        driver.get("https://giga.ba/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        driver.findElement(By.linkText("Prijava")).click();
        driver.findElement(By.id("CustomerEmail")).click();
        driver.findElement(By.id("CustomerEmail")).sendKeys("lanolog879@kvegg.com");
        driver.findElement(By.id("CustomerPassword")).click();
        driver.findElement(By.id("CustomerPassword")).sendKeys("SecurePass123");

        driver.findElement(By.cssSelector(".customer__buttons:nth-child(6) > .button--primary")).click();
        Thread.sleep(6000);
        assertTrue(driver.getCurrentUrl().equals("https://giga.ba/account"));
    }

    @Test
    public void loginFail() throws InterruptedException {
        driver.get("https://giga.ba/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".header__icon--account > .icon")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Prijava")).click();
        driver.findElement(By.id("CustomerEmail")).click();
        driver.findElement(By.id("CustomerEmail")).sendKeys("lanolog87@kvegg.com");
        driver.findElement(By.id("CustomerPassword")).click();
        driver.findElement(By.id("CustomerPassword")).sendKeys("SecurePass123");

        driver.findElement(By.cssSelector(".customer__buttons:nth-child(6) > .button--primary")).click();

        Thread.sleep(6000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[2]/ul/li")).getText().contains("Incorrect email or password."));
    }

    @Test
    public void TestSearch() throws InterruptedException {
        driver.get("https://giga.ba/");
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
        driver.get("https://giga.ba/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.id("Search-In-Modal-1")).click();
        driver.findElement(By.id("Search-In-Modal-1")).sendKeys("xyz123");
        driver.findElement(By.cssSelector(".icon-search")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"shopify-section-template--16504047894704__main\"]/div/div[1]/div/div[2]/predictive-search/div/form/p")).getText().contains("Nema rezultata"));
    }

    @Test
    public void searchSuggestion() throws InterruptedException {
        driver.get("https://giga.ba/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.id("Search-In-Modal-1")).click();
        driver.findElement(By.id("Search-In-Modal-1")).sendKeys("lap");
        Thread.sleep(2000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"predictive-search-results-groups-wrapper\"]/div")).getText().toLowerCase().contains("lap"));
    }

    @Test
    public void filters() throws InterruptedException {
        driver.get("https://giga.ba/");
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
        driver.get("https://giga.ba/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement secondGridItem = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cards-grid__item:nth-child(2) > .cards-grid__link")));
        secondGridItem.click();

        WebElement priceFilterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Filter-Cijena-LTE\"]")));
        priceFilterInput.clear();
        priceFilterInput.sendKeys("68");
        priceFilterInput.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement productGrid = driver.findElement(By.xpath("//*[@id=\"product-grid\"]/li[1]/div/a"));
        String productText = productGrid.getText();

        String regex = "\\d+[.,]?\\d{0,2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(productText);

        while (matcher.find()) {
            String numberStr = matcher.group().replace(",", ".");
            double price = Double.parseDouble(numberStr);
            assertTrue(price <= 68.00, "Price found greater than 68.00: " + price);
        }
    }

    @Test
    public void filterClearFilters() throws InterruptedException {
        driver.get("https://giga.ba/");
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
        assertFalse(driver.getCurrentUrl().equals("https://giga.ba/collections/gaming-setovi?sort_by=best-selling&filter.v.price.gte=0.00&filter.v.price.lte=400.00"));


    }

    @Test
    public void seleniumAddCartSingle() throws InterruptedException {
        driver.get("https://giga.ba/");
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
        driver.get("https://giga.ba/");
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
        driver.get("https://giga.ba/");
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
