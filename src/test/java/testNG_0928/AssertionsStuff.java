package testNG_0928;

import Reusable_information.Reusable_Library;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsStuff {
    WebDriver driver = null;

    @BeforeSuite
    public void openBorwser() {
        driver = Reusable_Library.createDriver();
    }

    @Test
    public void googleSearch() {
        driver.navigate().to("https://www.google.com");
        //using Hard assert
        //Assert.assertEquals(driver.getTitle(),"google");
        //using soft assert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(),"google");
        Reusable_Library.sendKey(driver,"//*[@name='q']","Cars","searchField");
        //to catch an exception
        softAssert.assertAll();
    }

    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }
}
