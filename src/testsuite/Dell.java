package testsuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class Dell extends Utility {
    String baseUrl = "https://www.amazon.co.uk/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifySearchBox() throws InterruptedException {
        Thread.sleep(1000);
        //accept cookies
        clickOnElement(By.xpath("//a[contains(text(),'Continue without accepting')]"));
        ////Type "Dell Laptop" in the search box and press enter or click on search Button.
        sendTextToElement(By.xpath("//input[@id='twotabsearchtextbox']"), "Dell Laptop");
        //press enter
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
        //Click on the checkbox brand Dell on the left side.
        clickOnElement(By.xpath("(//i[@class='a-icon a-icon-checkbox'])[4]"));
        //Verify that the  30(May be different) products are displayed on the page.
        Thread.sleep(1000);
        verifyItem(30, countItem(By.xpath("//h2")));
        //Print all product names in the console.
        boolean productfound = false;
        while (!productfound) {
            List<WebElement> list = driver.findElements(By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']/a/span[@class='a-size-medium a-color-base a-text-normal']"));
            ArrayList<String> productList = new ArrayList<>();
            for (WebElement e : list) {
                productList.add(e.getText());
            }
            System.out.println(productList);

            Thread.sleep(1000);
        //Click on the product name 'Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3" FHD TOUCH'
            List<WebElement> listOfProducts = driver.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[@data-uuid]/div/div/div/div/div/div[2]/div/div/div/h2"));
            ArrayList<String> products = new ArrayList<>();
            for (WebElement f : listOfProducts) {
                products.add(f.getText());
            }
            System.out.println(products);

            for (String name : products) {
                if (name.equalsIgnoreCase("Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3\" FHD TOUCH")) {
                    clickOnElement(By.xpath("//span[contains(text(),'Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3\" FHD TOUCH')]"));
                    productfound = true;
                    break;
                }
            }
            if (!productfound && !products.contains("Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3\" FHD TOUCH")) {
                clickOnElement(By.xpath("//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']"));
            } else {
                break;
            }
        }
        //Verify the Product name 'Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3" FHD TOUCH'
        Thread.sleep(2000);
        verifyThis("Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3\" FHD TOUCH",By.xpath("//span[@id='productTitle']"));
}
    @After
    public void tearDown(){
        //closeBrowser();
    }
}
