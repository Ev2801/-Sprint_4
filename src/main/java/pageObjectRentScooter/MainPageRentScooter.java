package pageObjectRentScooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageRentScooter {

    // Локаторы
    // Кнопка принятия куки "да все привыкли"
    private static final By COOKIE_BUTTON = By.id("rcc-confirm-button");
    // Кнопка "Заказать" в хедере
    private static final By HEADER_ORDER_BUTTON = By.className("Button_Button__ra12g");
    // Кнопка "Заказать" в середине страницы
    private static final By MIDDLE_PAGE_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Лого Самокат
    private static final By LOGO_SAMOKAT = By.className("Header_LogoScooter__3lsAR");
    // Лого Яндекс
    private static final By LOGO_YANDEX = By.className("Header_LogoYandex__3TSOI");
    //Лого Дзен
    private static final By LOGO_DZEN = By.xpath(".//span[@aria-label='Логотип Дзен']/*[@class='zen-ui-generic-svg']");
    // Заголовок Вопросы о важном
    private static final By HEADER_QUESTIONS_ABOUT = By.xpath(".//div[@class='Home_FourPart__1uthg']/div['Home_SubHeader__zwi_E']");
    // Кнопка "Статус заказа"
    private static final By CHECK_STATUS_ORDER_BUTTON = By.xpath("//button[@class='Header_Link__1TAG7']");
    // Поле ввода номера заказа
    private static final By ORDER_NUMBER_INPUT_FIELD = By.xpath(".//input[@placeholder=\"Введите номер заказа\"]");
    // Кнопка Go
    private static final By GO_BUTTON = By.xpath(".//button[@class=\"Button_Button__ra12g Header_Button__28dPO\"]");
    // Картинка Заказа не найден
    private static final By NOT_FOUND_IMAGE = By.cssSelector("div.Track_NotFound__6oaoY > img");
    // Массив вопросов в разделе "Вопросы о важном"
    private static final String DROP_DOWN_LIST_QUESTIONS = "accordion__heading-";
    // Массив ответов в разделе "Вопросы о важном"
    private static final String DROP_DOWN_LIST_ANSWERS = ".//div[@id='accordion__panel-%d']/p";

    private WebDriver driver;

    public MainPageRentScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogoSamokat() {
        driver.findElement(LOGO_SAMOKAT).click();
    }

    public String checkLoadHomePage() {
        WebElement homeSubHeader = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(HEADER_QUESTIONS_ABOUT));
        return homeSubHeader.getText();
    }

    public void clickLogoYandex() {
        driver.findElement(LOGO_YANDEX).click();
    }

    public boolean checkLoadHomePageYandex() {
        WebElement homeHeaderYandex = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(LOGO_YANDEX));
        return homeHeaderYandex.isDisplayed();
    }

    public void clickCookieButton() {
        driver.findElement(COOKIE_BUTTON).click();
    }

    // Check DropDown List Questions
    public void clickQuestion(int num_item) {
        driver.findElement(By.id(DROP_DOWN_LIST_QUESTIONS + num_item)).click();
    }

    public String getAnswer(int num_item) {
        WebElement dropDownItem = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DROP_DOWN_LIST_ANSWERS, num_item))));
        String answerText = dropDownItem.getText();
        return answerText;
    }

    // Check Status nonExisting Order
    public void clickCheckOrderButton() {
        driver.findElement(CHECK_STATUS_ORDER_BUTTON).click();
    }

    public void enterOrderNumber(String orderNumber) {
        WebElement inputFieldViaLambda = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(ORDER_NUMBER_INPUT_FIELD));
        inputFieldViaLambda.sendKeys(orderNumber);
    }

    public void clickGoButton() {
        driver.findElement(GO_BUTTON).click();
    }

    public boolean isImageNotFoundDisplayed() {
        WebElement notFoundImage = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(NOT_FOUND_IMAGE));
        return notFoundImage.isDisplayed();
    }

    //Order flow
    public void clickOrderButton(String orderButton) {
        if (orderButton.equals("headerButton")) {
            driver.findElement(HEADER_ORDER_BUTTON).click();
        } else if (orderButton.equals("middleButton")) {
            driver.findElement(MIDDLE_PAGE_ORDER_BUTTON).click();
        }
    }

}