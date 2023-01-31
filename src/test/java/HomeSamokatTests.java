import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

import pageObjects.HomeSamokatPage;

@RunWith(Parameterized.class)
public class HomeSamokatTests {
    private WebDriver driver;
    private final int numberOfQuestion;
    private final String expectedAnswer;

    public HomeSamokatTests(int numberOfQuestion, String expectedAnswer) {
        this.numberOfQuestion = numberOfQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Before
    public void setUp() {
        // подготовка браузера
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void prepareHomePage() {
        // драйвер для браузера Chrome
        driver = new ChromeDriver();
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // чистим куки
        driver.manage().deleteAllCookies();

        HomeSamokatPage homePage = new HomeSamokatPage(driver);
        homePage.acceptCookie();
    }

    @Parameterized.Parameters
    public static Object[][] getAnswers() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void blockWithQuestionsTest() {
        HomeSamokatPage homePage = new HomeSamokatPage(driver);
        homePage.scrollToQuestionsBlock();
        String actualAnswer = homePage.getAnswerOnTheQuestion(numberOfQuestion);
        assertEquals("Отобразился некорректный ответ на вопрос №" + (numberOfQuestion + 1), expectedAnswer, actualAnswer);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}