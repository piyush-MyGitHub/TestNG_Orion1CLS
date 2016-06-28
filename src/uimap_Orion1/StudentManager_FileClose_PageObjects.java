package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class StudentManager_FileClose_PageObjects {
	
		public StudentManager_FileClose_PageObjects(WebDriver driver) {
				
				//Initialize A AddALeadReferralPageObjects.
				PageFactory.initElements(driver, this);
			}

		public String sStudent_Manager_FileClosed_WindowName = "FileClosed";
		
		public String sMain_WindowName ="";
		
		    
		  //Contact Information Tab
		
		    @FindBy(how=How.LINK_TEXT, using="Contact Information")
		    public WebElement ContactInformationTab;
		    public String strContactInformation = "Contact Information"; 
		    
		    //Appointment Tab
		    
		    @FindBy(how=How.LINK_TEXT, using="Appointments")
		    public WebElement AppointmentsTab;
		    
		    
		    //File Close
		    
			@FindBy(how=How.ID, using="_ctl3_btnFileClose")
			public WebElement btnFileClose;
			
			
			//File closed page details
			
			//Reason DropDown
			@FindBy(how=How.ID, using="ddStatusReasons")
			public WebElement ddReason;
			
		   
		    //Sub Category DropDown
		    
		    @FindBy(how=How.ID, using = "ddStatusReasonsSubCategory")
			public WebElement ddSubCategory;	
		
			
			//Notes Required field
			@FindBy(how=How.ID, using = "txtNote")
			public WebElement txtNotesRequired;
			
			//Save and Close Button
			@FindBy(how=How.ID, using="btnSaveAndClose")
			public WebElement btnSaveAndClose;
			

			    
		}

