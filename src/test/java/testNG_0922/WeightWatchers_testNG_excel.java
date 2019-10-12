package testNG_0922;

import Reusable_information.Reusable_Library;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WeightWatchers_testNG_excel {

    WebDriver driver;
    Workbook readableFile;
    WritableWorkbook writableFile;
    Sheet readableSheet;
    WritableSheet wSheet;
    int rows;

    @BeforeSuite
    public void openBrowser() throws IOException, BiffException {
        driver = Reusable_Library.createDriver();
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\AI14data.xls"));
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\AI14Results.xls"), readableFile);
        readableSheet = readableFile.getSheet(0);
        wSheet = writableFile.getSheet(0);
        rows = wSheet.getRows();
    }

    @Test(priority = 1)
    public void clickOnStudio() throws InterruptedException, WriteException {
        for(int i = 1; i < rows; i++) {
            driver.navigate().to("https://www.weightwatchers.com");
            Thread.sleep(2000);
            String titleResult = driver.getTitle() == "Weight Loss Program, Recipes & Help | Weight Watchers" ? "Title Matches" : "Titles don't match";
            System.out.println(titleResult);

            Reusable_Library.mouseClick(driver, "//*[@class='find-a-meeting']", "studioClick");
            Thread.sleep(2000);
            String titleResult2 = driver.getTitle() == "Find a Studio & Meeting Near You |" ? "Title Matches" : "Titles don't match";
            System.out.println(titleResult2);
            Reusable_Library.sendKey(driver, "//*[@placeholder='Enter location']", wSheet.getCell(0,i).getContents(), "sendingLocation");
            Thread.sleep(500);
            Reusable_Library.mouseClick(driver, "//*[@spice='SEARCH_BUTTON']", "clickingSearch");
            String text = Reusable_Library.captureTextByIndex(driver, "//*[@class='meeting-location']", 0, "firstResult");
            ArrayList<String> lines = new ArrayList<String>(Arrays.asList(text.split("\\r?\\n")));
            String resultAndDistance = lines.get(0) + " And it is " + lines.get(1) + " away.";
            Thread.sleep(1000);
            driver.findElements(By.xpath("//*[@class='meeting-location']")).get(0).click();
            Thread.sleep(500);
            String hoursOfOperation = null;
            try {
                //hoursOfOperation = Reusable_Library.CaptureText(driver, "//*[contains(@class,'hours-list--currentday')]", "operations");
                Thread.sleep(1000);
                hoursOfOperation = driver.findElements(By.xpath("//*[contains(@class,'currentday')]")).get(0).getText();
            } catch (Exception err) {
                hoursOfOperation = "This location isn't open today";
            }
            Label label1 = new Label(1, i, resultAndDistance);
            wSheet.addCell(label1);

            Label label2 = new Label(2, i, hoursOfOperation);
            wSheet.addCell(label2);
        }
    }

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {

        writableFile.write();
        writableFile.close();
        readableFile.close();
        driver.quit();
    }
}
