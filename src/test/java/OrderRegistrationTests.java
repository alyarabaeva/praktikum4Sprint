import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.HomeSamokatPage;
import pageObjects.OrderRegistrationPage;

@RunWith(Parameterized.class)
public class OrderRegistrationTests {
    private WebDriver driver;

    private final int numOfOrderButton;
    private final String clientName;
    private final String clientSurname;
    private final String clientAddress;
    private final int numberOfStation;
    private final String clientPhone;
    private final String clientDate;
    private final int numberOfRentalPeriod;
    private final int clientColor;
    private final String clientComment;

    public OrderRegistrationTests(int numOfOrderButton, String clientName, String clientSurname, String clientAddress, int numberOfStation, String clientPhone, String clientDate, int numberOfRentalPeriod, int clientColor, String clientComment) {
        this.numOfOrderButton = numOfOrderButton;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientAddress = clientAddress;
        this.numberOfStation = numberOfStation;
        this.clientPhone = clientPhone;
        this.clientDate = clientDate;
        this.numberOfRentalPeriod = numberOfRentalPeriod;
        this.clientColor = clientColor;
        this.clientComment = clientComment;
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
    public static Object[][] getTestData() {
        return new Object[][]{
                {0, "Ян", "Ли", "Псков", 0, "+79999999999", "01.01.2024", 0, 0, "Позвоните перед отправкой"},
                {1, "Александра", "Ярабаева", "Санкт-Петербург", 1, "89999999999", "01.01.2024", 2, 1, "Тест"},
        };
    }

    @Test
    public void orderSamokatTest() {
        HomeSamokatPage homePage = new HomeSamokatPage(driver);
        homePage.openOrderRegistrationPage(numOfOrderButton);

        OrderRegistrationPage orderPage = new OrderRegistrationPage(driver);
        orderPage.waitForOrderPage();

        orderPage.fillOrderFields(clientName, clientSurname, clientAddress, numberOfStation, clientPhone, clientDate, numberOfRentalPeriod, clientColor, clientComment);
        orderPage.orderRegistration();
        orderPage.checkSucceedOrderRegistration();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
