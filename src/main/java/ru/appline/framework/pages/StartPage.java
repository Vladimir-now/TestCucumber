package ru.appline.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {

    @FindBy(xpath = "//button[@class=\"kitt-cookie-warning__close\"]")
    private WebElement cookieButton;

    @FindBy(xpath = "//ul[contains(@class, \"kitt-top-menu__list\")]//label")
    private List<WebElement> menuPanelList;

    @FindBy(xpath = "//li[contains(@class, \"item_opened\")]//a")
    private List<WebElement> subMenuList;

    private void closeCookie() {
        clickElement(cookieButton);
    }

    public StartPage selectMenu (String nameMenu) {
        closeCookie();
        clickElement(findItem(menuPanelList, nameMenu));
        return this;
    }

    public MortgageFormPage selectSubMenu (String nameSubMenu) {
        clickElement(findItem(subMenuList, nameSubMenu));
        return app.getMortgageFormPage();
    }
}
