package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

    public static void main(String[] args) {

        WebDriver wd = new ChromeDriver();

        wd.manage().window().maximize();

        wd.get("https://test.fieldforceconnect.com/");

        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(15));

      
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("username")));

        wd.findElement(By.name("username")).sendKeys("diyanemade68@gmail.com");

        wd.findElement(By.name("password")).sendKeys("DiyaNemade");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));

        wd.findElement(By.cssSelector("button[type='submit']")).click();

    }
}