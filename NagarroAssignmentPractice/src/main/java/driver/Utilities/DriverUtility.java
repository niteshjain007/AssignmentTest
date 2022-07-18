package driver.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import helper.Utilities.PropertyUtility;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DriverUtility {
    public static WebDriver driver;

    public static void setDriver()
    {
        switch(PropertyUtility.readConfig("webDriver"))
        {
            case "chrome":  WebDriverManager.chromedriver().setup();
                            driver =new ChromeDriver();
                            break;
            case "firefox": WebDriverManager.firefoxdriver().setup();
                            driver = new FirefoxDriver();
                            break;
            default:        driver=null;

        }
        driver.manage().window().maximize();
    }
    public static WebDriver getDriver()
    {
        return driver;
    }

    public static void closeDriver()
    {
        driver.quit();
    }

    //Mobile Driver
    public static AndroidDriver androidDriver ;
    public static void setAndroidDriver() throws IOException {

        File file = new File(PropertyUtility.readConfig("SelendroidApp"));
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "NexusTest");
       //NITESH// cap.setCapability(MobileCapabilityType.UDID,PropertyHandler.readData("deviceName"));
        //new line add for platform
        cap.setCapability("platformName", "Android");
        cap.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

       	androidDriver = new AndroidDriver(new URL(PropertyUtility.readConfig("appiumServerURL")), cap);
       // androidDriver = new AndroidDriver(cap);

    }
    public static AndroidDriver getAndroidDriver()
    {
        return androidDriver;
    }
    public static void closeAndroidDriver()
    {
        androidDriver.quit();
    }
}
