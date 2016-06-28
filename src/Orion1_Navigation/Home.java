package Orion1_Navigation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import uimap_Orion1.Homepage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

public class Home {
	
	//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			//Home Page Page Object Model
			public Homepage uiHomePageObjects;		
						
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
				
			}

//Verify Home tab
@Test
public void VerifyHome(){
	Assert.assertTrue(uiHomePageObjects.tabHome.isDisplayed(), "Home tab not displyed");
}

//Verify Admissions
@Test
public void VerifyAdmissions(){
	Assert.assertTrue(uiHomePageObjects.tabAdmissions.isDisplayed(), "Admissions tab not displyed");
}

//Verify University Platform
@Test
public void VerifyUniPlatform(){
	Assert.assertTrue(uiHomePageObjects.tabUniversityPlatform.isDisplayed(), "University Platform tab not displyed");
}			
 
//Verify Academics
@Test
public void VerifyAcademics(){
	Assert.assertTrue(uiHomePageObjects.tabAcademics.isDisplayed(), "Academics tab not displyed");
} 

////Verify Financial Aid
////@Test
//public void VerifyFinancialAid(){
//	Assert.assertTrue(uiHomePageObjects.tabFinAid.isDisplayed(), "Financial Aid tab not displyed");
//}  
  
//Verify Admin
@Test
public void VerifyAdmin(){
	Assert.assertTrue(uiHomePageObjects.tabAdmin.isDisplayed(), "Admin tab not displyed");
}

//Verify Accounting
@Test
public void VerifyAccounting(){
	Assert.assertTrue(uiHomePageObjects.tabAccounting.isDisplayed(), "Accounting tab not displyed");
}

//Verify Registrar
@Test
public void VerifyRegistrar(){
	Assert.assertTrue(uiHomePageObjects.tabRegistrar.isDisplayed(), "Registrar tab not displyed");
}

//Verify Ticketing
@Test
public void VerifyTicketing(){
	Assert.assertTrue(uiHomePageObjects.tabTicketing.isDisplayed(), "Ticketing tab not displyed");
}

//Verify Marketing
@Test
public void VerifyMarketing(){
	Assert.assertTrue(uiHomePageObjects.tabAdmissions.isDisplayed(), "Marketing tab not displyed");
}

//Verify Reports
@Test
public void VerifyReports(){
	Assert.assertTrue(uiHomePageObjects.tabReports.isDisplayed(), "Reports tab not displyed");
}

//Verify Program Maintenance
@Test
public void VerifyProgMaintenance(){
	Assert.assertTrue(uiHomePageObjects.tabProgMaintenance.isDisplayed(), "Program Maintanance tab not displyed");
}

//Verify My Profile Image 
@Test
public void VerifyMyProfileImage(){
	Assert.assertTrue(uiHomePageObjects.imgMyProfile.isDisplayed(), "My Profile Image not displyed");
}

//Verify Welcome Heading
@Test
public void VerifyWelcomeHeading(){
	Assert.assertTrue(uiHomePageObjects.lblWelcome.isDisplayed(), " Welcome heading not displyed");
}

//Verify Student Look up
@Test
public void VerifyStudentLookup(){
	Assert.assertTrue(uiHomePageObjects.lnkStuLookup.isDisplayed(), "Student lookup link not displyed");
}

//Verify Add New Lead/Referral
@Test
public void VerifyAddNewLeadReferral(){
	Assert.assertTrue(uiHomePageObjects.tabAdmissions.isDisplayed(), "Add New Lead/Referral link not displyed");
}

//Verify Logout
@Test
public void VerifyLogout(){
	Assert.assertTrue(uiHomePageObjects.tabAdmissions.isDisplayed(), "Logout link not displyed");
}

@AfterClass
  public void AfterNavigation(){
	  if (driver !=null) {driver.quit();}
			  
  }
  }