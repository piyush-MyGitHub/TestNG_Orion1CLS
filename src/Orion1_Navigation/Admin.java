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

import uimap_Orion1.AdminPage;
import uimap_Orion1.Homepage;
import uimap_Orion1.StudenLookupPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

public class Admin {
	
	//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			//Home Page Page Object Model
			public Homepage uiHomePageObjects;		
			public AdminPage uiAdminPageObjects;			
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
				uiAdminPageObjects = new AdminPage(driver);
				uiHomePageObjects.tabAdmin.click();
				
											
			}

// verify Create User Link
@Test
public void VerifyCreateUserLink(){
	Assert.assertTrue(uiAdminPageObjects.lnkCreateUser.isDisplayed(), "Create User Link is not displayed");
}
//verify Manage User Link
@Test
public void VerifyManageUserLink(){
	Assert.assertTrue(uiAdminPageObjects.lnkManagerUser.isDisplayed(), "Manage User Link is not displayed");
}
//verify Manage Departments Link
@Test
public void VerifyManageDepartmentsLink(){
	Assert.assertTrue(uiAdminPageObjects.lnkManageDepts.isDisplayed(), "Manage Departments Link is not displayed");
}
//verify Manage Task Tracker  Link
@Test
public void VerifyManageTaskTrackerLink(){
	Assert.assertTrue(uiAdminPageObjects.lnkManageTaskTracker.isDisplayed(), "Manage Task Tracker Link is not displayed");
}
//verify Error Reporting  Link
@Test
public void VerifyErrorReportingLink(){
	Assert.assertTrue(uiAdminPageObjects.lnkErrorReporting.isDisplayed(), "Error Reporting Link is not displayed");
}
//verify Residential Data Migration Mgmt link
@Test
public void VerifyResDataMigLink(){
	Assert.assertTrue(uiAdminPageObjects.lnkResidentialDataMigrationMgmt.isDisplayed(), "Residential Data Migration Mgmt link Link is not displayed");
}
//verify Manage Campus Link
@Test
public void VerifyManageAssignExReasonLink(){
	Assert.assertTrue(uiAdminPageObjects.lnkManageAssignmentExceptionReason.isDisplayed(), "Manage Assignment Exception Reason link is not displayed");
}
//verify Manage Assignment Exception Reason Link
@Test
public void VerifyManageCampusLink(){
	Assert.assertTrue(uiAdminPageObjects.lnkManageAssignmentExceptionReason.isDisplayed(), "Manage Campus link is not displayed");
}
//verify Manage Holiday Link
@Test
public void VerifyManageHolidayLink(){
	Assert.assertTrue(uiAdminPageObjects.lnkManageHoliday.isDisplayed(), "Manage Holiday link is not displayed");
}
//verify Component Manager Link
@Test
public void VerifyComponentMgrLink(){
	Assert.assertTrue(uiAdminPageObjects.lnkComponentManager.isDisplayed(), "Component Manager link is not displayed");
}


//*****Create User Page************
@Test
public void VerifyCreateUserPage(){
	//Navigate to Create User
	uiAdminPageObjects.lnkCreateUser.click();
	//Verify Heading
	Assert.assertTrue(uiAdminPageObjects.hdngCreateUser.isDisplayed(), "CreateUser Page: CreateUser heading is not displayed");
	//Verify CreateUser CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdminPageObjects.sCCCCreateUser), "CreateUser Page: CreateUser crum Cell is not as expected");
}

//*****Manage User Page************
@Test
public void VerifyManageUserPage(){
	//Navigate to Manage User
	uiAdminPageObjects.lnkManagerUser.click();
	//verify SearchUsers Heading
	Assert.assertTrue(uiAdminPageObjects.hdngSearchUsers.isDisplayed(), "ManageUser Page: SearchUsers heading is not displayed");
	//Verify ManageUser CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdminPageObjects.sCCCManageUsers), "ManageUser Page: ManageUSer crum Cell is not as expected");
}

//*****Manage Dept Page************
@Test
public void VerifyManageDepartmentPage(){
	//navigate to Manage Departments
	uiAdminPageObjects.lnkManageDepts.click();
	//Verify Heading
	Assert.assertTrue(uiAdminPageObjects.hdngDepartment.isDisplayed(), "ManageDepartment Page: Department heading is not displayed");
	//Verify Manage Department CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdminPageObjects.sCCCManageDepts), "ManageDept Page: ManageDept crum Cell is not as expected");
}

//*****Error Reporting Page************
@Test
public void VerifyErrorReportingPage(){
	//Navigate to Error Reporting 
	uiAdminPageObjects.lnkErrorReporting.click();
	//Verify Heading
	Assert.assertTrue(uiAdminPageObjects.hdngErrorReport.isDisplayed(), "Error Reporting Page: Error Report heading is not displayed");
	//Verify Error Reporting CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdminPageObjects.sCCCErrorReport), "Error Report Page: Error Reporting crum Cell is not as expected");
}

//*****Manage Holiday Page************
@Test
public void VerifyManageHolidayPage(){
	//Navigate to Manage Holiday
	uiAdminPageObjects.lnkManageHoliday.click();
	//Verify Heading
	Assert.assertTrue(uiAdminPageObjects.hdngManageHoliday.isDisplayed(), "Manage Holiday  Page:  Manage Holiday heading is not displayed");
	//Verify Manage Holiday CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdminPageObjects.sCCCManageHoliday), "Manage Holiday  Page: Manage Holiday crum Cell is not as expected");
}


//*****Manage campuses Page************
@Test
public void VerifyManageCampusPage(){
	//Navigate to Manage Campus
	uiAdminPageObjects.lnkManageCampus.click();
	//Verify Manage campus heading
	Assert.assertTrue(uiAdminPageObjects.hdngManageCampuses.isDisplayed(), "Manage campus  Page:  Manage Campus heading is not displayed");
	//Verify Manage Campus CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdminPageObjects.sCCCManageCampus), "Manage Campus Page: Manage Campus crum Cell is not as expected");
}


//*****Manage Task Tracker Page************
//*****Component Manager Page************
@Test
public void VerifyComponentManagerPage(){
	//Navigate to Component Manager
	uiAdminPageObjects.lnkComponentManager.click();
	//Verify heading
	Assert.assertTrue(uiAdminPageObjects.lnkComponentManager.isDisplayed(), "Component Manager Page: heading is not displayed");
	//Verify CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdminPageObjects.sCCCComponentMgr), "Component Manager Page: crum Cell is not as expected");
}

//***** Manage Residential Data Migration Page************
@Test
public void VerifyResDataMigrationPage(){
	//Navigate to Residential Data Migration
	uiAdminPageObjects.lnkResidentialDataMigrationMgmt.click();
	//Verify Manage Residential Data Migration  heading
	Assert.assertTrue(uiAdminPageObjects.lnkResidentialDataMigrationMgmt.isDisplayed(), "Residential Data Migration Page: heading is not displayed");
	//Verify Residential Data Migration CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdminPageObjects.sCCCManageResicamMig), "Residential Data Migration Page: Crum Cell is not as expected");
}




@AfterClass
  public void AfterNavigation(){
	  if (driver !=null) {driver.quit();}
			  
  }
  }