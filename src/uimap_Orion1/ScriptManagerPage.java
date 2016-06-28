package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ScriptManagerPage {
	//constructor to intialize page elements
  public ScriptManagerPage(WebDriver driver){
	  PageFactory.initElements(driver, this);
  }
  
  //Cookie Crum Cell
  public String sCCCScriptManager= "Home :: Admissions :: Admin";
  
  //delete button 
  @FindBy(how=How.ID, using="btnDelete")
  public WebElement btnDelete;
  
  //Add New button
  @FindBy(how=How.ID, using="btnAddNew")
  public WebElement btnAddNew;
  
  //Save Button
  @FindBy(how=How.ID, using="btnSave")
  public WebElement btnSave;
  
 }