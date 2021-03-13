
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.Categories;
import pages.LandingPage;
import pages.ResultsPage;
import pages.SignUpPage;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class Runner extends Hooks{

    private static final Logger log = getLogger(Runner.class.getName());

    @Test
    public void register(){
        LandingPage landing = new LandingPage(driver);

        log.info("Calling SignUp");
        SignUpPage signUpPage = landing.signUpPage();

        log.info("Filling the SignUp Form");
        signUpPage.signUpForm();

        By bannerText = signUpPage.getBannerText();
        String texts = driver.findElement(bannerText).getAttribute("data-success-message");

        log.info("Banner Text " + texts);
        assertThat("Attribute contains 'We just sent you an email!'", texts,
                equalTo("We just sent you an email! Please check your inbox and spam folder in a few minutes."));

    }

    @Test
    public void logIn(){
        LandingPage landing = new LandingPage(driver);

        log.info("Calling login fancy box");
        landing.login();

        log.info("Getting the Account Name Holder");
        assertThat("Attribute contains 'Kilian Jornet'", landing.getAccountName(),
                equalTo("Kilian Jornet"));

    }

    @Test
    public void loginFailed(){
        LandingPage landing = new LandingPage(driver);

        log.info("Calling login fancy box");
        landing.loginFailed();

        //Pendiente Validacion
    }

    @Test
    public void search(){
        LandingPage landing = new LandingPage(driver);

        log.info("Calling login fancy box");
        //landing.seachItem("Fender Stratocaster");

        assertThat("Element contains 'Fender Stratocaster'", landing.seachItem("Fender Stratocaster"),
                containsString("Fender Stratocaster"));
    }

    @Test
    public void filterPrice(){
        LandingPage landingPage = new LandingPage(driver);
        String query = "Gibson Les Paul";

        log.info("Calling search method");
        landingPage.search(query);

        ResultsPage resultsPage = new ResultsPage(driver);
        String minVal = "1200";
        String maxVal = "1200";
        By resultList = resultsPage.priceFilter(minVal, maxVal);

        int Numberelement = driver.findElements(resultList).size();
        int counter=0;
        for (int i=0; i < Numberelement; i++) {
            log.info("Text: " + driver.findElements(resultList).get(i).getText());
            String strValidation = driver.findElements(resultList).get(i).getText();
            if (strValidation.contains("Gibson Les Paul")){
                counter = counter + i;
                break;
            }

        }
        log.info("counter es: " + counter);

        String validationItem  = driver.findElements(resultList).get(counter).getText();
        log.info("Item Text contains: " + validationItem);
        log.info("Validation - Items contains 'Gibson Les Paul' ->" + validationItem.contains("Gibson Les Paul"));

        //Pendiente Asserion
    }


    @Test
    public void orderPriceHighToLow(){
        LandingPage landingPage = new LandingPage(driver);
        String query = "Condenser Microphones";

        log.info("Calling search method");
        landingPage.search(query);

        ResultsPage resultsPage = new ResultsPage(driver);
        By resultList = resultsPage.orderPriceHighToLow();

        int Numberelement = driver.findElements(resultList).size();
        int counter=0;
        for (int i=0; i < Numberelement; i++) {
            log.info("Text: " + driver.findElements(resultList).get(i).getText());
            String strValidation = driver.findElements(resultList).get(i).getText();
            if (strValidation.contains("Gibson Les Paul")){
                counter = counter + i;
                break;
            }

        }
        log.info("counter es: " + counter);

        String validationItem  = driver.findElements(resultList).get(counter).getText();
        log.info("Item Text contains: " + validationItem);
        log.info("Validation - Items contains 'Gibson Les Paul' ->" + validationItem.contains("Gibson Les Paul"));

        //Pendiente Asserion
    }

    @Test
    public void choseCategory(){
        Categories categories = new Categories(driver);

        log.info("Clicking Drums Category");
        categories.choseCategory();

        log.info("Getting the Header Title of Acoustic Drums Page");
        assertThat("Attribute contains 'Acoustic Drums'", categories.choseCategory(),
                equalTo("Acoustic Drums"));
    }



}
