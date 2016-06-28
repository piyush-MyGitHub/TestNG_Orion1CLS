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
import uimap_Orion1.TicketingPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

	public class Ticketing {
				
				//Remote Web driver for remote execution
						public RemoteWebDriver driver = null;
						
						//BrowseManagement to set the browser capabilities
						public BrowserManagement objBrowserMgr = null;
						
						//Home Page Page Object Model
						public Homepage uiHomePageObjects;		
						public TicketingPageObjects uiTicketingPageObjects;			
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
							uiTicketingPageObjects = new TicketingPageObjects(driver);
							
							uiHomePageObjects =new Homepage(driver);
							
							//uiTicketingPageObjects.tabTicketing.click();
							
						}

			//Verify Ticketing Home Link
			@Test
			public void VerifyTicketingHomeLink(){
				uiTicketingPageObjects = new TicketingPageObjects(driver);
				uiTicketingPageObjects.tabTicketing.click();
				//Assert.assertTrue(uiTicketingPageObjects.tabTicketing.isDisplayed(), "Ticketing Home Link is not displayed");
				//Assert.assertTrue(uiTicketingPageObjects.lnkTicketingHome.isDisplayed(), "Ticketing Home Link is not displayed");
			}
			//Verify Ticketing Dashboard Link
			/*@Test
			public void VerifyTicketingDashboardLink(){
				Assert.assertTrue(uiTicketingPageObjects.lnkTicketingDashboard.isDisplayed(), "Ticketing Dashboard Link is not displayed");
			}
			//verify Ticketing Admin Link
			@Test
			public void VerifyTicketingAdminLink(){
				Assert.assertTrue(uiTicketingPageObjects.lnkTicketingAdmin.isDisplayed(), "Ticketing Admin Link is not displayed");
			}
			//Verify Activity Type Admin Link
			@Test
			public void VerifyActivityTypeAdminLink(){
				Assert.assertTrue(uiTicketingPageObjects.lnkActivityTypeAdmin.isDisplayed(), "Activity Type Admin Link is not displayed");
			*/
			
			@AfterClass
			  public void AfterNavigation(){
				  if (driver !=null) {driver.quit();}
						  
			  }
			  

		}
