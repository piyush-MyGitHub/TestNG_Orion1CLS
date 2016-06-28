package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {
	//constructor to intialize page elements
  public AdminPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
  //Create User Link
  @FindBy(how=How.LINK_TEXT, using="Create User")
  public WebElement lnkCreateUser;
  public String sCCCCreateUser= "Home :: Admin :: Create User";
  
  //Manage Users Link
  @FindBy(how=How.LINK_TEXT, using="Manage Users")
  public WebElement lnkManagerUser;
    
//Manage Departments Link
  @FindBy(how=How.LINK_TEXT, using="Manage Departments")
  public WebElement lnkManageDepts;

//Error Reporting Link
  @FindBy(how=How.LINK_TEXT, using="Error Reporting")
  public WebElement lnkErrorReporting;
  
//Manage Task Tracker
  @FindBy(how=How.LINK_TEXT, using="Manage Task Tracker")
  public WebElement lnkManageTaskTracker;
  
//Residential Data Migration Mgmt link
  @FindBy(how=How.LINK_TEXT, using="Residential Data Migration Mgmt")
  public WebElement lnkResidentialDataMigrationMgmt;

//Manage Assignment Exception Reason link
  @FindBy(how=How.LINK_TEXT, using="Manage Assignment Exception Reason")
  public WebElement lnkManageAssignmentExceptionReason;

//Manage Campus link
  @FindBy(how=How.LINK_TEXT, using="Manage Campus")
  public WebElement lnkManageCampus;
  
//Manage Holiday link
  @FindBy(how=How.LINK_TEXT, using="Manage Holiday")
  public WebElement lnkManageHoliday;
  
//Component Manager Link
  @FindBy(how=How.LINK_TEXT, using="Component Manager")
  public WebElement lnkComponentManager;
  
  //**************Create User locators**********
  
  //create user heading
  @FindBy(how=How.XPATH, using="//td[text()='Create Orion Only User']")
  public WebElement hdngCreateUser;
  
  //create user buttton
  @FindBy(how=How.XPATH, using=".//*[@id='CreateUserButton']")
  public WebElement btnCreateUser;
  

  //**************Manage User locators**********
  
  //Search users heading
  @FindBy(how=How.XPATH, using="//td[text()='Search Users']")
  public WebElement hdngSearchUsers;
  //Cookie Crum Cell text
  public String sCCCManageUsers="Home :: Admin :: Manage Users";
   

//**************Manage Departments locators**********
  
  //Department heading
  @FindBy(how=How.XPATH, using="//span[text()='Department']")
  public WebElement hdngDepartment;
  //Cookie Crum Cell text
  public String sCCCManageDepts="Home :: Admin :: Manage Departments";
   
//**************Error Reporting locators**********
  //Error Report heading
  @FindBy(how=How.XPATH, using="//td[text()='Error Report']")
  public WebElement hdngErrorReport;
  //Cookie Crum Cell text
  public String sCCCErrorReport="Home :: Admin :: Error Reporting";
   
//**************Manage Holiday locators**********
  //mANAGE Holiday heading
  @FindBy(how=How.XPATH, using="//td[text()='Manage Holiday']")
  public WebElement hdngManageHoliday;
  //Cookie Crum Cell text
  public String sCCCManageHoliday="Home :: Admin :: Manage Holidays";
//**************Manage Campus locators**********
  //Manage Campus heading
  @FindBy(how=How.XPATH, using="//td[text()='Manage Campuses']")
  public WebElement hdngManageCampuses;
  //Cookie Crum Cell text
  public String sCCCManageCampus="Home :: Admin :: Manage Campus";
//**************Manage Tas k Trackerlocators**********
  //Manage tASK Tracker heading
  @FindBy(how=How.XPATH, using=".//*[text()='Task Tracker Administration']")
  public WebElement hdngManageTaskTracker;
//**************Manage Residential campus migration locators**********
  //Manage Residential campus migration heading
  @FindBy(how=How.XPATH, using=".//*[contains(text(),'Manage Residential Campus Migration')]")
  public WebElement hdngManageResiCampusMigration;
  //Cookie Crum Cell text
  public String sCCCManageResicamMig="Home :: Admin :: Residential Data Migration Manager";
//**************Components Manager locators**********
  //Components link
  @FindBy(how=How.LINK_TEXT , using="Components")
  public WebElement lnkComponents;
  //Cookie Crum Cell text
  public String sCCCComponentMgr="Home :: Admin :: Components";
}
