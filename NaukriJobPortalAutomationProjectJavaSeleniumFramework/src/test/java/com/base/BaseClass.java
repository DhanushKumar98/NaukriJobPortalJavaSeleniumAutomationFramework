package com.base;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utility.FileReaderManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	// private static ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();
	public static ExtentTest test;

	public static WebDriver driver;
	public static ExtentReports extentReports;
	private static JavascriptExecutor js;
	private static File file;
	private static Robot robot;

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void setDriver(WebDriver driver) {
		tlDriver.set(driver);
	}

//	public static void setTest(ExtentTest test) {
//		tlTest.set(test);
//	}
//	
//	public static ExtentTest getTest() {
//		return tlTest.get();
//	}

	protected static void launchBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				System.out.println("Chrome Browser is Launched.....");
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				System.out.println("FireFox Browser is Launched.....");
			} else if (browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				System.out.println("Edge Browser is Launched.....");
			} else {
				System.out.println("Invalid BrowserName Can't Launch Browser : " + browserName);
			}
			setDriver(driver);
			driver.manage().window().maximize();

		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING BROWSER LAUNCH");
		}
	}

	protected static void launchUrl(String url) {
		try {
			getDriver().get(url);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING OPEN URL");
		}
	}

	protected static void getTitle() {
		try {
			String currentTitle = getDriver().getTitle();
			System.out.println("Current Tilte : " + currentTitle);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING GETTING TITLE");
		}
	}

	protected static void getCurrentUrl() {
		try {
			String currentUrl = getDriver().getCurrentUrl();
			System.out.println("Current URL : " + currentUrl);
		} catch (Exception e) {
			Assert.fail("ERORR : OCCURS DURING GETTING CURRENT URL");
		}
	}

	protected static void getWindowHandle() {
		try {
			String getWindowHandle = getDriver().getWindowHandle();
			System.out.println("window Handle : " + getWindowHandle);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING GETTING WINDOW HANDLING");
		}
	}

	protected static void navigationPage(String url) {
		try {
			getDriver().navigate().to(url);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING NAVIGATTING URL");
		}
	}

	protected static void navigateCommand(String command) {
		try {
			if (command.equalsIgnoreCase("back")) {
				getDriver().navigate().back();
			} else if (command.equalsIgnoreCase("forward")) {
				getDriver().navigate().forward();
			} else if (command.equalsIgnoreCase("refresh")) {
				getDriver().navigate().refresh();
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING NAVIGATING PAGE");
		}
	}

//	protected static void clearField(WebElement element) {
//		try {
//
//			explicitWaitVisibilityOfElement(element, 10);
//			scrollByElement(element);
//			element.clear();
//		} catch (Exception e) {
//			Assert.fail("ERROR : OCCURS DURING CLEARING FIELD");
//		}
//	}

	protected static void switchToFrame(String frameType, WebElement element, int index) {
		try {
			switch (frameType.toLowerCase()) {

			case "index":
				getDriver().switchTo().frame(index);
				break;
			case "element":
				getDriver().switchTo().frame(element);
				break;
			case "default":
				getDriver().switchTo().defaultContent();
				break;
			case "parent":
				getDriver().switchTo().parentFrame();
				break;

			default:
				Assert.fail("Invalid frame type provided");
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING FRAME SWITCHING : " + e.getMessage());
		}
	}
	// switchToFrame("index", null, 0);
	// switchToFrame("element", frameElement, 0);
	// switchToFrame("default", null, 0);

	protected static void switchTowindowByIndex(int index) {
		try {
			List<String> windows = new ArrayList<>(getDriver().getWindowHandles());

			if (windows.size() <= index) {
				Assert.fail("Window index " + index + "does not exist");
			}
			driver.switchTo().window(windows.get(index));
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING WINDOW BY INDEX : " + e.getMessage());
		}
	}

	// switchToWindowByIndex(0); // Parent window
	// switchToWindowByIndex(1); // Child window

	protected static String getParentWindow() {
		return driver.getWindowHandle();

	}

	protected static void switchToChildWindow(String parentWindowId) {
		try {
			for (String window : getDriver().getWindowHandles()) {
				if (!window.equals(parentWindowId)) {
					getDriver().switchTo().window(window);
					return;
				}
			}
			Assert.fail("Child window not Found");
		} catch (Exception e) {
			Assert.fail("ERROR WHILE SWITCHING TO CHILD WINDOW : " + e.getMessage());
		}
	}

	// String parent = getParentWindow();
	// click(paymentLink);
	// swtichToChildWindow(parent);

	protected static void closeChildAndSwitchToParent(String parentWindowId) {
		try {
			for (String window : driver.getWindowHandles()) {
				if (!window.equals(parentWindowId)) {
					driver.switchTo().window(window);
					driver.close();
				}
			}
			driver.switchTo().window(parentWindowId);

		} catch (Exception e) {
			Assert.fail("ERROR WHILE CLOSING CHILD WINDOW : " + e.getMessage());
		}
	}

	protected static void terminateBrowser() {
		try {
			getDriver().quit();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING QUITING BROWSER");
		}
	}

	protected static void explicitWaitVisibilityOfElement(WebElement element, int second) {
		try {
			scrollByElement(element);
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING EXPLICIT WAIT ELEMENT");
		}
	}

	protected static void explicitWaitElementToBeClickable(WebElement element, int seconds) {
		try {
			scrollByElement(element);
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING EXPLICIT WAIT CLICKABLE ELEMENT");
		}
	}

	protected static void explicitWaitVisibilityOfAllElement(List<WebElement> elements, int second) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING EXPLICIT WAIT VISIBILITY OF ALL ELEMENTS");
		}
	}

	protected static void implicitWait(int seconds) {
		try {
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING IMPLICIT WAIT");
		}
	}

	protected static void clickSelenium(WebElement element) {
		try {
			explicitWaitElementToBeClickable(element, 10);
			scrollByElement(element);
			element.click();
		} catch (Exception e) {
			Assert.fail("ERRRO : OCCURS DURING SELENIUM CLICK");
		}
	}

	protected static void clickSelenium2(WebElement element) {
		try {
			explicitWaitElementToBeClickable(element, 10);
			scrollByElement(element);
			element.click();
		} catch (Exception e) {
			Assert.fail("ERRRO : OCCURS DURING SELENIUM CLICK");
		}
	}

	protected static void clickJS(WebElement element) {
		try {
			scrollToElement(element);
			explicitWaitElementToBeClickable(element, 10);
			js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING JAVASCRIPT CLICK");
		}
	}

	public static void scrollDownToLoadLazyContent() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,800);");
	}

	protected static void typeJS(WebElement element, String text) {
		try {
			explicitWaitVisibilityOfElement(element, 10);
			scrollByElement(element);
			js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].value=arguments[1];", element, text);
		} catch (Exception e) {
			System.out.println("JS Sendkeys failed : " + e.getMessage());
			Assert.fail("ERROR : OCCURS DURING JAVASCRIPT TYPE");
		}
	}

	protected static void sendkeys(WebElement element, String text) {
		try {
			explicitWaitVisibilityOfElement(element, 10);
			scrollByElement(element);
			element.clear();
			element.click();
			element.sendKeys(text);
		} catch (Exception e) {
			System.out.println("Selenium sendkeys failed : " + e.getMessage());
			Assert.fail("ERROR : OCCURS DURING SELENIUM SENDKEYS");
		}
	}

	protected static String getText(WebElement element) {
		try {
			explicitWaitVisibilityOfElement(element, 10);
			scrollByElement(element);
			return element.getText();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING GETTING TEXT");
			return null;
		}
	}

	protected static void selectByVisibilityText(WebElement element, String text) {
		try {
			Select select = new Select(element);
			scrollByElement(element);
			select.selectByVisibleText(text);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING Select by Visibility Text");
		}
	}

	protected static void selectByValue(WebElement element, String value) {
		try {
			Select select = new Select(element);
			scrollByElement(element);
			select.selectByValue(value);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING Select By Value");
		}
	}

	protected static void selectByIndex(WebElement element, int index) {
		try {
			Select select = new Select(element);
			select.selectByIndex(index);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING Select By Index");
		}
	}

	protected static void selectByVisibilityTextLocator(By loator, String text) {
		try {
			Select select = new Select(driver.findElement(loator));
			select.selectByVisibleText(text);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING Select By Visibility Text Locator");
		}
	}

	protected static void deselectByValueOfLocator(By locator, String value) {
		try {
			Select select = new Select(getDriver().findElement(locator));
			select.deselectByValue(value);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING Select By Value Locator");
		}
	}

	protected static void selectByIndexOfLocator(By locator, int index) {
		try {
			Select select = new Select(getDriver().findElement(locator));
			select.selectByIndex(index);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING Select By Index Locator" + e.getMessage());
		}
	}

	protected static void deselectByVisibilityText(WebElement element, String text) {
		try {
			Select select = new Select(element);
			select.deselectByVisibleText(text);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING Deselect By Visibility Text" + e.getMessage());
		}
	}

	protected static void deselectByValue(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.deselectByValue(value);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING Deselect By Value" + e.getMessage());
		}
	}

	protected static void deselectByIndex(WebElement element, int index) {
		try {
			Select select = new Select(element);
			select.deselectByIndex(index);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING Deselect By Index" + e.getMessage());
		}
	}

	protected static List<String> getAllDropdownOptions(WebElement element) {
		try {
			Select select = new Select(element);
			List<String> optionsText = new ArrayList<>();

			for (WebElement option : select.getOptions()) {
				optionsText.add(option.getText().trim());
			}
			return optionsText;
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING GETTING ALL OPTIONS" + e.getMessage());
			return new ArrayList<>(); // Required for Compilation
		}
	}

	protected static void selectCustomDropdownJSClick(WebElement dropdown, List<WebElement> options, String value) {
		try {
			implicitWait(5);
			clickJS(dropdown);

			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase(value)) {
					option.click();
					break;
				}
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING CUSTOM DROPDOWN" + e.getMessage());
		}
	}

	protected static void selectCustomDropdown(WebElement dropdown, List<WebElement> options, String value) {
		try {
			implicitWait(5);
			dropdown.click();

			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase(value)) {
					option.click();
					break;
				}
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING CUSTOM DROPDOWN" + e.getMessage());
		}
	}
	// Usage

