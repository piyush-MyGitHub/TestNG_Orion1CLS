package Orion1_Navigation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import uimap_Orion1.Homepage;
import uimap_Orion1.Home_AddNewLead;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

public class Home_AddNewLeadReferral {
	
	//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			//Home Page Page Object Model
			public Homepage uiHomePageObjects;		
			public Home_AddNewLead uiHomeAddNewLeadPageObjects;
			
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
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
					ScreenShotOnTestFailure.init(driver, EnvironmentVariables.sEnv, EnvironmentVariables.sApp);
				}
				catch(Exception ex)
				{	
					Reporter.log("Unable to create the Remotedriver" +  ex.getMessage());		
				}
				//Open Application
				driver.get(EnvironmentVariables.sUrl_Orion1);
				driver.manage().window().maximize();
				driver.switchTo().frame("Orion");
				
				//instantiating page objects
				uiHomePageObjects = new Homepage(driver);
				uiHomeAddNewLeadPageObjects = new Home_AddNewLead(driver);
				//Navigate to Add New Lead/Referral page
				uiHomePageObjects.lnkAddRefLead.click();
				
			}

//Verify AddNewLeadReferral Heading
@Test
public void VerifyAddNewLeadReferralHeading(){
	Assert.assertTrue(uiHomeAddNewLeadPageObjects.hdAddLead.isDisplayed(),"Add New Lead Referral heading is not displayed");
}

//Verify Referral Info Label
@Test
public void VerifyReferralInfoLabel(){
	Assert.assertTrue(uiHomeAddNewLeadPageObjects.lblRefInfo.isDisplayed(),"Referral information label is not displayed");
}

//Verify Student Providing Referral Label
@Test
public void VerifyStuProReferralLabel(){
	Assert.assertTrue(uiHomeAddNewLeadPageObjects.lblStuProRef.isDisplayed(),"Student Provinding Referral label is not displayed");
}

//Verify  First Name text box
@Test
public void VerifyFirstName(){
	Assert.assertTrue(uiHomeAddNewLeadPageObjects.tbFName.isDisplayed(),"First Name text box is not displayed");
}

//Verify Last Name text box
@Test
public void VerifyLastName(){
	Assert.assertTrue(uiHomeAddNewLeadPageObjects.tbLName.isDisplayed(),"Last Name text box is not displayed");
}

//Verify Email Text box 
@Test
public void VerifyEmail(){
	Assert.assertTrue(uiHomeAddNewLeadPageObjects.lblStuProRef.isDisplayed(),"Student Provinding Referral label is not displayed");
}








@AfterClass
  public void AfterNavigation(){
	  if (driver !=null) {driver.quit();}
			  
  }
  }