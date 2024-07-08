package pageObjectRentScooter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageRentScooter {

    // Локаторы
    //Поле Имя
    private static final By NAME_FIELD = By.xpath(".//input[@placeholder='* Имя']");
    // Поле фамилия
    private static final By SURNAME_FIELD = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле адрес
    private static final By ADDRESS_FIELD = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле Станция метро
    private static final By METRO_FIELD = By.xpath(".//input[@placeholder='* Станция метро']");
    // Поле Телефон
    private static final By PHONE_FIELD = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private static final By NEXT_BUTTON = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Поле Когда привезти самокат?
    private static final By DATE_FIELD = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле Срок аренды
    private static final By PERIOD_FIELD = By.xpath(".//div[text()='* Срок аренды']");
    private static final String PERIOD_LIST_FIELD = ".//div[@class='Dropdown-root is-open']//div[text()='%s']";
    // Поле Цвет самоката
    private static final By BLACK_COLOR_SCOOTER_FIELD = By.id("black");
    private static final By GRAY_COLOR_SCOOTER_FIELD = By.id("grey");
    // Поле Комментарий для курьера
    private static final By COMMENT_FIELD = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка Заказать итоговая
    private static final By ORDER_BUTTON_FINAL = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Кнопка Да в окне Хотите оформить заказ?
    private static final By YES_BUTTON = By.xpath(".//button[text()='Да']");
    // Заголовок окна Заказ оформлен
    private static final By HEADER_MODAL_WINDOW_ORDER_PLACED = By.xpath(".//div[text()='Заказ оформлен']");

    private WebDriver driver;

    public OrderPageRentScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        WebElement inputFieldName = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(NAME_FIELD));
        inputFieldName.sendKeys(name);
    }

    public void enterSurname(String surname) {
        driver.findElement(SURNAME_FIELD).sendKeys(surname);
    }

    public void enterAddress(String address) {
        driver.findElement(ADDRESS_FIELD).sendKeys(address);
    }

    public void choiceMetro(String metro) {
        WebElement inputFieldMetro = driver.findElement(METRO_FIELD);
        inputFieldMetro.click();
        inputFieldMetro.sendKeys(metro);
        inputFieldMetro.sendKeys(Keys.DOWN, Keys.ENTER);
    }

    public void enterPhone(String phone) {
        driver.findElement(PHONE_FIELD).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }

    public void enterDate(String date) {
        WebElement inputFieldDate = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(DATE_FIELD));
        inputFieldDate.sendKeys(date);
        inputFieldDate.sendKeys(Keys.ENTER);
    }

    public void enterPeriod(String period) {
        WebElement inputFieldPeriod = driver.findElement(PERIOD_FIELD);
        inputFieldPeriod.click();
        String locatorSelectListPeriod = String.format(PERIOD_LIST_FIELD, period);
        WebElement selectListPeriod = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorSelectListPeriod)));
        selectListPeriod.click();
    }

    public void choiceColor(String color) {
        if (!color.equals("")) {
            if (color.equals("чёрный жемчуг")) {
                driver.findElement(BLACK_COLOR_SCOOTER_FIELD).click();
            } else if (color.equals("серая безисходность")) {
                driver.findElement(GRAY_COLOR_SCOOTER_FIELD).click();
            }
        }
    }

    public void enterComment(String comment) {
        driver.findElement(COMMENT_FIELD).sendKeys(comment);
    }

    public void clickOrderButtonFinal() {
        driver.findElement(ORDER_BUTTON_FINAL).click();
    }

    public void clickYesButton() {
        driver.findElement(YES_BUTTON).click();
    }

    public boolean isModalWindowOrderPlacedDisplayed() {
        WebElement modalWindowDisplayed = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(HEADER_MODAL_WINDOW_ORDER_PLACED));
        return modalWindowDisplayed.isDisplayed();
    }

}