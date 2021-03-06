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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

	public class Lead_LiveChat {


			
					//Remote Web driver for remote execution
					public RemoteWebDriver driver = null;
					
					//BrowseManagement to set the browser capabilities
					public BrowserManagement objBrowserMgr = null;
					
					public String mainwinhandle;
					public int initialwinhancount,winhancountafterLeadsubmit ;
					
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
					public String sTCPA;
					public String sHighestEducation;
					
					//Static variable
					String sRandStr = RandomStringUtils.randomAlphabetic(5);
					public String sFirstName = "TestNGFNLC_" + sRandStr;
					public String sLastName = "TestNGLNLC_" + sRandStr;			
					public String sEmailAddress = sFirstName + "@kap.com";
					public String sAddressLine1 = "kaplan";
					public String sCity = "NewYork";
					public String sPhone ="9545151234";
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
						sBechalorDegree = objProperties.getProperty("sBechalorDegree");
						sSpouseMilitary = objProperties.getProperty("sSpouseMilitaryType");
						sHighestEducation = objProperties.getProperty("sHighestEduction");
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
						catch(Exception e){
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
					catch(Exception e){
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
							ReportExtn.Fail(e.getMessage());
												}
					}
				
					
					/*@Test(dependsOnMethods={"BrowseToAddNewLeadPage"})
					public void Leads_ClassificationDetails(Method objMethod)
					{
						Assert.assertEquals(uiAddNewLeadsPageObjects.rbtnLeadType_InfoCall.getAttribute("checked"), "true", "Info Call Lead Type is not getting selected");
						uiAddNewLeadsPageObjects.rbtnLeadType_LiveChat.click();
						UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.ddlPromotionCode);
						Select ddlPromotionCode = new Select(uiAddNewLeadsPageObjects.ddlPromotionCode);
						//Select ChannelGroup
						//uiAddNewLeadsPageObjects.ddlPromotionCode = UserExtension.GetStaleElement(driver, uiAddNewLeadsPageObjects.ddlPromotionCodeLocator);
						
						uiAddNewLeadsPageObjects.SelectChannelGroupAs(sChannelGroup);
						
						WebDriverWait wait = new WebDriverWait(driver, 10);
						WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("CtlApplyForm1_ddPromotion")));
							
						//Select Promotion
						ddlPromotionCode.selectByIndex(1);
					}
						
						

						@Test(dependsOnMethods={"Leads_ClassificationDetails"})
						public void Leads_ProgramOfInterest(Method objMethod)
						{					
							Select ddlAreaOfStudy = new Select(uiAddNewLeadsPageObjects.ddlAreaOfStudyTemp);				
							//Area of Study
							
							ddlAreaOfStudy.selectByVisibleText(sAreaOfStudy);
							//Program of Interest
							Select ddlProgramOfInterest = new Select(uiAddNewLeadsPageObjects.ddlProgramOfInterest);
							
							ddlProgramOfInterest.selectByVisibleText(sProgramofInterest);				
						}
						
						@Test(dependsOnMethods={"Leads_ProgramOfInterest"})
						public void Leads_Details(Method objMethod)
						
						{
							
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							
							//Select Saturation
							
							Select ddlSaturationCode = new Select(uiAddNewLeadsPageObjects.ddSaturation);
							
							ddlSaturationCode.selectByVisibleText("Mr.");
							//uiAddNewLeadsPageObjects.ddSaturation.sendKeys(sFirstName);
							uiAddNewLeadsPageObjects.txtFirstName.sendKeys(sFirstName);
							uiAddNewLeadsPageObjects.txtLastName.sendKeys(sLastName);
							uiAddNewLeadsPageObjects.txtEmailAddress.sendKeys(sEmailAddress);
							
							uiAddNewLeadsPageObjects.txtAddress.sendKeys(sAddressLine1);
							
							uiAddNewLeadsPageObjects.txtCity.sendKeys(sCity);
							
							uiAddNewLeadsPageObjects.txtHomePhone.sendKeys(sPhone);
							uiAddNewLeadsPageObjects.txtZipCode.sendKeys(sZipCode);
							
							//Country select
							
	           Select ddlCountry = new Select(uiAddNewLeadsPageObjects.ddlCountry);
							
	                ddlCountry.selectByVisibleText("United States");
							//Spouse Military Status
							if(sBechalorDegree.equalsIgnoreCase("yes"))
							{
								uiAddNewLeadsPageObjects.rbtnBechalor_degree_yes.click();					
							}
							else
							{
								uiAddNewLeadsPageObjects.rbtnBechalor_degree_No.click();
							}
								
							
							//TCPA Disclosure
							if(sTCPA.equalsIgnoreCase("yes"))
							{
								uiAddNewLeadsPageObjects.rtbnTCPA_Yes.click();					
							}
							else
							{
								uiAddNewLeadsPageObjects.rtbnTCPA_No.click();
							}
							
														
						}*/
					
					@Test (dependsOnMethods={"BrowseToAddNewLeadPage"})
					public void CreateLiveChatLead(Method objMethod)
					{	try{
						
						
							//Wait for the presence of Promotion Code dd
							UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.ddlPromotionCode);
							System.out.println("Promotion code element present");
							
							//Select Live chat option to create Lead for Livechat
							uiAddNewLeadsPageObjects.rbtnLeadType_LiveChat.click();

							//Select ChannelGroup
							uiAddNewLeadsPageObjects.SelectChannelGroupAs(sChannelGroup);
							//wait for default value of Promotion to appear in ddl
							UserExtension.IsElementPresent(driver,uiAddNewLeadsPageObjects.ddoption_Promotiondefault);
							
							//select Promotion code					
							Select ddlPromotionCode = new Select(uiAddNewLeadsPageObjects.ddlPromotionCode);
							ddlPromotionCode.selectByIndex(1);
							
							//select Area of Study
							Select ddlAreaOfStudy = new Select(uiAddNewLeadsPageObjects.ddlAreaOfStudyTemp);				
							ddlAreaOfStudy.selectByVisibleText(sAreaOfStudy);
							//wait for default value of POI to appear in ddl
							UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.ddoption_POIdefault);
							
							//Select Program of Interest
							Select ddlProgramOfInterest = new Select(uiAddNewLeadsPageObjects.ddlProgramOfInterest);
							ddlProgramOfInterest.selectByVisibleText(sProgramofInterest);	
							
							//Enter First name
							uiAddNewLeadsPageObjects.txtFirstName.sendKeys(sFirstName);
							//Enter Last name
							uiAddNewLeadsPageObjects.txtLastName.sendKeys(sLastName);
							//Enter EmailAddress
							uiAddNewLeadsPageObjects.txtEmailAddress.sendKeys(sEmailAddress);
							//Enter Addressline1
							uiAddNewLeadsPageObjects.txtAddress.sendKeys(sAddressLine1);
							//Enter City
							uiAddNewLeadsPageObjects.txtCity.sendKeys(sCity);
							//Enter Phone
							uiAddNewLeadsPageObjects.txtHomePhone.sendKeys(sPhone);
							//Enter zip code
							uiAddNewLeadsPageObjects.txtZipCode.sendKeys(sZipCode);
													
							//Spouse Military Status
							if(sBechalorDegree.equalsIgnoreCase("yes"))
							{
								uiAddNewLeadsPageObjects.rbtnBechalor_degree_yes.click();					
							}
							else
							{
								uiAddNewLeadsPageObjects.rbtnBechalor_degree_No.click();
							}
								
							//TCPA Disclosure
							if(sTCPA.equalsIgnoreCase("yes"))
							{
								uiAddNewLeadsPageObjects.rtbnTCPA_Yes.click();					
							}
							else
							{
								uiAddNewLeadsPageObjects.rtbnTCPA_No.click();
							}
							
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
						catch(Exception e){
							ReportExtn.Fail(e.getMessage());
						}
					}
					
					//Verify Lead in Admissions Manager
					@Test (dependsOnMethods={"CreateLiveChatLead"})
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
					
