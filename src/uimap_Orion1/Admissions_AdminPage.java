package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Admissions_AdminPage {
	//constructor to intialize page elements
  public Admissions_AdminPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
  //Cookie Crum Cell
  public String sCCCAdmin= "Home :: Admissions :: Admin :: Manage Reps";
  
  //Manage Reps button 
  @FindBy(how=How.LINK_TEXT, using="Manage Reps")
  public WebElement lnkManageReps;
  
//Manage Teams link 
  @FindBy(how=How.LINK_TEXT, using="Manage Teams")
  public WebElement lnkManageTeams;
  
//Reassign Leads link 
  @FindBy(how=How.LINK_TEXT, using="Reassign Leads")
  public WebElement lnkReassignLeads;
  
//Reassign Leads To CRM link 
  @FindBy(how=How.LINK_TEXT, using="Reassign Leads To CRM")
  public WebElement lnkReassignLeadsToCRM;
  
//Manage PIC link 
  @FindBy(how=How.LINK_TEXT, using="Manage PIC")
  public WebElement lnkManagePIC;
  
//iWD Lead Transfer link 
  @FindBy(how=How.LINK_TEXT, using="iWD Lead Transfer")
  public WebElement lnkiWDLeadTransfer;
  
//Manage Managers link 
  @FindBy(how=How.LINK_TEXT, using="Manage Managers")
  public WebElement lnkManageManagers;
    
//Manage SACs link 
  @FindBy(how=How.LINK_TEXT, using="Manage SACs")
  public WebElement lnkManageSACs;
  
//Search Reps link 
  @FindBy(how=How.LINK_TEXT, using="Search Reps")
  public WebElement lnkSearchReps;
  
//Manage Emails link 
  @FindBy(how=How.LINK_TEXT, using="Manage Emails")
  public WebElement lnkManageEmails;
 }