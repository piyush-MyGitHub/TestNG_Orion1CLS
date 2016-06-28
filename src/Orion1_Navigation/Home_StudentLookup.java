package Orion1_Navigation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uimap_Orion1.Homepage;
import uimap_Orion1.StudenLookupPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

public class Home_StudentLookup {
	
	//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			//Home Page Page Object Model
			public Homepage uiHomePageObjects;		
			public StudenLookupPage uiStuLookupPageObjects;					
			//Browser Parameter received from TestNg.xml
			@Parameters({"Browser"})
			@BeforeClass
			public void BeforeNavigation(String sBrowser) throws MalformedURLException
			{
				
				//Edit Browser Capabilities as per project
				//Fire fox Profile		
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("network.automatic-ntlm-auth.trusted-uris",EnvironmentVariables.sUrl_Orion1);
				
				//Capability
				objBrowserMgr = new BrowserManagement(sBrowser);
				objBrowserMgr.capability.setCapability(FirefoxDriver.PROFILE, profile);		
					
				//Create the Remote Driver Instance
				try
				{						
					driver = new RemoteWebDriver(new URL("http://".concat(EnvironmentVariables.sHub).concat(":").concat(EnvironmentVariables.sHubPort).concat("/wd/hub")), objBrowserMgr.capability);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
				}
				catch(Exception ex)
				{	
					Reporter.log("Unable to create the Remotedriver" +  ex.getMessage());		
				}
				driver.get(EnvironmentVariables.sUrl_Orion1);
				driver.manage().window().maximize();
				driver.switchTo().frame("Orion");
				
				//instantiating page objects
				uiHomePageObjects = new Homepage(driver);
				uiStuLookupPageObjects = new StudenLookupPage(driver);
				uiHomePageObjects.lnkStuLookup.click();
							
			}

//vERIFY Left Nav Queues label
@Test
public void VerifyQueueslabel(){
	Assert.assertTrue(uiStuLookupPageObjects.lblQueues.isDisplayed(), "Queues label is not displayed");
}
//Left Nav Misc label
public void VerifyMisclabel(){
	Assert.assertTrue(uiStuLookupPageObjects.lblMisc.isDisplayed(), "Misc label is not displayed");
}

//Task Stats Manager label
public void VerifyTaskStatsManagerlabel(){
	Assert.assertTrue(uiStuLookupPageObjects.lblTaskTracker.isDisplayed(), "Task Stats Manager label is not displayed");
}
  
//Search All Students link
public void VerifySearchAllStuslabel(){
	Assert.assertTrue(uiStuLookupPageObjects.lnkSearchAllStu.isDisplayed(), "Search All Students link is not displayed");
}

@AfterClass
  public void AfterNavigation(){
	  if (driver !=null) {driver.quit();}
			  
  }
  }