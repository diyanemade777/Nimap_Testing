package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest {

    @DataProvider(name = "customerData")
    public Object[][] customerData() {

        return new Object[][] {
            {"Jatin Gidh", "12345", "9876543210", "jatingidh04@gmail.com"},
            {"Sarvesh Bankar", "12346", "9876543211", "bankarsarvesh@gmail.com"}
        };
    }

    @Test(dataProvider = "customerData")
    public void addCustomer(String customerName, String refNo,
                            String mobile, String email) throws InterruptedException {

        WebDriver wd = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(
                wd, Duration.ofSeconds(15));

        wd.manage().window().maximize();

        // Open website
        wd.get("https://test.fieldforceconnect.com/auth/login");

        // Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("username")))
                .sendKeys("diyanemade68@gmail.com");

        wd.findElement(By.name("password"))
                .sendKeys("DiyaNemade");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']")))
                .click();

        // Wait for Dashboard
        wait.until(ExpectedConditions.urlContains("dashboard"));

        // My Customers
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='My Customers']")))
                .click();

        // My Customer
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='My Customer']")))
                .click();

        // Wait for page to load
        Thread.sleep(2000);

        // Manage
        By manageButton = By.xpath(
                "//button[normalize-space()='Manage']");

        wait.until(ExpectedConditions.elementToBeClickable(
                manageButton));

        wd.findElement(manageButton).click();

        // New Customer
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='New Customer']")))
                .click();

        // Customer Name
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("LeadName")))
                .sendKeys(customerName);

        // Reference Number
        wd.findElement(By.name("RefNo"))
                .sendKeys(refNo);

        // Person Name
        wd.findElement(By.name("PersonName"))
                .sendKeys("Test Person");

        // Mobile Number
        wd.findElement(By.name("ContactNo"))
                .sendKeys(mobile);

        // Email
        wd.findElement(By.name("Email"))
                .sendKeys(email);

        // Location
        wd.findElement(By.name("PersonLocation"))
                .sendKeys("Mumbai");

        // Save
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[normalize-space()='Save']]")))
                .click();

        Thread.sleep(2000);

        // Validate customer
        boolean customerAdded =
                wd.getPageSource().contains(customerName);

        System.out.println(
                customerName + " - Customer Added: " + customerAdded);

        Assert.assertTrue(
                customerAdded,
                customerName + " was not added successfully");

        wd.quit();
    }
}