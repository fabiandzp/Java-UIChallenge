package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.logging.log4j.LogManager.getLogger;

public class LoginPopUp extends BasePage {

    public LoginPopUp (WebDriver driver){
        super(driver);
    }

    private static final Logger log = getLogger(LandingPage.class.getName());

    private final By signIn = By.cssSelector("li a[href='/signin']");
    private final By popup = By.id("new_user_session");
    private final By usernameLogin = By.id("user_session_login");
    private final By passwordLogin = By.id("user_session_password");
    private final By btnLogin = By.cssSelector("input[value='Log In']");

    public void logIn(){
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();

        log.info("Clicking SignIn link page");
        driver.findElement(signIn).click();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(popup));

        log.info("Login Form");
        driver.findElement(usernameLogin).sendKeys("kilianjornet86@gmail.com");
        driver.findElement(passwordLogin).sendKeys("Everest123.!");
        driver.findElement(btnLogin).click();

        driver.switchTo().window(winHandleBefore);

    }


    private final By menu = By.className("site-header__nav__link__icon");
    private final By subMenu = By.cssSelector("div[data-track-clicks='SiteMenu']");
    private final By accountName = By.cssSelector("div[class='bdb-1 bd--offwhite padding-1 pt-0 weight-bold']");
    public String getAccountName(){
        log.info("Mouse Over Action Step");
        Actions action = new Actions(driver);
        WebElement we = driver.findElements(menu).get(4);
        action.moveToElement(we).perform();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(subMenu));

        String accountNameHolder = driver.findElement(accountName).getText();
        log.info("Check Name " + accountNameHolder);
        return accountNameHolder;
    }

    private final By filedLoginMessage = By.className("alert-box--red");
    public String loginFailed(){
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();

        log.info("Clicking SignIn link page");
        driver.findElement(signIn).click();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(popup));

        log.info("Login Form");
        driver.findElement(usernameLogin).sendKeys("kilianjornet86@gmail.com");
        driver.findElement(passwordLogin).sendKeys("wrongPasswrod.!");
        driver.findElement(btnLogin).click();

        String invalidLoginMessage = driver.findElement(filedLoginMessage).getText();

        driver.switchTo().window(winHandleBefore);
        return invalidLoginMessage;
    }

}
