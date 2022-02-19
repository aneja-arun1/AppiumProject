package com.arun.tests;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.*;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class TestList {
  private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
  private static final String APPIUM = "http://localhost:4723/wd/hub";
  
  private AndroidDriver driver;
  private WebDriverWait wait;  
  private DesiredCapabilities caps;
  
  private By listDemoLink;
  private By fogLink;
  private By cirrusLink;
      
  @BeforeMethod
  public void setUp() throws MalformedURLException{
    caps = new DesiredCapabilities();
    caps.setCapability("platformName", "Android");
    caps.setCapability("platformVersion", "9");
    caps.setCapability("deviceName", "Pixel 4 API 28");
    caps.setCapability("automationName", "UiAutomator2");
    caps.setCapability("app", APP);
    
    driver = new AndroidDriver(new URL(APPIUM), caps);
    wait = new WebDriverWait(driver, 10);
    
    listDemoLink = MobileBy.AccessibilityId("List Demo");
    fogLink = MobileBy.AccessibilityId("Fog");
    cirrusLink = MobileBy.AccessibilityId("Cirrus");
  }
  
  @Test
  public void testListScroll() {
    System.out.println("Inside listScroll");
    WebElement listDemo = wait.until(
        ExpectedConditions.presenceOfElementLocated(
            listDemoLink));
    listDemo.click();
    
    WebElement fog = wait.until(
        ExpectedConditions.presenceOfElementLocated(
            fogLink));
    System.out.println(fog.getLocation());
    
    WebElement cirrus = wait.until(
        ExpectedConditions.presenceOfElementLocated(
            cirrusLink));
    System.out.println(cirrus.getLocation());
    
    //Touch action from cirrus to fog
    PointerInput finger = new PointerInput(
        PointerInput.Kind.TOUCH, "finger");
    
    Interaction moveToStart = finger.createPointerMove(
        Duration.ZERO, Origin.viewport(), 
        fog.getLocation().getX(),
        fog.getLocation().getY());
    
    Interaction pressDown = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
    Interaction moveToEnd = finger.createPointerMove(
        Duration.ofSeconds(2), 
        Origin.viewport(),
        cirrus.getLocation().getX(),
        cirrus.getLocation().getY());

    
    Interaction pressUp = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
    
    Sequence swipe = new Sequence(finger, 0);
    swipe.addAction(moveToStart);
    swipe.addAction(pressDown);
    swipe.addAction(moveToEnd);
    swipe.addAction(pressUp);
    
    driver.perform(Arrays.asList(swipe));
    
    
    
    try {
      Thread.sleep(10000);
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
  

}
