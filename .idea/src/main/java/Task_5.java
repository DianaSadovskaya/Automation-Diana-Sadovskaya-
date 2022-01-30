import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class Task_5 {
    WebDriver driver = null;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
    }

    /*
    Написать 3 теста по сценарию ниже с различными данными и вариантами.
Открыть сайт https://masterskayapola.ru/kalkulyator/laminata.html
Ввести параметры для расчета.
Нажать на кнопку ‘Рассчитать’.
Проверить полученные значения.
Закрыть окно браузера.

     */
    @Test(dataProvider = "data")
    public void calculator(String width, String height, String laminateWidth, String laminateHeight,
                           String pack, String priceOf, String biasOf, String wallDistance, List <String> expectedData) {
        driver.get("https://masterskayapola.ru/kalkulyator/laminata.html");
        WebElement roomWidth = driver.findElement(By.name("calc_roomwidth"));
        WebElement roomHeight = driver.findElement(By.name("calc_roomheight"));
        WebElement lamWidth = driver.findElement(By.name("calc_lamwidth"));
        WebElement lamHeight = driver.findElement(By.name("calc_lamheight"));
        WebElement inPack = driver.findElement(By.name("calc_inpack"));
        WebElement price = driver.findElement(By.name("calc_price"));

        WebElement setDirect = driver.findElement(By.name("calc_direct"));
        Select direct = new Select(setDirect);

        WebElement bias = driver.findElement(By.name("calc_bias"));
        WebElement wallDist = driver.findElement(By.name("calc_walldist"));
        WebElement calcBtn = driver.findElement(By.cssSelector("[value='Рассчитать']"));

        List<WebElement> results = driver.findElements(By.xpath("//div[@class='col-xs-12 col-sm-12 whiteback']//div[@class='col-xs-12 col-sm-12']"));

        //Actions
        delete(roomWidth);
        roomWidth.sendKeys(width);
        delete(roomHeight);
        roomHeight.sendKeys(height);
        delete(lamWidth);
        lamWidth.sendKeys(laminateWidth);
        delete(lamHeight);
        lamHeight.sendKeys(laminateHeight);
        delete(inPack);
        inPack.sendKeys(pack);
        delete(price);
        price.sendKeys(priceOf);
        direct.selectByValue("tow");
        delete(bias);
        bias.sendKeys(biasOf);
        delete(wallDist);
        wallDist.sendKeys(wallDistance);
        calcBtn.click();

        //Results
        List<String> actualData = new ArrayList<>() {{
            results.forEach((element) -> add(element.getText()));
        }};
        Assert.assertEquals(actualData, expectedData);
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {"10", "8", "1000", "1000", "25", "10000", "200", "5", new ArrayList<String>() {{
                    add("Площадь укладки: 79.82 м2.");
                    add("Кол-во панелей: 82 шт.");
                    add("Кол-во упаковок: 4 шт.");
                    add("Стоимость: 1000000 руб.");
                    add("Остатки: 18 шт.");
                    add("Отрезки: 6 шт.");
                }}},
                {"3", "3", "100", "200", "100", "700", "20", "20", new ArrayList<String>() {{
                    add("Площадь укладки: 8.76 м2.");
                    add("Кол-во панелей: 450 шт.");
                    add("Кол-во упаковок: 5 шт.");
                    add("Стоимость: 7000 руб.");
                    add("Остатки: 50 шт.");
                    add("Отрезки: 13 шт.");
                }}},
                {"11", "10", "2000", "500", "70", "3590", "2", "2", new ArrayList<String>() {{
                    add("Площадь укладки: 109.92 м2.");
                    add("Кол-во панелей: 120 шт.");
                    add("Кол-во упаковок: 2 шт.");
                    add("Стоимость: 502600 руб.");
                    add("Остатки: 20 шт.");
                    add("Отрезки: 39 шт.");
                }}}
        };
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public void delete(WebElement webElement) {
        webElement.click();
        while (webElement.getAttribute("value").length() != 0) {
            webElement.sendKeys(Keys.BACK_SPACE);


        }


    }


}

