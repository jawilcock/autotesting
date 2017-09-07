import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dresses {
    WebDriver driver;

    @FindBy(css = "#ul_layered_category_0 > li:nth-child(3) > label:nth-child(2) > a:nth-child(1)")
    static WebElement dressCategory;

    @FindBy(css = ".cat-name")
    static WebElement categoryFilter;

    @FindBy(css = "li.nomargin:nth-child(4) > label:nth-child(2) > a:nth-child(1)")
    static WebElement colourPicker;

    @FindBy(css = "#color_20")
    static WebElement dressColour;

    @FindBy(css = "#color_14")
    static WebElement colour;

    @FindBy(css = ".pb-center-column > h1:nth-child(1)")
    static  WebElement dressName;
}