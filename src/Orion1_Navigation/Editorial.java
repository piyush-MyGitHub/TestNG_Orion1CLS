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
import uimap_Orion1.EditorialPageObjects;
import uimap_Orion1.Homepage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

	public class Editorial {
			
			//Remote Web driver for remote execution
					public RemoteWebDriver driver = null;
					
					//BrowseManagement to set the browser capabilities
					public BrowserManagement objBrowserMgr = null;
					
					//Home Page Page Object Model
					public Homepage uiHomePageObjects;		
					public EditorialPageObjects uiEditorialPageObjects;			
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
						uiEditorialPageObjects = new EditorialPageObjects(driver);
						
						uiHomePageObjects =new Homepage(driver);
						
						uiEditorialPageObjects.tabEditorial.click();
						
													
					}

		// Verify Editorial Link
		@Test
		public void VerifyEditorialLink(){
			Assert.assertTrue(uiEditorialPageObjects.tabEditorial.isDisplayed(), "Accounting Link is not displayed");
		}
		//Verify Program Mapping Link
		@Test
		public void VerifyProgramMappingLink(){
			Assert.assertTrue(uiEditorialPageObjects.lnkProgramMapping.isDisplayed(), "Program Group Mapping Link is not displayed");
		}
		//Verify Program Group Mapping Link
		@Test
		public void VerifyProgramGroupMappingLink(){
			Assert.assertTrue(uiEditorialPageObjects.lnkProgramGroupMapping.isDisplayed(), "Program Group Mapping Link is not displayed");
		}
		//verify Global Program Mapping Link
		@Test
		public void VerifyGlobalProgramMappingLink(){
			Assert.assertTrue(uiEditorialPageObjects.lnkGlobalProgramMapp.isDisplayed(), "Global Program Mapping Link is not displayed");
		}
		
		

		//*****Verify Program Mapping Link Page************
		@Test
		public void VerifyProgramMappingPage(){
			//Navigate to Editorial Page
			uiEditorialPageObjects.tabEditorial.click();
			//Verify Heading
			uiEditorialPageObjects.lnkProgramMapping.click();
			

			//Verify Error Program Mapping Link Page
			Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiEditorialPageObjects.sCCCProgramMapping), "Error Report Page: Error Program Mapping crum Cell is not as expected");
			
		}
		
		

		//*****Program Group Mapping Page************
		@Test
		public void VerifyProgramGroupMappingPage(){
			//Navigate to Program Group Mapping Link
			uiEditorialPageObjects.tabEditorial.click();
			uiEditorialPageObjects.lnkProgramGroupMapping.click();
			

			//Verify Error Program Group Mapping Link Page
			Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiEditorialPageObjects.sCCCProgramGroupMapping), "Error Report Page: Error Program Group Mapping crum Cell is not as expected");
			
		}


		//*****Global Program Mapping Page************
		@Test
		public void VerifyGlobalProgramMappingPage(){
			
			
			uiEditorialPageObjects.tabEditorial.click();
			uiEditorialPageObjects.lnkGlobalProgramMapp.click();
			
			//Verify Error Global Program Mapping CrumCell
			
			Assert.assertEquals(uiEditorialPageObjects.txtGlobalProgramMapp.getText().trim(),"LMS Program Name");
			
//Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiEditorialPageObjects.sCCCGlobalProgramMapping), "Error Report Page: Error Program Group Mapping crum Cell is not as expected");
		}

		

		@AfterClass
		  public void AfterNavigation(){
			  if (driver !=null) {driver.quit();}
					  
		  }
		  

	}

