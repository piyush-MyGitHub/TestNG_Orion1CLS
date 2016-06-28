package orion1_NewLeadsPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

	public class AddNewLeadPageObjects {

		public AddNewLeadPageObjects(WebDriver driver) {
			//Initialize A PageObjects.
			PageFactory.initElements(driver, this);	
		}
		public String sAddNewLead_WindowName = "script";
		
		public String sMain_WindowName ="";
		
		//Promotion default after selecting Channel Group
		@FindBy(how=How.XPATH, using="//select/option[@value='0'][text()='Choose a Promotion']")
		public WebElement ddoption_Promotiondefault;
		
		//program of interest default after selecting Area of study
		@FindBy(how=How.XPATH, using= "//select/option[@value='0'][text()='Select a program']")
		public WebElement ddoption_POIdefault;
		
		
		//Admissions tab
		  @FindBy(how=How.XPATH, using= ".//td[text()='Admissions']")
		  public WebElement tabAdmissions;
		  
		//Admission Manager link
		
		  @FindBy(how=How.LINK_TEXT, using="Admissions Manager")
		  public WebElement lnkAdmissionsManager;
		
		//Add a Lead Referral lbl
		@FindBy(how=How.XPATH, using=".//*[@id='tabnavi_TabRow']/td[20]/a/img")
		public WebElement lblAddNewLeadReferral;
		public String strAddALeadReferral = "Add New Lead/Referral";
		
       //Lead Origination Type
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_RadCheck_0")
		public WebElement rbtnLeadType_InfoCall;

		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_RadCheck_1")
		public WebElement rbtnLeadType_Referral;

		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_RadCheck_2")
		public WebElement rbtnLeadType_LiveChat;
		
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_RadCheck_3")
		public WebElement rbtnLeadType_WarmTransfer;
		
//		Source Code
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_tbSourceCode")
		public WebElement txtSourceCode;
		
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_BtnSourceCode")
		public WebElement btnSourceCode;
		
//		ChannelGroups 
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_radChannelGroup_0")
		public WebElement rbtnCG_Warm_Transfer;
		
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_radChannelGroup_1")
		public WebElement rbtnUnsourced;
		
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_radChannelGroup_2")
		public WebElement rbtnEMail;
		
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_radChannelGroup_3")
		public WebElement rbtnPhysical_Mail;
		
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_radChannelGroup_4")
		public WebElement rbtnGOK;
		
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_radChannelGroup_5")
		public WebElement rbtnELS_Partner;
		
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_radChannelGroup_6")
		public WebElement rbtnCG_Events;
		
		@FindBy(how=How.ID_OR_NAME,using="CtlApplyForm1_radChannelGroup_7")
		public WebElement rbtnPrint;
		
		//DropDown Promotion
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_ddPromotion")
		public WebElement ddlPromotionCode;	
		public By ddlPromotionCodeLocator = new By.ById("CtlApplyForm1_ddPromotion");
		
		public String sPromotionCodeDefaultText_before =  "Please Select a Channel Group First";
		public String sPromotionCodeDefaultText_after = "Please Choose a Promotion";
		
		//DropDown Area of Study 
		@FindBy(how=How.ID, using = "CtlApplyForm1_ddAreaStudy")
		public WebElement ddlAreaOfStudyTemp;
		public String sAreaofStudyDefaultText = "Select An Area of Study";
		
		//DropDown Program of Interest
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_ddProgramInterest")
		public WebElement ddlProgramOfInterest;
		public String sProgramOfInterest = "Select a Program";
		
		
		//Saturation

		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRSalutation")
		public WebElement ddSaturation;
		
		//First Name

		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRFname")
		public WebElement txtFirstName;
		
		//Last Name

		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRLname")
		public WebElement txtLastName;

		//Email Address
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLREmail")
		public WebElement txtEmailAddress;
		
		//Home Phone
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLNHPhone")
		public WebElement txtHomePhone;
		
		//Day Time Phone
		
				@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRWPhone")
				public WebElement txtDayTime;
				
		
		//Address Line 1

		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRAddress")
		public WebElement txtAddress;
		
		//Address Line 2
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLNAddress2")
		public WebElement txtAddress2;
		
		//city

		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRCity")
		public WebElement txtCity;
		
		
		//State

		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLNState")
		public WebElement ddlState;
		
		//ZIP Code
		
		

		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLNZip")
		
		public WebElement txtZipCode;
		
		//ZIP Referral
		
@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRZip")
		
		public WebElement txtZipCodeReferral;
		
		//DropDown Country
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRCountry")
		public WebElement ddlCountry;
		
	
		
		//Bachelor degree yes
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRBachelors_0")
		public WebElement rbtnBechalor_degree_yes;
		
		//Bachelor degree No
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRBachelors_1")
		public WebElement rbtnBechalor_degree_No;
		
		
		
		//Spouse Radio Button yes
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRMilitary_0")
		public WebElement rbtnSpouse_Yes;
		
		//Spouse Radio Button No
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRMilitary_1")
		public WebElement rbtnSpouse_No;
		
		//DropDown Military Types
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLNMilitary2")
		public WebElement ddlMilitaryType;
		
		

		//TCPA button Yes

		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRTCPA_Disc_0")
		public WebElement rtbnTCPA_Yes;
		
		//TCPA button No

		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLRTCPA_Disc_1")
		public WebElement rtbnTCPA_No;
		
		//Highest Level of Education
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLNHighestEducation")
		public WebElement ddHighestEducation;
		
		//Military Type
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_XMLNMilitary2")
		public WebElement ddMilitaryType;
		
	
		//First Lead Link
		
		@FindBy(how=How.ID, using="dgNewLeads")
		
	    public WebElement lnkFirstLeadInTable;

		//Button Add Lead
		
		@FindBy(how=How.ID, using = "CtlApplyForm1_btnAddLead")
		public WebElement btnAddALead;
		
		//Reusable Methods
		public void SelectLeadOriginatedFromAs(String sLeadType)
		{
			if(sLeadType.equalsIgnoreCase("InfoCall"))
			{
				rbtnLeadType_InfoCall.click();
			}
			else if(sLeadType.equalsIgnoreCase("LiveChat"))
			{
				rbtnLeadType_LiveChat.click();
			}
			else if(sLeadType.equalsIgnoreCase("Referral"))
			{
				rbtnLeadType_Referral.click();
			}
			else if(sLeadType.equalsIgnoreCase("WarmTransfer"))
			{
				rbtnLeadType_WarmTransfer.click();
			}
		}
		
		public void SelectChannelGroupAs(String sChannelGroup)
		{
			if(sChannelGroup.equalsIgnoreCase("Warm Transfers"))
			{
				rbtnCG_Warm_Transfer.click();
			}
			else if(sChannelGroup.equalsIgnoreCase("Email"))
			{
				rbtnEMail.click();
			}
			else if(sChannelGroup.equalsIgnoreCase("Physical Mail"))
			{
				rbtnPhysical_Mail.click();
			}
			else if(sChannelGroup.equalsIgnoreCase("GOK"))
			{
				rbtnGOK.click();
			}
			else if(sChannelGroup.equalsIgnoreCase("ELS Partner"))
			{
				rbtnELS_Partner.click();
			}
			else if(sChannelGroup.equalsIgnoreCase("Unsourced/Unknown Inbound Calls"))
			{
				rbtnUnsourced.click();
			}
			else if(sChannelGroup.equalsIgnoreCase("Events"))
			{
				rbtnCG_Events.click();
			}
			
			
			else if(sChannelGroup.equalsIgnoreCase("Print"))
			{
				rbtnPrint.click();
			}
			
		}
		
		
	}



