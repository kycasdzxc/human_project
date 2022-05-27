package works.yermi.selenium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, SecurityConfig.class})
@Log4j
public class SeleniumTest {
	
	private final String WEB_DRIVER_ID = "webdriver.chrome.driver"; 
	private final String WEB_DRIVER_PATH = "C:\\devtools\\selenium\\chromedriver.exe";
	private String url = "http://project.yermi.works/member/mypage/listInquiry"; 
	private WebDriver driver;

	public SeleniumTest() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		driver = new ChromeDriver();
	}
	
	public static void main(String[] args) throws InterruptedException {
		SeleniumTest test = new SeleniumTest();
		test.crawl();
	}
	
	
	@Test
	public void crawl() throws InterruptedException {
		
		driver.get(url);  //여수어때 url 접속
		log.info(url);
		
		driver.findElement(By.id("username")).sendKeys("javaman");
		driver.findElement(By.id("password")).sendKeys("1234");
		driver.findElement(By.id("btnLogin")).click();
		
	}
	
	static By select(String selector) {
		return By.cssSelector(selector);
	}
	
}
