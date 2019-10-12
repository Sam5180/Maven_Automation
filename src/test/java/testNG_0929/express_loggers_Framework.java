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

public class express_loggers_Framework extends Abstract_Class {
    Workbook readableFile;
    WritableWorkbook writableFile;
    Sheet readableSheet;
    WritableSheet wSheet;
    int rows;


    @Test
    public void expressCheckout() throws InterruptedException, WriteException, IOException, BiffException {
        //starting your test case to the report
        logger = report.startTest("Express Checkout");
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\AI15data.xls"));
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\AI15Results.xls"), readableFile);
        readableSheet = readableFile.getSheet(0);
        wSheet = writableFile.getSheet(0);
        rows = wSheet.getRows();

        for(int i=1;i<2;i++) {
            //logger.log(LogStatus.INFO, "navigating to express");
            driver.navigate().to("https://www.express.com");
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            Thread.sleep(2000);
            String titleResult = driver.getTitle() == "Men’s and Women’s Clothing" ? "Title Matches" : "Titles don't match";
            System.out.println(titleResult);
            mouseHover(driver, "//*[@href='/womens-clothing']", "WomenLink", logger);
            Thread.sleep(500);
            mouseHover(driver, "//*[@href='/womens-clothing/dresses/cat550007']", "DressesLink", logger);
            Thread.sleep(200);
            mouseClick(driver, "//*[@href='/womens-clothing/dresses/jumpsuits-rompers/cat320051']", "JumperLink", logger);
            Thread.sleep(2000);
            jse.executeScript("scroll(0,400);");
            mouseClick(driver, "//*[@alt='v-neck tie waist jumpsuit']", "1stLink", logger);
            Thread.sleep(500);
            String size = readableSheet.getCell(0, i).getContents().toLowerCase();
            mouseClick(driver, "//*[@value='" + size + "']", "selectSize", logger);
            mouseClick(driver, "//*[contains(text(),'Add to Bag')]", "Addtobag", logger);
            Thread.sleep(1000);
            mouseClick(driver, "//*[text()='View Bag']", "checkoutButton", logger);
            String quantity = readableSheet.getCell(1, i).getContents();
            dropDownByText(driver, "//*[@id='qdd-0-quantity']", quantity, "QuantityCheck", logger);
            mouseClick(driver, "//*[@id='continue-to-checkout']", "checkoutButton", logger);
            Thread.sleep(1000);
            mouseClick(driver, "//*[contains(text(),'Continue as Guest')]", "checkOut2", logger);
            sendKey(driver, "//*[@name='firstname']", wSheet.getCell(2, i).getContents(), "first", logger);
            sendKey(driver, "//*[@name='lastname']", wSheet.getCell(3, i).getContents(), "last", logger);
            sendKey(driver, "//*[@name='email']", wSheet.getCell(4, i).getContents(), "email", logger);
            sendKey(driver, "//*[@name='confirmEmail']", wSheet.getCell(4, i).getContents(), "confirm", logger);
            sendKey(driver, "//*[@name='phone']", wSheet.getCell(5, i).getContents(), "phone", logger);
            mouseClick(driver, "//*[@type='submit']", "submit", logger);
            Thread.sleep(2000);
            mouseClick(driver, "//*[@type='submit']", "submit", logger);
            Thread.sleep(1000);
            sendKey(driver, "//*[@name='shipping.line1']", wSheet.getCell(6, i).getContents(), "address", logger);
            sendKey(driver, "//*[@name='shipping.postalCode']", wSheet.getCell(7, i).getContents(), "zipcode", logger);
            sendKey(driver, "//*[@name='shipping.city']", wSheet.getCell(8, i).getContents(), "city", logger);
            String state = readableSheet.getCell(9, i).getContents();
            dropDownByText(driver, "//*[@name='shipping.state']", state, "state", logger);
            mouseClick(driver, "//*[@type='submit']", "submit", logger);
            Thread.sleep(1000);
            sendKey(driver, "//*[@name='creditCardNumber']", wSheet.getCell(10, i).getContents(), "creditcard", logger);
            mouseClick(driver, "//*[@type='submit']", "submit", logger);
            String err = CaptureText(driver, "//*[contains(@class,'dOxMH')]", "error_message", logger);
            Label label1 = new Label(11, i, err);
            wSheet.addCell(label1);
            driver.manage().deleteAllCookies();
        }
        writableFile.write();
        writableFile.close();
        readableFile.close();

    }

}
