package Pages;

import TableParcers.ResultTableParcer;
import com.codeborne.selenide.Condition;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;

public class CityResultPage {
    private WebDriver driver;

    @FindBy(id="forecast_list_ul")
    private WebElement cityFindResult;

    @FindBy(xpath = "//*[@id=\"pbar\"]")
    private WebElement loadResult;

    @FindBy(xpath="/html/body/main/div/div[2]/div[1]/div[1]/div[2]/div[1]/span")
    private WebElement temperature;

    public void init(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void SelectFirstCityTemperatureInList() {
        ResultTableParcer table = new ResultTableParcer(cityFindResult);
        table.getCell(1, 2)
                .findElement(By.xpath(".//b[1]"))
                .click();

        //Ожидание прогрузки температуры на странице
        //WebElement waitLoadPage = new WebDriverWait(driver, 10)
        //        .until(ExpectedConditions.visibilityOf(temperature));
        $(temperature).should(Condition.visible);
    }
}
