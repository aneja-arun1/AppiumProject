package com.arun.tests;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;


public class TestSession {
  private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
  private static final String APPIUM = "http://localhost:4723/wd/hub";
  By loginScreenLink = MobileBy.AccessibilityId("Login Screen"); 
  
  private AndroidDriver driver;
  
  @BeforeTest
  public void setUp() throws MalformedURLException {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("platformName", "Android");
    caps.setCapability("platformVersion", "9");
    caps.setCapability("deviceName", "Pixel 4 API 28");
    caps.setCapability("automationName", "UiAutomator2");
    caps.setCapability("app", APP);
    
    driver = new AndroidDriver(new URL(APPIUM), caps);
    
    try {
    Thread.sleep(10000);
    }catch (Exception e){
      e.printStackTrace();
    }
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
    driver.quit();
  }
  
}
