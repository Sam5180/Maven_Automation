package Yahoo_Test_Page_Object;

import Reusable_information.Abstract_Class;
import Reusable_information.Reusable_Library_Loggers;
import Reusable_information.Reusable_Library_Loggers_POM;
import jxl.write.Label;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

import static Yahoo_Page_Object.Yahoo_Base_Class.yahoo_registrationPage;

public class Yahoo_Registration_TC extends Abstract_Class {

    @Test
    public static void registerAccount() throws IOException, InterruptedException, WriteException {
        for(int i=1;i<rows;i++) {
            driver.navigate().to("https://login.yahoo.com/account/create");
            yahoo_registrationPage().sendFirst(i);
            yahoo_registrationPage().sendLast(i);
            yahoo_registrationPage().sendEmail(i);
            yahoo_registrationPage().sendPass(i);
            yahoo_registrationPage().sendPhone(i);
            yahoo_registrationPage().sendMonth(i);
            yahoo_registrationPage().sendDay(i);
            yahoo_registrationPage().sendYear(i);
            yahoo_registrationPage().clickSubmit(i);
            yahoo_registrationPage().writeVerification(i);
        }
    }

}
