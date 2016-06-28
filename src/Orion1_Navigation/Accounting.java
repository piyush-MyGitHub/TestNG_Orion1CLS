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
import uimap_Orion1.AccountingPageObjects;
import uimap_Orion1.Homepage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

	public class Accounting {
			
			//Remote Web driver for remote execution
					public RemoteWebDriver driver = null;
					
					//BrowseManagement to set the browser capabilities
					public BrowserManagement objBrowserMgr = null;
					
					//Home Page Page Object Model
					public Homepage uiHomePageObjects;		
					public AccountingPageObjects uiAccountingPageObjects;			
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
						uiAccountingPageObjects = new AccountingPageObjects(driver);
						
						uiHomePageObjects =new Homepage(driver);
						
						uiAccountingPageObjects.tabAccounting.click();
						
													
					}

		// Verify Accounting Link
		@Test
		public void VerifyAcademicAdvisingLink(){
			Assert.assertTrue(uiAccountingPageObjects.tabAccounting.isDisplayed(), "Accounting Link is not displayed");
		}
		//Verify Shift Delivery Types Link
		@Test
		public void VerifyTeamDirectorLink(){
			Assert.assertTrue(uiAccountingPageObjects.lnkShiftDeliveryTypes.isDisplayed(), "Shift Delivery Types Link is not displayed");
		}
		//Verify Campus Code Management Link
		@Test
		public void VerifyTutoringLink(){
			Assert.assertTrue(uiAccountingPageObjects.lnkCampusCodeManagement.isDisplayed(), "Campus Code Management Link is not displayed");
		}
		//verify Accounting code Management Link
		@Test
		public void VerifyAccountingcodeManagementLink(){
			Assert.assertTrue(uiAccountingPageObjects.lnkAccountingCodeManagement.isDisplayed(), "Accounting code Management Link is not displayed");
		}
		//Verify Output File service Management Link
		@Test
		public void VerifyFileServiceLink(){
			Assert.assertTrue(uiAccountingPageObjects.lnkOutputFileServiceManagement.isDisplayed(), "Output File service Link is not displayed");
		}
		//Verify File Export Utility Link
		@Test
		public void VerifyFileExportUtilityLink(){
			Assert.assertTrue(uiAccountingPageObjects.lnkFileExportUtility.isDisplayed(), "File Export Utility Link is not displayed");
		}
	
		

		//*****Shift Delivery Types Link Page************
		@Test
		public void VerifyAccountingPage(){
			//Navigate to Accounting Page
			uiAccountingPageObjects.tabAccounting.click();
			//Verify Heading
			uiAccountingPageObjects.lnkShiftDeliveryTypes.click();
			

			//Verify Error Shift Delivery Types Link Page
			Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAccountingPageObjects.sCCCShiftDeliveryManagement), "Error Report Page: Error Reporting crum Cell is not as expected");
			
		}
		
		

		//*****Delivery Type Page************
		@Test
		public void VerifyDeliveryType(){
			//Navigate to Delivery Type Link
			uiAccountingPageObjects.tabAccounting.click();
			uiAccountingPageObjects.lnkDeliveryTypes.click();
			

			//Verify Error Shift Delivery Types Link Page
			Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAccountingPageObjects.sCCCShiftDeliveryType), "Error Report Page: Error Reporting crum Cell is not as expected");
			
		}


		//*****Campus code Management Page************
		@Test
		public void VerifyCampusCodeManagementLinkPage(){
			
			
			uiAccountingPageObjects.tabAccounting.click();
			uiAccountingPageObjects.lnkCampusCodeManagement.click();
			
			//Verify Error Campus code Management CrumCell
			Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAccountingPageObjects.sCCCampusCodeManagement), "Error Report Page: Error Reporting crum Cell is not as expected");
		}

		//*****Assignment Exceptions Page************
		@Test
		public void VerifyAssignmentExceptionsPage(){
			
			uiAccountingPageObjects.tabAccounting.click();
			uiAccountingPageObjects.lnkAccountingCodeManagement.click();
			
			//Verify Accounting Code Management Page
			Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAccountingPageObjects.sCCAccountingCodeManagement), "Manage Holiday  Page: Manage Holiday crum Cell is not as expected");
		}

		//*****Output File Service Management Page************
		@Test
		public void VerifyOutputFileManagementPage(){
			
			uiAccountingPageObjects.tabAccounting.click();
			uiAccountingPageObjects.lnkAccountingCodeManagement.click();
			
			//Verify Accounting Code Management Page
			Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAccountingPageObjects.sCCAccountingCodeManagement), "Manage Holiday  Page: Manage Holiday crum Cell is not as expected");
		}

		//*****Verify File Export Utility Page************
		@Test
		public void VerifyFileExportUtilityPage(){
			
			uiAccountingPageObjects.tabAccounting.click();
			uiAccountingPageObjects.lnkFileExportUtility.click();
			
			//Verify File Export Utility Page
			Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiAccountingPageObjects.sCCCFileExportUtility), "Manage Holiday  Page: Manage Holiday crum Cell is not as expected");
		}


		@AfterClass
		  public void AfterNavigation(){
			  if (driver !=null) {driver.quit();}
					  
		  }
		  

	}

