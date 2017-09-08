import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CucTest implements En{

    public CucTest(){
        Given("I want to test .*$", (String url) -> {
            WebDriver driver = new ChromeDriver();
            driver.navigate().to(url);
        });

    }
}
