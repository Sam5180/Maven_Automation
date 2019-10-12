package Yahoo_Page_Object;

import Reusable_information.Abstract_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Yahoo_Base_Class extends Abstract_Class {

    public Yahoo_Base_Class(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    //// Object for yahoo home page
    public static Yahoo_Homepage yahoo_homepage() {
        Yahoo_Homepage yahoo_homepage = new Yahoo_Homepage(driver);
        return yahoo_homepage;
    }

    //// Object for yahoo home page
    public static Yahoo_SearchResultPage yahoo_searchResultPage() {
        Yahoo_SearchResultPage yahoo_searchResultPage = new Yahoo_SearchResultPage(driver);
        return yahoo_searchResultPage;
    }

    //Object for yahoo registration
    public static Yahoo_registrationPage yahoo_registrationPage() {
        Yahoo_registrationPage yahoo_registrationPage = new Yahoo_registrationPage(driver);
        return yahoo_registrationPage;
    }

}
