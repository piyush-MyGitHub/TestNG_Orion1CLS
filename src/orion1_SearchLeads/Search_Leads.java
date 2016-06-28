package orion1_SearchLeads;

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
import org.openqa.selenium.Keys;
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
import uimap_Orion1.AdmissionsManagerPage;
import uimap_Orion1.Homepage;
import uimap_Orion1.SearchLeadsPageObjects;


import commonfunctions.BrowserManagement;
import commonfunctions.ReportExtn;
import commonfunctions.ScreenShotOnTestFailure;
import commonfunctions.UserExtension;
import environment.EnvironmentVariables;

public class Search_Leads {


		//Remote Web driver for remote execution
		public RemoteWebDriver driver = null;
		
		//BrowseManagement to set the browser capabilities
		public BrowserManagement objBrowserMgr = null;
		
		//Home Page Page Object Model
		
		public SearchLeadsPageObjects uiSearchLeadsPageObjects;
		public AdmissionsManagerPage uiAdmMgrPageObjects;
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
		{try{
			
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

		//This Method picks the first lead in New Lead Queue, Opens Student manager and fetch details for Search
	@Test
		public void FetchLeadDetailstoSearch(Method objMethod)
		{ try{

				//Navigate to Admissions Manager
				uiSearchLeadsPageObjects =new SearchLeadsPageObjects(driver);
				uiAdmMgrPageObjects = new AdmissionsManagerPage(driver);
				uiSearchLeadsPageObjects.tabAdmissions.click();
				uiSearchLeadsPageObjects.lnkAdmissionsManager.click();
				
				//Pick the Lead to search | Get email address and Lead ID from student Manager
				System.out.println("Following Lead will be searched"+uiAdmMgrPageObjects.lnkFirstLeadinAdmMgr.getText().trim());
				
				//implicit wait
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				
				//click on the First Lead in New Leads queue
				uiAdmMgrPageObjects.lnkFirstLeadinAdmMgr.click();
				
				//switch to Student Manager Panel
				driver.switchTo().window(uiSearchLeadsPageObjects.sStudentManager_WindowName);	
				UserExtension.IsElementPresent(driver, uiSearchLeadsPageObjects.ContactInformationTab);
				Assert.assertEquals(uiSearchLeadsPageObjects.ContactInformationTab.getText().trim(), "Contact Information");
				
				//Store Email Address and Lead ID from Student Manager
				
				sEmailAddress =uiSearchLeadsPageObjects.txtEmailAddress.getAttribute("value");
				sleadId =uiSearchLeadsPageObjects.LeadID.getText();
				//Close Student Manager
				driver.close();
				
				//switch to main windowHandle
				
				driver.switchTo().window(mainwinhandle);
				
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		}
			
		//This method Search Lead by Email address
		@Test(dependsOnMethods={"FetchLeadDetailstoSearch"})
		public void SearchLeadbyEmailAddress(Method objMethod)
		{	try{
			
			//Switch to main Window and frame
			driver.switchTo().window(mainwinhandle);
			driver.switchTo().frame("Orion");
			
			//Search Lead in Orion and Verify Lead Status
			ReusableMethods objReusableMethods = new ReusableMethods();
			boolean bSrchRes = objReusableMethods.SearchLeadinOrion(driver, sEmailAddress);
			System.out.println("Search Lead  Succeesful: "+bSrchRes);
			Assert.assertTrue(bSrchRes);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
						
		}
		
		//This method Search Lead by mkleadID
				@Test(dependsOnMethods={"SearchLeadbyEmailAddress"})
				public void SearchLeadbymkLeadID(Method objMethod)
				{	try{
						//Clear Field and Click Search again to Reset the search page
						uiSearchLeadsPageObjects.txtSearchLeadsEmail.clear();
						uiAdmMgrPageObjects.lnkSearch.click();
						
						//Enter mkleadId to search and click Search
						uiSearchLeadsPageObjects.txtSearchLeadID.sendKeys(sleadId);
						uiSearchLeadsPageObjects.btnLeadsSearch.click();
				  		System.out.println("Waiting for 10 secs....");
						Thread.sleep(10000);
				  		System.out.println(driver.findElement(By.xpath("(//td[@class='datagridcell'])[6]")).getText());
					  	//Verify Searched Lead email
				  		Assert.assertEquals(driver.findElement(By.xpath("(//td[@class='datagridcell'])[6]")).getText().trim(), sEmailAddress );
				  			
						
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
								
				}
		
	}




