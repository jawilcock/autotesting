import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class mouseTest {

    WebDriver driver;
    Actions builder;
    private static ExtentReportManager reportManager;

    @BeforeClass
    public static void beforeSetup(){
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\MouseTestReport",
                "Basic Extent Report","Basic Report");
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML,reportDetails);
    }

    @Before
    public void setup(){

        driver = new ChromeDriver();
        builder = new Actions(driver);
        PageFactory.initElements(driver,Draggable.class);
        PageFactory.initElements(driver,Dropping.class);
        PageFactory.initElements(driver,Resizing.class);
        PageFactory.initElements(driver,Selectable.class);
    }

    @After
    public void testOver(){
        driver.quit();
    }

    @Test
    public void TestDraggable(){

        ExtentTest draggingbox = reportManager.setUpTest();

        driver.navigate().to("http://demoqa.com/draggable/");
        draggingbox.log(Status.INFO,"Moved to the draggable page.");

        int oldX = Draggable.draggable.getLocation().x;
        int oldY = Draggable.draggable.getLocation().y;

        builder.dragAndDropBy(Draggable.draggable, 300, 200).perform();
        draggingbox.log(Status.INFO,"Attempted to drag box");
        try{
            int difX = Draggable.draggable.getLocation().x - oldX;
            int difY = Draggable.draggable.getLocation().y - oldY;

            Assert.assertEquals(difX,300);
            Assert.assertEquals(difY,200);
            draggingbox.log(Status.INFO,"Box dragged successfully");
            draggingbox.pass("Dragged");
        }
        catch(AssertionError a){
            draggingbox.log(Status.INFO,"Failed dragging.");
            draggingbox.fail("Failed");
        }
    }

    @Test
    public void TestDroppable(){
        ExtentTest droppingbox = reportManager.setUpTest();

        driver.navigate().to("http://demoqa.com/droppable/");
        droppingbox.log(Status.INFO,"Moved to the dropping page.");

        builder.dragAndDropBy(Dropping.draggableBox, 150, 20).perform();
        droppingbox.log(Status.INFO,"Attempted to drag box");

        try{
            Assert.assertEquals(Dropping.droppingView.getText(),"Dropped!");

            droppingbox.log(Status.INFO,"Box dropped successfully");
            droppingbox.pass("Dragged");
        }
        catch(AssertionError a){
            droppingbox.log(Status.INFO,"Failed dropping.");
            droppingbox.fail("Failed");
        }
    }

    @Test
    public void TestResizable(){
        ExtentTest resizingbox = reportManager.setUpTest();

        driver.navigate().to("http://demoqa.com/resizable/");
        resizingbox.log(Status.INFO,"Moved to the resizing page.");

        int locX = Resizing.resizable.getLocation().x;
        int locY = Resizing.resizable.getLocation().y;

        builder.dragAndDropBy(Resizing.resizable, 100, 100).perform();
        resizingbox.log(Status.INFO,"Attempted to resize box");

        try{
            int newX = Resizing.resizable.getLocation().x - locX;
            int newY = Resizing.resizable.getLocation().y - locY;


            Assert.assertEquals(newX,83);
            Assert.assertEquals(newY,83);

            resizingbox.log(Status.INFO,"Box dragged successfully");
            resizingbox.pass("Dragged");
        }
        catch(AssertionError a){
            resizingbox.log(Status.INFO,"Failed dragging.");
            resizingbox.fail("Failed");
        }
    }

    @Test
    public void TestSelectable(){
        ExtentTest selectableBox = reportManager.setUpTest();

        driver.navigate().to("http://demoqa.com/selectable/");
        selectableBox.log(Status.INFO,"Moved to the selectable page.");

        builder.dragAndDropBy(Selectable.firstselectable, 0, 300).perform();
        selectableBox.log(Status.INFO,"Attempted to select all boxes");



        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{

            Assert.assertEquals(Selectable.firstselectable.getAttribute("class").equalsIgnoreCase("ui-widget-content ui-corner-left ui-selectee ui-selected"),true);
            Assert.assertEquals(Selectable.lastselectable.getAttribute("class").equalsIgnoreCase("ui-widget-content ui-corner-left ui-selectee ui-selected"),true);



            selectableBox.log(Status.INFO,"Boxes selected successfully");
            selectableBox.pass("Selected");
        }
        catch(AssertionError a){
            selectableBox.log(Status.INFO,"Failed selecting.");
            selectableBox.fail("Failed");
        }

    }

    @AfterClass
    public static void cleanUp(){
        reportManager.clearTests();
    }

}
