package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.PageManager;

import java.util.List;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class BasePage {

    protected PageManager app = PageManager.getPageManager();
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 15, 1000);
    protected JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }


    protected void scrollTo (WebElement element) {
        if (!element.isDisplayed()) javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void clickElement (WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected WebElement findItem (List<WebElement> items, String value) {
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(value)) return item;
        }
        Assert.fail("Элемент \"" + value + "\" не найден");
        return null;
    }

    private  void cleanField (WebElement field) {
        field.sendKeys(Keys.CONTROL,"a");
        field.sendKeys(Keys.DELETE);
    }

    protected void fillInputField (WebElement element, String value) {
        scrollTo(element);
        cleanField(element);
        element.sendKeys(value);
        if (!element.getAttribute("value").equals(value)) sleeper(500);
    }

    protected void switcher (WebElement element, String value) {
        if (!element.getAttribute("aria-checked").equals(value)) element.click();
    }

    protected int converter (String value) {
        return Integer.parseInt(value.replaceAll("[^0-9]",""));
    }

    protected void checkValue(WebElement element, String value, String nameField){
        int expected = converter(value);
        int actual = converter(element.getText());

        if (expected != actual) sleeper(500);
        Assert.assertEquals("В поле '" + nameField + "' значения не совпадают",
               expected, actual);
    }

    protected void checkValuePercent(WebElement element, String value, String nameField){
        if (!value.equals(element.getText())) sleeper(500);
        Assert.assertEquals("В поле '" + nameField + "' значения не совпадают",
                value, element.getText());
    }

    protected void sleeper(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
