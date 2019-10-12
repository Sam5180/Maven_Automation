package Reusable_information;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reusable_Library {

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

    public static void mouseHover(WebDriver driver, String locator, String elementName){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions mouseMove = new Actions(driver);
        try {
            //System.out.println("Hovering to Element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            mouseMove.moveToElement(element).perform();
        } catch (Exception err){
            System.out.println("Unable to hover to element " + elementName + " --" + err);
        }
    }

    public static void mouseClick(WebDriver driver, String locator, String elementName){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
        } catch (Exception err){
            System.out.println("Unable to click on element " + elementName + " --" + err);
        }
    }
    public static void sendKey(WebDriver driver, String locator, String userValue, String elementName){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            //System.out.println("Entering value on element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            element.clear();
            element.sendKeys(userValue);
        } catch (Exception err){
            System.out.println("Unable to enter to element " + elementName + " --" + err);
        }
    }

    public static String CaptureText(WebDriver driver, String locator, String elementName){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String text = null;
        try {
            //System.out.println("capturing text of Element " + elementName);
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).getText();
        } catch (Exception err){
            System.out.println("Unable to capture text of element " + elementName + " --" + err);
        }
        return text;
    }
    public static String captureTextByIndex(WebDriver driver, String locator, int index, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        String text = null;
        try{
            //System.out.println("Capturing text on element " + elementName);
            text = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index).getText();
        } catch (Exception err) {
            System.out.println("Unable to capture text on element " + elementName + " --" + err);
        }

        return text;
    }//end of getText method


    public static void dropDownByText(WebDriver driver, String locator, String userValue, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement element = null;
        try{
            //System.out.println("Selecting value " + userValue + " from element " + elementName);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            Select dDown = new Select(element);
            dDown.selectByVisibleText(userValue);
        } catch (Exception err) {
            System.out.println("Unable to select value from element " + elementName + " --" + err);
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
