import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Runner {

    @Test
    public void demoTest(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.wikipedia.org");


        By searchBar = By.id("searchInput");
        driver.findElement(searchBar).sendKeys("Endava");

        By suggestionsSearches = By.cssSelector(".suggestions-dropdown a");

        //Implicit Waits - Telling the browser to wait
        //If Element is there, then just go a head
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Explicit waits
        //Telling to wait to a certain condition happens
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(suggestionsSearches, 1));

        driver.findElements(suggestionsSearches).get(0).click();

        driver.quit();
    }

}
