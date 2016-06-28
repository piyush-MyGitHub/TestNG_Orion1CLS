		/* This test checks the Orion CLS Lead Progression */
package orionLeadFlow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ObjectUtils.Null;
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
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Orion1_Reusable.ReusableMethods;

import com.sun.org.apache.regexp.internal.REUtil;

import orion1_NewLeadsPageObject.AddNewLeadPageObjects;
import uimap_Orion1.AdmissionsManagerPage;
import uimap_Orion1.StudentManagerPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class OrionLeadFlow {

				//Remote Web driver for remote execution
				public RemoteWebDriver driver = null;
				
				//BrowseManagement to set the browser capabilities
				public BrowserManagement objBrowserMgr = null;
				public String mainwinhandle;
				public int initialwinhancount;
				public int winhancountafterLeadsubmit;
				public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
				public StudentManagerPageObjects uiStudentManagerPageObjects;
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
				public String sFirstName = "TestNGFNLF_" + sRandStr;
				public String sLastName = "TestNGLNLF_" + sRandStr;			
				public String sEmailAddress = sFirstName + "IC@kap.com";
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
				
				@AfterClass
				public void AfterNavigation()
				{	try{
						//Quit the test after test class execution
						if(driver != null)
							{
								driver.quit();			
							}
						}
				catch(Exception e){
					ReportExtn.Fail(e.getMessage());
				}
					
				}
			
				//In this method we navigate to Add New Lead page
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
			
				//Create Lead
				@Test (dependsOnMethods={"BrowseToAddNewLeadPage"})
				public void CreateLead(Method objMethod)
				{	try{
					
					
						//Wait for the presence of Promotion Code dd
						UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.ddlPromotionCode);
						System.out.println("Promotion code element present");
						
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
				@Test (dependsOnMethods={"CreateLead"})
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
				
				//Save contact Information on Student Manager Page, Close and reopen it to verify the changes
				
				@Test (dependsOnMethods={"VerifyLeadInAdmisssionManager"})
				public void SaveContactInfo(Method objMethod){
					try{
						//Initializing Page Objects
						uiStudentManagerPageObjects =new StudentManagerPageObjects(driver);
						uiAdmMgrPageObjects  = new AdmissionsManagerPage(driver) ;
						
						//click on Lead and open Student Manager
						uiAdmMgrPageObjects.lnkFirstLeadinAdmMgr.click();
						driver.switchTo().window(uiStudentManagerPageObjects.sStudent_Manager_WindowName);	
						UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.ContactInformationTab);
						uiStudentManagerPageObjects.ContactInformationTab.click();
						
						//enter the Required information and click Save
						//UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.ProgramDropDown);
						Select dropdownProgram = new Select(uiStudentManagerPageObjects.ProgramDropDown);
						dropdownProgram.selectByVisibleText("Executive JD Program - No Emphasis");
						//Address Line1 and city
						uiStudentManagerPageObjects.addressLine1.clear();
						uiStudentManagerPageObjects.addressLine1.sendKeys("test123");
						uiStudentManagerPageObjects.city.clear();
						uiStudentManagerPageObjects.city.sendKeys("London");
						//Select StartDate
						UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.startDateDropDown);
						Select dropdownStartDate = new Select(uiStudentManagerPageObjects.startDateDropDown);
						dropdownStartDate.selectByIndex(1);
						//Save Link and Yes button Link
						//UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.btnSave);
						uiStudentManagerPageObjects.btnSave.click();
						System.out.println("COntact Info Saved in Student Manager");
						
						//Verify Save [close stumgr and again open to verify saved info]
						System.out.println("Student Manager closed");
						driver.switchTo().window(mainwinhandle);
						driver.switchTo().frame("Orion");
						uiAdmMgrPageObjects.lnkFirstLeadinAdmMgr.click();
						System.out.println("Student Manager again opened");
						driver.switchTo().window(uiStudentManagerPageObjects.sStudent_Manager_WindowName);	
						UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.ContactInformationTab);
						uiStudentManagerPageObjects.ContactInformationTab.click();
						//get the selected program to verify
						String sPrgname=  driver.findElement(By.xpath("//select[@id='_ctl3_dpProgram']/option[@selected='selected']")).getText().trim();
						System.out.println(sPrgname);
						Assert.assertEquals(sPrgname, "Executive JD Program - No Emphasis");
						Thread.sleep(10000);
									
						
					}
					catch (Exception e){
						System.out.println(e.getMessage());
					}
				}
				
				//Progress Lead to contacted. verify on Student Manager. ALso verify by Searching the LEad
				@Test (dependsOnMethods={"SaveContactInfo"})
				public void ProgresstoContacted(Method objMethod){
					try{
						//Change Lead status to Contact
						uiStudentManagerPageObjects.btnStatusChange.click();
						WebDriverWait wait = new WebDriverWait(driver, 10000);
						wait.until(ExpectedConditions.textToBePresentInElement(uiStudentManagerPageObjects.txtAdmLeadStatus, uiStudentManagerPageObjects.scontactLeadStatus));
						
						//Assert Adm Lead Status
						Assert.assertEquals(uiStudentManagerPageObjects.txtAdmLeadStatus.getText().trim(), uiStudentManagerPageObjects.scontactLeadStatus);
						
						//Switch to main window
						System.out.println("Page title before switching to default"+driver.getTitle());
						driver.switchTo().window(mainwinhandle);
						driver.switchTo().frame("Orion");
											
						//Search Lead in Orion and Verify Lead Status
						ReusableMethods objReusableMethods = new ReusableMethods();
						boolean bSrchRes = objReusableMethods.SearchLeadinOrion(driver, sEmailAddress);
						System.out.println("Search Lead  Succeesful: "+bSrchRes);
						Assert.assertTrue(bSrchRes);
						String ActLeadStatus = driver.findElement(By.xpath("(//td[@class='datagridcell'])[10]")).getText().trim();
						System.out.println("Actual Leadstatus: "+ActLeadStatus);
						System.out.println("Expected Lead Status: "+uiStudentManagerPageObjects.scontactLeadStatus);
						Assert.assertTrue(driver.findElement(By.xpath("(//td[@class='datagridcell'])[10]")).getText().trim().equalsIgnoreCase(uiStudentManagerPageObjects.scontactLeadStatus));
						System.out.println(objMethod.getName()+": File Close Lead Status Verified");
									
						
					}
					catch (Exception e){
						System.out.println(e.getMessage());
					}
				}
				
				//Progress Lead to Interview Scheduled and Verify In Student Manager
				@Test (dependsOnMethods={"ProgresstoContacted"})
				public void ProgressLeadtoIntScheduled(Method objMethod){
					try{
						//Switch to Student Manager Panel
						driver.switchTo().window(uiStudentManagerPageObjects.sStudent_Manager_WindowName);	
						UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.ContactInformationTab);
						
						//Schedule Interview
						uiStudentManagerPageObjects.lnkAppointmentTab.click();
						uiStudentManagerPageObjects.txtAppointmentNotes.clear();
						uiStudentManagerPageObjects.txtAppointmentNotes.sendKeys("Test");
						uiStudentManagerPageObjects.btnAppointmentSave.click();
						try{
							if(uiStudentManagerPageObjects.btnYes.isDisplayed()){
								System.out.println("Schedule already exists message. Need to click YES");
								uiStudentManagerPageObjects.btnYes.click();
								
							}
						}
						catch (Exception e){
							System.out.println("No Schedule already exists message. excetion caught ");
						}
						
						//Verify Adm Lead Status
						uiStudentManagerPageObjects.ContactInformationTab.click();
						Assert.assertEquals(uiStudentManagerPageObjects.txtAdmLeadStatus.getText().trim(), uiStudentManagerPageObjects.sIntScheduledLeadStatus);
						
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
				}

				//Progress Lead to Int Complete and Verify that in Stu Manager and Search lead
				@Test (dependsOnMethods={"ProgressLeadtoIntScheduled"})
				public void ProgressLeadtoIntComplete(Method objMethod){
					try{
						//Change Lead status to Interview Complete
						uiStudentManagerPageObjects.btnStatusChange.click();
						WebDriverWait wait = new WebDriverWait(driver, 10000);
						wait.until(ExpectedConditions.textToBePresentInElement(uiStudentManagerPageObjects.txtAdmLeadStatus, uiStudentManagerPageObjects.sIntCompleteLeadStatus));
						
						//Assert Adm Lead Status
						Assert.assertEquals(uiStudentManagerPageObjects.txtAdmLeadStatus.getText().trim(), uiStudentManagerPageObjects.sIntCompleteLeadStatus);
						
						//Switch to main window
						System.out.println("Page title before switching to default"+driver.getTitle());
						driver.switchTo().window(mainwinhandle);
						driver.switchTo().frame("Orion");
											
						//Search Lead in Orion and Verify Lead Status
						ReusableMethods objReusableMethods = new ReusableMethods();
						boolean bSrchRes = objReusableMethods.SearchLeadinOrion(driver, sEmailAddress);
						System.out.println("Search Lead  Succeesful: "+bSrchRes);
						Assert.assertTrue(bSrchRes);
						String ActLeadStatus = driver.findElement(By.xpath("(//td[@class='datagridcell'])[10]")).getText().trim();
						System.out.println("Actual Leadstatus: "+ActLeadStatus);
						System.out.println("Expected Lead Status: "+uiStudentManagerPageObjects.sIntCompleteLeadStatus);
						Assert.assertTrue(driver.findElement(By.xpath("(//td[@class='datagridcell'])[10]")).getText().trim().equalsIgnoreCase(uiStudentManagerPageObjects.sIntCompleteLeadStatus));
						}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
				
				//Progress lead to Exam Pending and Verify that in Student Manager
				@Test (dependsOnMethods={"ProgressLeadtoIntComplete"})
				public void ProgressLeadtoExamPending(Method objMethod){
					try{
						//Switch to Student Manager Panel
						driver.switchTo().window(uiStudentManagerPageObjects.sStudent_Manager_WindowName);	
						UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.ContactInformationTab);
						
						//Change Lead status to Exam Pending
						uiStudentManagerPageObjects.btnStatusChange.click();
						WebDriverWait wait = new WebDriverWait(driver, 10000);
						wait.until(ExpectedConditions.textToBePresentInElement(uiStudentManagerPageObjects.txtAdmLeadStatus, uiStudentManagerPageObjects.sExamPendingLeadStatus));
						
						//Assert Adm Lead Status
						Assert.assertEquals(uiStudentManagerPageObjects.txtAdmLeadStatus.getText().trim(), uiStudentManagerPageObjects.sExamPendingLeadStatus);
						
					}
					catch (Exception e){
						System.out.println(e.getMessage());
					}
					
				}

				//Progress Lead to Exam Received
				@Test(dependsOnMethods={"ProgressLeadtoExamPending"})
				public void ProgressLeadtoExamReceived(Method onjMethod){
					try{
						//Change Lead status to Exam Received
						uiStudentManagerPageObjects.btnStatusChange.click();
						WebDriverWait wait = new WebDriverWait(driver, 10000);
						wait.until(ExpectedConditions.textToBePresentInElement(uiStudentManagerPageObjects.txtAdmLeadStatus, uiStudentManagerPageObjects.sExamReceivedLeadStatus));
						
						//Assert Adm Lead Status
						Assert.assertEquals(uiStudentManagerPageObjects.txtAdmLeadStatus.getText().trim(), uiStudentManagerPageObjects.sExamReceivedLeadStatus);
						
					}
					catch (Exception e){
						System.out.println(e.getMessage());
					}
				}

				
				//Progress Lead to Pending Enrollment
				@Test(dependsOnMethods={"ProgressLeadtoExamReceived"})
				public void ProgressLeadPendingEnrollment(Method onjMethod){
					try{
						//Schedule appointment for enrollment
						//Schedule Interview
						uiStudentManagerPageObjects.lnkAppointmentTab.click();
						uiStudentManagerPageObjects.txtAppointmentNotes.clear();
						uiStudentManagerPageObjects.txtAppointmentNotes.sendKeys("Test");
						uiStudentManagerPageObjects.btnAppointmentSave.click();
						try{
							if(uiStudentManagerPageObjects.btnYes.isDisplayed()){
								System.out.println("Schedule already exists message. Need to click YES");
								uiStudentManagerPageObjects.btnYes.click();
								
							}
						}
						catch (Exception e){
							System.out.println("No Schedule already exists message. excetion caught ");
						}
						
						//Verify Adm Lead Status in Contact Information page
						uiStudentManagerPageObjects.ContactInformationTab.click();
						Assert.assertEquals(uiStudentManagerPageObjects.txtAdmLeadStatus.getText().trim(), uiStudentManagerPageObjects.sPendingEnrollmentLeadStatus);
						
												
						//Search Lead and Verify Status
						//Switch to main window
						System.out.println("Page title before switching to default"+driver.getTitle());
						driver.switchTo().window(mainwinhandle);
						driver.switchTo().frame("Orion");
											
						//Search Lead in Orion and Verify Lead Status
						ReusableMethods objReusableMethods = new ReusableMethods();
						boolean bSrchRes = objReusableMethods.SearchLeadinOrion(driver, sEmailAddress);
						System.out.println("Search Lead  Succeesful: "+bSrchRes);
						Assert.assertTrue(bSrchRes);
						String ActLeadStatus = driver.findElement(By.xpath("(//td[@class='datagridcell'])[10]")).getText().trim();
						System.out.println("Actual Leadstatus: "+ActLeadStatus);
						System.out.println("Expected Lead Status: "+uiStudentManagerPageObjects.sPendingEnrollmentLeadStatus);
						Assert.assertTrue(driver.findElement(By.xpath("(//td[@class='datagridcell'])[10]")).getText().trim().equalsIgnoreCase(uiStudentManagerPageObjects.sPendingEnrollmentLeadStatus));
				
						
					}
					catch (Exception e){
						System.out.println(e.getMessage());
					}
				}
}
