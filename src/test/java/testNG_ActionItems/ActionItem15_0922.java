package testNG_ActionItems;

import Reusable_information.Reusable_Library;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.JsonOutput;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ActionItem15_0922 {

    WebDriver driver;
    Workbook readableFile;
    WritableWorkbook writableFile;
    Sheet readableSheet;
    WritableSheet wSheet;
    int rows;

    @BeforeSuite
    public void createBrowser() throws IOException, BiffException {
        driver = Reusable_Library.createDriver();
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\AI15data.xls"));
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\AI15Results.xls"), readableFile);
        readableSheet = readableFile.getSheet(0);
        wSheet = writableFile.getSheet(0);
        rows = wSheet.getRows();
    }

    @Test
    public void expressCheckout() throws InterruptedException, WriteException {
        for(int i=1;i<rows;i++) {
            driver.navigate().to("https://www.express.com");
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            Thread.sleep(2000);
            String titleResult = driver.getTitle() == "Men’s and Women’s Clothing" ? "Title Matches" : "Titles don't match";
            System.out.println(titleResult);
            Reusable_Library.mouseHover(driver, "//*[@href='/womens-clothing']", "WomenLink");
            Thread.sleep(500);
            Reusable_Library.mouseHover(driver, "//*[@href='/womens-clothing/dresses/cat550007']", "DressesLink");
            Thread.sleep(200);
            Reusable_Library.mouseClick(driver, "//*[@href='/womens-clothing/dresses/jumpsuits-rompers/cat320051']", "JumperLink");
            Thread.sleep(2000);
            jse.executeScript("scroll(0,400);");
            Reusable_Library.mouseClick(driver, "//*[@alt='v-neck tie waist jumpsuit']", "1stLink");
            Thread.sleep(500);
            String size = readableSheet.getCell(0, i).getContents().toLowerCase();
            Reusable_Library.mouseClick(driver, "//*[@value='" + size + "']", "selectSize");
            Reusable_Library.mouseClick(driver, "//*[contains(text(),'Add to Bag')]", "Addtobag");
            Thread.sleep(1000);
            Reusable_Library.mouseClick(driver, "//*[@href='/check-out/basket.jsp']", "checkoutButton");
            String quantity = readableSheet.getCell(1, i).getContents();
            Reusable_Library.dropDownByText(driver, "//*[@id='qdd-0-quantity']", quantity, "QuantityCheck");
            Reusable_Library.mouseClick(driver, "//*[@id='continue-to-checkout']", "checkoutButton");
            Reusable_Library.mouseClick(driver, "//*[contains(text(),'Continue as Guest')]", "checkOut2");
            Reusable_Library.sendKey(driver, "//*[@name='firstname']", wSheet.getCell(2, i).getContents(), "first");
            Reusable_Library.sendKey(driver, "//*[@name='lastname']", wSheet.getCell(3, i).getContents(), "last");
            Reusable_Library.sendKey(driver, "//*[@name='email']", wSheet.getCell(4, i).getContents(), "email");
            Reusable_Library.sendKey(driver, "//*[@name='confirmEmail']", wSheet.getCell(4, i).getContents(), "confirm");
            Reusable_Library.sendKey(driver, "//*[@name='phone']", wSheet.getCell(5, i).getContents(), "phone");
            Reusable_Library.mouseClick(driver, "//*[@type='submit']", "submit");
            Thread.sleep(2000);
            Reusable_Library.mouseClick(driver, "//*[@type='submit']", "submit");
            Thread.sleep(1000);
            Reusable_Library.sendKey(driver, "//*[@name='shipping.line1']", wSheet.getCell(6, i).getContents(), "address");
            Reusable_Library.sendKey(driver, "//*[@name='shipping.postalCode']", wSheet.getCell(7, i).getContents(), "zipcode");
            Reusable_Library.sendKey(driver, "//*[@name='shipping.city']", wSheet.getCell(8, i).getContents(), "city");
            String state = readableSheet.getCell(9, i).getContents();
            Reusable_Library.dropDownByText(driver, "//*[@name='shipping.state']", state, "state");
            Reusable_Library.mouseClick(driver, "//*[@type='submit']", "submit");
            Thread.sleep(1000);
            Reusable_Library.sendKey(driver, "//*[@name='creditCardNumber']", wSheet.getCell(10, i).getContents(), "creditcard");
            Reusable_Library.mouseClick(driver, "//*[@type='submit']", "submit");
            String err = Reusable_Library.CaptureText(driver, "//*[contains(@class,'dOxMH')]", "error_message");
            Label label1 = new Label(11, i, err);
            wSheet.addCell(label1);
            driver.manage().deleteAllCookies();
            String[] apple = new String[]{"apple", "oranges","pineapple"};
            System.out.println(apple[0]);
        }
    }

    @AfterSuite
    public void captureResult() throws IOException, WriteException {
        writableFile.write();
        writableFile.close();
        readableFile.close();
        driver.quit();
    }




}
