package orion1_NewLeads;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import orion1_NewLeadsPageObject.AddNewLeadPageObjects;
import uimap_Orion1.AdmissionsManagerPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

	public class Lead_Referral {


			
					//Remote Web driver for remote execution
					public RemoteWebDriver driver = null;
					
					//BrowseManagement to set the browser capabilities
					public BrowserManagement objBrowserMgr = null;
					
					public String mainwinhandle;
					public int winhancountafterLeadsubmit,initialwinhancount ;
					
					//Home Page Page Object Model
					//public HomePageObjects uiHomePageObjects;
					//public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
					public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
					public AdmissionsManagerPage uiAdmMgrPageObjects;
					
					
					//Variables from Properties file
					public String sLeadType;
					public String sChannelGroup;
					public String sAreaOfStudy;
					public String sProgramofInterest;
					public String sBechalorDegree;
					public String sSpouseMilitary;
					public String sSpouseMilitaryType;
					public String sTCPA;
					public String sHighestEduction;
					
					//Static variable
					String sRandStr = RandomStringUtils.randomAlphabetic(5);
					public String sFirstName = "TestNGFNRfl_" + sRandStr;
					public String sLastName = "TestNGLNRfl_" + sRandStr;			
					public String sEmailAddress = sFirstName + "@kap.com";
					public String sAddressLine1 = "kaplan";
					public String sCity = "NewYork";
					public String sDayTimePhone ="9545151234";
					public String sZipCode = "30256";
					
					
					//Method which will executed before the class loads
					//Browser Parameter received from TestNg.xml
					@Parameters({"Browser"})
					@BeforeClass
					public void BeforeNavigation(String sBrowser) throws MalformedURLException
					{
						try{
						//Read the application properties file
						//Load environment variable from properties file
						String sPath_AppProperties="";
						FileInputStream objFileInputStream = null;
						Properties objProperties = new Properties();
						
						//Set file path as per environment
						if (EnvironmentVariables.sEnv.equalsIgnoreCase("dev"))
						{
							sPath_AppProperties = ".//Resources//ApplicationProperties/DevApplication.properties";
						}
						else if (EnvironmentVariables.sEnv.equalsIgnoreCase("stage"))
						{
							sPath_AppProperties = ".//Resources//ApplicationProperties/StageApplication.properties";			
						}
						else if (EnvironmentVariables.sEnv.equalsIgnoreCase("lt"))
						{
							sPath_AppProperties = ".//Resources//ApplicationProperties/LtApplication.properties";			
						}
						else
						{
							sPath_AppProperties = ".//Resources//ApplicationProperties/TestApplication.properties";			
						}
						
						//Load the Application variable file into File Input Stream.
						File objFileApplication = new File(sPath_AppProperties);
						try
						{
							objFileInputStream = new FileInputStream(objFileApplication);
						}catch (FileNotFoundException ex)
						{
							ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
						}
						
						//Load the File Input Stream into the Properties file
						try
						{
							objProperties.load(objFileInputStream);
							
						} catch (IOException ex) {

							ReportExtn.Fail("Unable to Read the Properties file" +  ex.getMessage());
						}
						
						//Reading from Properties file
						sLeadType = objProperties.getProperty("sLeadType");
						sChannelGroup = objProperties.getProperty("sChannelGroup");
						sTCPA = objProperties.getProperty("sTCPADisclosure");
						//sBechalorDegree = objProperties.getProperty("sBechalorDegree");
						sSpouseMilitary = objProperties.getProperty("sSpouseMilitaryType");
						sHighestEduction = objProperties.getProperty("sHighestEduction");
						sAreaOfStudy = objProperties.getProperty("sAreaOfStudy");
						sProgramofInterest = objProperties.getProperty("sProgramofInterest");
									
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
							ReportExtn.Fail("Unable to create the Remotedriver" +  ex.getMessage());			
						}
						driver.get(EnvironmentVariables.sUrl_Orion1);
						mainwinhandle = driver.getWindowHandle();
						driver.manage().window().maximize();
						driver.switchTo().frame("Orion");	
						}
						catch (Exception e){
						System.out.println(e.getMessage());	
						}
					}
					
					@AfterClass
					public void AfterNavigation()
					{try{
						//Quit the test after test class execution
						if(driver != null)
						{
							driver.quit();			
						}
					}
					catch (Exception e){
						System.out.println(e.getMessage());
					}
					}

					//Browse to Add new Lead page
					@Test
					public void BrowseToAddNewLeadPage(Method objMethod)
					{	try{
							//Instantiating the Add Lead page
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							uiAddNewLeadsPageObjects.tabAdmissions.click();
							UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.lnkAdmissionsManager);
							uiAddNewLeadsPageObjects.lnkAdmissionsManager.click();
							UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.lblAddNewLeadReferral);
							uiAddNewLeadsPageObjects.lblAddNewLeadReferral.click();
							Thread.sleep(5000);
							//switching to New Lead Page
							driver.switchTo().window(uiAddNewLeadsPageObjects.sAddNewLead_WindowName);
							
							//Verify InfoCall rb is checked
							Assert.assertEquals(uiAddNewLeadsPageObjects.rbtnLeadType_InfoCall.getAttribute("checked"), "true", "Info Call Lead Type is not selected");
							System.out.println("win is switched "+ uiAddNewLeadsPageObjects.rbtnLeadType_InfoCall.getAttribute("checked"));
						}
						catch(Exception e){
							System.out.println(e.getMessage());
												}
					}
				
					@Test (dependsOnMethods={"BrowseToAddNewLeadPage"})
					public void CreateReferralLead(Method objMethod)
					{	try{
						
						
							//Wait for the presence of Promotion Code dROPDOWN
							UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.txtFirstName);
							System.out.println("fIRST NAME element present");
							uiAddNewLeadsPageObjects.rbtnLeadType_Referral.click();
							
														
							//Enter First name
							uiAddNewLeadsPageObjects.txtFirstName.sendKeys(sFirstName);
							//Enter Last name
							uiAddNewLeadsPageObjects.txtLastName.sendKeys(sLastName);
							//Enter EmailAddress
							uiAddNewLeadsPageObjects.txtEmailAddress.sendKeys(sEmailAddress);
							//Enter Phone
							driver.findElement(By.xpath("//td[text()='Primary Phone:']/following-sibling::td/input")).sendKeys(sDayTimePhone);
							//Enter zip code
							driver.findElement(By.xpath("//td[text()='Zip Code/Postal Code:']/following-sibling::td/input")).sendKeys(sZipCode);
													
															
							//TCPA Disclosure
							driver.findElement(By.xpath("//input[contains(@id, 'TCPA')][@value='No']")).click();
							
							//win handle count before Clicking Submit Lead
							initialwinhancount = driver.getWindowHandles().size();
							System.out.println("initialwinhancount: "+initialwinhancount);
							
							//Click Submit Lead
							uiAddNewLeadsPageObjects.btnAddALead.click();
							Thread.sleep(15000);

							//win handle count after clicking Submit Lead
							winhancountafterLeadsubmit = driver.getWindowHandles().size();
							System.out.println("winhancountafterLeadsubmit: "+winhancountafterLeadsubmit);
							
							//Verify add LEad by Asserting win handles counts
							Assert.assertEquals(winhancountafterLeadsubmit, 1);
							}
						catch(Throwable e){
							System.out.println(e.getMessage());
						}
					}
					
					//Verify Lead in Admissions Manager
					@Test (dependsOnMethods={"CreateReferralLead"})
					public void VerifyLeadInAdmisssionManager(Method objMethod)
					{ try
						{	uiAdmMgrPageObjects  = new AdmissionsManagerPage(driver) ;
							//switching to main window
							driver.switchTo().window(mainwinhandle);
							//select orion frame
							driver.switchTo().frame("Orion");
												
							//Navigate to Admissions Manager
							uiAddNewLeadsPageObjects.tabAdmissions.click();
							uiAddNewLeadsPageObjects.lnkAdmissionsManager.click();
							
							System.out.println("String to Search: "+sLastName+", "+sLastName);
							
							//Call Reusable method to wait for Lead
							Orion1_Reusable.ReusableMethods.WaitforLeadinAdmManager(driver, 30000, sFirstName, sLastName);

							//Verify Lead in NewLeads Queue
							Assert.assertTrue(uiAdmMgrPageObjects.lnkFirstLeadinAdmMgr.getText().trim().equalsIgnoreCase(sLastName+", "+sFirstName));
							System.out.println("assertion passes for "+ uiAdmMgrPageObjects.lnkFirstLeadinAdmMgr.getText());
						}
						catch (Exception e)
						{
							System.out.println(e.getMessage());
						}
						
					}	
				
					}			
					

