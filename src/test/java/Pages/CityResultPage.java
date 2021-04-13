package Pages;

import Elements.Loader;
import TableParcers.ResultTableParcer;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;

public class CityResultPage {
    private WebDriver driver;

    @FindBy(id="forecast_list_ul")
    private WebElement cityFindResult;

    @FindBy(xpath = "//*[@id=\"pbar\"]")
    private WebElement loadResult;

    private Loader loader;

    public void init(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        loader = new Loader();
        loader.init(driver);
    }

    public void SelectFirstCityTemperatureInList() {
        ResultTableParcer table = new ResultTableParcer(cityFindResult);
        table.getCell(1, 2)
                .findElement(By.xpath(".//b[1]"))
                .click();
        loader.waitLoad();
    }
}
