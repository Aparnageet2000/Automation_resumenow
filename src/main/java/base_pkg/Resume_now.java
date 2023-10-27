package base_pkg;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

public class Resume_now {
	WebDriver driver;
	By Viewbox=By.xpath("/html/body/header/div[1]/div[1]/div[1]");
	By coverletter=By.xpath("/html/body/header/div[1]/div[1]/div[2]/nav/ul/li[5]");
	By cvbuilder=By.xpath("/html/body/header/div[1]/div[1]/div[2]/nav/ul/li[5]/ul/li[1]/a");
	By login=By.xpath("/html/body/header/div[1]/div[1]/div[2]/nav/ul/li[10]");
	By emailid=By.xpath("//*[@id=\"widget-user-email\"]");
	By pass=By.xpath("//*[@id=\"widget-user-password\"]");
	By log=By.xpath("//*[@id=\"btnSignIn\"]");
	By fb=By.xpath("/html/body/div[2]/footer/div[1]/div/div[5]/div/ul/li[1]/a");
	By fblogin=By.xpath("//*[@id=\"loginbutton\"]");
	By createresume=By.xpath("//*[@id=\"content\"]/div/div/div[1]/section[1]/div/div[2]/button");
	By skipfornow=By.xpath("//*[@id=\"skipChooseTemplate\"]");
	By ahresume=By.xpath("//*[@id=\"dz-component\"]");
	By browse=By.xpath("//*[@id=\"btnBrowse\"]");


	public Resume_now(WebDriver driver)
	{
		this.driver=driver;
	}
	public void viewboxcheck()
	{
		WebElement l=driver.findElement(Viewbox);
		boolean b=l.isDisplayed();
		if(b)
		{
			System.out.println("view box is Displayed");
		}
		else
		{
			System.out.println("view box is not Displated");
		}
	}
	public void titleverification()
	{
		String title=driver.getTitle();
		String a="resume-now";
	if(title.equals(a)) {
	System.out.println("PASS titleverification");
	}
	else {
		System.out.println("FAIL titleverification");
	}
	}
	public void contentverification()
	{
		String content=driver.getPageSource();
		if(content.contains("Cover Letter")) {
			System.out.println("PASS contentverification");
		}
		else
		{
		    System.out.println("FAIL contentverification");
		}
	}
	public void mouseover() {
		WebElement a=driver.findElement(coverletter);
		Actions act=new Actions(driver);
		act.moveToElement(a).perform();
		driver.findElement(cvbuilder).click();
		
	}
	public void screenshoot() throws IOException
	{
		File fi=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(fi, new File("D:\\screenshot.png"));
	}
	

     public void login()throws IOException{
    	 driver.findElement(login).click();
    	 File f=new File("D:\\resumenow.xlsx");
    	 FileInputStream fi=new FileInputStream(f);
    	 XSSFWorkbook wb=new  XSSFWorkbook(fi);
    	 XSSFSheet sh=wb.getSheet("Sheet1");
    	 System.out.println(sh.getLastRowNum());
    	 
    	 for(int i=1;i<=sh.getLastRowNum();i++)
    	 {
    		 String email =sh.getRow(i).getCell(0).getStringCellValue();
    		 System.out.println("email");
    		 String pswd=sh.getRow(i).getCell(1).getStringCellValue();
    		 System.out.println("pswd");
    		 
    		 driver.findElement(emailid).sendKeys(email);
    		 driver.findElement(pass).sendKeys(pswd);
    		 driver.findElement(log).click();
    		
    		
    	 }
     }
     
     public void windowhandling() {
    	 String parentwindow= driver.getWindowHandle();
    	 System.out.println("parent window Title" +driver.getTitle());
    	 driver.findElement(fb).click();
    	 Set<String> allwindowhandles=driver.getWindowHandles();
    	 
    	 for(String handle:allwindowhandles)
    	 {
    		 System.out.println(handle);
    		 
    		 if(!handle.equalsIgnoreCase(parentwindow))
    		 {
    			 driver.switchTo().window(handle);
    			 driver.findElement(fblogin).click();
    			 driver.close();
    		 }
    		 driver.switchTo().window(parentwindow);
    	 }
    
     }
     public void scrolldown()
     {
    	 JavascriptExecutor js=(JavascriptExecutor) driver;
    	 js.executeScript("window.scrollBy(0,1000)", "");
    	 
    	 driver.findElement(fb);
     }
     public void fileupload() throws Exception{
    	 driver.findElement(skipfornow).click();
    	 driver.findElement(ahresume).click();
    	 driver.findElement(browse).click();
    	 
    	 
    	 Robot rb=new Robot();
    	 StringSelection str=new StringSelection("D:\\FAKE RESUME.docx");
    	 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
    	 
    	 rb.keyPress(KeyEvent.VK_CONTROL);
    	 rb.keyPress(KeyEvent.VK_V);
    	 rb.keyRelease(KeyEvent.VK_V);
    	 rb.keyRelease(KeyEvent.VK_CONTROL);
    	 rb.keyPress(KeyEvent.VK_ENTER);
    	 rb.keyRelease(KeyEvent.VK_ENTER);
    	 
  
    	 
    	
    	 
     }
}
