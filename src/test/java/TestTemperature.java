import Pages.CityResultPage;
import Pages.MainPage;
import Pages.TemperaturePage;
import Temperature.TemperatureConverter;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
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
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("87.0");
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("screenResolution", "1280x1024x24");
        driver = new RemoteWebDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
    public void CheckTempConvert(String city) {
        main.FindCity(city);
        cities.SelectFirstCityTemperatureInList();

        //temperature.ChangeTempToCelsius();
        TemperatureConverter converter = new TemperatureConverter(temperature.GetTemperature());
        temperature.SwitchTemperature();
        String switchedTemp = temperature.GetTemperature();
        assertEquals(switchedTemp, converter.get_changedTemperature());
    }

    @AfterClass
    public void Close() {
        driver.close();
    }
}
