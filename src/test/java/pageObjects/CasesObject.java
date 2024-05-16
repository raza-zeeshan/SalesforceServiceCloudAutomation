package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
 
import BaseClass.Login;
 
public class CasesObject extends Login{

	 public CasesObject(WebDriver driver) {
		super(driver);
		
	}
 
 
	//WebElements Locators+identification
	@FindBy(id="Case_Tab")
	WebElement btn_cases;
	@FindBy(xpath ="//input[@title='New']")
	WebElement btn_new;
	@FindBy(id="p3")
	WebElement dropDown;
	@FindBy(xpath = "//input[@title='Continue']")
	WebElement btn_continue;
			public void clickCases()
			{
				btn_cases.click();
			}
			public void clickNew()
			{
				btn_new.click();
			}
			public void recordType()
			{
				Select sc=new Select(dropDown);
				sc.selectByVisibleText("Fms support");
			}
			public void clickContinue()
			{
				btn_continue.click();
			}

}

