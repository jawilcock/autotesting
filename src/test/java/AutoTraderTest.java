import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.gargoylesoftware.htmlunit.Page;
import com.sun.jna.platform.win32.OaIdl;
import gherkin.lexer.Pa;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.regexp.RE;
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AutoTraderTest {

    WebDriver driver;
    Actions builder;
    private static ExtentReportManager reportManager;
    SpreadSheetReader spreadSheetReader;

    @BeforeClass
    public static void beforeSetup(){
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\ATTestReport",
                "Basic Extent Report","Basic Report");
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML,reportDetails);
    }

    @Before
    public void setup(){

        driver = new ChromeDriver();
        builder = new Actions(driver);
        PageFactory.initElements(driver,AutoTraderHome.class);
        PageFactory.initElements(driver, MercedesAMGPage.class);
        PageFactory.initElements(driver, MobileATHome.class);
        PageFactory.initElements(driver, Reviews.class);

        spreadSheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/AutoTrader_Details.xlsx");

    }

    @Test
    public void loadedWeb()
    {
        ExtentTest loadedWeb = reportManager.setUpTest();

        driver.navigate().to("http://www.autotrader.co.uk/");
        loadedWeb.log(Status.INFO,"Moved to the home page.");

        try{

            Assert.assertEquals(AutoTraderHome.logo.getAttribute("class").equalsIgnoreCase("test-header__logo"),true);

            loadedWeb.log(Status.INFO,"Loaded the homepage successfully");
            loadedWeb.pass("Success");

        } catch(AssertionError a){
            loadedWeb.log(Status.INFO,"Failed loading the homepage.");
            loadedWeb.fail("Failed");
        }

    }

    @Test
    public void searchCar()
    {
        ExtentTest searchCar = reportManager.setUpTest();

        driver.navigate().to("http://www.autotrader.co.uk/");
        searchCar.log(Status.INFO,"Moved to the home page.");

        AutoTraderHome.postcode.sendKeys(spreadSheetReader.readRow(0, "Sheet1").get(0));
        searchCar.log(Status.INFO,"Entered the postcode from the spreadsheet");

        AutoTraderHome.distanceDropList.click();
        AutoTraderHome.distance.click();
        AutoTraderHome.makeDropDownList.click();
        AutoTraderHome.make.click();
        AutoTraderHome.modelDropDownList.click();
        AutoTraderHome.model.click();
        AutoTraderHome.searchCars.click();
        searchCar.log(Status.INFO,"Entered all the details into the search form");

        try{

            Assert.assertEquals(MercedesAMGPage.make.getText(),"Mercedes-Benz");
            Assert.assertEquals(MercedesAMGPage.model.getText(),"Amg");

            searchCar.log(Status.INFO,"Loaded the car page successfully");
            searchCar.pass("Success");

        } catch(AssertionError a){
            searchCar.log(Status.INFO,"Failed loading the car page.");
            searchCar.fail("Failed");
        }

    }

    @Test
    public void ReadReview()
    {
        ExtentTest mobileSite = reportManager.setUpTest();

        driver.navigate().to("http://www.autotrader.co.uk/");
        mobileSite.log(Status.INFO,"Moved to the home page.");

        AutoTraderHome.reviewButton.click();
        mobileSite.log(Status.INFO,"Moved to the reviews page.");
        Reviews.reviewMakeDrop.click();
        Reviews.reviewMake.click();
        Reviews.reviewModelDrop.click();
        Reviews.reviewModel.click();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Reviews.reviewSearch.click();
        Reviews.reviewLink.click();

        try{

            Assert.assertEquals(Reviews.reviewTitle.getText(),"Abarth 124 Spider convertible (2016 - ) review");

            mobileSite.log(Status.INFO,"Loaded the car review successfully");
            mobileSite.pass("Success");

        } catch(AssertionError a){
            mobileSite.log(Status.INFO,"Failed loading the car review.");
            mobileSite.fail("Failed");
        }
    }

    @Test
    public void CarAd()
    {
        ExtentTest createCarAd = reportManager.setUpTest();

        driver.navigate().to("http://www.autotrader.co.uk/");
        createCarAd.log(Status.INFO,"Moved to the home page.");

        AutoTraderHome.carReg.sendKeys(spreadSheetReader.readRow(1, "Sheet1").get(0));
        createCarAd.log(Status.INFO,"Entered the reg from the spreadsheet");
        AutoTraderHome.carMiles.sendKeys(spreadSheetReader.readRow(2, "Sheet1").get(0));
        createCarAd.log(Status.INFO,"Entered the car miles from the spreadsheet");
        AutoTraderHome.createCarAd.click();

        try{

            Assert.assertEquals(AutoTraderHome.carAdDets.getText(),"Vehicle details found:");

            createCarAd.log(Status.INFO,"Loaded the car ad successfully");
            createCarAd.pass("Success");

        } catch(AssertionError a){
            createCarAd.log(Status.INFO,"Failed loading the car ad.");
            createCarAd.fail("Failed");
        }
    }

    //working in progress
    @Test
    public void RegisterUser(){
        ExtentTest registerUser = reportManager.setUpTest();

        driver.navigate().to("http://www.autotrader.co.uk/");
        registerUser.log(Status.INFO,"Moved to the home page.");

        AutoTraderHome.signInButton.click();
        AutoTraderHome.signUpButton.click();
    }

    @Test
    public void mobileSite()
    {
        ExtentTest mobileSite = reportManager.setUpTest();

        driver.navigate().to("http://www.autotrader.co.uk/");
        mobileSite.log(Status.INFO,"Moved to the home page.");

        AutoTraderHome.mobileButton.click();
        try{

            Assert.assertEquals(MobileATHome.mobileLogo.getAttribute("class").equalsIgnoreCase("tracking-click-layer tracking-header-link"),true);

            mobileSite.log(Status.INFO,"Loaded the mobile homepage successfully");
            mobileSite.pass("Success");

        } catch(AssertionError a){
            mobileSite.log(Status.INFO,"Failed loading the mobile homepage.");
            mobileSite.fail("Failed");
        }
    }

    @After
    public void testOver(){
        driver.quit();
    }

    @AfterClass
    public static void cleanUp(){
        reportManager.clearTests();
    }
}