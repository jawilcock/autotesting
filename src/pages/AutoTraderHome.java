import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AutoTraderHome {

    WebDriver driver;

    @FindBy(css = ".test-header__logo")
    static WebElement logo;

    @FindBy(css = "#postcode")
    static WebElement postcode;

    @FindBy(css = "#radius")
    static WebElement distanceDropList;

    @FindBy(css = "#radius > option:nth-child(4)")
    static WebElement distance;

    @FindBy(css = "#searchVehiclesMake")
    static WebElement makeDropDownList;

    @FindBy(css = "#searchVehiclesMake > option:nth-child(32)")
    static WebElement make;

    @FindBy(css = "#searchVehiclesModel")
    static WebElement modelDropDownList;

    @FindBy(css = "#searchVehiclesModel > option:nth-child(4)")
    static WebElement model;

    @FindBy(css = "#search")
    static WebElement searchCars;

    @FindBy(css = "button.u-color--blue")
    static WebElement mobileButton;

    @FindBy(css = ".content__nav > a:nth-child(2)")
    static WebElement reviewButton;

    @FindBy(css = "input.form-input:nth-child(1)")
    static WebElement carReg;

    @FindBy(css = "input.form-input:nth-child(2)")
    static WebElement carMiles;

    @FindBy(css = "button.btn")
    static WebElement createCarAd;

    @FindBy(css = ".form__label")
    static WebElement carAdDets;

    @FindBy(css = ".test-header__sign-in")
    static WebElement signInButton;

    @FindBy(css = "#js-social__signup-tab")
    static WebElement signUpButton;

}
