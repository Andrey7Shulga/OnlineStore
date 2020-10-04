package sel.automation.practice.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import sel.automation.practice.listeners.WebDriverListener;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;

public class BaseTest {

    protected WebDriver driver;


    @BeforeClass
    public void setup() {
        ///Log4J configuration
        BasicConfigurator.configure();

        Configuration.timeout = 10000;
        Configuration.collectionsTimeout = 10000;

        //WebDriver setup
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();

        //WebListener setup
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new WebDriverListener());
        driver = eventFiringWebDriver;

    }

    @AfterClass
    public void quitForever() {

        if (driver != null) {
            clearBrowserCookies();
            driver.quit();
        }

    }

}
