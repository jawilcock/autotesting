import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobileATHome {

    WebDriver driver;

    @FindBy(css = ".logo-autotrader > span:nth-child(2)")
    static WebElement mobileLogo;

    @FindBy(xpath = "//*[@id=pageFooter]/section[1]/form/input[2]")
    static WebElement desktopSiteButton;

}
