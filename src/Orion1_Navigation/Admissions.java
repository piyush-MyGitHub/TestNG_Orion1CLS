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
import uimap_Orion1.AdmissionsManagerPage;
import uimap_Orion1.AdmissionsPage;
import uimap_Orion1.Admissions_AdminPage;
import uimap_Orion1.Homepage;
import uimap_Orion1.ScriptManagerPage;
import uimap_Orion1.SkillCutoffManagerPage;
import uimap_Orion1.StudenLookupPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

public class Admissions {
	
	//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			//Home Page Page Object Model
			public Homepage uiHomePageObjects;		
			public AdmissionsPage uiAdmissionsPageObjects;	
			public Admissions_AdminPage uiAdmsAdminPageObjects;
			public AdmissionsManagerPage uiAdmManagerPageObjects;
			public ScriptManagerPage uiScriptManagerPageObjects;
			public SkillCutoffManagerPage uiskillscutoffMgrPageObjects;
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
					driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
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
				uiHomePageObjects=new Homepage(driver);		
				uiAdmissionsPageObjects= new AdmissionsPage(driver);	
				uiAdmsAdminPageObjects= new Admissions_AdminPage(driver);
				uiAdmManagerPageObjects= new AdmissionsManagerPage(driver);
				uiScriptManagerPageObjects= new ScriptManagerPage(driver);
				uiskillscutoffMgrPageObjects = new SkillCutoffManagerPage(driver); 
				uiHomePageObjects.tabAdmissions.click();
	}

//skills cut off manager Link
@Test
public void VerifySkillsCutoffMgrLink(){
	Assert.assertTrue(uiAdmissionsPageObjects.lnkSkillsCutoffMgr.isDisplayed(), "skills cut off manager link is not displayed");
}
//Script Manager Link
@Test
public void VerifyScriptManagerLink(){
	Assert.assertTrue(uiAdmissionsPageObjects.lnkScriptMgr.isDisplayed(), "Script Manager link is not displayed");
}
//ADmin Link
@Test
public void VerifyAdminLink(){
	Assert.assertTrue(uiAdmissionsPageObjects.lnkadmin.isDisplayed(), "Admin link is not displayed");
}
//Operations Link
@Test
public void VerifyOperationsLink(){
	Assert.assertTrue(uiAdmissionsPageObjects.lnkOperations.isDisplayed(), "Operations link is not displayed");
}
//Admissions Manager Link
@Test
public void VerifyAdmissionsManagerLink(){
	Assert.assertTrue(uiAdmissionsPageObjects.lnkAdmissionsManager.isDisplayed(), "Admissions Manager link is not displayed");
}
//Enrollment Exceptions Tracker Link
@Test
public void VerifyEnrollmentExceptionsLink(){
	Assert.assertTrue(uiAdmissionsPageObjects.lnkEnrollmentExceptions.isDisplayed(), "Enrollment Exceptions link is not displayed");
}
//Bonus Tracker Link
@Test
public void VerifyBonusTrackerLink(){
	Assert.assertTrue(uiAdmissionsPageObjects.lnkBonusTracker.isDisplayed(), "Bonus Tracker link is not displayed");
}
//Appraisals Manager Link
@Test
public void VerifyAppraisalsLink(){
	Assert.assertTrue(uiAdmissionsPageObjects.lnkAppraisals.isDisplayed(), "Appraisals link is not displayed");
}
//Settings Link
@Test
public void VerifySettingsLink(){
	Assert.assertTrue(uiAdmissionsPageObjects.lnkSettings.isDisplayed(), "Settings link is not displayed");
}
// Enter Admissions Manager Button
public void VerifyEnterAdmissionsManagerBtn(){
	Assert.assertTrue(uiAdmissionsPageObjects.btnEnterAdMgr.isDisplayed(), "Enter Admissions Manager Button is not displayed");
}

//Verify Operations
public void VerifyOperations(){
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdmissionsPageObjects.sCCCOperatios), "Operations Page is not displayed correctly");
}
//Verify Appraisals
public void VerifyAppraisals(){
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdmissionsPageObjects.sCCCAppraisals), "Appraisals Page is not displayed correctly");
}
//Verify Bonus Tracker
public void VerifyBonusTracker(){
Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdmissionsPageObjects.sCCCBonustracker), "Bonus Tracker Page is not displayed correctly");
}
//Verify Enrollment Exceptions
public void VerifyEnrollEx(){
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdmissionsPageObjects.sCCCEnrollEx), "EnrollMent Exceptions Page is not displayed correctly");
}
//Verify Setting
public void VerifySettings(){
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdmissionsPageObjects.sCCCSetting), "Settings Page is not displayed correctly");
}


//*****Skills Cut Off Manager Page************
@Test
public void VerifySkillsCutOffManagerPage(){
	//Navigate to Skills Cutoff Manager 
	uiAdmissionsPageObjects.lnkSkillsCutoffMgr.click();
	//Verify CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiskillscutoffMgrPageObjects.sCCCSkillCutoffManager), "skills CutOff Manager crum Cell is not as expected");
	//Assign Skills
	Assert.assertTrue(uiskillscutoffMgrPageObjects.lnkAssignSkills.isDisplayed(), "Assign Skills link is not displayed");
	//Assign Cut Off
	Assert.assertTrue(uiskillscutoffMgrPageObjects.lnkAssignCutoff.isDisplayed(), "Assign Cutoff link is not displayed");
	//Current Cut Off
	Assert.assertTrue(uiskillscutoffMgrPageObjects.lnkAssignSkills.isDisplayed(), "Current CutOff  link is not displayed");
}

