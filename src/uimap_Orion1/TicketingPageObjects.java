package uimap_Orion1;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;

	public class TicketingPageObjects {
		//constructor to initialize page elements
	  public TicketingPageObjects(WebDriver driver){
		  PageFactory.initElements(driver, this);
	  }
	  
	//Ticketing tab
	 @FindBy(how=How.XPATH, using= ".//td[text()='Ticketing']")
	public WebElement tabTicketing;
	  
	//Ticketing tab
	 // @FindBy(how=How.XPATH, using= ".//*[@id='ctl00_pnlHeader']/table[2]/tbody/tr/td[17]")
	  //public WebElement tabTicketing;
	  
	 
	  
	  
	//Ticketing Home link 
	  @FindBy(how=How.LINK_TEXT, using="Ticketing Home")
	  public WebElement lnkTicketingHome;
	//Cookie Crum Cell text
	  //public String sCCCShiftDeliveryManagement="Home :: Accounting :: Shift Delivery Type Management";
	  
	//Ticketing Dashboard link 
	  @FindBy(how=How.LINK_TEXT, using="Ticketing Dashboard")
	  public WebElement lnkTicketingDashboard;
	  
	//Cookie Crum Cell text
	  //public String sCCCShiftDeliveryType="Home :: Accounting :: Delivery Types";
	  
	//Campus Code Management link 
	  @FindBy(how=How.LINK_TEXT, using="Ticketing Admin")
	  public WebElement lnkTicketingAdmin;
	//Cookie Crum Cell text
	  //public String sCCCampusCodeManagement="Home :: Accounting :: Campus Code Managemet";
	  
	//Activity Type Admin link 
	  @FindBy(how=How.LINK_TEXT, using="Activity Type Admin")
	  public WebElement lnkActivityTypeAdmin;
	  
	//Cookie Crum Cell text
	  //public String sCCAccountingCodeManagement="Home :: Accounting :: Accounting Code Management";
	  
	
	 }




