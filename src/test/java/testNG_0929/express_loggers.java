package testNG_0929;

import Reusable_information.Abstract_Class;
import Reusable_information.Reusable_Library;
import Reusable_information.Reusable_Library_Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class express_loggers {

    WebDriver driver;
    Workbook readableFile;
    WritableWorkbook writableFile;
    Sheet readableSheet;
    WritableSheet wSheet;
    int rows;
    ExtentReports report = null;
    ExtentTest logger = null;

    @BeforeSuite
    public void createBrowser() throws IOException, BiffException {
        driver = Reusable_Library.createDriver();
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\AI15data.xls"));
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\AI15Results.xls"), readableFile);
        readableSheet = readableFile.getSheet(0);
        wSheet = writableFile.getSheet(0);
        rows = wSheet.getRows();
        //define the path of the extent report folder
        report = new ExtentReports("src/main/java/Report_Folder/ExpressReport.html", true);
    }

    @Test
    public void expressCheckout() throws InterruptedException, WriteException, IOException {
        //starting your test case to the report
        logger = report.startTest("Express Checkout");

        for(int i=1;i<rows;i++) {
            logger.log(LogStatus.INFO, "navigating to express");
            driver.navigate().to("https://www.express.com");
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            Thread.sleep(2000);
            String titleResult = driver.getTitle() == "Men’s and Women’s Clothing" ? "Title Matches" : "Titles don't match";
            System.out.println(titleResult);
            Reusable_Library_Loggers.mouseHover(driver, "//*[@href='/womens-clothing']", "WomenLink", logger);
            Thread.sleep(500);
            Reusable_Library_Loggers.mouseHover(driver, "//*[@href='/womens-clothing/dresses/cat550007']", "DressesLink", logger);
            Thread.sleep(200);
            Reusable_Library_Loggers.mouseClick(driver, "//*[@href='/womens-clothing/dresses/jumpsuits-rompers/cat320051']", "JumperLink", logger);
            Thread.sleep(2000);
            jse.executeScript("scroll(0,400);");
            Reusable_Library_Loggers.mouseClick(driver, "//*[@alt='v-neck tie waist jumpsuit']", "1stLink", logger);
            Thread.sleep(500);
            String size = readableSheet.getCell(0, i).getContents().toLowerCase();
            Reusable_Library_Loggers.mouseClick(driver, "//*[@value='" + size + "']", "selectSize", logger);
            Reusable_Library_Loggers.mouseClick(driver, "//*[contains(text(),'Add to Bag')]", "Addtobag", logger);
            Thread.sleep(1000);
            Reusable_Library_Loggers.mouseClick(driver, "//*[@href='/check-out/basket.jsp']", "checkoutButton", logger);
            String quantity = readableSheet.getCell(1, i).getContents();
            Reusable_Library_Loggers.dropDownByText(driver, "//*[@id='qdd-0-quantity']", quantity, "QuantityCheck", logger);
            Reusable_Library_Loggers.mouseClick(driver, "//*[@id='continue-to-checkout']", "checkoutButton", logger);
            Thread.sleep(1000);
            Reusable_Library_Loggers.mouseClick(driver, "//*[contains(text(),'Continue as Guest')]", "checkOut2", logger);
            Reusable_Library_Loggers.sendKey(driver, "//*[@name='firstname']", wSheet.getCell(2, i).getContents(), "first", logger);
            Reusable_Library_Loggers.sendKey(driver, "//*[@name='lastname']", wSheet.getCell(3, i).getContents(), "last", logger);
            Reusable_Library_Loggers.sendKey(driver, "//*[@name='email']", wSheet.getCell(4, i).getContents(), "email", logger);
            Reusable_Library_Loggers.sendKey(driver, "//*[@name='confirmEmail']", wSheet.getCell(4, i).getContents(), "confirm", logger);
            Reusable_Library_Loggers.sendKey(driver, "//*[@name='phone']", wSheet.getCell(5, i).getContents(), "phone", logger);
            Reusable_Library_Loggers.mouseClick(driver, "//*[@type='submit']", "submit", logger);
            Thread.sleep(2000);
            Reusable_Library_Loggers.mouseClick(driver, "//*[@type='submit']", "submit", logger);
            Thread.sleep(1000);
            Reusable_Library_Loggers.sendKey(driver, "//*[@name='shipping.line1']", wSheet.getCell(6, i).getContents(), "address", logger);
            Reusable_Library_Loggers.sendKey(driver, "//*[@name='shipping.postalCode']", wSheet.getCell(7, i).getContents(), "zipcode", logger);
            Reusable_Library_Loggers.sendKey(driver, "//*[@name='shipping.city']", wSheet.getCell(8, i).getContents(), "city", logger);
            String state = readableSheet.getCell(9, i).getContents();
            Reusable_Library_Loggers.dropDownByText(driver, "//*[@name='shipping.state']", state, "state", logger);
            Reusable_Library_Loggers.mouseClick(driver, "//*[@type='submit']", "submit", logger);
            Thread.sleep(1000);
            Reusable_Library_Loggers.sendKey(driver, "//*[@name='creditCardNumber']", wSheet.getCell(10, i).getContents(), "creditcard", logger);
            Reusable_Library_Loggers.mouseClick(driver, "//*[@type='submit']", "submit", logger);
            String err = Reusable_Library_Loggers.CaptureText(driver, "//*[contains(@class,'dOxMH')]", "error_message", logger);
            Label label1 = new Label(11, i, err);
            wSheet.addCell(label1);
            driver.manage().deleteAllCookies();
        }
        report.endTest(logger);
    }

    @AfterSuite
    public void captureResult() throws IOException, WriteException {
        writableFile.write();
        writableFile.close();
        readableFile.close();
        //write back to the report
        report.flush();
        report.close();
        driver.quit();
    }

}
