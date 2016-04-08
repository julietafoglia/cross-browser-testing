package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Checks {

public static final String USERNAME = "julietafoglia5";
public static final String AUTOMATE_KEY = "jMSXSe5pLnbsPCUiTwiu";
public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
private static WebDriver driver;
protected static WebDriverWait wait;
  
  @BeforeClass
  @Parameters(value={"browser","version","platform", "device"})
  public void setUp(String browser, String version, String platform, String device) throws Exception {
    
	DesiredCapabilities capability = new DesiredCapabilities();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    
    capability.setCapability("platform", platform);
    capability.setCapability("browserName", browser);
    capability.setCapability("browserVersion", version);
    capability.setCapability("device", device);
    capability.setCapability("project", "Cross browser testing");
    capability.setCapability("build", dateFormat.format(date));
    driver = new RemoteWebDriver(new URL(URL), capability);
    System.out.println("Testing " + browser + " " + version + " on " + platform + " with " + device + " device");
  }
  
  @Test
  public static void main() throws Exception{
	  
    driver.get("https://s3-eu-west-1.amazonaws.com/gtm-data-layer/qa/0.0.2/test.html");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
    WebElement link = driver.findElement(By.partialLinkText("Go to test"));
    link.click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
    WebElement e = driver.findElement(By.id("e"));
    WebElement tna = driver.findElement(By.id("tna"));
    WebElement p = driver.findElement(By.id("p"));
    WebElement aid = driver.findElement(By.id("aid"));
    WebElement duid = driver.findElement(By.id("duid"));
    WebElement sid = driver.findElement(By.id("sid"));
    WebElement tv = driver.findElement(By.id("tv"));;
    WebElement url = driver.findElement(By.id("url"));
    WebElement refr = driver.findElement(By.id("refr"));
    WebElement page = driver.findElement(By.id("page"));
    WebElement after = driver.findElement(By.id("after"));
    
    //Check that all elements have correct values
    Assert.assertEquals(e.getText(), "pv");
    Assert.assertEquals(tna.getText(), "test");
    Assert.assertEquals(p.getText(), "web");
    Assert.assertEquals(aid.getText(), "publisher1");
    Assert.assertEquals(tv.getText(), "pub-0.0.2");
    //-----CHECK IF THE LINK IS CORRECT FOR THE NEXT TWO ELEMENTS
    //Assert.assertEquals(url.getText(), "http://cdn.mojn.com/spp/loadsnowplow.html");
    //Assert.assertEquals(refr.getText(), "http://cdn.mojn.com/spp/test.html");
    Assert.assertEquals(page.getText(), "Browser Test");
    Assert.assertEquals(after.getText(), "OK");
    
    //Check that sid and duid have different values
    Assert.assertNotEquals(duid.getText(), sid.getText());
    
    driver.quit();
  }
}