package testNG_0928;

import Reusable_information.Reusable_Library;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ActionItemClassTestNG {
    WebDriver driver;

    @BeforeSuite
    public void openBrowser() {
        driver = Reusable_Library.createDriver();
    }

    @Test
    public void yahooSearch() throws InterruptedException {
        driver.navigate().to("https://www.yahoo.com");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(),"Yahoo");
        List<WebElement> tabsCount = driver.findElements((By.xpath("//*[contains(@class,'Mstart(21px)')]")));
        System.out.println("The tabs count is " + tabsCount.size());
        Reusable_Library.sendKey(driver, "//*[@id='uh-search-box']","Nutrition","itemSearch");
        Reusable_Library.mouseClick(driver,"//*[@id='uh-search-button']","click");
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        //String result = Reusable_Library.captureTextByIndex(driver, "//*[@class='compPagination']",6,"getResult");
        WebElement element = driver.findElement(By.xpath("//*[@class='compPagination']"));
        String result = element.findElement(By.tagName("span")).getText();
        System.out.println(result);
        Thread.sleep(500);
        jse.executeScript("scroll(0, -1000);");
        Thread.sleep(1000);
        Reusable_Library.mouseClick(driver,"//*[contains(@href,'yahoo.com/search/images')]","images");
        List<WebElement> imageCount = driver.findElements((By.xpath("//*[contains(@data-bns,'API')]")));
        System.out.println("The tabs count is " + imageCount.size());
        Reusable_Library.mouseClick(driver,"//*[contains(@id,'login_signIn')]","signIn");
        Thread.sleep(2000);
        boolean elementState = driver.findElement(By.xpath("//*[@type='checkbox']")).isSelected();
        System.out.println("Is element selected? " + elementState);
        Reusable_Library.sendKey(driver, "//*[@class='phone-no ']","8888888888","username");
        Thread.sleep(1000);
        Reusable_Library.mouseHover(driver, "//*[contains(@class, 'orko-button') and @value='Next']", "Hovering");
        Thread.sleep(1000);
        Reusable_Library.mouseClick(driver,"//div[@class='login-box ']//input[contains(@class, 'orko-button') and @value='Next']","submitButton");
        String err = Reusable_Library.CaptureText(driver,"//*[@id='username-error']","errorMessage");
        System.out.println(err);
    }

    @AfterSuite
    public void closeBrowser(){
        driver.quit();
    }
}
