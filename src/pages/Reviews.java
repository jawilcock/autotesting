import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Reviews {

    WebDriver driver;

    @FindBy (css = ".content__search-bar__makes")
    static WebElement reviewMakeDrop;

    @FindBy (css = ".content__search-bar__makes > option:nth-child(2)")
    static WebElement reviewMake;

    @FindBy (css = ".content__search-bar__models")
    static WebElement reviewModelDrop;

    @FindBy (css = ".content__search-bar__makes > option:nth-child(2)")
    static WebElement reviewModel;

    @FindBy (css = ".content__search-bar__button")
    static WebElement reviewSearch;

    @FindBy (css = ".review-page--review-all-link")
    static WebElement reviewLink;

    @FindBy (css = ".display-1")
    static WebElement reviewTitle;
}