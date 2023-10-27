package exe_pkg;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base_pkg.Resume_now;

public class Resume_now_test {
	WebDriver driver;
 
	
	

	@BeforeTest
	public void setup()
	{
		driver=new ChromeDriver();
	}
	
	@BeforeMethod
	public void urlloading()
	{
		driver.get(" https://www.resume-now.com/");
	}
	@Test
	public void testresume() throws Exception{
		Resume_now rn=new Resume_now(driver);
		driver.manage().window().maximize();
		rn.viewboxcheck();
		rn.titleverification();
		rn.contentverification();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		rn.screenshoot();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		rn.mouseover();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		rn.scrolldown();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		rn.windowhandling();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		rn.login();
		//driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		rn.fileupload();
		
	}
	
}
