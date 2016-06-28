package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NewLeadPage {
	//constructor to intialize page elements
  public NewLeadPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  //*********Lead Origination
  //Info Call Radio button
  @FindBy(how=How.XPATH , using=".//*[@id='CtlApplyForm1_RadCheck_0']")
  public WebElement rbInfocall;
  
  //Referral Radio button
  @FindBy(how=How.XPATH , using=".//*[@id='CtlApplyForm1_RadCheck_1']")
  public WebElement rbReferral;
  
//Live Chat Radio button
  @FindBy(how=How.XPATH , using=".//*[@id='CtlApplyForm1_RadCheck_2']")
  public WebElement rbLivechat;
  
//Warm Transfer Radio button
  @FindBy(how=How.XPATH , using=".//*[@id='CtlApplyForm1_RadCheck_3']")
  public WebElement rbWarmTransfer;
  
  
  
  
  
 }