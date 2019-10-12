package Yahoo_Page_Object;

import Reusable_information.Abstract_Class;
import Reusable_information.Reusable_Library_Loggers_POM;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class Yahoo_Homepage extends Abstract_Class {

    ExtentTest logger=null;
    //create constructor to use driver and logger as a Page Object
    public Yahoo_Homepage(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
        this.logger = super.logger;
    }

    @FindBy(xpath = "//*[@id='uh-search-box']")
    public static WebElement searchKeyLocator;
    @FindBy(xpath = "//*[@id='uh-search-button']")
    public static WebElement searchIconLocator;


    public Yahoo_Homepage searchKey(String userInput) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.userInput(driver,searchKeyLocator,0,userInput,logger,"Search Field");
        return new Yahoo_Homepage(driver);
    }

    public Yahoo_Homepage clickOnSearchIcon() throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.click(driver, searchIconLocator, 0, logger, "Search Icon");
        return new Yahoo_Homepage(driver);
    }

}
