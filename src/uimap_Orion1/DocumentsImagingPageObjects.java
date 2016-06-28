package uimap_Orion1;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.How;
	import org.openqa.selenium.support.PageFactory;

	public class DocumentsImagingPageObjects {
		//constructor to initialize page elements
	  public DocumentsImagingPageObjects(WebDriver driver){
		  PageFactory.initElements(driver, this);
	  }
	  
	//Documents Imaging Tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Document Imaging']")
	  public WebElement tabDocumentsImaging;
	  
	  
	//Document Imaging Home link 
	  @FindBy(how=How.LINK_TEXT, using="Document Imaging Home")
	  public WebElement lnkDocumentImagingHome;
	
	  //Verify Text Documents Imaging
	  
	  @FindBy(how=How.XPATH, using=".//*[@id='DefaultNavTabs_TabRow']/td[2]/strong/a")
	  public WebElement txtSearchStudents;
	  
	  
	  
	//Admin link 
	  @FindBy(how=How.LINK_TEXT, using="Admin")
	  public WebElement lnkAdmin;
	
	  
	//Document Mapping link 
	  @FindBy(how=How.LINK_TEXT, using="Document Mapping")
	  public WebElement lnkDocumentMapping;
	  
	
	//Document Verification link 
	  @FindBy(how=How.LINK_TEXT, using="Document Verification")
	  public WebElement lnkDocumentVerification;
	  
	  
		//Display Scheduling on  Documents Mapping page
		  @FindBy(how=How.XPATH, using=".//*[@id='Table5']/tbody/tr/td[2]")
		  public WebElement txtDocumentMappingPage;
		  
		  public String txtDisplayStudents="Display Students";
		  
		  

	  
	//Document Scheduling link 
	  @FindBy(how=How.LINK_TEXT, using="Document Scheduling")
	  public WebElement lnkDocumentScheduling;
	
	  
	//Reports link 
	  @FindBy(how=How.LINK_TEXT, using="Reports")
	  public WebElement lnkReports;

	  
	 }