//*****Script Manager Page************
@Test
public void VerifyScriptManagerPage(){
	//Navigate to Script anager 
	uiAdmissionsPageObjects.lnkScriptMgr.click();
	//Verify CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiScriptManagerPageObjects.sCCCScriptManager), "ScriptManager crum Cell is not as expected");
	//Delete Button
	Assert.assertTrue(uiScriptManagerPageObjects.btnDelete.isDisplayed(), "Delete Button is not displayed");
	//Add New Button 
	Assert.assertTrue(uiScriptManagerPageObjects.btnAddNew.isDisplayed(), "Add New Button is not displayed");
	//Save Button
	Assert.assertTrue(uiScriptManagerPageObjects.btnSave.isDisplayed(), "Save Button is not displayed");
}

//*****Admissions Admin Page************
@Test
public void VerifyAdmissionAdminPage(){
	//Navigate to Admissions Admin  
	uiAdmissionsPageObjects.lnkadmin.click();
	//Verify CrumCell
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAdmsAdminPageObjects.sCCCAdmin), "ADmissions Admin crum Cell is not as expected");
	//Manage Reps link
	Assert.assertTrue(uiAdmsAdminPageObjects.lnkManageReps.isDisplayed(), "Manage Reps Link is not displayed");
	//Manage Teams
	Assert.assertTrue(uiAdmsAdminPageObjects.lnkManageTeams.isDisplayed(), "Manage Teams Link is not displayed");
	//Reassign Leads
	Assert.assertTrue(uiAdmsAdminPageObjects.lnkReassignLeads.isDisplayed(), "Reassign Leads Link is not displayed");
	//Reassign Leads to CRM
	Assert.assertTrue(uiAdmsAdminPageObjects.lnkReassignLeadsToCRM.isDisplayed(), "Reassign Leads to CRM Link is not displayed");
	//Manage PIC
	Assert.assertTrue(uiAdmsAdminPageObjects.lnkManagePIC.isDisplayed(), "Manage PIC Link is not displayed");
	//Iwd Lead Transfer
	Assert.assertTrue(uiAdmsAdminPageObjects.lnkiWDLeadTransfer.isDisplayed(), "IWD Lead Transfer Link is not displayed");
	//Manage Managers
	Assert.assertTrue(uiAdmsAdminPageObjects.lnkManageManagers.isDisplayed(), "Manage Managers Link is not displayed");
	//Manage SACs
	Assert.assertTrue(uiAdmsAdminPageObjects.lnkManageSACs.isDisplayed(), "Manage SACs Link is not displayed");
	//Search Reps
	Assert.assertTrue(uiAdmsAdminPageObjects.lnkSearchReps.isDisplayed(), "Search Reps Link is not displayed");
	//Manage Emails
	Assert.assertTrue(uiAdmsAdminPageObjects.lnkManageEmails.isDisplayed(), "Manage Emails Link is not displayed");
}

//*****Admissions Manager Page************
@Test
public void VerifyAdmissionsManagerPage(){
	//Navigate to Admissions Manager 
	uiAdmissionsPageObjects.lnkAdmissionsManager.click();
	//Verify Add New Lead Button
	Assert.assertTrue(uiAdmManagerPageObjects.btnAddNewLead.isDisplayed(), "Add New Lead button is not displayed");
	//Verify Leads
	Assert.assertTrue(uiAdmManagerPageObjects.lnkLeads.isDisplayed(), "Leads link is not displayed");
	//Verify Follow UP
	Assert.assertTrue(uiAdmManagerPageObjects.lnkFollowUp.isDisplayed(), "Follow up link is not displayed");
	//Verify Interview
	Assert.assertTrue(uiAdmManagerPageObjects.lnkInterviews.isDisplayed(), "Interviews link is not displayed");
	//Verify Funding
	Assert.assertTrue(uiAdmManagerPageObjects.lnkFunding.isDisplayed(), "Funding link is not displayed");
	//Verify Pending Enrollment
	Assert.assertTrue(uiAdmManagerPageObjects.lnkPendingEnrollments.isDisplayed(), "Pending Enrollments link is not displayed");
	//Verify Start Manager
	Assert.assertTrue(uiAdmManagerPageObjects.lnkStartManager.isDisplayed(), "Start Manager link is not displayed");
	//Verify Current Link
	Assert.assertTrue(uiAdmManagerPageObjects.lnkCurrent.isDisplayed(), "Current link is not displayed");
	//Verify Search Link
	Assert.assertTrue(uiAdmManagerPageObjects.lnkSearch.isDisplayed(), "Search link is not displayed");
}
@AfterClass
  public void AfterNavigation(){
	  if (driver !=null) {driver.quit();}
			  
  }
  }