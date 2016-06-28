package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	//Constructor to initailize Page elements
	public Homepage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	
	//Cookie Crum Cell
	@FindBy(how=How.XPATH, using= "//*[contains(@id,'CookieCrumCell')]")
	  public WebElement CookieCrumcell;
	
	//Home tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Home']")
	  public WebElement tabHome;
	  
	//Admissions tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Admissions']")
	  public WebElement tabAdmissions;
	  
	//University Paltform tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='University Platform']")
	  public WebElement tabUniversityPlatform;
	  
	//Academics tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Academics']")
	  public WebElement tabAcademics;
	  
	//Financial Aid tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Financial Aid']")
	  public WebElement tabFinAid;
	  
	//Accounting tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Accounting']")
	  public WebElement tabAccounting;
	  
	//Admin tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Admin']")
	  public WebElement tabAdmin;
	  
	//Marketing tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Marketing']")
	  public WebElement tabMarketing;
	  
	//Registrar tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Registrar']")
	  public WebElement tabRegistrar;
	  
	//Ticketing tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Ticketing']")
	  public WebElement tabTicketing;
	  
	//Reports tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Reports']")
	  public WebElement tabReports;
	  
	  //Program Maintenance
	  @FindBy(how=How.XPATH, using= ".//td[text()='Program Maintenance']")
	  public WebElement tabProgMaintenance;

	  //My Profile Image
	  @FindBy(how=How.ID, using= "_ctl2_ProfileImage")
	  public WebElement imgMyProfile;
	  
	//Welcome Label
	  @FindBy(how=How.ID, using= "lblWelcome")
	  public WebElement lblWelcome;
	  
	//Student Lookup link
	  @FindBy(how=How.LINK_TEXT, using= "Student Lookup")
	  public WebElement lnkStuLookup;
	  
	//Home Add New Lead/ Referral link
	  @FindBy(how=How.LINK_TEXT, using= "Add New Lead/Referral")
	  public WebElement lnkAddRefLead;
	  
	//Logout link
	  @FindBy(how=How.LINK_TEXT, using= "logout")
	  public WebElement lnkLogout;
	  

  
}
