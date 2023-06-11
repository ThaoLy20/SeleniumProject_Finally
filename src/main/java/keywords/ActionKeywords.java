package keywords;

import org.testng.AssertJUnit;

import utils.*;
import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionKeywords {
	private static final int timeoutWait = 10;
	private static final int timeoutWaitForPageLoaded = 20;
	public static WebDriver driver;
	private static Actions action;
	private static JavascriptExecutor js;
	private static WebDriverWait wait;
	public static Properties OR = new Properties(System.getProperties());

	private static WebElement GetElement(String locatorType, String locatorValue) {
		WebElement element = null;

		if (locatorType.equalsIgnoreCase("className"))
			element = driver.findElement(By.className(locatorValue));
		else if (locatorType.equalsIgnoreCase("cssSelector"))
			element = driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.equalsIgnoreCase("id"))
			element = driver.findElement(By.id(locatorValue));
		else if (locatorType.equalsIgnoreCase("partialLinkText"))
			element = driver.findElement(By.partialLinkText(locatorValue));
		else if (locatorType.equalsIgnoreCase("name"))
			element = driver.findElement(By.name(locatorValue));
		else if (locatorType.equalsIgnoreCase("xpath"))
			element = driver.findElement(By.xpath(locatorValue));
		else if (locatorType.equalsIgnoreCase("tagName"))
			element = driver.findElement(By.tagName(locatorValue));
		else {
			LogUtils.error("GetElement " + locatorType + "=" + locatorValue);
		}
		return element;
	}

	// Keyword Mở trình duyệt
//	public static void openBrowser(String browserName) {
//		try {
//			LogUtils.info("Executing: Open browser: " + browserName);
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//			driver.manage().window().maximize();
//		} catch (Exception e) {
//			LogUtils.error("Executing: Open browser: " + browserName + "FAIL");
//		}
//	}
	// Khoi tao cau hinh cua cac Browser de dua vao Switch Case
	private static WebDriver initChromeDriver() {
		LogUtils.info("Launching Chrome browser...");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	private static WebDriver initFirefoxDriver() {
		System.out.println("Launching Firefox browser...");
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	private static WebDriver initOperaDriver() {
		System.out.println("Launching Opera browser...");
		WebDriverManager.operadriver().setup();
		// driver=new OperaDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver openBrowser(String browserType) throws Exception {
		switch (browserType.trim().toLowerCase()) {
		case "chrome":
			driver = initChromeDriver();
			break;
		case "firefox":
			driver = initFirefoxDriver();
			break;
		case "opera":
			driver = initOperaDriver();
			break;
		default:
			// System.out.println("Browser: " + browserType + " is invalid, Launching Chrome
			// as browser of choice...");
			driver = initChromeDriver();
		}
		// wait=new WebDriverWait(driver,Duration.ofSeconds(timeoutWait));
		//Thread.sleep(5000);
		return driver;
	}

//		// Keyword truy cập URL
//		public static void navigate(String appURL) throws InterruptedException {
//			try {
//				LogUtils.info("Executing: Open Url: " + appURL);
//				driver.navigate().to(appURL);
//				waitForPageLoaded();
//			} catch (Exception e) {
//				LogUtils.error("Executing: Open Url:" + appURL + "FAIL");
//			}
//		}
	public static void navigate(String url) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			LogUtils.info("Navigate to " + url);
			wait = new WebDriverWait(driver, 5);
			driver.get(url);
			driver.manage().window().maximize();
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Error..." + e.getStackTrace());
		}
	}

	// Keyword thoát khỏi trình duyệt
	public static void closeBrowser() {

		try {
			LogUtils.info("Executing: Quit");
			driver.manage().deleteAllCookies();
			driver.quit();
		} catch (Exception e) {
			LogUtils.error("Executing: Quit FAIL");
		}
	}

	// Keyword Di chuyển tới đối tượng nhưng không ấn
	public static void ElementPerfom(String address) {

		try {
			if (address.contains("[")) {
				Actions a = new Actions(driver);
				waitForPageLoaded();
				a.moveToElement(driver.findElement(By.xpath(address))).build().perform();
				LogUtils.info("Executing: Move mouse");
			} else {
				Actions a = new Actions(driver);
				a.moveToElement(driver.findElement(By.id(address))).build().perform();
			}
		} catch (Exception e) {
			LogUtils.error("Move mouse: FAIL");
		}
	}

	// Keyword làm mới lại trang
	public static void Refresh() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(3000);
		LogUtils.info("Executing: Refresh website");
	}

	public static void waitForPageLoaded() {

		// wait for Javascript to loaded
		ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").toString().equals("complete");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(jsLoad);
		} catch (Throwable error) {
			AssertJUnit.fail("Timeout for page load (Javascript). (" + 20 + "s)");
		}
	}

	// Keyword nhập vào ô textbox
