package com.twosigma.beakerx.autotests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public static WebDriver driver;
    public static String baseURL;
    public static BasePageObject beakerxPO;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("window-size=1920,1080");
        driver = new ChromeDriver(options);
        if("lab".equalsIgnoreCase(System.getProperty("cur_app"))){
            System.out.println("current app is jupyter lab");
            beakerxPO = new LabPO(driver);
        }
        else {
            System.out.println("current app is jupyter notebook");
            beakerxPO = new NotebookPO(driver);
        }
    }

    public static void setBaseURL(String baseURL) {
        BaseTest.baseURL = baseURL;
    }

    @AfterClass
    public static void teardownClass() {
        beakerxPO.closeAndHaltNotebook();
        if (driver != null) {
            driver.quit();
        }
    }

}
