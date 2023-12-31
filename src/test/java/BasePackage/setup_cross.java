package BasePackage;


import Pages.A0_HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class setup_cross {
    protected WebDriver d;
    protected A0_HomePage Login_P_Obj;

    @BeforeMethod
    @Parameters("browserType")
    public void start(String browserType) {
        System.out.println("We are in step >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        if (browserType.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            d = new EdgeDriver();
        } else if (browserType.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            d = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("Firefox")) {
            WebDriverManager.chromedriver().setup();
            d = new FirefoxDriver();
        }
        d.get("https://homzmart.com/en/");
        Reporter.log("Browser URL IS Opened ");
        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        Reporter.log("Browser Maximized");
        d.manage().window().maximize();
        Login_P_Obj = new A0_HomePage(d);
    }

    @AfterMethod
    public void quit(ITestResult result) {

        System.out.println("We are in end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (d != null) {
//            d.close();
            d.quit();
        }

        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) d;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot.toPath(), new File("resources/screenshots" + result.getName() + ".png").toPath());
                Reporter.log("screenshot Saved");
            } catch (IOException e) {
                e.printStackTrace();
            }

            Reporter.log("Application closed:" + result.getName());

        }
    }
}

