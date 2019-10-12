package Reusable_information;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.reflect.misc.FieldUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static jxl.biff.BaseCellFeatures.logger;

public class Reusable_Library_Loggers {

    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {
        // String path = "C:\\Users\\sumon.kashem\\Desktop\\Screenshots\\";
        String path = "src\\main\\java\\Report_Folder\\ScreenShots\\";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots\\" + fileName);
        logger.log(LogStatus.FAIL, "", image);
    }

    //method to navigate using webdriver
    public static WebDriver createDriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "incognito","disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
    public static WebDriver navigate(String url){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "incognito","disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        driver.navigate().to(url);
        return driver;
    }

    public static void mouseHover(WebDriver driver, String locator, String elementName, ExtentTest logger) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions mouseMove = new Actions(driver);
        try {
            //System.out.println("Hovering to Element " + elementName);
            logger.log(LogStatus.INFO,"Hovering to element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            mouseMove.moveToElement(element).perform();
        } catch (Exception err){
            System.out.println("Unable to hover to element " + elementName + " --" + err);
            logger.log(LogStatus.FAIL, "unable to hover over " + elementName);
            getScreenshot(driver, logger, elementName);
        }

    }
    public static void mouseClick(WebDriver driver, String locator, String elementName, ExtentTest logger) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            logger.log(LogStatus.INFO,"Clicking on element " + elementName);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
        } catch (Exception err){
            System.out.println("Unable to click on element " + elementName + " --" + err);
            logger.log(LogStatus.FAIL, "Unable to click on "+elementName);
            getScreenshot(driver, logger, elementName);
        }
    }
    public static void sendKey(WebDriver driver, String locator, String userValue, String elementName, ExtentTest logger) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            //System.out.println("Entering value on element " + elementName);
            logger.log(LogStatus.INFO,"sending on element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            element.clear();
            element.sendKeys(userValue);
        } catch (Exception err){
            System.out.println("Unable to enter to element " + elementName + " --" + err);
            logger.log(LogStatus.FAIL, "Unable to enter to element "+elementName);
            getScreenshot(driver, logger, elementName);
        }
    }

    public static String CaptureText(WebDriver driver, String locator, String elementName, ExtentTest logger) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String text = null;
        try {
            //System.out.println("capturing text of Element " + elementName);
            logger.log(LogStatus.INFO,"Capturing text on element " + elementName);
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).getText();
        } catch (Exception err){
            System.out.println("Unable to capture text of element " + elementName + " --" + err);
            logger.log(LogStatus.FAIL, "Unable to capture text from "+elementName);
            getScreenshot(driver, logger, elementName);
        }
        return text;
    }
    public static String captureTextByIndex(WebDriver driver, String locator, int index, String elementName, ExtentTest logger) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        String text = null;
        try{
            //System.out.println("Capturing text on element " + elementName);
            logger.log(LogStatus.INFO,"Capturing text on element " + elementName);
            text = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index).getText();
        } catch (Exception err) {
            logger.log(LogStatus.FAIL, "unable to capture text from element " + elementName);
            System.out.println("Unable to capture text on element " + elementName + " --" + err);
            getScreenshot(driver, logger, elementName);
        }

        return text;
    }//end of getText method


    public static void dropDownByText(WebDriver driver, String locator, String userValue, String elementName, ExtentTest logger) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement element = null;
        try{
            //System.out.println("Selecting value " + userValue + " from element " + elementName);
            logger.log(LogStatus.INFO,"Selecting value " + userValue + " from element " + elementName);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            Select dDown = new Select(element);
            dDown.selectByVisibleText(userValue);
        } catch (Exception err) {
            logger.log(LogStatus.FAIL, "unable to select from dropdown "+ elementName);
            System.out.println("Unable to select value from element " + elementName + " --" + err);
            getScreenshot(driver, logger, elementName);
        }
    }

    public static String getDateTime() {
        SimpleDateFormat sdfDateTime;
        String strDateTime;
        sdfDateTime = new SimpleDateFormat("yyyyMMdd'_'HHmmss'_'SSS");
        Date now = new Date();
        strDateTime = sdfDateTime.format(now);
        return strDateTime;
    }
}
