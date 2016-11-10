package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import pageobjects.SeleniumTimers;

public class Login {

    private WebDriver driver;
    By loginFormLocator = By.id("login");
    By usernameLocator  = By.id("username");
    By passwordLocator  = By.id("password");
    By submitButton     = By.cssSelector("button");
    By successMessageLocator = By.cssSelector(".flash.success");
    By failureMessageLocator = By.cssSelector(".flash.error");
    SeleniumTimers wait;
    //Constructor
    public Login(WebDriver driver) {
        this.driver = driver;
        wait = new SeleniumTimers(driver);
        driver.get("http://the-internet.herokuapp.com/login");
        assertTrue("The login form is not present",
                driver.findElement(loginFormLocator).isDisplayed());
    }

    public void with(String username, String password) {
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(submitButton).click();
        //This click presses the submit button to logon, but there is no wait, or verification that the
        // next page has completed its load.
        // logically, it makes sense to ensure that the find element incorporates the wait since at this point
        // we do not know if the page will progress past the login screen, or represent with failure.
    }

    public Boolean successMessagePresent() {
        //WebElement nextPage = (new WebDriverWait(driver,5)).until(ExpectedConditions.presenceOfElementLocated(successMessageLocator));
        //return driver.findElement(successMessageLocator).isDisplayed();
        //
        //Note that by refactoring the wait (from above) into its own class we simplify the test code further
        //The wait object must be instantiated in the constructor so that the methods are
        //exposed for use in the rest of this pageObject.
        return wait.waitForElementByLocator(successMessageLocator,3);
    }

    public Boolean failureMessagePresent() {
        WebElement nextPage = (new WebDriverWait(driver,5)).until(ExpectedConditions.presenceOfElementLocated(failureMessageLocator));
        return driver.findElement(failureMessageLocator).isDisplayed();
    }
}