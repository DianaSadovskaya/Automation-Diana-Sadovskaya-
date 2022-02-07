package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

    public class Main {
        WebDriver driver = new ChromeDriver();

        @Test
        public void test1() {
            System.setProperty("webdriver.chrome.driver", "/Users/di.sadovskaya/IdeaProjects/Automation(Diana Sadovskaya)/src/test/resources/chromedriver");
            driver.get("http://the-internet.herokuapp.com/");
            driver.findElement(By.xpath("//div[text()='A/B Testing']")).click();
            driver.quit();
        }

    }