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
import uimap_Orion1.AcademicPage;
import uimap_Orion1.Homepage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

	public class Academic {
		
		//Remote Web driver for remote execution
				public RemoteWebDriver driver = null;
				
				//BrowseManagement to set the browser capabilities
				public BrowserManagement objBrowserMgr = null;
				
				//Home Page Page Object Model
				public Homepage uiHomePageObjects;		
				public AcademicPage uiAcademicPageObjects;			
				//Browser Parameter received from TestNg.xml
				@Parameters({"Browser"})
				@BeforeClass
				public void BeforeNavigation(String sBrowser) throws MalformedURLException
				{
					try{
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
					uiAcademicPageObjects = new AcademicPage(driver);
					
					uiHomePageObjects =new Homepage(driver);
					
					uiAcademicPageObjects.tabAcademics.click();
					}
					catch (Exception e){
						System.out.println(e.getMessage());
					}
					
												
				}

	// Verify Academic Advising Link
	@Test
	public void VerifyAcademicAdvisingLink(){
		Assert.assertTrue(uiAcademicPageObjects.lnkAcademicAdvising.isDisplayed(), "Academic Advising Link is not displayed");
	}
	//Verify Team Director View Link
	@Test
	public void VerifyTeamDirectorLink(){
		Assert.assertTrue(uiAcademicPageObjects.lnkTeamDirectorView.isDisplayed(), "Team Director View Link is not displayed");
	}
	//Verify Tutoring Link
	@Test
	public void VerifyTutoringLink(){
		Assert.assertTrue(uiAcademicPageObjects.lnkTutoring.isDisplayed(), "Tutoring Link is not displayed");
	}
	//verify Manage Task Tracker  Link
	@Test
	public void VerifyAdminLink(){
		Assert.assertTrue(uiAcademicPageObjects.lnkAdmin.isDisplayed(), "Admin Link is not displayed");
	}
	//verify Rep Group Link
	@Test
	public void VerifyRepGroupLink(){
		Assert.assertTrue(uiAcademicPageObjects.lnkRepGroups.isDisplayed(), "Rep Group Link is not displayed");
	}
	//verify Enrollment Exceptions Link
	@Test
	public void VerifyEnrollmentExceptionsLink(){
		Assert.assertTrue(uiAcademicPageObjects.lnkEnrollmentExceptions.isDisplayed(), "Enrollment Exception Link is not displayed");
	}
	//verify Advisor Assignment Link
	@Test
	public void VerifyMLink(){
		Assert.assertTrue(uiAcademicPageObjects.lnkAdvisorAssignment.isDisplayed(), "Advisor Assignment link is not displayed");
	}
	//verify Assignment Exception Link
	@Test
	public void VerifyAssignmentExceptionLink(){
		Assert.assertTrue(uiAcademicPageObjects.lnkAssignmentExceptions.isDisplayed(), "Assignment Exceptions link is not displayed");
	}
	

	//*****Academic Advising Page************
	@Test
	public void VerifyAcademicAdvisingPage(){
		//Navigate to Academic Page
		uiAcademicPageObjects.tabAcademics.click();
		//Verify Heading
		uiAcademicPageObjects.lnkAcademicAdvising.click();
		Assert.assertEquals(uiAcademicPageObjects.txtAcademicAdvising.getText().trim(), "TASK STATS TRACKER");
	}

	//*****Tutoring Page************
	@Test
	public void VerifyTutoringViewPage(){
		//Navigate to Team Director View Link
		uiAcademicPageObjects.tabAcademics.click();
		uiAcademicPageObjects.lnkTutoring.click();
		//verify Tutoring Page Message
		Assert.assertEquals(uiAcademicPageObjects.txtTutoringMessage.getText().trim(), uiAcademicPageObjects.strTutoringMessage);
	
	}

	//*****Admin Page************
	@Test
	public void VerifyAdminPage(){
		
		uiAcademicPageObjects.tabAcademics.click();
		uiAcademicPageObjects.lnkAdmin.click();
				
	Assert.assertEquals(uiAcademicPageObjects.txtAdminPage.getText().trim(), uiAcademicPageObjects.strTextAdmin);
	
	}

	//*****Advisor Assignment Link Page************
	@Test
	public void VerifyAdvisorAssignmentLinkPage(){
		
		uiAcademicPageObjects.tabAcademics.click();
		uiAcademicPageObjects.lnkAdvisorAssignment.click();
		
		//Verify Error Advisor Assignment CrumCell
		Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAcademicPageObjects.sCCCAdvisorAssignment), "Error Report Page: Error Reporting crum Cell is not as expected");
	}

	//*****Assignment Exceptions Page************
	@Test
	public void VerifyAssignmentExceptionsPage(){
		
		uiAcademicPageObjects.tabAcademics.click();
		uiAcademicPageObjects.lnkAssignmentExceptions.click();
		
		//Verify Assignment Exceptions
		Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAcademicPageObjects.sCCCAssignmentExceptions), "Manage Holiday  Page: Manage Holiday crum Cell is not as expected");
	}


	@AfterClass
	  public void AfterNavigation(){
		try{
		  if (driver !=null) {driver.quit();}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
				  
	  }
	  

}
