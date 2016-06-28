package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MarketingPage {
	//constructor to intialize page elements
  public MarketingPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
  //Marketing User Link
  @FindBy(how=How.LINK_TEXT, using="Marketing Home")
  public WebElement lnkMarketingHome;
    
  //Disposition Manager Link
  @FindBy(how=How.LINK_TEXT, using="Disposition Manager")
  public WebElement lnkDispositionManager;
  
  //SIF Manager Link
  @FindBy(how=How.LINK_TEXT, using="SIF Manager")
  public WebElement lnkSIFManager;
  
  //Sif Search Link
  @FindBy(how=How.LINK_TEXT, using="Sif Search")
  public WebElement lnkSifSearch;
  
  //Question Search Link
  @FindBy(how=How.LINK_TEXT, using="Question Search")
  public WebElement lnkQuestionSearch;
  
  //Sif Comparison Link
  @FindBy(how=How.LINK_TEXT, using="Sif Comparison")
  public WebElement lnkSifComparison;
  
  //Manage Sif Sites Link
  @FindBy(how=How.LINK_TEXT, using="Manage Sif Sites")
  public WebElement lnkManageSifSites;
    
  //DNC Link
  @FindBy(how=How.LINK_TEXT, using="DNC")
  public WebElement lnkDNC;
  
  //Lead Scoring Link
  @FindBy(how=How.LINK_TEXT, using="Lead Scoring")
  public WebElement lnkLeadScoring;	
    
//**************Marketing Home locators**********
  //vendor
  @FindBy(how=How.LINK_TEXT , using="Vendors")
  public WebElement lnkVendors;
//Cookie Crum Cell text
  public String sCCCVendors="Home :: Marketing :: Vendors";

  //Sources
  @FindBy(how=How.LINK_TEXT , using="Sources")
  public WebElement lnkSources;
//Cookie Crum Cell text
  public String sCCCSource="Home :: Marketing :: Source Search";
  
//Campaigns
  @FindBy(how=How.LINK_TEXT , using="Campaigns")
  public WebElement lnkCampaigns;
//Cookie Crum Cell text
  public String sCCCCampaigns="Home :: Marketing :: Campaign Management";

//Channel
  @FindBy(how=How.LINK_TEXT , using="Channel")
  public WebElement lnkChannel;
//Cookie Crum Cell text
  public String sCCCChannel="Home :: Marketing :: Channel Management";
  
//Channel Group
  @FindBy(how=How.LINK_TEXT , using="Channel Group")
  public WebElement lnkChannelGroup;
//Cookie Crum Cell text
  public String sCCCChannelGroup="Home :: Marketing :: Channel Group Management";

//Promotion 
  @FindBy(how=How.LINK_TEXT , using="Promotion")
  public WebElement lnkPromotion;
//Cookie Crum Cell text
  public String sCCCPromotion="Home :: Marketing :: Channel Group Management";
  
  //disposition manager//
  public String sCCCDisManager= "Home :: Marketing :: Disposition Reasons";
  
//SIF Manager
  public String sCCCSIFManager="Home :: Marketing :: SIF Manager";
  
  //Lead SCoring
  @FindBy(how=How.XPATH, using="//td[text()='Lead Scoring']")
  public WebElement hdngLeadScoring;
  public String sCCCLeadScoring="Home :: Marketing :: Lead Scoring";

}
