package com.ak.sample;

//package <set your test package>;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class SampleTest2 {
  private String reportDirectory = "c:\\appium\\reports";
  private String reportFormat = "xml";
  private String testName = "SampleTest2";
  protected IOSDriver<IOSElement> driver = null;

  DesiredCapabilities dc = new DesiredCapabilities();
  
  @BeforeMethod
  public void setUp() throws MalformedURLException {
      dc.setCapability("reportDirectory", reportDirectory);
      dc.setCapability("reportFormat", reportFormat);
      dc.setCapability("testName", testName);
      dc.setCapability(MobileCapabilityType.UDID, "f331d2f8e5ce04aeb8e4f28b381114dcc2818258");
      dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.BrowserO");
      dc.setCapability("accessKey", "eyJ4cC51Ijo2ODAwMDU4LCJ4cC5wIjo2ODAwMDU3LCJ4cC5tIjoiTVRVMk1UZzVNREV5TlRVd05RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4NzcyNTAxMjcsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.5UUG1Rp8UBId3iQ4_Vi0_xnv6WitojIKS-l7pQpbOmY");
      driver = new IOSDriver<>(new URL("https://cloud.seetest.io:443/wd/hub"), dc);
      driver.setLogLevel(Level.INFO);
  }
  
  @Test
  public void testGoogle() {
      driver.getKeyboard().sendKeys("{HOME}");
      driver.executeScript("seetest:client.launch(\"com.apple.mobilesafari\", \"false\",\"true\")");
      driver.findElement(By.xpath("//*[@text='Address' and ./parent::*[@text='Address']]")).sendKeys("www.google.com");
      driver.getKeyboard().sendKeys("{ENTER}");
      new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='hplogo']")));
      driver.findElement(By.xpath("//*[@name='q']")).sendKeys("appium");
      driver.getKeyboard().sendKeys("{ENTER}");
      new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@nodeName='A' and @width>0 and ./*[@text='Web results'] and ./*[@text='Appium: Mobile App Automation Made Awesome.']]")));
  }


  @AfterMethod
  public void tearDown() {
      driver.quit();
  }
}