//	selectCustomDropdown(
//		    driver.findElement(By.id("skills")),
//		    driver.findElements(By.xpath("//li[@role='option']")),
//		    "Selenium"
//		);

	// Custom Dropdown Using Dynamic XPATH
	protected static void selectCustomerDropdown(WebElement dropdown, String optionText) {
		try {
			dropdown.click();
			driver.findElement(By.xpath("//li[normalize-space()='" + optionText + "']")).click();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING Dynamic Xpath" + e.getMessage());
		}
	}

	// Multi-Select DropDown(Check Boxes Based)
	protected static void selectMultipleOptions(List<WebElement> options, String[] values) {
		try {
			for (WebElement option : options) {
				for (String value : values) {
					if (option.getText().equalsIgnoreCase(value.trim()) && !option.isSelected()) {
						option.click();
					}
				}
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING SELECT MULTIPLE OPTIONS" + e.getMessage());
		}
	}

	protected static boolean isMultiSelect(WebElement element) {
		try {
			Select select = new Select(element);
			return select.isMultiple();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING isMultipleSelect" + e.getMessage());
			return false; // required for compilation
		}
	}

	protected static void deselectAll(WebElement element) {
		try {
			Select select = new Select(element);
			select.deselectAll();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING DESECLECT ALL");
		}
	}

	protected static void selectDropDown(WebElement dropdownElement, List<WebElement> options, String selectType,
			String values) {
		try {

			String[] valueArray = values.split(",");

// CASE 1: <select> tag dropdown
			if (dropdownElement.getTagName().equalsIgnoreCase("select")) {

				Select select = new Select(dropdownElement);

				for (String value : valueArray) {

					switch (selectType.toLowerCase()) {

					case "text":
						select.selectByVisibleText(value.trim());
						break;

					case "value":
						select.selectByValue(value.trim());
						break;

					case "index":
						select.selectByIndex(Integer.parseInt(value.trim()));
						break;

					default:
						throw new IllegalArgumentException("Invalid selectType. Use: text | value | index");
					}
				}
			}

// CASE 2: Custom / Non-Select dropdown
			else {

				dropdownElement.click();

				for (String value : valueArray) {
					for (WebElement option : options) {

						if (option.getText().trim().equalsIgnoreCase(value.trim())) {
							option.click();
							break; // important to avoid multiple clicks
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println("DropDown selection failed: " + e.getMessage());
		}
	}

	// Select dropdown by visible text----
	// selectDropDown(countryDropdown, null, "text", "India");
	// Select multiple values----
	// selectDropDown(skillsDropdown, null, "text", "Java,Selenium,TestNG");
	// Custom dropdown-----
	// selectDropDown(customDropdown, customOptions, "", "Chennai");

	protected static void doAction(WebDriver driver, String action, WebElement element, WebElement target,
			String value) {
		try {
			Actions actions = new Actions(getDriver());

			switch (action.toLowerCase()) {
			case "click":
				actions.click(element).perform();
				break;
			case "doubleclick":
				actions.doubleClick(element).perform();
				break;
			case "rightclick":
				actions.contextClick(element).perform();
				break;
			case "moveTo":
				actions.moveToElement(element).perform();
				break;
			case "hoverandclick":
				actions.moveToElement(element).moveToElement(target).click().perform();
				break;
			case "draganddrop":
				actions.dragAndDrop(element, target).perform();
				break;
			case "scrollto":
				actions.moveToElement(element).perform();
				break;

			default:
				System.out.println("Invalid Action : " + action);
			}
		} catch (Exception e) {
			System.out.println("Action failed : " + e.getMessage());
		}
	}

	// Usage
//	ActionUtils.doAction(driver, "click", loginBtn, null, null);
//  ActionUtils.doAction(driver, "sendkeys", usernameTxt, null, "Admin");	
//  ActionUtils.doAction(driver, "hoverandclick", menu, subMenu, null);
//  ActionUtils.doAction(driver, "dragdrop", source, target, null);

	protected static void ActionsPerform(WebElement source, WebElement target, String actionType) {
		try {
			Actions action = new Actions(getDriver());

			switch (actionType.toLowerCase()) {

			case "mousehover":
				action.moveToElement(source).perform();
				break;
			case "click":
				action.moveToElement(source).click().perform();
				break;
			case "doubleclick":
				action.doubleClick(source).perform();
				break;
			case "rightclick":
				action.contextClick(source).perform();
				break;
			case "dragandrop":
				if (target != null) {
					action.dragAndDrop(source, target).perform();
				}
				break;
			default:
				System.out.println("Invalid actionType : " + actionType);
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING ACTIONS PERFORM : " + e.getMessage());
		}
	}

	// How to Use
	// Mouse Hover
	// ActionsPerform(menu, null, "mousehover");
	// Click
	// ActionsPerform(loginBtn, null, "click");
	// Double click
	// ActionsPerform(button, null, "doubleclick");
	// Right Click
	// ActionsPerform(element, null, "rightclick");
	// Drag and Drop
	// ActionsPerform(source, target, "draganddrop");

//	protected static void actionsPerform(WebElement source, WebElement target, String actionType) {
//		try {
//			WebDriver driver = getDriver();
//			Actions actions = new Actions(driver);
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//
//			// Explicit wait
//			if (source != null) {
//				wait.until(ExpectedConditions.visibilityOf(source));
//				wait.until(ExpectedConditions.elementToBeClickable(source));
//			}
//
//			switch (actionType.toLowerCase()) {
//			case "mousehover":
//				actions.moveToElement(source).perform();
//				test.log(Status.INFO, "Mouse hovered on element");
//				break;
//			case "click":
//				try {
//					actions.moveToElement(source).click().perform();
//					test.log(Status.INFO, "Clicked using Actions");
//				} catch (Exception e) {
//					js.executeScript("arguments[0].click();", source);
//					test.log(Status.WARNING, "Actions click failed , JS click used");
//				}
//				break;
//			case "doubleclick":
//				actions.doubleClick(source).perform();
//				// getTest().log(Status.INFO, "Double clicked element");
//				test.log(Status.INFO, "Double clicked element");
//				break;
//			case "rightclick":
//				actions.contextClick(source).perform();
//				test.log(Status.INFO, "Right click element");
//				break;
//			case "draganddrop":
//				if (target != null) {
//					wait.until(ExpectedConditions.visibilityOf(target));
//					actions.dragAndDrop(source, target).perform();
//					test.log(Status.INFO, "Drag and drop performed");
//				}
//				break;
//			default:
//				test.log(Status.WARNING, "Invalid actionType : " + actionType);
//			}
//		} catch (Exception e) {
//			test.log(Status.FAIL, "Action failed : " + e.getMessage());
//			Assert.fail("ERROR : OCCURS DURING ACTIONS PERFORM");
//		}
//	}
	// How to Use in Tests / Steps
	// Click (With JS fallback)
	// actionsPerform(loginBtn, null, "click");
	// Mouse Hover
	// actionsPerform(menu, null, "mousehover");
	// Drag and Drop
	// actionsPerform(source, target, "draganddrop");

	protected static String getTimeStamp() {
		return new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
	}

	public static void extentReportStart(String location) {
		try {
			String timeStamp = getTimeStamp();
			String reportName = "Automation_Report";

			file = new File(location + reportName + "_" + timeStamp + "_" + ".html");

			ExtentSparkReporter spark = new ExtentSparkReporter(file);
			extentReports = new ExtentReports();
			extentReports.attachReporter(spark);
			extentReports.setSystemInfo("OS", System.getProperty("os.name"));
			extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
			extentReports.setSystemInfo("User", System.getProperty("user.name"));
			extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
			extentReports.setSystemInfo("Thread", String.valueOf(Thread.currentThread().getId()));
			extentReports.setSystemInfo("Environment", "QA");
			extentReports.setSystemInfo("Application URL", FileReaderManager.getDataProperty("url"));
			extentReports.setSystemInfo("Browser Type : ", "chrome");
			extentReports.setSystemInfo("Browser Version : ", "144.0.7559.61(Official Build)(64-bit)");
			extentReports.setSystemInfo("Tester Name : ", "DHANUSH KUMAR");
			extentReports.setSystemInfo("Excution Type : ", "LOCAL");
			spark.config().setReportName("NAUKRI JOB PORTAL WEB APP AUTOMATION REPORT - REGRESSION SUITE");
			spark.config().setDocumentTitle("NAUKRI TEST EXECUTION REPORT");
			spark.config().setTheme(Theme.DARK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void extentReportTearDown() {
		try {
			extentReports.flush();
			Desktop.getDesktop().browse(file.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void addMetaInfo() {
		test.assignAuthor("Dhanush Kumar K N - SDET").assignCategory("End to End for Regression Testing")
				.assignDevice("Windows 11 - Chrome");
	}

	protected static String takeScreenshot(String screenshotName) {
		String timeStamp = getTimeStamp();
		String screenshotpath = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + "_" + timeStamp
				+ ".png";

		try {
			WebDriver driver = getDriver();

			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File(screenshotpath);

			destination.getParentFile().mkdirs(); // auto- create folder
			Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.out.println("Screenshot failed: " + e.getMessage());
		}
		return screenshotpath; // important for ExtentReports
	}

	protected static void localWait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING LOCAL WAIT");
		}
	}

	protected static void scrollByElement(WebElement element) {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", element);
		} catch (Exception e) {
			Assert.fail("ERROR: Unable to scroll to element");
		}
	}

	public static void click(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement elements = wait.until(ExpectedConditions.elementToBeClickable(element));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", elements);

		elements.click();
	}

	public static void scrollToElement(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			throw new RuntimeException("ERROR: Unable to scroll to element", e);
		}
	}

	// ----------- Robot Class Reusable Methods-------------
	static {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException("Failed to Initialize Robot Class");
		}
	}

	/* ------------------------KEYBOARD ACTIONS---------------------- */

	// Press & release a single key

	protected static void pressKey(int key) {
		robot.keyPress(key);
		robot.keyRelease(key);
	}
	// How to Use
	// RobotUtils.pressKey(KeyEvent.VK_ENTER);
	// RobotUtils.pressKey(KeyEvent.VK_TAB);
	// RobotUtils.pressKey(KeyEvent.VK_ESCAPE);

	// CTRL & KEY
	protected static void pressCtrlKey(int key) {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(key);
		robot.keyRelease(key);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	// How to Use
	// RobotUtils.pressCtrlKey(KeyEvent.VK_C);
	// RobotUtils.pressCtrlKey(KeyEvent.VK_V);
	// RobotUtils.pressCtrlKey(KeyEvent.VK_A);
	// RobotUtils.pressCtrlKey(KeyEvent.VK_S);

	// ALT & KEY
	protected static void pressAltKey(int key) {
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(key);
		robot.keyRelease(key);
		robot.keyRelease(KeyEvent.VK_ALT);
	}
	// How to Use

	// SHIFT + KEY
	protected static void pressShiftKey(int key) {
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(key);
		robot.keyRelease(key);
		robot.keyRelease(KeyEvent.VK_SHIFT);
	}

	// How to Use KeyBoard Action
	// RobotUtils.pressKey(KeyEvent.VK_ENTER);

	// FILE UPLOAD
	protected static void uploadFile(String filePath) {
		StringSelection selection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		robot.delay(1000);
		pressCtrlKey(KeyEvent.VK_V);
		robot.delay(500);
		pressKey(KeyEvent.VK_ENTER);
	}

//	How to Use Upload File
//	clickSelenium(uploadButton);
//	RobotUtils.uploadFile("C:\\Users\\Dhanush\\Documents\\resume.pdf");

	/*-----------------BROWSER ACTION--------------------*/

	public static void refreshPage() {
		pressKey(KeyEvent.VK_F5);
	}

	public static void newTab() {
		pressKey(KeyEvent.VK_T);
	}

	public static void closeTab() {
		pressKey(KeyEvent.VK_W);
	}

	public static void switchTab() {
		pressKey(KeyEvent.VK_TAB);
	}

	// How to Use Browser Control
//	RobotUtils.refreshPage();
//	RobotUtils.newTab();
//	RobotUtils.closeTab();

	/*-------------DELAY--------------*/
	public static void waitFor(int millis) {
		robot.delay(millis);
	}

	protected static void clickAll(List<WebElement> elements) {
		for (WebElement element : elements) {
			explicitWaitElementToBeClickable(element, 10);
			scrollByElement(element);
			ActionsPerform(element, null, "mousehover");
			localWait(5000);
			clickJS(element);
			localWait(5000);
		}
	}

	protected static void scrollToBottom(WebElement element) {
		js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", element);
	}

	protected static void clickAndBackspace(WebElement element) {
		try {
			element.click();
			element.sendKeys(Keys.BACK_SPACE);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING BACKSPACE");
		}
	}

	protected static void pressKeyMultipleTimes(Keys key, int times) {
		try {
			for (int i = 0; i < times; i++) {
				getDriver().switchTo().activeElement().sendKeys(key);
				localWait(1000);
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING PRESS KEY MULTIPLE TIMES");
		}
	}

	// How to Use

//	usernameField.click();
//	pressKeyMultipleTimes(Keys.BACK_SPACE, 8);

//	This works for:
//
//		Keys.ENTER
//		Keys.TAB
//		Keys.DELETE
//		Keys.BACK_SPACE

	public static void clearField(WebElement element) {
		explicitWaitVisibilityOfElement(element, 10);
		scrollByElement(element);
		explicitWaitElementToBeClickable(element, 10);
		element.click();
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.sendKeys(Keys.BACK_SPACE);
	}

//	Fast
//	✔ Reliable
//	✔ Minimal looping

	protected static void sendKeys(WebElement element, List<String> values) {
		try {
			element.click();
			element.clear();
			for (String value : values) {
				element.sendKeys(value);
				localWait(3000);
				pressKey(KeyEvent.VK_TAB);
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING SENDING LIST OF STRING VALUES");
		}
	}

	public static void scrollDownPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1500);");
	}

	public static void scrollAndHighlight(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
			js.executeScript("arguments[0].style.border='2px solid red'", element);

		} catch (Exception e) {
			Assert.fail("ERROR: Scroll failed", e);
		}
	}

	public void clickActionButton(String action, WebElement element) {

	    switch (action.toLowerCase()) {

	        case "Delete":
	        case "Upload resume":
	        case "result":
	           clickSelenium(element);
	            break;

	        default:
	            Assert.fail("Invalid action: " + action);
	    }
	}
	
	public void clickActionButtons(String action, WebElement element) {

	    if (action == null || element == null) {
	        return;
	    }

	    switch (action.toLowerCase()) {

	        case "deleteOneTheme":
	        case "Upload resume":
	        case "result":
	          clickSelenium(element);
	            break;

	        default:
	            // intentionally skipped
	    }
	}
	
	
	
	
	

}
