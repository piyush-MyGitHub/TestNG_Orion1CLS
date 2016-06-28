package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ManageUsersPage {
//constructor to intialize page elements
  public ManageUsersPage(WebDriver driver){
	  PageFactory.initElements(driver, this);}
  
  //Cookie Crum cell text for Manage USers
  public String sCCCCreateUser= "Home :: Admin :: Manage Users";
  
  //View Search oprion image
  @FindBy(how=How.NAME, using= "chevron")
  public WebElement imgViewSrchOptions;
  
  //First name search box
  @FindBy(how=How.ID , using= "FirstNameSearchBox")
  public WebElement tbFirstName;
  
//Last name search box
  @FindBy(how=How.ID , using= "LastNameSearchBox")
  public WebElement tbLastName;
  
//search button
  @FindBy(how=How.ID , using= "button")
  public WebElement btnSearch;

//Edit button
  @FindBy(how=How.XPATH , using= ".//*[@id='UsersSearchResultGrid__ctl3_EditUserButton']")
  public WebElement btnEdit;
  
//Default Campus drop downlist
  @FindBy(how=How.ID , using= "ddlDefaultCampus")
  public WebElement ddlDefaultCampus;

//UpdateUser button
  @FindBy(how=How.ID , using= "EditUserButton")
  public WebElement btnUpdateUser;
  
  //Search results firstname
  @FindBy(how=How.XPATH , using= "//tr[2]/td[2]")
  public WebElement srchrsltFname;
  
  //Search results LastName
  @FindBy(how=How.XPATH , using= "//tr[2]/td[3]")
  public WebElement srchrsltLname;
  
}
