package works.yermi.selenium;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, SecurityConfig.class})
@Log4j
public class SeleniumTest_dongyeop {
	
	private final String WEB_DRIVER_ID = "webdriver.chrome.driver"; 
	private final String WEB_DRIVER_PATH = "C:/Users/SAMSUNG/Desktop/devtools/chromedriver.exe";
	private String url = "http://project.yermi.works/member/mypage/listReply"; 
	private WebDriver driver;

	public SeleniumTest_dongyeop() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		driver = new ChromeDriver();
	}
	
	public static void main(String[] args) throws InterruptedException {
		SeleniumTest_yechan test = new SeleniumTest_yechan();
		test.crawl();
	}
	
	
	@Test
	public void crawl() throws InterruptedException {
		
		driver.get(url); //여수어때 url 접속
		log.info(url);
		
		driver.findElement(By.id("username")).sendKeys("dullin");
		driver.findElement(By.id("password")).sendKeys("Ehdduq8207!");
		driver.findElement(By.id("btnLogin")).click();
		
		Thread.sleep(1000);
		
		List<WebElement> list = driver.findElements(By.cssSelector("tbody > tr"));
		log.info(list.size() + "건");
		
		for(int i = 0 ; i < list.size() ; i++) {
			WebElement product = list.get(i);
			log.info("=========================================");
			log.info("예약번호 :: " + product.findElement(By.cssSelector("td")).getText());
			log.info("숙소이름 :: " + product.findElement(By.cssSelector("td > a")).getText());
			log.info("체 크 인 :: " + product.findElement(By.cssSelector("td:nth-child(3)")).getText());
			log.info("체크아웃 :: " + product.findElement(By.cssSelector("td:nth-child(4)")).getText());
			log.info("승인상태 :: " + product.findElement(By.cssSelector("td:nth-child(5)")).getText());
			
		}
		
	}
	
	static By select(String selector) {
		return By.cssSelector(selector);
	}
	
}
