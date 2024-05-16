package BaseClass;


 
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
 
public class TestBase {
 
	

	public static WebDriver driver;

	public Logger logger;

	public Properties p;

	@BeforeTest(groups= {"sanity","regression","master"})

	@Parameters({"browser","os"})

	public void setup(String br,String os) throws IOException

	{


		FileReader file=new FileReader(".//src//test//resources//properties");

		p=new Properties();

		p.load(file);


		//loading log4j 

		logger=LogManager.getLogger(this.getClass());//Log4j


		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))

		 	{	

			DesiredCapabilities capabilities=new DesiredCapabilities();

		//os

		if(os.equalsIgnoreCase("windows"))

		{

			capabilities.setPlatform(Platform.WIN11);

		}

		else if(os.equalsIgnoreCase("mac"))

		{

			capabilities.setPlatform(Platform.MAC);

		}

		else

		{

			System.out.println("No matching os..");

			return;

		}

		//browser

		switch(br.toLowerCase())

		{

		case "chrome" : capabilities.setBrowserName("chrome"); break;

		case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;

		default: System.out.println("No matching browser.."); return;

		}

		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		 	}

		else if(p.getProperty("execution_env").equalsIgnoreCase("local"))

		{

	switch (br.toLowerCase()) {

	case "chrome": driver=new ChromeDriver(); break;

	case  "edge": driver=new EdgeDriver(); break;

	default:

		System.out.println("No Matching Browser");

		return;

	}

		}

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));	

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.get(p.getProperty("URL"));

		driver.manage().window().maximize();	

	}

	public String captureScreen(String tname) throws IOException {
 
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";

		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
 
	}


    @AfterClass(groups= {"sanity","regression","master"})

    void TearDown()

    {

    	driver.quit();

    }
 


}