//		public static void setText(String locatorType, String locatorValue, String value) {
//			try {
//				LogUtils.info("Executing: Enter text: " + value);
//				WebElement element = GetElement(locatorType, locatorValue);
//				waitForPageLoaded();
//				element.clear();
//				element.sendKeys(value);
//			} catch (NoSuchElementException e) {
//				LogUtils.error("SendKeys:" + locatorType + "=" + locatorValue + " not found to sendKeys| " + e.getMessage());
//			}
//		}
	
	  public static boolean verifyElementText(String locatorType, String locatorValue, String expected) {
	        LogUtils.info("Executing: Verify Text");
	        String actual;
	        WebElement element= GetElement(locatorType, locatorValue);
	        waitForPageLoaded();
	        actual = element.getText();
	        LogUtils.info("Expected result: "+expected);
	        LogUtils.info("Actual result: "+actual);
	        if (actual.contains(expected))
	        {
	            return true;
	        }
	        else return false;
	  }
	  
	public static void setText(String locatorType, String locatorValue, String value) {
		WebElement element = GetElement(locatorType, locatorValue);
		waitForPageLoaded();
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}

	// Keyword ấn chuột trái
	public static void clickElement(String locatorType, String locatorValue) throws InterruptedException {
		try {
			LogUtils.info("Executing: Click element: " + locatorValue);
			WebElement element = GetElement(locatorType, locatorValue);
			waitForPageLoaded();
			element.click();
		} catch (NoSuchElementException e) {
			LogUtils.error("Click:" + locatorValue + " not found to click " + e.getMessage());
		}
		// Thread.sleep(5000);

	}

	// Keyword ấn chọn button
	public static void clickButton(String locatorType, String locatorValue) throws InterruptedException {
		try {
			LogUtils.info("Executing: Click button: " + locatorValue);
			WebElement element = GetElement(locatorType, locatorValue);
			element.click();
			//waitForPageLoaded();
		} catch (NoSuchElementException e) {
			LogUtils.error("Click:" + locatorValue + " not found to click " + e.getMessage());
		}
		// Thread.sleep(5000);
	}

	// Keyword ấn chuột trái nhưng với JS
	public static void clickElementWithJs(String locatorType, String locatorValue) throws InterruptedException {
		js = (JavascriptExecutor) driver; // khởi tạo
		try {
			LogUtils.info("Executing: Scroll mouse down and click element: " + locatorValue);
			WebElement element = GetElement(locatorType, locatorValue);
			waitForPageLoaded();
			js.executeScript("arguments[0].scrollIntoView(true)", element);
			js.executeScript("arguments[0].click();", element);
		} catch (NoSuchElementException e) {
			LogUtils.error("|clickElementWithJs:" + locatorType + "=" + locatorValue + " not found to click| "
					+ e.getMessage());
		}
	}


	// Keyword bấm chuột phải
	public static void rightClickElement(String locatorType, String locatorValue) throws InterruptedException {
		try {
			LogUtils.info("Executing: |Right click| element: " + locatorType + "= " + locatorValue);
			WebElement element = GetElement(locatorType, locatorValue);
			waitForPageLoaded();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			action.contextClick().build().perform();
		} catch (NoSuchElementException e) {
			LogUtils.error(
					"|Right click: " + locatorType + "= " + locatorValue + " not found to click| " + e.getMessage());
		}
	}

	// Keyword lấy url hiện tại
	public static boolean verifyUrl(String expected) throws InterruptedException {
		LogUtils.info("Executing: Get URL");
		waitForPageLoaded();
		Thread.sleep(3000);
		String actual = driver.getCurrentUrl();
		LogUtils.info("Expected result --> " + expected);
		LogUtils.info("Actual result --> " + actual);
		if (actual.equals(expected)) {
			return true;
		} else
			return false;
	}

	// Keyword 
	public static boolean verifyToast(String expected) throws InterruptedException {
		LogUtils.info("Verify toast in sign in page");
		driver.switchTo().activeElement();
		//waitForPageLoaded();
		WebElement toast = driver.findElement(By.id("NotiflixNotifyWrap"));
		String actual = toast.getText();
		LogUtils.info("Expected Result: " + expected);
		LogUtils.info("Actual Result: " + actual);
		if (actual.contains(expected)) {
			return true;
		} else
			return false;
	}

	public static boolean verifyAlertNameofSignUpPage(String expected) throws InterruptedException {
		LogUtils.info("Executing: Verify alert name of sign up page");
		WebElement name = driver.findElement(By.id("full_name"));
		String actual = name.getAttribute("validationMessage");
		waitForPageLoaded();
		LogUtils.info("Expected Result: " + expected);
		LogUtils.info("Actual Result: " + actual);
		if (actual.equals(expected)) {
			return true;
		} else
			return false;
	}

	public static boolean verifyAlertEmailofSignUpPage(String expected) throws InterruptedException {
		LogUtils.info("Executing: Verify alert email of sign up page");
		WebElement email = driver.findElement(By.id("email"));
		String actual = email.getAttribute("validationMessage");
		waitForPageLoaded();
		LogUtils.info("Expected Result: " + expected);
		LogUtils.info("Actual Result: " + actual);
		if (actual.equals(expected)) {
			return true;
		} else
			return false;
	}

	public static boolean verifyAlertPhoneNumberofSignUpPage(String expected) throws InterruptedException {
		LogUtils.info("Executing: Verify alert of phone number");
		WebElement sdt = driver.findElement(By.id("preview_phone"));
		String actual = sdt.getAttribute("validationMessage");
		waitForPageLoaded();
		LogUtils.info("Expected Result: " + expected);
		LogUtils.info("Actual Result: " + actual);
		if (actual.equals(expected)) {
			return true;
		} else
			return false;
	}

	public static boolean verifyElementTextPhoneNumberofSignUpPage(String expected) throws InterruptedException {
		LogUtils.info("Verify text phone number of sign up page");
		// WebDriverWait wait = new WebDriverWait(driver,10);
		driver.switchTo().activeElement();
		waitForPageLoaded();
		WebElement PhoneNumber = driver.findElement(By.xpath("/html/body/main/section/div[2]/div/div/div/div[1]/form/div[3]/small/div"));
		String actual = PhoneNumber.getText();
		LogUtils.info("Expected Result: " + expected);
		LogUtils.info("Actual Result: " + actual);
		if (actual.contains(expected)) {
			return true;
		} else
			return false;
	}

	public static boolean verifyAlertPasswordofSignUpPage(String expected) throws InterruptedException {
		LogUtils.info("Executing: Verify alert password of sign up page");
		WebElement passwordSU = driver.findElement(By.id("password"));
		String actual = passwordSU.getAttribute("validationMessage");
		waitForPageLoaded();
		LogUtils.info("Expected Result: " + expected);
		LogUtils.info("Actual Result: " + actual);
		if (actual.equals(expected)) {
			return true;
		} else
			return false;
	}
	
	public static boolean verifyElementTextCourseNameofSearchPage( String expected)throws InterruptedException {
		LogUtils.info("Executing: Verify text course name of search page");
		// WebDriverWait wait = new WebDriverWait(driver,10);
		driver.switchTo().activeElement();
		waitForPageLoaded();
		WebElement text = driver.findElement(By.xpath("(//h3[@class='title-course'])[1]"));
		String actual = text.getText();
		LogUtils.info("Expected Result: " + expected);
		LogUtils.info("Actual Result: " + actual);
		if (actual.contains(expected)) {
			return true;
		} else
			return false;
	}
	
	public static boolean verifyAlertCourseNameofSearchPage(String expected) throws InterruptedException {
		LogUtils.info("Executing:  Verify alert course name of search page");
		WebElement coursename = driver.findElement(By.id("text_search"));
		String actual = coursename.getAttribute("validationMessage");
		waitForPageLoaded();
		LogUtils.info("Expected Result: " + expected);
		LogUtils.info("Actual Result: " + actual);
		if (actual.equals(expected)) {
			return true;
		} else
			return false;
	}
	
	
	public static void screenShot(String CaseName) throws IOException {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// Tạo tham chiếu của TakesScreenshot với driver hiện tại
		TakesScreenshot ts = (TakesScreenshot) driver;
		// Gọi hàm capture screenshot - getScreenshotAs
		File source = ts.getScreenshotAs(OutputType.FILE);
		// Kiểm tra folder tồn tại. Nêu không thì tạo mới folder
		File theDir = new File("./Screenshots/");
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
		// result.getName() lấy tên của test case xong gán cho tên File chụp màn hình
		// luôn
		FileHandler.copy(source, new File("./Screenshots/" + CaseName + ".png"));

	}

}
