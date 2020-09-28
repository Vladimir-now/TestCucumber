package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.framework.managers.DriverManager;

public class MortgageFormPage extends BasePage {

    @FindBy(xpath = "//div[@data-label=\"Стоимость недвижимости\"]/input")
    private WebElement realtyPrice;

    @FindBy(xpath = "//div[@data-label=\"Первоначальный взнос\"]/input")
    private WebElement initialPayment;

    @FindBy(xpath = "//div[@data-label=\"Срок кредита\"]/input")
    private WebElement creditTerm;

    @FindBy(xpath = "//span[text() = \"Скидка 0,3% при покупке квартиры на ДомКлик\"]/../..//input")
    private WebElement discountHouseClick;

    @FindBy(xpath = "//span[text() = \"Страхование жизни\"]/../..//input")
    private WebElement lifeInsurance;

    @FindBy (xpath = "//span[text() = \"Молодая семья\"]/../..//input")
    private WebElement youngFamily;

    @FindBy (xpath = "//span[text() = \"Электронная регистрация сделки\"]/../..//input")
    private WebElement electronicRegistration;

    @FindBy(xpath = "//span[text() = \"Сумма кредита\"]/../span[contains(@data-e2e-id, \"medium-result-credit-sum\")]")
    private WebElement creditAmount;

    @FindBy(xpath = "//span[text() = \"Ежемесячный платеж\"]/../span[contains(@data-e2e-id, \"medium-result-monthly-payment\")]")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//span[text() = \"Необходимый доход\"]/../span[contains(@data-e2e-id, \"medium-result-required-income\")]")
    private WebElement requiredIncome;

    @FindBy(xpath = "//span[text() = \"Процентная ставка\"]/../span[contains(@data-e2e-id, \"medium-result-credit-rate\")]")
    private WebElement mortgageRate;

    private boolean frameFlag = false;

    private void switchToFrame() {
        if(!frameFlag) {
            frameFlag = true;
            DriverManager.getDriver().switchTo().frame("iFrameResizer0");
        }
    }

    public MortgageFormPage fillField(String nameField, String value) {
        switchToFrame();

        WebElement element = null;
        switch (nameField) {
            case "Стоимость недвижимости":
                fillInputField(realtyPrice, value);
                element = realtyPrice;
                break;
            case "Первоначальный взнос":
                fillInputField(initialPayment, value);
                element = initialPayment;
                break;
            case "Срок кредита":
                fillInputField(creditTerm, value);
                element = creditTerm;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице.");
        }
        Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
                converter(value), converter(element.getAttribute("value")));
        return this;
    }

    public MortgageFormPage switchButton (String nameTick, String value) {
        sleeper(500);
        switch (nameTick) {
            case "Скидка 0,3% при покупке квартиры на ДомКлик":
                switcher(discountHouseClick, value);
                break;
            case "Страхование жизни":
                switcher(lifeInsurance, value);
                break;
            case "Молодая семья":
                switcher(youngFamily, value);
                break;
            case "Электронная регистрация сделки":
                switcher(electronicRegistration, value);
            default:
                Assert.fail("Поле с наименованием '" + nameTick + "' отсутствует на странице.");
                break;
        }
        return this;
    }

    public MortgageFormPage checkFieldValue(String nameField, String value) {

        switch (nameField) {
            case "Сумма кредита":
                checkValue(creditAmount, value, nameField);
                break;
            case "Ежемесячный платеж":
                checkValue(monthlyPayment, value, nameField);
                break;
            case "Необходимый доход":
                checkValue(requiredIncome, value, nameField);
                break;
            case "Процентная ставка":
                checkValuePercent(mortgageRate, value, nameField);
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице.");
                break;
        }
        return this;
    }
}
