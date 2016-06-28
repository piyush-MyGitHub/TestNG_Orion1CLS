package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdmissionsPage {
	//constructor to intialize page elements
  public AdmissionsPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
  //Skills/Cut Off Manager Link
  @FindBy(how=How.LINK_TEXT, using="Skills/Cut Off Manager")
  public WebElement lnkSkillsCutoffMgr;
   
//Script Manager link
@FindBy(how=How.LINK_TEXT, using="Script Manager")
public WebElement lnkScriptMgr;
 
//Admin link
@FindBy(how=How.LINK_TEXT, using="Admin")
public WebElement lnkadmin;

//Operations link
@FindBy(how=How.LINK_TEXT, using="Operations")
public WebElement lnkOperations;

//Admissions Manager link
@FindBy(how=How.LINK_TEXT, using="Admissions Manager")
public WebElement lnkAdmissionsManager;

//Appraisals link
@FindBy(how=How.LINK_TEXT, using="Appraisals")
public WebElement lnkAppraisals;

//Bonus Tracker link
@FindBy(how=How.LINK_TEXT, using="Bonus Tracker")
public WebElement lnkBonusTracker;

//Enrollment Exceptions link
@FindBy(how=How.LINK_TEXT, using="Enrollment Exceptions")
public WebElement lnkEnrollmentExceptions;

//Settings link
@FindBy(how=How.LINK_TEXT, using="Settings")
public WebElement lnkSettings;

//Enter Admissions Manager btn
@FindBy(how=How.ID, using= "btnGo")
public WebElement btnEnterAdMgr;


//************Cookies Crum Cell for pages under Admissions Page*****************
//Cookies Crum Cell Operations
public String sCCCOperatios = "Home :: Admissions :: Cohort Manager";

//Cookies Crum Cell Appraisals
public String sCCCAppraisals = "Home :: Admissions :: Appraisals";

//Cookies Crum Cell Bonus Tracker
public String sCCCBonustracker = "Home :: Admissions :: Bonus Tracker :: Payout Dates";

//Cookies Crum Cell Enrollment Exceptions
public String sCCCEnrollEx = "Home :: Admissions :: Enrollment Exceptions";

//Cookies Crum Cell Setting
public String sCCCSetting = "Home :: Admissions :: Settings";
}