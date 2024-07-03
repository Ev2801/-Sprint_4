import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjectRentScooter.MainPageRentScooter;
import pageObjectRentScooter.OrderPageRentScooter;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderFlowTest {
    private WebDriver driver;
    private final String orderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;
    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public OrderFlowTest(String orderButton, String name, String surname, String address, String metro, String phone, String date, String period, String color, String comment) {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDataForOrder() {
        return new Object[][] {
                {"headerButton", "Евгений", "Пешехонов", "Москва", "Черкизовская", "+79998885462", "29.06.2024", "семеро суток", "серая безысходность", "Быстрее!"},
                {"middleButton", "Валерия", "Добровольская", "Балашиха", "Лубянка", "+71212223321", "01.07.2024", "сутки", "чёрный жемчуг", ""},
        };
    }

    @Before
    public void setUp() {
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(PAGE_URL);
    }

    @Test
    public void checkOrderFlow_ShowOrderConfirmation() {

        MainPageRentScooter mainPage = new MainPageRentScooter(driver);
        mainPage.clickCookieButton();
        mainPage.clickOrderButton(orderButton);
        OrderPageRentScooter orderPage = new OrderPageRentScooter(driver);
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.choiceMetro(metro);
        orderPage.enterPhone(phone);
        orderPage.clickNextButton();
        orderPage.enterDate(date);
        orderPage.enterPeriod(period);
        orderPage.choiceColor(color);
        orderPage.enterComment(comment);
        orderPage.clickOrderButtonFinal();
        orderPage.clickYesButton();
        orderPage.isModalWindowOrderPlacedDisplayed();
        boolean modalWindowOrderPlacedDisplayed = orderPage.isModalWindowOrderPlacedDisplayed();
        assertTrue(modalWindowOrderPlacedDisplayed);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}