package ru.appline.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.appline.framework.utils.PropConst.PATH_CHROME_DRIVER;


public class DriverManager {

    private static WebDriver driver;

    private static PropertiesManager properties = PropertiesManager.getTestPropManager();

    private DriverManager() {
    }

    private static void initDriver() {
                System.setProperty("webdriver.chrome.driver", properties.getProperty(PATH_CHROME_DRIVER));
                driver = new ChromeDriver();
        }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
    }
}
