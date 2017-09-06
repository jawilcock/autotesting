import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class shopHome {
    WebDriver driver;

    @FindBy(css = "#search_query_top")
    static WebElement searchBox;

    @FindBy(css = "button.btn:nth-child(5)")
    static WebElement searchButton;

    @FindBy(css = "li.ajax_block_product:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1)")
    static WebElement addCartButton;

    @FindBy(css = ".icon-ok")
    static WebElement successTick;

    @FindBy(css= ".shopping_cart")
    static WebElement cartButton;
}