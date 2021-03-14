package pages;



import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.apache.logging.log4j.LogManager.getLogger;


public class LandingPage extends BasePage {
    private final By searchBar = By.className("site-search__controls__input");
    private final By signUp = By.cssSelector(".site-header__nav__link[href='/signup']");
    private final By locationBanner = By.className("intl-settings-nag__close");



    private static final Logger log = getLogger(LandingPage.class.getName());

    public LandingPage(WebDriver driver){
        super(driver);
    }

    public SignUpPage signUpPage(){
        log.info("Clicking SignUp link page");
        driver.findElement(signUp).click();

        log.info("Waiting for the location Banner to close");
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(locationBanner));
        if (driver.findElement(locationBanner).isDisplayed()){
            driver.findElement(locationBanner).click();
        }
        return new SignUpPage(driver);
    }


    private final By resultItemsList = By.cssSelector("h4[class='grid-card__title']");
    //private final By readItems = By.xpath("//li[contains(@class,'sortable-tile sortable-tile--4up-order')]");
    private final By readItems = By.className("faceted-grid__main");
    private final By sortableList = By.cssSelector("ul[class='sortable-tiles']");


    public String seachItem(String item) {
        log.info("Start method Search Item");
        driver.findElement(searchBar).sendKeys(item);
        driver.findElement(searchBar).sendKeys(Keys.RETURN);

        //List<WebElement> list = driver.findElements(filtereItemsList);
        //esperar a que se cargue elementos con texto

        String textf = "Fender Stratocaster";

        WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.presenceOfElementLocated(filtereItemsList));
        //wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(readItems, 10));
        wait.until(ExpectedConditions.presenceOfElementLocated(readItems));
        //wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(filtereItemsList), "Fender Stratocaster"));

        int Numberelement = driver.findElements(resultItemsList).size();
        int counter=0;
        for (int i=0; i < Numberelement; i++) {
            log.info("Text: " + driver.findElements(resultItemsList).get(i).getText());
            String strValidation = driver.findElements(resultItemsList).get(i).getText();
            if (strValidation.contains("Fender Stratocaster")){
                counter = counter + i;
                break;
            }

        }
        log.info("counter es: " + counter);


        String validationItem  = driver.findElements(resultItemsList).get(counter).getText();
        log.info("Item Text is: " + validationItem);
        log.info("Validation - Items contains 'Fender Stratocaster' ->" + validationItem.contains("Fender Stratocaster"));

        return validationItem;
    }


    public void search(String query) {
        log.info("Start method Search Item");
        driver.findElement(searchBar).sendKeys(query);
        driver.findElement(searchBar).sendKeys(Keys.RETURN);
    }
}
