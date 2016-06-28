package uimap_Orion1;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;

	public class EditorialPageObjects {
		//constructor to initialize page elements
	  public EditorialPageObjects(WebDriver driver){
		  PageFactory.initElements(driver, this);
	  }
	  
	//Editorial tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Editorial']")
	  public WebElement tabEditorial;
	  
	  
	//Program Mapping link 
	  @FindBy(how=How.LINK_TEXT, using="Program Mapping")
	  public WebElement lnkProgramMapping;
	//Cookie Crum Cell text
	  public String sCCCProgramMapping="Home :: Editorial :: Program Mapping";
	  
	//Program Group Mapping link 
	  @FindBy(how=How.LINK_TEXT, using="Program Group Mapping")
	  public WebElement lnkProgramGroupMapping;
	  
	//Cookie Crum Cell text
	  public String sCCCProgramGroupMapping="Home :: Editorial :: Program Group Management";
	  
	//Global Program Mapping link 
	  @FindBy(how=How.LINK_TEXT, using="Global Program Mapping")
	  public WebElement lnkGlobalProgramMapp;
	//Cookie Crum Cell text
	  public String sCCCGlobalProgramMapping="Home :: Editorial :: Global Program Mapping ";
	  
	//Global Program Mapping text page 
	  @FindBy(how=How.XPATH, using=".//*[@id='dgGPM']/tbody/tr[1]/td[1]/a")
	  public WebElement txtGlobalProgramMapp;
	//Cookie Crum Cell text
	  
	  
	  

}
