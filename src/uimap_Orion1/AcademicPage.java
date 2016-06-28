package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AcademicPage {
	//constructor to initialize page elements
  public AcademicPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
//Academics tab
  @FindBy(how=How.XPATH, using= ".//td[text()='Academics']")
  public WebElement tabAcademics;
  
  
//Academic Advising link 
  @FindBy(how=How.LINK_TEXT, using="Academic Advising")
  public WebElement lnkAcademicAdvising;
  
  //Academic Advising text link
  
  @FindBy(how=How.XPATH, using=".//*[@id='Table2']/tbody/tr/td[2]")
  public WebElement txtAcademicAdvising;
  public String strAcademicAdvising = "TASK STATS TRACKER"; 
  
  
//Team Director View link 
  @FindBy(how=How.LINK_TEXT, using="Team Director View")
  public WebElement lnkTeamDirectorView;
  
//Tutoring link 
  @FindBy(how=How.LINK_TEXT, using="Tutoring")
  public WebElement lnkTutoring;
  
  //Tutoring Error message
  @FindBy(how=How.ID, using="message")
  public WebElement txtTutoringMessage;
  public String strTutoringMessage = "We're sorry there was an error while processing your request.";
  
  
//Admin link 
  @FindBy(how=How.LINK_TEXT, using="Admin")
  public WebElement lnkAdmin;
  
  //Admin page text
  
  @FindBy(how=How.XPATH, using=".//*[@id='Table2']/tbody/tr/td[2]")
  public WebElement txtAdminPage;
  public String strTextAdmin="Accadmic Advising Administration";
  
//Rep Groups link 
  @FindBy(how=How.LINK_TEXT, using="Rep Groups")
  public WebElement lnkRepGroups;
  
//Enrollment Exceptions link 
  @FindBy(how=How.LINK_TEXT, using="Enrollment Exceptions")
  public WebElement lnkEnrollmentExceptions;
  public String strEnrollmentExceptions = "We're sorry there was an error while processing your request.";
 
    
//Advisor Assignment link 
  @FindBy(how=How.LINK_TEXT, using="Advisor Assignment")
  public WebElement lnkAdvisorAssignment;
  
  //Cookie Crum Cell text
  public String sCCCAdvisorAssignment="Home :: Academics :: Advisor Assignment Manager";
  
//Assignment Exceptions link 
  @FindBy(how=How.LINK_TEXT, using="Assignment Exceptions")
  public WebElement lnkAssignmentExceptions;
  
  //Cookie Crum Cell text
  public String sCCCAssignmentExceptions="Home :: Academics :: Assignments Exceptions";

 }