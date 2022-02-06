import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Task_6 {
    WebDriver driver = null;

    @BeforeTest
    public void preconditions() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(priority = 1)
    public void logIn() {
        //WebElements
        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[contains(@name,'password')]"));
        WebElement logInButton = driver.findElement(By.id("login-button"));

        //Actions
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        logInButton.click();
    }

    @Test(priority = 2)
    public void checkTheCart() {
        //WebElements
        WebElement product = driver.findElement(By.cssSelector("#item_4_title_link"));
        WebElement price = driver.findElement(By.xpath("//div[@class='inventory_list']/div[1]//div[2]//div[@class='inventory_item_price']"));
        WebElement addToCart = driver.findElement(By.xpath("//button [@id='add-to-cart-sauce-labs-backpack' and @name='add-to-cart-sauce-labs-backpack']"));
        WebElement shoppingCart = driver.findElement(By.cssSelector(".shopping_cart_container"));

        //expected results List
        List<String> expectedResult = new ArrayList<String>() {{
            add(product.getText());
            add(price.getText());
        }};

        //Actions
        addToCart.click();
        shoppingCart.click();

        //WebElements
        WebElement itemName = driver.findElement(By.className("inventory_item_name"));
        WebElement itemPrice = driver.findElement(By.className("inventory_item_price"));

        //Results
        List<String> actualResult = new ArrayList<String>() {{
            add(itemName.getText());
            add(itemPrice.getText());
        }};
        Assert.assertEquals(expectedResult, actualResult);
    }

    @AfterTest
    public void postconditions() {
        driver.close();
        driver.quit();
    }
}
