package Orion1_Navigation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uimap_Orion1.AdminPage;
import uimap_Orion1.Homepage;
import uimap_Orion1.ManageUsersPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

public class ChangeCampus {
	//Remote Web driver for remote execution
	public RemoteWebDriver driver = null;
	
	//BrowseManagement to set the browser capabilities
	public BrowserManagement objBrowserMgr = null;
	
	//Home Page Page Object Model
	public Homepage uiHomePageObjects;		
	public AdminPage uiAdminPageObjects;
	public ManageUsersPage uiManageUserPageObjects;
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
		uiHomePageObjects = new Homepage(driver);
		uiAdminPageObjects = new AdminPage(driver);
		uiManageUserPageObjects = new ManageUsersPage(driver);
		uiHomePageObjects.tabAdmin.click();
		uiAdminPageObjects.lnkManagerUser.click();
		
									
	}

//Search User
@Test
public void SearchUser(){
	uiManageUserPageObjects.imgViewSrchOptions.click();
	uiManageUserPageObjects.tbFirstName.sendKeys(EnvironmentVariables.sAdvisorFname);
	uiManageUserPageObjects.tbLastName.sendKeys(EnvironmentVariables.sAdvisorLname);
	uiManageUserPageObjects.btnSearch.click();
	//VerifySearchUser Results
	Assert.assertTrue(uiManageUserPageObjects.srchrsltFname.getText().trim().equalsIgnoreCase(EnvironmentVariables.sAdvisorFname), "Search Result: Fisrtname is not correct");
	Assert.assertTrue(uiManageUserPageObjects.srchrsltLname.getText().trim().equalsIgnoreCase(EnvironmentVariables.sAdvisorLname), "Search Result: Lastname is not correct");
}

@Test (dependsOnMethods= "SearchUser")
public void ChangetoCampus(){
	uiManageUserPageObjects.btnEdit.click();
	Select s = new Select(uiManageUserPageObjects.ddlDefaultCampus);
	s.selectByVisibleText(EnvironmentVariables.sCampus);	
	uiManageUserPageObjects.btnUpdateUser.click();
	
	
}

@Test (dependsOnMethods= "ChangetoCampus")
public void VerifyChangetoCampus(){
	uiAdminPageObjects.lnkManagerUser.click();
	uiManageUserPageObjects.imgViewSrchOptions.click();
	uiManageUserPageObjects.tbFirstName.sendKeys(EnvironmentVariables.sAdvisorFname);
	uiManageUserPageObjects.tbLastName.sendKeys(EnvironmentVariables.sAdvisorLname);
	uiManageUserPageObjects.btnSearch.click();
	uiManageUserPageObjects.btnEdit.click();
	Select s = new Select(uiManageUserPageObjects.ddlDefaultCampus);
	Assert.assertTrue(s.getFirstSelectedOption().getText().trim().equalsIgnoreCase(EnvironmentVariables.sCampus), "Campus is not changed");   
}



@AfterClass
public void AfterNavigation(){
if (driver !=null) {driver.quit();}
	  
}
}
