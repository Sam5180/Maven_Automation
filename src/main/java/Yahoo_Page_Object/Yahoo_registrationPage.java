package Yahoo_Page_Object;

import Reusable_information.Abstract_Class;
import Reusable_information.Reusable_Library_Loggers_POM;
import com.relevantcodes.extentreports.ExtentTest;
import jxl.write.Label;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.io.IOException;

public class Yahoo_registrationPage extends Abstract_Class {
    ExtentTest logger = null;

    public Yahoo_registrationPage(WebDriver driver){
        super();
        PageFactory.initElements(driver, this);
        this.logger = super.logger;
    }

    @FindBy(xpath = "//*[@class='ureg-fname ' and @name='firstName']")
    public static WebElement firstName;
    @FindBy(xpath = "//*[@class='ureg-lname ' and @name='lastName']")
    public static WebElement lastName;
    @FindBy(xpath = "//*[@id='usernamereg-yid']")
    public static WebElement email;
    @FindBy(xpath = "//*[@name='password' and @placeholder='Password']")
    public static WebElement password;
    @FindBy(xpath = "//*[@type='tel' and @name='phone']")
    public static WebElement phone;
    @FindBy(xpath = "//*[@id='usernamereg-month']")
    public static WebElement month;
    @FindBy(xpath = "//*[@id='usernamereg-day']")
    public static WebElement day;
    @FindBy(xpath = "//*[@id='usernamereg-year']")
    public static WebElement year;
    @FindBy(xpath = "//*[@id='reg-submit-button']")
    public static WebElement submit;
    @FindBy(xpath = "//h1[@class='txt-align-center']")
    public static WebElement verification;

    public Yahoo_registrationPage sendFirst(int i) throws IOException, InterruptedException {
        String exlFirst = wSheet.getCell(0,i).getContents();
        Reusable_Library_Loggers_POM.userInput(driver, firstName, 0, exlFirst,logger,"fname");
        Thread.sleep(1000);
        return new Yahoo_registrationPage(driver);
    }
    public Yahoo_registrationPage sendLast(int i) throws IOException, InterruptedException {
        String exlLast = wSheet.getCell(1,i).getContents();
        Reusable_Library_Loggers_POM.userInput(driver, lastName, 0, exlLast,logger,"lname");
        Thread.sleep(1000);
        return new Yahoo_registrationPage(driver);
    }
    public Yahoo_registrationPage sendEmail(int i) throws IOException, InterruptedException {
        String exlEmail = wSheet.getCell(2,i).getContents();
        Reusable_Library_Loggers_POM.userInput(driver, email, 0, exlEmail,logger,"email");
        Thread.sleep(1000);
        return new Yahoo_registrationPage(driver);
    }
    public Yahoo_registrationPage sendPass(int i) throws IOException, InterruptedException {
        String exlPass = wSheet.getCell(3,i).getContents();
        Reusable_Library_Loggers_POM.userInput(driver, password, 0, exlPass,logger,"pass");
        Thread.sleep(1000);
        return new Yahoo_registrationPage(driver);
    }
    public Yahoo_registrationPage sendPhone(int i) throws IOException, InterruptedException {
        String exlPhone = wSheet.getCell(4,i).getContents();
        Reusable_Library_Loggers_POM.userInput(driver, phone, 0, exlPhone,logger,"phone");
        Thread.sleep(1000);
        return new Yahoo_registrationPage(driver);
    }
    public Yahoo_registrationPage sendMonth(int i) throws IOException, InterruptedException {
        String exlMonth = wSheet.getCell(5,i).getContents();
        Reusable_Library_Loggers_POM.dropDownByText(driver, month, 0,exlMonth,logger,"dropMonth");
        Thread.sleep(1000);
        return new Yahoo_registrationPage(driver);
    }
    public Yahoo_registrationPage sendDay(int i) throws IOException, InterruptedException {
        String exlDay = wSheet.getCell(6,i).getContents();
        Reusable_Library_Loggers_POM.userInput(driver, day, 0, exlDay,logger,"phone");
        Thread.sleep(1000);
        return new Yahoo_registrationPage(driver);
    }
    public Yahoo_registrationPage sendYear(int i) throws IOException, InterruptedException {
        String exlYear = wSheet.getCell(7,i).getContents();
        Reusable_Library_Loggers_POM.userInput(driver, year, 0, exlYear,logger,"phone");
        Thread.sleep(1000);
        return new Yahoo_registrationPage(driver);
    }
    public Yahoo_registrationPage clickSubmit(int i) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.click(driver, submit, 0,logger,"submit");
        return new Yahoo_registrationPage(driver);
    }
    public Yahoo_registrationPage writeVerification(int i) throws IOException, InterruptedException, WriteException {
        Thread.sleep(4000);
        driver.switchTo().frame("recaptcha-iframe");
        String verfText = driver.findElement(By.xpath("//*[@class='txt-align-center']")).getText();
        Label label1 = new Label(8, i, verfText);
        wSheet.addCell(label1);
        System.out.println(verfText);
        driver.switchTo().defaultContent();
        return new Yahoo_registrationPage(driver);
    }
}
