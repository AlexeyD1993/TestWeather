import Pages.CityResultPage;
import Pages.MainPage;
import Pages.TemperaturePage;
import Temperature.TemperatureConverter;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class TestTemperature {
    private WebDriver driver;

    private CityResultPage cities;
    private MainPage main;
    private TemperaturePage temperature;

    @BeforeClass
    public void SetUp() {
        //driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        driver = new RemoteWebDriver(capabilities);
        driver.get("https://openweathermap.org/");

        main = new MainPage();
        main.init(driver);

        cities = new CityResultPage();
        cities.init(driver);

        temperature = new TemperaturePage();
        temperature.init(driver);
    }

    @DataProvider
    public Object[][] cityNames() {
        return new Object[][] {
                new Object[] {"Moscow"},
                new Object[] {"London"},
                new Object[] {"Yoshkar-Ola"}
        };
    }

    @Test(dataProvider = "cityNames")
    public void CheckTempConvert(String city) throws InterruptedException {
        main.FindCity(city);
        cities.SelectFirstCityTemperatureInList();

        //temperature.ChangeTempToCelsius();
        TemperatureConverter converter = new TemperatureConverter(temperature.GetTemperature());
        temperature.SwithTemperature();
        String switchedTemp = temperature.GetTemperature();
        assertEquals(switchedTemp, converter.get_changedTemperature());
    }

    @AfterClass
    public void Close() {
        driver.close();
    }
}
