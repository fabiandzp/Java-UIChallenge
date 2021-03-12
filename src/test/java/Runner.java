
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.SignUpPage;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;
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

    }
}
