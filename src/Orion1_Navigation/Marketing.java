package Orion1_Navigation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uimap_Orion1.AdminPage;
import uimap_Orion1.Homepage;
import uimap_Orion1.MarketingPage;
import uimap_Orion1.StudenLookupPage;
import commonfunctions.BrowserManagement;
import commonfunctions.ScreenShotOnTestFailure;
import environment.EnvironmentVariables;

public class Marketing {
	
	//Remote Web driver for remote execution
			public RemoteWebDriver driver = null;
			
			//BrowseManagement to set the browser capabilities
			public BrowserManagement objBrowserMgr = null;
			
			//Home Page Page Object Model
			public Homepage uiHomePageObjects;		
			public MarketingPage uiMarketingPageObjects;			
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
				uiHomePageObjects = new Homepage(driver);
				uiMarketingPageObjects = new MarketingPage(driver);
				uiHomePageObjects.tabMarketing.click();
				
											
			}

// verify Marketing Home Link
@Test
public void VerifyMarketingLink(){
	Assert.assertTrue(uiMarketingPageObjects.lnkMarketingHome.isDisplayed(), "Marketing Home Link is not displayed");
}
//Verify SIF Manager
@Test
public void VerifySIFManaerLink(){
	Assert.assertTrue(uiMarketingPageObjects.lnkSIFManager.isDisplayed(), "SIF Manager Link is not displayed");
}
//Verify Disposition Manager
@Test
public void VerifyDispManaerLink(){
	Assert.assertTrue(uiMarketingPageObjects.lnkDispositionManager.isDisplayed(), "Disposition Manager Link is not displayed");
}
//Verify SIF Search
//@Test
public void VerifySIFSearchLink(){
	Assert.assertTrue(uiMarketingPageObjects.lnkSifSearch.isDisplayed(), "SIF Searcg Link is not displayed");
}
//Verify Question Search Link
//@Test
public void VerifyQuesSearchLink(){
	Assert.assertTrue(uiMarketingPageObjects.lnkQuestionSearch.isDisplayed(), "Question Search Link is not displayed");
}
//Verify Sif Comparison Link
//@Test
public void VerifySIFComparisonLink(){
	Assert.assertTrue(uiMarketingPageObjects.lnkSifComparison.isDisplayed(), "SIF Comparison Link is not displayed");
}
//Verify Manage Sif Sites Link
//@Test
public void VerifyManageSIFSitesLink(){
	Assert.assertTrue(uiMarketingPageObjects.lnkManageSifSites.isDisplayed(), "Manage SIF Sites Link is not displayed");
}
//Verify DNC Link
@Test
public void VerifyDNCLink(){
	Assert.assertTrue(uiMarketingPageObjects.lnkDNC.isDisplayed(), "DNC Link is not displayed");
	
}
//Verify Lead Scoring Link
//@Test
public void VerifyLeadScoringLink(){
	Assert.assertTrue(uiMarketingPageObjects.lnkLeadScoring.isDisplayed(), "Lead scoring Link is not displayed");
	
}

//*****Marketing Home Page************
//vendor page
@Test
public void VerifyVendor(){
	//Verify Crum Cell vendor
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiMarketingPageObjects.sCCCVendors), "Marketing HomePage: vendor crum cell is not as expected");
}
//Sources Page
public void VerifySourcePage(){
	//Navigate to Source
	uiMarketingPageObjects.lnkSources.click();
	//Verify Crum Cell Sources
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiMarketingPageObjects.sCCCSource), "Marketing HomePage: Source crum cell is not as expected");
}
//Campaigns Page
public void VerifyCampaignsPage(){
	//Navigate to Campaingns
	uiMarketingPageObjects.lnkCampaigns.click();
	//Verify Crum Cell Campaigns
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiMarketingPageObjects.sCCCCampaigns), "Marketing HomePage: Campaigns crum cell is not as expected");
}
//Channel Page
public void VerifyChannelPage(){
	//Navigate to Channel
	uiMarketingPageObjects.lnkChannel.click();
	//Verify Crum Cell Channel
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiMarketingPageObjects.sCCCCampaigns), "Marketing HomePage: Campaigns crum cell is not as expected");
}
//Channel Group Page
public void VerifyChannelGroupPage(){
	//Navigate to ChannelGroup
	uiMarketingPageObjects.lnkChannelGroup.click();
	//Verify Crum Cell ChannelGroup
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiMarketingPageObjects.sCCCChannelGroup), "Marketing HomePage: Campaigns crum cell is not as expected");
}
//Promotion Page
public void VerifyPromotionPage(){
	//Navigate to ChannelGroup
	uiMarketingPageObjects.lnkChannelGroup.click();
	//Verify Crum Cell ChannelGroup
	Assert.assertTrue(uiHomePageObjects.CookieCrumcell.getText().trim().equalsIgnoreCase(uiMarketingPageObjects.sCCCChannelGroup), "Marketing HomePage: Campaigns crum cell is not as expected");
}


@AfterClass
  public void AfterNavigation(){
	  if (driver !=null) {driver.quit();}
			  
  }
  }