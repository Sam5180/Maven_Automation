package Reusable_information;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class Abstract_Class extends Reusable_Library_Loggers{
    public static WebDriver driver = null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static Workbook readableFile;
    public static WritableWorkbook writableFile;
    public static Sheet readableSheet;
    public static WritableSheet wSheet;
    public static int rows;

    @BeforeSuite
    public void openBrowser() throws IOException, BiffException {
        report = new ExtentReports("src/main/java/Report_Folder/Automation_"+getDateTime()+".html", true);
        logger = report.startTest("Yahoo stuff");
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\AI16data.xls"));
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\AI16Results.xls"), readableFile);
        readableSheet = readableFile.getSheet(0);
        wSheet = writableFile.getSheet(0);
        rows = wSheet.getRows();
    }

    @Parameters("Browser")
    @BeforeMethod
    public static void captureTestName(Method methodName, String Browser){
        if(Browser.equalsIgnoreCase("Firefox")){
            System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if (Browser.equalsIgnoreCase("Chrome")) {
            driver = createDriver();
        }
        logger = report.startTest(methodName.getName() + "--" + Browser);
        logger.log(LogStatus.INFO, "Automation Test Scenario Started....");
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public static void endTest(){
        report.endTest(logger);
        logger.log(LogStatus.INFO, "Automation Test Scenario ended....");
    }

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {
        writableFile.write();
        writableFile.close();
        readableFile.close();
        report.flush();
        report.close();
        driver.quit();
    }
}
