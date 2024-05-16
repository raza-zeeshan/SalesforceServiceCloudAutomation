package testCase;



import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Login;
import BaseClass.TestBase;
import pageObjects.CaseDetails;
import pageObjects.CasesObject;
import pageObjects.CloneCase;
import pageObjects.CloseCase;
import pageObjects.EmailCase;
import pageObjects.EscalateCase;
 
public class ServiceTest extends TestBase{
	Login sb;
	CasesObject co;
	CaseDetails cd;
	CloneCase cc;
	EscalateCase ec;
	EmailCase em;
	CloseCase end;

	@Test(priority = 1)
	public void login()
	{
		sb=new Login(driver);
		logger.info("//starting Login Test//");
		logger.info("//Entering Username and Password//");

		sb.setUserName(p.getProperty("username"));
		sb.setPassword(p.getProperty("password"));
		
		sb.clickLogin();
		logger.info("//Login Test Completed Successfully//");

	} 

    @Test(priority = 2)
    void cases()
    {
    	co=new CasesObject(driver);
		logger.info("//starting Case Test//");

    	co.clickCases();
		logger.info("//Clicked on Case Object//");

    	co.clickNew();
		logger.info("//Clicked on New Button//");
    	
    	co.recordType();
		logger.info("//Record Type Selected//");

    	co.clickContinue();
		logger.info("//Case Test Completed Successfully//");

    }  

    @Test(priority = 3)
    void casedetails() throws InterruptedException
    {
    	cd=new CaseDetails(driver);
		logger.info("//Starting Case Details Test//");

    	cd.click_Status();
		logger.info("//Clicked on Status Button//");

    	cd.click_Caseorigin();
		logger.info("//Case Origin Selected//");

    	cd.getSubject(p.getProperty("subject"));
		logger.info("//Subject Added//");

    	cd.getDescription(p.getProperty("desc"));
		logger.info("//Description Added//");

    	cd.clickAccountname();
    	cd.clickSearch(p.getProperty("Aname"));
		logger.info("//Account Name Added//");

    	cd.clickGo();
    	cd.clickAname();
    	cd.clickSave();   
		logger.info("//Clicked on Save Button//");
		logger.info("//Case Details Test Completed Successfully//");


    } 

    @Test(priority = 4)
    void cloneCase()
    {
    	cc=new CloneCase(driver);
		logger.info("//Starting Clone Case Test//");

    	cc.clickDetails();
		logger.info("//Clicked on Details Section//");

    	cc.clickClone();
		logger.info("//Clicked on Clone Button//");

    	cc.getSubclone(p.getProperty("Clone"));
		logger.info("//Subject Changed Successfully//");

    	cc.clickSave();	
		logger.info("//Clicked on Save Button//");
		logger.info("//Clone Case Test Completed Successfully//");


    }  

    @Test(priority = 5)
    void checkclone()
    {		
    	logger.info("//Starting Clone Check Test//");

    	Assert.assertEquals(true,cc.check());
		logger.info("//Clone Check Test Completed Successfully//");

    } 

    @Test(priority = 6)
    void Escalate()
    {
		logger.info("//Starting Escalate Test//");

    	ec=new EscalateCase(driver);
    	ec.clickEscalate();
		logger.info("//Clicked on Escalate Button//");

    	ec.getParent(p.getProperty("parent"));
		logger.info("//Parent Record Selected//");

    	ec.click_Caseorigin();
		logger.info("//Case Origin Changed//");

    	ec.click_Status();
		logger.info("//Status Value Changed//");

    	ec.clickSLA();
    	ec.getSubject(p.getProperty("escalate"));
		logger.info("//Subject Modified//");

    	ec.clickSave();	
		logger.info("//Clicked on Save Button//");
		logger.info("//Escalate Test Completed Successfully//");


    }  
    @Test(priority = 7)
    void checkStatus()
    {		
    	logger.info("//Starting Check Status Test//");

    	Assert.assertEquals("Working",ec.checkStatus());
		logger.info("//Check Status Test Completed Successfully//");

 
    } 

    @Test(priority = 8)
    void email()
    {
		logger.info("//Starting Email Test//");

    	em= new EmailCase(driver);
    	em.intoCases();
		logger.info("//Clicked on Case Button//");

    	em.clickClone();
		logger.info("//Clicked on Clone Button//");

    	em.clickDetails();
		logger.info("//Clicked on Details Section//");

    	em.clickEmail();
		logger.info("//Clicked on Email Button//");

    	em.clickTo();
    	em.clickSearch(p.getProperty("to"));
		logger.info("//Clicked on To Button//");

    	em.clickGo();
    	em.clickAname();
		logger.info("//Selected Account Name//");

    	em.clickTemplate();
		em.selectTemp();
		logger.info("//Selected Template//");
    	em.clickAttach();
    	logger.info("//Clicked on Attached File Button//");
    	em.clickfile();
    	logger.info("//Selected File//");
    	em.clickAttachMail();
    	em.clickDone();   
    	logger.info("//Clicked on Done Button//");
    	em.clickSend();
		logger.info("//Clicked on Send Button//");
		logger.info("//Email Case Completed Successfully//");

    } 
    @Test(priority = 9)
    void SLA()
    {
		logger.info("//Starting SLA Test//");

    	end=new CloseCase(driver);
    //	Assert.assertEquals("Yes",end.validateSLA());
    	end.clickEdit();
		logger.info("//Clicked on Edit Button//");

    	end.changeStatus();
		logger.info("//Status Value Modified//");

    	end.clickSave();
		logger.info("//Clicked on SAve Button//");
		logger.info("//SLA Test Completed Successfully//");


    }
}
