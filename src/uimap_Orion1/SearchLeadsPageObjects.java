package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import commonfunctions.UserExtension;

public class SearchLeadsPageObjects {
	 
		public SearchLeadsPageObjects(WebDriver driver) {
				
				//Initialize A SearchPageObjects.
				PageFactory.initElements(driver, this);
			}

		public String sStudentManager_WindowName = "StudentInfo";
		public String sSearchMain_WindowName ="";
		
		

		//Admissions Tab
		//@FindBy(how=How.XPATH, using=".//*[@id='_ctl1_TopNavRow']/td[2]")
		//public WebElement lnkAdmissionsTab;
		
		//Admissions tab
		  @FindBy(how=How.XPATH, using= ".//td[text()='Admissions']")
		  public WebElement tabAdmissions;
		  
		//Admission Manager link
		
		  @FindBy(how=How.LINK_TEXT, using="Admissions Manager")
		  public WebElement lnkAdmissionsManager;
		  
		  @FindBy(how=How.LINK_TEXT, using = "Search")
			public WebElement lnkSearchLeads;
		  
		  //Admission Manager link
		
		//@FindBy(how=How.XPATH, using=".//*[@id='_ctl1_ApplicationsNavRow']/a[5]")
		//public WebElement lnkAdmissionManager;
		
		
		
		
	    //Search Leads
	   //@FindBy(how=How.XPATH, using=".//*[@id='tabnavi_TabRow']/td[18]/a")
		//public WebElement lnkSearchLeads;
		
	 //Click on Admissions - > Search Leads
	//public SearchLeadsPageObjects ClickAdmissionsManager(WebDriver driver)
		{
			//UserExtension.MouseOver(driver, tabAdmissions);
			//UserExtension.IsElementPresent(driver, lnkAdmissionManager);
			//lnkAdmissionManager.click();
		//SearchLeadsPageObjects objPage = new SearchLeadsPageObjects(driver);
			//return(objPage);
		}
		
		//First Lead Link
		
		@FindBy(how=How.ID, using="dgNewLeads")
		
	    public WebElement lnkFirstLeadInTable;
		
		
		
		
		//Contact Information page details
		
		
			
			//Contact Information Tab
			@FindBy(how=How.XPATH, using=".//*[@id='TabRow']/td[2]/strong/a")
			public WebElement ContactInformationTab;
		    public String strContactInformation = "Contact Information"; 
		    
		    //First Name
		    
			@FindBy(how=How.ID, using="_ctl3_cifirstname")
			public WebElement txtFirstName;
			
			//Last Name
			@FindBy(how=How.ID, using="_ctl3_cilastname")
			public WebElement txtLastName;
			
			//Email Address
			
			@FindBy(how=How.ID, using="_ctl3_ciemail")
			public WebElement txtEmailAddress;
			
		    //Lead ID
		    
		    @FindBy(how=How.ID, using = "_ctl3_lblcileadid")
			public WebElement LeadID;
		    
		    //Search Lead button
		    
		   // @FindBy(how=How.XPATH, using = ".//*[@id='tabnavi_TabRow']/td[18]/a")
			//public WebElement btnSearchLeads;
		    
		    
		    
		    
			
			//text fields on Search Leads page
			
			//Lead Id text box

			@FindBy(how=How.ID, using = "tbSearchAllLeadID")
			public WebElement txtSearchLeadID;
			
			//First Name Text Box
			
			@FindBy(how=How.ID, using = "tbSearchAllFname")
			public WebElement txtSearchLeadsFirstName;
			
			//Last Name
			
			@FindBy(how=How.ID, using = "tbSearchAllLname")
			public WebElement txtSearchLeadsLastName;
			
			//Email Text Box
			@FindBy(how=How.ID, using="tbSearchAllEmail")
			public WebElement txtSearchLeadsEmail;
		
			
			//Leads Search Button
			@FindBy(how=How.ID, using="btnSearchAllFilter")
			public WebElement btnLeadsSearch;
			
			//Email Result fields
			@FindBy(how=How.XPATH, using= ".//*[@id='dgSearchAll']/tbody/tr[2]/td[6]")
			public WebElement resultsEmailField;
			
				// First Search Result
				
				//@FindBy(how=How.XPATH, using=".//*[@id='nm26866051']")
					
				//public WebElement firstSearchResult;
				
			
}








	
