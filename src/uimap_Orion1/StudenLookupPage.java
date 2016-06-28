package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class StudenLookupPage {
	//constructor to intialize page elements
  public StudenLookupPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
  //Left Nav Queues label
  @FindBy(how=How.XPATH, using= "//td[text()='Queues']")
  public WebElement lblQueues;
    
//Left Nav Misc label
  @FindBy(how=How.XPATH, using= "//td[text()='Miscellaneous']")
  public WebElement lblMisc;
  
//Task Stats Manager label
  @FindBy(how=How.XPATH, using= "//td[text()='TASK STATS TRACKER']")
  public WebElement lblTaskTracker;
    
//Search All Students link
  @FindBy(how=How.XPATH, using= "//input[@id='UcTasks1_btnSearchAllStudents']")
  public WebElement lnkSearchAllStu;
  
}
