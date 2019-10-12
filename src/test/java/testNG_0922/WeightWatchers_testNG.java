package testNG_0922;

import Reusable_information.Abstract_Class;
import Reusable_information.Reusable_Library;
import jxl.write.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WeightWatchers_testNG extends Abstract_Class {

    @Test
    public void captureOpHour() throws InterruptedException, IOException {
        driver.navigate().to("https://www.weightwatchers.com/us");
        String titleResult = driver.getTitle() == "Weight Loss Program, Recipes & Help | Weight Watchers" ? "Title Matches" : "Titles don't match";
        System.out.println(titleResult);
        mouseClick(driver, "//*[@class='find-a-meeting']", "studioClick", logger);
        Thread.sleep(2000);
        String titleResult2 = driver.getTitle() == "Find a Studio & Meeting Near You |" ? "Title Matches" : "Titles don't match";
        System.out.println(titleResult2);
        sendKey(driver,"//*[@placeholder='Enter location']","11218","sendingLocation", logger);
        Thread.sleep(500);
        mouseClick(driver,"//*[@spice='SEARCH_BUTTON']","clickingSearch", logger);
        String text = Reusable_Library.captureTextByIndex(driver,"//*[@class='meeting-location']",0,"firstResult");
        ArrayList<String> lines = new ArrayList<String>(Arrays.asList(text.split("\\r?\\n")));
        String resultAndDistance = lines.get(0) + " And it is " +lines.get(1) + " away.";
        Thread.sleep(1000);
        driver.findElements(By.xpath("//*[@class='meeting-location']")).get(0).click();
        Thread.sleep(500);
        String hoursOfOperation = CaptureText(driver, "//*[contains(@class,'hours-list--currentday')]", "operations", logger);
        System.out.println(resultAndDistance);
        System.out.println(hoursOfOperation);
    }

}
