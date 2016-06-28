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
import uimap_Orion1.DocumentsImagingPageObjects;
import uimap_Orion1.Homepage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

	public class DocumentsImaging {
				
				//Remote Web driver for remote execution
						public RemoteWebDriver driver = null;
						
						//BrowseManagement to set the browser capabilities
						public BrowserManagement objBrowserMgr = null;
						
						//Home Page Page Object Model
						public Homepage uiHomePageObjects;		
						public DocumentsImagingPageObjects uiDocumentsImagingPageObjects;			
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
							uiDocumentsImagingPageObjects = new DocumentsImagingPageObjects(driver);
							
							uiHomePageObjects =new Homepage(driver);
							
							uiDocumentsImagingPageObjects.tabDocumentsImaging.click();
							
														
						}

			// Verify Documents Link
			@Test
			public void VerifyEditorialLink(){
				Assert.assertTrue(uiDocumentsImagingPageObjects.tabDocumentsImaging.isDisplayed(), "Documents Imaging Link is not displayed");
			}
			//Verify Document Imaging Home Link
			@Test
			public void VerifyDocumentsImagingHomeLink(){
				Assert.assertTrue(uiDocumentsImagingPageObjects.lnkDocumentImagingHome.isDisplayed(), "Documents Imaging Home Link is not displayed");
			}
			//Verify Documents Mapping Link
			@Test
			public void VerifyProgramGroupMappingLink(){
				Assert.assertTrue(uiDocumentsImagingPageObjects.lnkAdmin.isDisplayed(), "Admin Link is not displayed");
			}
			//verify Global Program Mapping Link
			@Test
			public void VerifyGlobalProgramMappingLink(){
				Assert.assertTrue(uiDocumentsImagingPageObjects.lnkDocumentMapping.isDisplayed(), "Document Mapping Link is not displayed");
			}
			
			

			//*****Verify Documents Imaging Home Link Page************
			@Test
			public void VerifyDocumentsImagingHomePage(){
				//Navigate to Documents Imaging Page
				uiDocumentsImagingPageObjects.tabDocumentsImaging.click();
				//Verify Heading
				uiDocumentsImagingPageObjects.lnkDocumentImagingHome.click();
				

				//Verify Search Students text on Documents Imaging Page
				Assert.assertEquals(uiDocumentsImagingPageObjects.txtSearchStudents.getText().trim(), "Search Students");
				
			}
			
			

			//*****Document Scheduling Page************
			@Test
			public void VerifyDocumentsSchedulingPage(){
				//Navigate to Program Group Mapping Link
				uiDocumentsImagingPageObjects.tabDocumentsImaging.click();
				uiDocumentsImagingPageObjects.lnkDocumentScheduling.click();
				

				//Verify Error Program Group Mapping Link Page
				Assert.assertEquals(uiDocumentsImagingPageObjects.txtDocumentMappingPage.getText().trim(), "Display Students");
				
			}



			@AfterClass
			  public void AfterNavigation(){
				  if (driver !=null) {driver.quit();}
						  
			  }
			  

		}
