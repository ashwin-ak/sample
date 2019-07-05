package com.ak.sample;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Google {
	  private String reportDirectory = "c:\\appium\\reports";
	  private String reportFormat = "xml";
	  private String testName = "Google";
	  protected IOSDriver<IOSElement> driver = null;
	  
	  String accessKey = System.getenv("SEETEST_IO_ACCESS_KEY");

	  DesiredCapabilities dc = new DesiredCapabilities();
	  
	  @BeforeMethod
	  public void setUp() throws MalformedURLException {
	      dc.setCapability("reportDirectory", reportDirectory);
	      dc.setCapability("reportFormat", reportFormat);
	      dc.setCapability("testName", testName);
	      //dc.setCapability(MobileCapabilityType.UDID, "f331d2f8e5ce04aeb8e4f28b381114dcc2818258");
	      dc.setCapability(MobileCapabilityType.UDID, "7dc39287e84ae7c6f1459f9ba1b8e7d84a8fd002");
	      //dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.BrowserO");
	      dc.setCapability("accessKey", accessKey);
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