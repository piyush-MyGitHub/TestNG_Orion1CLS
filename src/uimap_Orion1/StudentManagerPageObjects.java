package uimap_Orion1;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


	public class StudentManagerPageObjects {
	public StudentManagerPageObjects(WebDriver driver) {
			
			//Initialize A AddALeadReferralPageObjects.
			PageFactory.initElements(driver, this);
		}

	public String sStudent_Manager_WindowName = "StudentInfo";
	
	public String sMain_WindowName ="";
	
	//Admissions tab
	  @FindBy(how=How.XPATH, using= ".//td[text()='Admissions']")
	  public WebElement tabAdmissions;
	  
	//Admission Manager link
	
	  @FindBy(how=How.LINK_TEXT, using="Admissions Manager")
	  public WebElement lnkAdmissionsManager;
		
  //First Lead Link
		
	 @FindBy(how=How.ID, using="dgNewLeads")
				
	public WebElement lnkFirstLeadInTable;
		
		//New Lead Tab
		@FindBy(how=How.XPATH, using=".//*[@id='tabnavi_TabRow']/td[2]/strong/a")
		public WebElement NewLeadTab;
		public String strNewLeadTab = "New Leads";

		
		//@FindBy(how=How.XPATH, using=".//*[@id='TabRow']/td[2]/strong/a")
		//public WebElement ContactInformationTab;
	    //public String strContactInformation = "Contact Information"; 
	    
	  //Contact Information Tab
	    @FindBy(how=How.LINK_TEXT, using="Contact Information")
	    public WebElement ContactInformationTab;
	    public String strContactInformation = "Contact Information"; 
	    
	    //Address Line1
	    
		@FindBy(how=How.ID, using="_ctl3_ciaddr")
		public WebElement addressLine1;
		
		//City
		@FindBy(how=How.ID, using="_ctl3_cicity")
		public WebElement city;
		
	   
	    //Program DropDown
	    
	    @FindBy(how=How.ID, using = "_ctl3_dpProgram")
		public WebElement ProgramDropDown;	
	
		
		//Start Date
		@FindBy(how=How.ID, using = "_ctl3_dpStartDate")
		public WebElement startDateDropDown;
		
		//Contact Button
		@FindBy(how=How.ID, using="_ctl3_btnStatusChange")
		public WebElement btnLeadContacted;
		public String strContactButton = "Lead Contacted";

		//Save button
		@FindBy(how=How.ID, using= "_ctl3_btnSaveAndClose")
		public WebElement btnSave;
	
			//Appointment Tab
			@FindBy(how=How.XPATH, using=".//*[@id='TabRow']/td[4]/a")
			public WebElement lnkAppointmentTab;
		    public String strAppointmentText = "Appointment";
		    
		    //Appointment Notes text field
		    
		    @FindBy(how=How.ID, using="_ctl3_tbnotes")
			public WebElement txtAppointmentNotes;
		  
		    
		    //Appointment Save button
		    
		    
		    @FindBy(how=How.ID, using="_ctl3_btnSave")
			public WebElement btnAppointmentSave;
		   
		    
		    //Appointment Yes link
		    
		    @FindBy(how=How.ID, using="_ctl3_btnSaveYes")
			public WebElement btnYes;
		   
		   
		    
		    //Check Interview Schedule text on Contact Information Tab
		    @FindBy(how=How.ID, using="_ctl3_lblcicurrentstatus")
			public WebElement txtInterviewSchedule;
		    
		    
		    //Interview complete link
		    @FindBy(how=How.ID, using="_ctl3_btnStatusChange")
			public WebElement btnInterviewComplete;
		    
		  //Status change button
		    @FindBy(how=How.ID, using="_ctl3_btnStatusChange")
			public WebElement btnStatusChange;
		    
		    //Admission Lead Status
		    @FindBy(how=How.ID, using="_ctl3_lblcicurrentstatus")
			public WebElement txtAdmLeadStatus;
		    public String scontactLeadStatus= "Contact";
		    public String sIntCompleteLeadStatus= "Interview Complete";
		    public String sIntScheduledLeadStatus= "Interview Scheduled";
		    public String sExamPendingLeadStatus= "Exam Pending";
		    public String sExamReceivedLeadStatus= "Exam Received";
		    public String sPendingEnrollmentLeadStatus= "Pending Enrollment";
		    public String sFileCloseStatus= "File Close";    
	}


