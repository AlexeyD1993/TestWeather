import Pages.AuthorizePage;
import Pages.CityResultPage;
import Pages.MainPage;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class TestsAuthorize {
    private WebDriver driver;

    private AuthorizePage auth;
    private MainPage main;

    private void assertThis(String userName) {
        assertEquals(main.GetUserName(), userName);
        assertEquals(main.GetPopupLoginCorrectText(), "Signed in successfully.");
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
            new Object[] {"xas83817@cuoly.com", "Zaqwsx_655", "Alexey"}
        };
    }

    @BeforeClass
    public void SetUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("87.0");
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("screenResolution", "1280x1024x24");
        driver = new RemoteWebDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://openweathermap.org/");

        auth = new AuthorizePage();
        auth.init(driver);

        main = new MainPage();
        main.init(driver);
    }

    @Test(dataProvider = "loginData")
    public void AuthorizeCorrect(String login, String password, String userName) {
        auth.GotoAuthorize()
                .TypeEmail(login)
                .TypePassword(password)
                .Submit();
        assertThis(userName);
    }

    @AfterClass
    public void Close() {
        driver.close();
    }
}
