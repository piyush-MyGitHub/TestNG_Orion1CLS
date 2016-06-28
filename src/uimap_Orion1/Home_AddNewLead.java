package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Home_AddNewLead {
	//constructor to intialize page elements
  public Home_AddNewLead(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
  //Add Lead/Referral heading
  @FindBy(how=How.XPATH, using= "//td[contains(text(), 'Add New Lead/Referral')]")
  public WebElement hdAddLead;
  
//Referral Information label
  @FindBy(how=How.XPATH, using= "//strong[contains(text(),'Referral')]")
  public WebElement lblRefInfo;
    
//Student providing the referral  label
  @FindBy(how=How.XPATH, using= "//strong[contains(text(),'Student providing the referral')]")
  public WebElement lblStuProRef;
  
//First Name text box
  @FindBy(how=How.ID, using= "tbRFName")
  public WebElement tbFName;
  
//Last Name text box
  @FindBy(how=How.ID, using= "tbRLName")
  public WebElement tbLName;

  //Email text box
  @FindBy(how=How.ID, using= "tbREmail")
  public WebElement tbEmail;
  
//DayPhone text box
  @FindBy(how=How.ID, using= "tbRDayPhone")
  public WebElement tbDayPhone;
  
//Evening Phone text box
  @FindBy(how=How.ID, using= "tbRNightPhone")
  public WebElement tvEvenPhone;
  
//Other Phone text box
  @FindBy(how=How.ID, using= "tbROtherPhone")
  public WebElement tbOtherPhone;
  
//City text box
  @FindBy(how=How.ID, using= "tbRCity")
  public WebElement tbCity;
  
//State drop down
  @FindBy(how=How.ID, using= "ddlState")
  public WebElement ddState;
  
//TCPADisclosure drop down
  @FindBy(how=How.ID, using= "ddlTCPADisclosure")
  public WebElement ddTCPAdis;
  
//SRtudentID drop down
  @FindBy(how=How.ID, using= "tbSRStudentID")
  public WebElement tbSRStuID;
  
 // Submit button
  @FindBy(how=How.ID, using= "btnSubmit")
  public WebElement btnSubmit;
 
  
  
}
