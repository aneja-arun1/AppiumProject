package com.arun.tests;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;


public class TestSession {
  private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
  private static final String APPIUM = "http://localhost:4723/wd/hub";
  
  private AndroidDriver driver;
  private WebDriverWait wait;
  private By loginScreenLink;
  
  private DesiredCapabilities caps;
  
  @BeforeTest
  public void setUp() throws MalformedURLException {
    caps = new DesiredCapabilities();
    caps.setCapability("platformName", "Android");
    caps.setCapability("platformVersion", "9");
    caps.setCapability("deviceName", "Pixel 4 API 28");
    caps.setCapability("automationName", "UiAutomator2");
    caps.setCapability("app", APP);
    
    driver = new AndroidDriver(new URL(APPIUM), caps);
    wait = new WebDriverWait(driver, 10);
    loginScreenLink = MobileBy.AccessibilityId("Login Screen");
    wait.until(ExpectedConditions.presenceOfElementLocated(loginScreenLink));
  }
  
  @Test
  public void test()
  {
    System.out.println("Inside the test");
    driver.findElement(loginScreenLink).click();
    
    try {
      Thread.sleep(10000);
      }catch (Exception e){
        e.printStackTrace();
      }
    
  }
  
  @AfterTest
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
  
}
