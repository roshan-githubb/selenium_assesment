package Macys.com.macysSelenium;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Utility {

    public static WebElement getElement(WebDriver driver, String locatorType, String locatorValue,Boolean clickable) {
        WebElement element = null;
        By by;
        switch (locatorType.toLowerCase()) {
            case "id":
                by = By.id(locatorValue);
                break;
            case "name":
                by = By.name(locatorValue);
                break;
            case "classname":
                by = By.className(locatorValue);
                break;
            case "tagname":
                by = By.tagName(locatorValue);
                break;
            case "linktext":
                by = By.linkText(locatorValue);
                break;
            case "partiallinktext":
                by = By.partialLinkText(locatorValue);
                break;
            case "cssselector":
                by = By.cssSelector(locatorValue);
                break;
            case "xpath":
                by = By.xpath(locatorValue);
                break;
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }

        Duration duration = Duration.ofSeconds(3);
        if(clickable){
            element = new WebDriverWait(driver, duration)
                    .until(ExpectedConditions.elementToBeClickable(by));
        }else {
            element = new WebDriverWait(driver, duration)
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        }

        return element;
    }

}