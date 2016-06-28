package Orion1_Reusable;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import commonfunctions.UserExtension;
import uimap_Orion1.AdmissionsManagerPage;

public class ReusableMethods {
	public uimap_Orion1.SearchLeadsPageObjects uiSearchLeadPage;
	public uimap_Orion1.Homepage uiHomePage;
	public uimap_Orion1.AdmissionsManagerPage uiAdmMgrPage;
	public uimap_Orion1.AdmissionsPage uiAdmissionsPage;
	
  @Test
  public static void WaitforLeadinAdmManager(WebDriver driver, int timeout, String Fname, String Lname ) throws InterruptedException {
	  int count =0;
		
		for(int i=0;i<5;i++)
		{ try {	
			//refreshing/reloading the page
			driver.findElement(By.xpath("//a[@class='subnav'][text()='Admissions Manager']")).click();
		  	Thread.sleep(1000);
		  	
		  	System.out.println(driver.findElement(By.xpath("(//td[@class='datagridcell']/a)[3]")).getText());
		  	System.out.println(Lname+", "+Fname);
		  	//Verification
		  	if((driver.findElement(By.xpath("(//td[@class='datagridcell']/a)[3]")).getText()).trim().equalsIgnoreCase(Lname+", "+Fname))
		  	{
		  		System.out.println("PASS");
		  		i=21;
		  	}
		  	else {
		  		System.out.println("FAIL");
				count=count+1;
				System.out.println("No of Iterations"+" "+count);
				Thread.sleep(timeout);
		
			}
		  	
		  }
			catch(Exception e)
			{
				System.out.println(e.getMessage());		
			}

		}
  }
//Prerequisite is that Orion main window should be on admissions Manager Page
  public  boolean SearchLeadinOrion(WebDriver driver, String sEmailAddress){
	  try{	
		  
		  	uiHomePage  = new uimap_Orion1.Homepage(driver);
	  		uiAdmissionsPage = new uimap_Orion1.AdmissionsPage(driver);
	  		uiAdmMgrPage= new uimap_Orion1.AdmissionsManagerPage(driver);
	  		uiSearchLeadPage = new uimap_Orion1.SearchLeadsPageObjects(driver);
	  		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  		//Navigate to Search Page
	  		System.out.println(driver.getCurrentUrl());
	  		driver.get(driver.getCurrentUrl());
	  		driver.switchTo().frame("Orion");
	  		uiAdmMgrPage.lnkSearch.click();
	  		System.out.println("Inside Search Lead Page");
	  		
	  		//wait till search button is displayed
	  		UserExtension.IsElementPresent(driver, uiSearchLeadPage.btnLeadsSearch);
	  		
	  		//Enter the Email address 
	  		uiSearchLeadPage.txtSearchLeadsEmail.sendKeys(sEmailAddress);
	  		uiSearchLeadPage.btnLeadsSearch.click();
	  		System.out.println("Lead Search click");
	  		Thread.sleep(10000);
	  		System.out.println(driver.findElement(By.xpath("(//td[@class='datagridcell'])[6]")).getText());
		  	//Verify Searched Lead email
	  		if(driver.findElement(By.xpath("(//td[@class='datagridcell'])[6]")).getText().trim().equalsIgnoreCase(sEmailAddress)) {
	  			System.out.println("Lead Searched successfuly");
	  			return true;
	  		}	
	  		else return false;
	  		 	
	  }
	  catch(Exception e){
		  System.out.println(e.getMessage());
		  return false;
	  }
	  
  }


}
