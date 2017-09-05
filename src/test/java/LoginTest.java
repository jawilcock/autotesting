import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginTest {

    WebDriver driver;
    private static ExtentReportManager reportManager;
    SpreadSheetReader spreadSheetReader;

    @Before
    public void setup(){

        spreadSheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/Example_Spreadsheet.xlsx");

        String choice = spreadSheetReader.readRow(2, "Sheet1").get(0);

        if (choice.equals("firefox")){
            driver = new FirefoxDriver();
        }
        else if (choice.equals("chrome")){
            driver = new ChromeDriver();
        }

        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\TestReport",
                "Basic Extent Report","Basic Report");
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML,reportDetails);

        PageFactory.initElements(driver,Home.class);
        PageFactory.initElements(driver,Login.class);

    }

    @After
    public void testOver(){
        driver.quit();
    }

    @Test
    public void myTest(){

        String username = spreadSheetReader.readRow(0, "Sheet1").get(0);
        String password = spreadSheetReader.readRow(1, "Sheet1").get(0);

        ExtentTest loggingIn = reportManager.setUpTest();

        driver.navigate().to("http://thedemosite.co.uk");

        Home.loginButton.click();

        Login.usernameField.sendKeys(username);
        System.out.println(username);
        Login.passwordField.sendKeys(password);
        System.out.println(password);

        Login.enterLogin.click();

        try {
            ScreenShot.take(driver,"screenshot");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String successCheck = Login.successText.getText();
        try{
            Assert.assertEquals(successCheck,"**Successful Login**");

            loggingIn.log(Status.INFO,"Successfully logged in");
            loggingIn.pass("Passed");
        }catch (AssertionError f){
            loggingIn.log(Status.INFO,"Failed logging in");
            loggingIn.fail("Failed");
        }
    }

    @AfterClass
    public static void cleanUp(){
        reportManager.clearTests();
    }
}
