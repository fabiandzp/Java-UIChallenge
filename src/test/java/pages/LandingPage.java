package pages;



import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.logging.log4j.LogManager.getLogger;


public class LandingPage extends BasePage {
    private final By signUp = By.cssSelector(".site-header__nav__link[href='/signup']");
    private final By locationBanner = By.className("intl-settings-nag__close");



    private static final Logger log = getLogger(LandingPage.class.getName());

    public LandingPage(WebDriver driver){
        super(driver);
    }

    public SignUpPage signUpPage(){
        log.info("Clicking SignUp Page Link");
        driver.findElement(signUp).click();

        log.info("Waiting for the location Banner to close");
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(locationBanner));
        if (driver.findElement(locationBanner).isDisplayed()){
            driver.findElement(locationBanner).click();
        }
        //driver.findElements(signUp).get(0).click();


        //driver.findElement(searchBar).sendKeys("Endava");
        return new SignUpPage(driver);
    }

    private final By signIn = By.cssSelector("li a[href='/signin']");
    private By usernameLogin = By.cssSelector("input[id='user_session_login']");
    private By passwordLogin = By.cssSelector("input[id='user_session_password']");
    public void login(){
        driver.findElement(signIn).click();

        driver.switchTo()

        log.info("Login Form");
        driver.findElement(usernameLogin).sendKeys("Kilian.Journet@live.com");
        driver.findElement(usernameLogin).sendKeys("Everest.!");
        //driver.findElement(login).click();


    }







}
