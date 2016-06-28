	package orion1_FileClose;

	import java.lang.reflect.Method;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.util.concurrent.TimeUnit;
	import org.apache.commons.lang3.RandomStringUtils;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
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
	import uimap_Orion1.FileCloseStudentManagerPage;
	import uimap_Orion1.SearchLeadsPageObjects;
	import commonfunctions.BrowserManagement;
	import commonfunctions.ScreenShotOnTestFailure;
	import commonfunctions.UserExtension;
	import environment.EnvironmentVariables;

	public class FileCloseAdmissionManager {


			//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			//Home Page Page Object Model
			
			public SearchLeadsPageObjects uiSearchLeadsPageObjects;
			
			public FileCloseStudentManagerPage uiFileCloseStudentManagerPage;
			public String mainwinhandle;
			
			
			
			//Static variable
			String sRandStr = RandomStringUtils.randomAlphabetic(5);
			public String sFirstName;
			public String sLastName;		
			public String sEmailAddress;
			public String sleadId;
			public String sDayPhone = "9545151234";
			public String sZipCode = "30256";
			
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
				mainwinhandle = driver.getWindowHandle();
				driver.manage().window().maximize();
				driver.switchTo().frame("Orion");
				
				uiSearchLeadsPageObjects = new SearchLeadsPageObjects(driver);
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
			public void ClickOnFirstLeads(Method objMethod)
			{
				uiSearchLeadsPageObjects =new SearchLeadsPageObjects(driver);
				
				uiSearchLeadsPageObjects.tabAdmissions.click();
				
				uiSearchLeadsPageObjects.lnkAdmissionsManager.click();
				
				uiSearchLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a"));
				
				uiSearchLeadsPageObjects.lnkFirstLeadInTable.click();
			
			    UserExtension.WaitTillGetTextValueIs(driver, uiSearchLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a")), sLastName + ", " + sFirstName);
				UserExtension.IsElementPresent(driver, uiSearchLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a")));
					uiSearchLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a")).click();
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					
					driver.switchTo().window(uiSearchLeadsPageObjects.sStudentManager_WindowName);	
					UserExtension.IsElementPresent(driver, uiSearchLeadsPageObjects.ContactInformationTab);
					Assert.assertEquals(uiSearchLeadsPageObjects.ContactInformationTab.getText().trim(), "Contact Information");
					uiSearchLeadsPageObjects.ContactInformationTab.click();
				
					
					//Store FirstName from student manager page
					
					sFirstName =uiSearchLeadsPageObjects.txtFirstName.getAttribute("value");
					
					//Store LastName from student manager page

					sLastName =uiSearchLeadsPageObjects.txtLastName.getAttribute("value");
					
					//Store Email address from student manager page
					sEmailAddress =uiSearchLeadsPageObjects.txtEmailAddress.getAttribute("value");
					
					//Store LeadId from student manager page
					sleadId =uiSearchLeadsPageObjects.LeadID.getText();
					
					//Close window
					
					driver.close();
					
					//Switch to Main Window
					
					driver.switchTo().window(mainwinhandle);
					
					driver.switchTo().frame("Orion");
					
					uiSearchLeadsPageObjects =new SearchLeadsPageObjects(driver);
					
					//driver.switchTo().window(uiFileCloseStudentManagerPage.sStudent_ManagerFileClose_WindowName);
					
					uiSearchLeadsPageObjects.tabAdmissions.click();
					
					uiSearchLeadsPageObjects.lnkAdmissionsManager.click();
					
					uiSearchLeadsPageObjects.lnkFirstLeadInTable.findElement(By.xpath("//tr[2]/td[4]/a"));
					
					uiFileCloseStudentManagerPage =new FileCloseStudentManagerPage(driver);
					
					
					uiFileCloseStudentManagerPage.lnkAdmissionPageFileClose.click();
					
					//Navigate to File Close Window
					
					driver.switchTo().window(uiFileCloseStudentManagerPage.sStudent_ManagerFileClose_WindowName);
					
					//Select DropDown Reason
					Select ddlReason = new Select(uiFileCloseStudentManagerPage.ddSelectReason);
					
					ddlReason.selectByIndex(2);
					
					//Select Sub Reason DropDown
					
	               //Select ddlSubReason = new Select(uiFileCloseStudentManagerPage.ddSubReason);
					
	                 //ddlSubReason.selectByIndex(2);
	                 
	                 //Select Notes Fields
	                 uiFileCloseStudentManagerPage.txtNotesRequired.clear();
	                 uiFileCloseStudentManagerPage.txtNotesRequired.sendKeys("test");
	                 
	                 //Click On Save Button
	                 uiFileCloseStudentManagerPage.btnSaveandCloseButton.click();
	                 
	                 Alert alert=driver.switchTo().alert();
	                 alert.accept();
					
					//driver.close();
					
					//switch to main windowHandle
					
			driver.switchTo().window(mainwinhandle);
			}
					
			
	@Test(dependsOnMethods={"ClickOnFirstLeads"})
			
			public void SearchLeadsInformationDetails(Method objMethod)
			
			
			{
				driver.switchTo().frame("Orion");
				
	          uiSearchLeadsPageObjects =new SearchLeadsPageObjects(driver);

	           UserExtension.IsElementPresent(driver, uiSearchLeadsPageObjects.tabAdmissions);
				//page scroll up
	           driver.findElement(By.xpath(".//td[text()='Admissions']")).sendKeys(Keys.PAGE_UP);
				
	             //click on Admission tab 
				uiSearchLeadsPageObjects.tabAdmissions.click();
				
			
				//Click on Admission Manager tab
		
				uiSearchLeadsPageObjects.lnkAdmissionsManager.click();
					
					UserExtension.IsElementPresent(driver, uiSearchLeadsPageObjects.lnkSearchLeads);
					uiSearchLeadsPageObjects.lnkSearchLeads.click();
					
					System.out.println(uiSearchLeadsPageObjects.lnkSearchLeads);
					
					driver.manage().window().maximize();
					
					//Search on the basis of LeadID
					
					uiSearchLeadsPageObjects.txtSearchLeadID.clear();
					
					uiSearchLeadsPageObjects.txtSearchLeadID.sendKeys(sleadId);
					
	              driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					
				uiSearchLeadsPageObjects.btnLeadsSearch.click();
					
			
			//Pass the value in search fields		
					
				uiSearchLeadsPageObjects.txtSearchLeadsFirstName.clear();;
				uiSearchLeadsPageObjects.txtSearchLeadsFirstName.sendKeys(sFirstName);
				
				uiSearchLeadsPageObjects.txtSearchLeadsLastName.clear();
					
					uiSearchLeadsPageObjects.txtSearchLeadsLastName.sendKeys(sLastName);
					
					uiSearchLeadsPageObjects.txtSearchLeadsEmail.clear();
					
					uiSearchLeadsPageObjects.txtSearchLeadsEmail.sendKeys(sEmailAddress);
					
					
					//click on Leads search button
					uiSearchLeadsPageObjects.btnLeadsSearch.click();
					
					//Verify the search Results
					
					uiFileCloseStudentManagerPage =new FileCloseStudentManagerPage(driver);
					Assert.assertEquals(uiFileCloseStudentManagerPage.txtFileCloseStatus.getText().trim(), "File Close");
					
					
				
				}
		}


