package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginParameterized {

    public static void main(String[] args) {

        String[][] loginData = {
            {"diyanemade68@gmail.com", "DiyaNemade", "valid"},
            {"diya@gmail.com", "diya123", "invalid"}
        };

        for (String[] data : loginData) {

            WebDriver wd = new ChromeDriver();

            wd.manage().window().maximize();

            wd.get("https://test.fieldforceconnect.com/");

            WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(15));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("username")));

            wd.findElement(By.name("username"))
              .sendKeys(data[0]);

            wd.findElement(By.name("password"))
              .sendKeys(data[1]);

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[type='submit']")));

            wd.findElement(By.cssSelector("button[type='submit']"))
              .click();

            if (data[2].equals("valid")) {

                if (wd.getCurrentUrl().contains("dashboard")) {
                    System.out.println("Valid Login - PASS");
                } else {
                    System.out.println("Valid Login - FAIL");
                }

            } else {

                if (!wd.getCurrentUrl().contains("dashboard")) {
                    System.out.println("Invalid Login - PASS");
                } else {
                    System.out.println("Invalid Login - FAIL");
                }
            }

            wd.quit();
        }
    }
}