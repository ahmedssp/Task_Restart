package BasePackage;


import Pages.A0_HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;


public class setup_simple {
    protected WebDriver d;
    protected A0_HomePage Login_P_Obj;

    @BeforeMethod
    public void start() {
//        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();

        d = new EdgeDriver();
//        d = new ChromeDriver();
        d.get("https://homzmart.com/en/");
//        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Reporter.log("Browser URL IS Opened ");
//        d.get("https://www.google.com/");
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        Reporter.log("Browser Maximized");
        d.manage().window().maximize();
        Login_P_Obj = new A0_HomePage(d);
    }
    @AfterMethod
    public void quit(ITestResult result) {

        if (d != null) {
            d.quit();
        }
        Reporter.log("Application closed:" + result.getName());

    }
}

