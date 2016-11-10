package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by shaun on 05/11/2016.
 * The purpose of this class is to implement explicit timers on selenium locators.
 * The class takes in a driver, a timeout value, and a locator and returns a boolean
 */
public class SeleniumTimers {
    private WebDriver driver;
    private static final int DEFAULT_TIMEOUT_VALUE = 5;

    //Constructor sets the driver
    public SeleniumTimers(WebDriver driver) {
        this.driver = driver;
        System.out.println("Instantiating SeleniumTimers class");
    }

    public boolean waitForElementByLocator(By locator ,Integer timerValue){
        //First of all check whether the timerValue was set in the instantiation of the class.
        //If not, then set a default value of 5 seconds.
        System.out.println("Executing method waitForElementByLocator with timer value of:" + timerValue);
        timerValue = timerValue != null ? timerValue : DEFAULT_TIMEOUT_VALUE;

        WebDriverWait wait = new WebDriverWait(driver, timerValue);
        try {
            //use presenceOfElementLocated since at this level of abstraction, we only care
            //that the element is in the DOM and not whether it is visible to the user (leave
            //that to the test case).
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException exception){
            System.out.println("about to return false because selenium.timeoutexception was thrown");
            return false;
        }
        System.out.println("about to return true");
        return true;

    }
}
