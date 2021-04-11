package TableParcers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultTableParcer {
    private WebElement webTable;

    public ResultTableParcer(WebElement webTable) {
        this.webTable = webTable;
    }

    public int getRowCount() {
        List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
        return tableRows.size();
    }

    public int getColumntCount() {
        List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
        WebElement headerRow = tableRows.get(0);
        List<WebElement> tableCols = headerRow.findElements(By.tagName("td"));
        return tableCols.size();
    }

    public WebElement getCell(int rowIdx, int colIdx) throws NoSuchElementException {
        try {
            List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
            WebElement currentRow = tableRows.get(rowIdx-1);
            List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
            WebElement cell = tableCols.get(colIdx-1);
            return cell;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Failed to get cell editor");
        }
    }
}
