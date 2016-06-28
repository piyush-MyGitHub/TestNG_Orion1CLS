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
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import orion1_NewLeadsPageObject.AddNewLeadPageObjects;
import uimap_Orion1.StudentManagerPageObjects;
import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class StudentManager {


		
				//Remote Web driver for remote execution
				public RemoteWebDriver driver = null;
				
				//BrowseManagement to set the browser capabilities
				public BrowserManagement objBrowserMgr = null;
				
				public String mainwinhandle;
				
				//Home Page Page Object Model
				//public HomePageObjects uiHomePageObjects;
				//public AdmissionsManagerPageObjects uiAdmissionMgrPageObjects;
				public AddNewLeadPageObjects uiAddNewLeadsPageObjects;
				
				public StudentManagerPageObjects uiStudentManagerPageObjects;
				
				
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
				public String sFirstName = "TestNGFNInfoCall_" + sRandStr;
				public String sLastName = "TestNGLNInfoCall_" + sRandStr;			
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
				{
					//Quit the test after test class execution
					if(driver != null)
					{
						driver.quit();			
					}
				}

				
				@Test
				public void BrowseToAddNewLeadPage(Method objMethod)
				{
					uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
					
					uiAddNewLeadsPageObjects.tabAdmissions.click();
					
					uiAddNewLeadsPageObjects.lnkAdmissionsManager.click();
					
					UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.lblAddNewLeadReferral);
					
					uiAddNewLeadsPageObjects.lblAddNewLeadReferral.click();
					
				
					driver.switchTo().window(uiAddNewLeadsPageObjects.sAddNewLead_WindowName);
					
				}
				
				@Test(dependsOnMethods={"BrowseToAddNewLeadPage"})
				public void Leads_ClassificationDetails(Method objMethod)
				{
					Assert.assertEquals(uiAddNewLeadsPageObjects.rbtnLeadType_InfoCall.getAttribute("checked"), "true", "Info Call Lead Type is not getting selected");
					
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
						
													
					}
					
					@Test(dependsOnMethods={"Leads_Details"})
					public void SubmitLead(Method objMethod)
					{
						uiAddNewLeadsPageObjects.btnAddALead.click();
									
					}
					
					@Test(dependsOnMethods={"SubmitLead"})
					public void VerifyLeadInAdmisssionManager(Method objMethod)
					{
						
						driver.switchTo().window(mainwinhandle);
						driver.switchTo().frame("Orion");
						uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
						
						uiAddNewLeadsPageObjects.tabAdmissions.click();
						
						uiAddNewLeadsPageObjects.lnkAdmissionsManager.click();
						
						UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a")));
						UserExtension.WaitTillGetTextValueIs(driver, uiAddNewLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a")), sLastName + ", " + sFirstName);
						
					}
						@Test(dependsOnMethods={"VerifyLeadInAdmisssionManager"})
						
						public void ContactInformationTabDetails(Method objMethod)

					{
						try{	
							UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a")));
							
							uiStudentManagerPageObjects =new StudentManagerPageObjects(driver);
							
							uiAddNewLeadsPageObjects =new AddNewLeadPageObjects(driver);
							
							uiAddNewLeadsPageObjects.tabAdmissions.click();
							
							uiAddNewLeadsPageObjects.lnkAdmissionsManager.click();
						
							UserExtension.IsElementPresent(driver, uiAddNewLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a")));
							uiStudentManagerPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a")).click();
						driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
						uiStudentManagerPageObjects = new StudentManagerPageObjects(driver);
						driver.switchTo().window(uiStudentManagerPageObjects.sStudent_Manager_WindowName);	
						UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.ContactInformationTab);
						
						uiStudentManagerPageObjects.ContactInformationTab.click();
						
						UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.ProgramDropDown);
						Select dropdownProgram = new Select(uiStudentManagerPageObjects.ProgramDropDown);
						WebDriverWait wait = new WebDriverWait(driver, 35);
						WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("_ctl3_dpProgram")));
							
							driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
							dropdownProgram.selectByVisibleText("Executive JD Program - No Emphasis");
							
							//Address Line1 and city
							
							uiStudentManagerPageObjects.addressLine1.clear();
							uiStudentManagerPageObjects.addressLine1.sendKeys("test123");
							uiStudentManagerPageObjects.city.clear();
							uiStudentManagerPageObjects.city.sendKeys("London");
							
							//Select StartDate
							
							UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.startDateDropDown);
							Select dropdownStartDate = new Select(uiStudentManagerPageObjects.startDateDropDown);
							
							WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("_ctl3_dpStartDate")));
							//dropdownStartDate.selectByValue("5/5/2015");
							dropdownStartDate.selectByIndex(1);
							
							//Save Link and Yes button Link
							
							UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.btnSave);
							uiStudentManagerPageObjects.btnSave.click();
							
							//Contact Link
							
							UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.btnLeadContacted);
							WebElement element4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("_ctl3_btnStatusChange")));
							uiStudentManagerPageObjects.btnLeadContacted.click();
							
							//Click On AppointmentTab
							
							WebElement element5= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='TabRow']/td[4]/a")));
							UserExtension.IsElementNotPresent(driver, uiStudentManagerPageObjects.lnkAppointmentTab);
							uiStudentManagerPageObjects.lnkAppointmentTab.click();
							
							//Add text in Appointment Notes field
							
							UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.txtAppointmentNotes);
							uiStudentManagerPageObjects.txtAppointmentNotes.clear();
							uiStudentManagerPageObjects.txtAppointmentNotes.sendKeys("Test");
							uiStudentManagerPageObjects.btnAppointmentSave.click();
							driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
							//uiStudentManagerPageObjects.btnAppointmentSave.click();
							
							//UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.txtAppointmentNotes);
							
							 try
						 		{
						 	
								 
						 		if(uiStudentManagerPageObjects.btnYes.isDisplayed())
						 			//UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.btnYes);
						 			uiStudentManagerPageObjects.btnYes.click();
						 		
						 		}
						 				
						 				catch(Exception e){
						 					Reporter.log(e.getMessage());
						 					
						 				}
								
							 
							 //driver.switchTo().window(uiStudentManagerPageObjects.sStudent_Manager_WindowName);	
								UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.ContactInformationTab);
								
								uiStudentManagerPageObjects.ContactInformationTab.click();
					       
							//Navigate to Contact Information Tab
							 //UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.ContactInformationTab);
							 
							//uiStudentManagerPageObjects.ContactInformationTab.click();
							//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //check Interview Schedule text on page
							
							UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.txtInterviewSchedule);
							Assert.assertEquals(uiStudentManagerPageObjects.txtInterviewSchedule.getText().trim(), "Interview Scheduled");
							
							//Click on Interview Complete link
							UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.btnInterviewComplete);
							uiStudentManagerPageObjects.btnInterviewComplete.click();
							
							
							//Click and Verify Application Received button Link
							
							UserExtension.IsElementPresent(driver, uiStudentManagerPageObjects.btnInterviewComplete);
							
							uiStudentManagerPageObjects.btnInterviewComplete.click();
							driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
						
							
							
							//Enrollment Confirmed
									//Assert.assertEquals(uiStudentManagerPageObjects.btnEnrollmentConfirmed.getText().trim(), "Enrollment Confirmed");
									UserExtension.IsElementNotPresent(driver,uiStudentManagerPageObjects.btnInterviewComplete);
									uiStudentManagerPageObjects.btnInterviewComplete.click();
									Assert.assertEquals(uiStudentManagerPageObjects.txtInterviewSchedule.getText().trim(), "Exam Received");
									driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					}catch (Exception e)
					{Reporter.log(e.getMessage());
						
					}
					}		
					}
					

