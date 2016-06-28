package uimap_Orion1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


			public class FileCloseStudentManagerPage {
			public FileCloseStudentManagerPage(WebDriver driver) {
					
					//Initialize A AddALeadReferralPageObjects.
					PageFactory.initElements(driver, this);
				}

			public String sStudent_ManagerFileClose_WindowName ="FileClosed";

			//Appointment Tab
			@FindBy(how=How.XPATH, using=".//*[@id='TabRow']/td[4]/a")
			public WebElement lnkAppointmentTab;
		    public String strAppointmentText = "Appointment";
		    
			    //FileClose Button
			    
				@FindBy(how=How.ID, using="_ctl3_btnFileClose")
				public WebElement btnFileClose;
				
				//File close Icon on Admission Page
				
				  @FindBy(how=How.XPATH, using="//td[14]/a/img")
				    public WebElement lnkAdmissionPageFileClose;
			  
                //Select Reason DropDown
			    
				@FindBy(how=How.ID, using="ddStatusReasons")
				public WebElement ddSelectReason;
				
				//Select Sub Reason Actions
				
				@FindBy(how=How.ID, using="ddStatusReasonsSubCategory")
				public WebElement ddSubReason;
				
				//Lead Contacted
				
				@FindBy(how=How.ID, using="cbLeadContacted")
				public WebElement checkBoxLeadContacted;
				
				//Text Notes Required
				
				@FindBy(how=How.ID, using="txtNote")
				public WebElement txtNotesRequired;
				
				
				//Save Button
				
				@FindBy(how=How.ID, using="btnSaveAndClose")
				public WebElement btnSaveandCloseButton;
				
				//File close text verification
				
				
				@FindBy(how=How.XPATH, using=".//*[@id='dgSearchAll']/tbody/tr[2]/td[10]")
				public WebElement txtFileCloseStatus;
				
				

			}
