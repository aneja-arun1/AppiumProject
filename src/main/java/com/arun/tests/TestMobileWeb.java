package com.arun.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestMobileWeb {
  private RemoteWebDriver driver;
  private DesiredCapabilities caps;
  private static final String APPIUM = "http://localhost:4723/wd/hub";
  
  @BeforeClass
  public void setUp() throws MalformedURLException{
    caps = new DesiredCapabilities();
    caps.setCapability("platformName", "Android");
    caps.setCapability("platformVersion", "9");
    caps.setCapability("deviceName", "Pixel 4 API 28");
    caps.setCapability("automationName", "UiAutomator2");
    caps.setCapability("browserName", "Chrome");
    
    driver = new RemoteWebDriver(new URL(APPIUM), caps);
    
    driver.get("http://www.yahoo.com");
    
  }
  
  @Test
  public void testWebAutomation() {
    System.out.println("inside testWebAutomation");
    try {
      Thread.sleep(10000);
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @AfterClass
  public void tearDown() {
    driver.quit();
  }

}
