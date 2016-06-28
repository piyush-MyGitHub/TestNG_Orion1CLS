package uimap_Orion1;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;

	public class AccountingPageObjects {
		//constructor to initialize page elements
	  public AccountingPageObjects(WebDriver driver){
		  PageFactory.initElements(driver, this);
	  }
	  
	//Accounting tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Accounting']")
	  public WebElement tabAccounting;
	  
	  
	//Shift Delivery Types link 
	  @FindBy(how=How.LINK_TEXT, using="Shift Delivery Types")
	  public WebElement lnkShiftDeliveryTypes;
	//Cookie Crum Cell text
	  public String sCCCShiftDeliveryManagement="Home :: Accounting :: Shift Delivery Type Management";
	  
	//Delivery Types link 
	  @FindBy(how=How.LINK_TEXT, using="Delivery Types")
	  public WebElement lnkDeliveryTypes;
	  
	//Cookie Crum Cell text
	  public String sCCCShiftDeliveryType="Home :: Accounting :: Delivery Types";
	  
	//Campus Code Management link 
	  @FindBy(how=How.LINK_TEXT, using="Campus Code Management")
	  public WebElement lnkCampusCodeManagement;
	//Cookie Crum Cell text
	  public String sCCCampusCodeManagement="Home :: Accounting :: Campus Code Managemet";
	  
	//Accounting Code Management link 
	  @FindBy(how=How.LINK_TEXT, using="Accounting Code Management")
	  public WebElement lnkAccountingCodeManagement;
	  
	//Cookie Crum Cell text
	  public String sCCAccountingCodeManagement="Home :: Accounting :: Accounting Code Management";
	  
	//Output File service Management link 
	  @FindBy(how=How.LINK_TEXT, using="Output File Service Management")
	  public WebElement lnkOutputFileServiceManagement;
	  
	//Cookie Crum Cell text
	  public String sCCOutputFileServiceManagement="Home :: Accounting :: Output File Service Management";
	  
	//File Export Utility link 
	  @FindBy(how=How.LINK_TEXT, using="File Export Utility")
	  public WebElement lnkFileExportUtility;
	  
	  //Cookie Crum Cell text
	  public String sCCCFileExportUtility="Home :: Accounting :: File Export Utility";
	  
	

	 }

