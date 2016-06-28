package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SkillCutoffManagerPage {
	//constructor to intialize page elements
  public SkillCutoffManagerPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
  //Cookie Crum Cell
  public String sCCCSkillCutoffManager= "Home :: Admissions :: Cut Off Manager";
  
  //Assign Skills link
  @FindBy(how=How.LINK_TEXT, using="Assign Skills")
  public WebElement lnkAssignSkills;
  
  //Assign Cutoff link
  @FindBy(how=How.LINK_TEXT, using="Assign Cutoff")
  public WebElement lnkAssignCutoff;
  
  //Current Cutoff link
  @FindBy(how=How.LINK_TEXT, using="Current Cutoff")
  public WebElement lnkCurrentCutoff;
  
 }