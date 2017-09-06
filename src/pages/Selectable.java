import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Selectable {
    WebDriver driver;

    @FindBy(css = "#selectable > li:nth-child(1)")
    static WebElement firstselectable;

    @FindBy(css = "#selectable > li:nth-child(7)")
    static WebElement lastselectable;

}