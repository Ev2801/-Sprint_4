import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import pageObjectRentScooter.MainPageRentScooter;

import static org.junit.Assert.assertEquals;
import static pageObjectRentScooter.constants.DropDownList.EXPECTED_ANSWERS;
import static pageObjectRentScooter.constants.DropDownList.QUESTIONS;

@RunWith(Parameterized.class)
public class DropDownListQuestionTest {

    private WebDriver driver;
    private final String question;
    private final String expectedAnswer;
    private final int num_item;
    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public DropDownListQuestionTest(String question, String expectedAnswer, int num_item) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
        this.num_item = num_item;
    }

    @Parameterized.Parameters
    public static Object[][] getDropDownListData() {

        Object[][] objDropDownListData = new Object[QUESTIONS.length][3];
        for (int i = 0; i < QUESTIONS.length; i++) {
            objDropDownListData[i][0] = QUESTIONS[i];
            objDropDownListData[i][1] = EXPECTED_ANSWERS[i];
            objDropDownListData[i][2] = i;
        }

        return  objDropDownListData;
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(PAGE_URL);
    }

    @Test
    public void checkDropDownList_ShowCorrectAppearsText() {

        MainPageRentScooter objMainPageRentScooter = new MainPageRentScooter(driver);

        objMainPageRentScooter.clickCookieButton();
        objMainPageRentScooter.clickQuestion(num_item);
        String answer = objMainPageRentScooter.getAnswer(num_item);
        assertEquals("Текст ответа не совпадает с вопросом: " + question, expectedAnswer, answer);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}