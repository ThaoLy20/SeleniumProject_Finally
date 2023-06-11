package executionEngine;

import email.EmailSendUtils;
import static report.ExtentManager.getExtentReports;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import email.EmailAttachmentsSender;
import email.EmailConfig;
import com.aventstack.extentreports.Status;

import keywords.*;
import report.ExtentManager;
import report.ExtentTestManager;
import utils.*;
import utils.RecordVideoUtils.RecordVideo;

public class ExecutionEngine {
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String locatorType;
	public static String locatorValue;
	public static String testData;
	public static String testsuiteID;
	public static String Description;
	int CasePass = 0;
	int CaseFail = 0;
	int CaseSkip = 0;

	ArrayList<String> arrCasename = new ArrayList<String>();

	ArrayList<String> arrName = new ArrayList<String>();

	ArrayList<String> arrEmail = new ArrayList<String>();

	ArrayList<String> arrPhoneNumber = new ArrayList<String>();

	ArrayList<String> arrPassword = new ArrayList<String>();

	ArrayList<String> arrPasswordConfirm = new ArrayList<String>();

	ArrayList<String> arrCourseName = new ArrayList<String>();

	ArrayList<String> arrResult = new ArrayList<String>();

	String sPath = System.getProperty("user.dir") + "\\src\\test\\resources\\dataEngine\\dataEngine.xlsx";

	@Ignore
	@Test
	public void TestScriptSignInPage() throws Exception {
		ExcelUtils.setExcelFile(sPath, "SignInPage1");
		Sheet sheet = ExcelUtils.getSheet("SignInPage1");
		int rowCount = sheet.getLastRowNum();
		int row = 1;
		String tmp;
		// Lay du lieu tung dong trong file Data dua vao tung mang arrI
		ExcelUtils.setExcelFile(sPath, "DataSignInPage1");
		Sheet dataSheet = ExcelUtils.getSheet("DataSignInPage1");
		int rowCountData = dataSheet.getLastRowNum();
		while (row <= rowCountData) {

			tmp = ExcelUtils.getCellData("DataSignInPage1", row, 1) + "";
			arrCasename.add(tmp);

			tmp = ExcelUtils.getCellData("DataSignInPage1", row, 2) + "";
			arrEmail.add(tmp);

			tmp = ExcelUtils.getCellData("DataSignInPage1", row, 3) + "";
			arrPassword.add(tmp);

			tmp = ExcelUtils.getCellData("DataSignInPage1", row, 4) + "";
			arrResult.add(tmp);

			row = row + 1;
		}
		RecordVideo.startRecord("TestScriptSignInPage");

		// Ko lay hang tieu de dau tien
		for (int i = 0; i < arrCasename.size(); i++) {
			if (i == 0) {
				for (int iRow = 1; iRow <= rowCount - 9; iRow++) {
					reuseSignIn(iRow);
					execute_Actions(testsuiteID, testData, arrCasename.get(i), null, arrEmail.get(i), null,
							arrPassword.get(i), null, null, arrResult.get(i));
				}
			}
			if (i >= 1 && i <= 8) {
				for (int iRow = 10; iRow <= rowCount; iRow++) {
					reuseSignIn(iRow);
					execute_Actions(testsuiteID, testData, arrCasename.get(i), null, arrEmail.get(i), null,
							arrPassword.get(i), null, null, arrResult.get(i));
				}
			}

		}

		RecordVideo.stopRecord();
		reportInConsole();
	}

	public void reuseSignIn(int iRow) {
		testsuiteID = ExcelUtils.getCellData("SignInPage1", iRow, 1);
		Description = ExcelUtils.getCellData("SignInPage1", iRow, 2);
		sActionKeyword = ExcelUtils.getCellData("SignInPage1", iRow, 3);
		locatorType = ExcelUtils.getCellData("SignInPage1", iRow, 4);
		locatorValue = ExcelUtils.getCellData("SignInPage1", iRow, 5);
		testData = ExcelUtils.getCellData("SignInPage1", iRow, 6);

	}

	@Ignore
	@Test
	public void TestScriptSignUpPage() throws Exception {
		ExcelUtils.setExcelFile(sPath, "SignUpPage");
		Sheet sheet = ExcelUtils.getSheet("SignUpPage");
		int rowCount = sheet.getLastRowNum();
		int row = 1;
		String tmp;
		// Lay du lieu tung dong trong file Data dua vao tung mang arrI
		ExcelUtils.setExcelFile(sPath, "DataSignUpPage");
		Sheet dataSheet = ExcelUtils.getSheet("DataSignUpPage");
		int rowCountData = dataSheet.getLastRowNum();
		while (row <= rowCountData) {

			tmp = ExcelUtils.getCellData("DataSignUpPage", row, 1) + "";
			arrCasename.add(tmp);

			tmp = ExcelUtils.getCellData("DataSignUpPage", row, 2) + "";
			arrName.add(tmp);

			tmp = ExcelUtils.getCellData("DataSignUpPage", row, 3) + "";
			arrEmail.add(tmp);

			tmp = ExcelUtils.getCellData("DataSignUpPage", row, 4) + "";
			arrPhoneNumber.add(tmp);

			tmp = ExcelUtils.getCellData("DataSignUpPage", row, 5) + "";
			arrPassword.add(tmp);

			tmp = ExcelUtils.getCellData("DataSignUpPage", row, 6) + "";
			arrPasswordConfirm.add(tmp);

			tmp = ExcelUtils.getCellData("DataSignUpPage", row, 7) + "";
			arrResult.add(tmp);

			row = row + 1;
		}
		RecordVideo.startRecord("TestScriptSignUpPage");

		// Ko lay hang tieu de dau tien
		for (int i = 0; i < arrCasename.size(); i++) {
			if (i == 0) {
				for (int iRow = 1; iRow <= rowCount - 48; iRow++) {
					reuseSignUpPage(iRow);
					execute_Actions(testsuiteID, testData, arrCasename.get(i), arrName.get(i), arrEmail.get(i),
							arrPhoneNumber.get(i), arrPassword.get(i), arrPasswordConfirm.get(i), null,
							arrResult.get(i));
				}
			}
			if (i == 1 || i == 2) {
				for (int iRow = 13; iRow <= rowCount - 36; iRow++) {
					reuseSignUpPage(iRow);
					execute_Actions(testsuiteID, testData, arrCasename.get(i), arrName.get(i), arrEmail.get(i),
							arrPhoneNumber.get(i), arrPassword.get(i), arrPasswordConfirm.get(i), null,
							arrResult.get(i));
				}
			}
			if (i == 3) {
				for (int iRow = 25; iRow <= rowCount - 24; iRow++) { // IROW la dong dau cuoi cua case truoc=
																		// closeBrowser
					reuseSignUpPage(iRow);
					execute_Actions(testsuiteID, testData, arrCasename.get(i), arrName.get(i), arrEmail.get(i),
							arrPhoneNumber.get(i), arrPassword.get(i), arrPasswordConfirm.get(i), null,
							arrResult.get(i));
				}
			}
			if (i >= 4 && i <= 6) {
				for (int iRow = 37; iRow <= rowCount - 12; iRow++) {
					reuseSignUpPage(iRow);
					execute_Actions(testsuiteID, testData, arrCasename.get(i), arrName.get(i), arrEmail.get(i),
							arrPhoneNumber.get(i), arrPassword.get(i), arrPasswordConfirm.get(i), null,
							arrResult.get(i));
				}
			}
			if (i >= 7 && i <= 10) {
				for (int iRow = 49; iRow <= rowCount; iRow++) {
					reuseSignUpPage(iRow);
					execute_Actions(testsuiteID, testData, arrCasename.get(i), arrName.get(i), arrEmail.get(i),
							arrPhoneNumber.get(i), arrPassword.get(i), arrPasswordConfirm.get(i), null,
							arrResult.get(i));
				}
			}

		}

		RecordVideo.stopRecord();
		reportInConsole();
	}

	public void reuseSignUpPage(int iRow) {
		testsuiteID = ExcelUtils.getCellData("SignUpPage", iRow, 1);
		Description = ExcelUtils.getCellData("SignUpPage", iRow, 2);
		sActionKeyword = ExcelUtils.getCellData("SignUpPage", iRow, 3);
		locatorType = ExcelUtils.getCellData("SignUpPage", iRow, 4);
		locatorValue = ExcelUtils.getCellData("SignUpPage", iRow, 5);
		testData = ExcelUtils.getCellData("SignUpPage", iRow, 6);

	}

	//@Ignore
	@Test
	public void TestScriptSearchPage() throws Exception {
		ExcelUtils.setExcelFile(sPath, "SearchPage");
		Sheet sheet = ExcelUtils.getSheet("SearchPage");
		int rowCount = sheet.getLastRowNum();
		int row = 1;
		String tmp;
		// Lay du lieu tung dong trong file Data dua vao tung mang arrI
		ExcelUtils.setExcelFile(sPath, "DataSearchPage");
		Sheet dataSheet = ExcelUtils.getSheet("DataSearchPage");
		int rowCountData = dataSheet.getLastRowNum();
		while (row <= rowCountData) {

			tmp = ExcelUtils.getCellData("DataSearchPage", row, 1) + "";
			arrCasename.add(tmp);

			tmp = ExcelUtils.getCellData("DataSearchPage", row, 2) + "";
			arrCourseName.add(tmp);

			tmp = ExcelUtils.getCellData("DataSearchPage", row, 3) + "";
			arrResult.add(tmp);

			row = row + 1;
		}
		RecordVideo.startRecord("TestScriptSearchPage");

		// Ko lay hang tieu de dau tien
		for (int i = 0; i < arrCasename.size(); i++) {
			if (i == 0 || i == 1) {
				for (int iRow = 1; iRow <= rowCount - 14; iRow++) {
					reuseSearchPage(iRow);
					execute_Actions(testsuiteID, testData, arrCasename.get(i), null, null, null, null, null,
							arrCourseName.get(i), arrResult.get(i));
				}

			}
			if (i == 2 || i == 3) {
				for (int iRow = 8; iRow <= rowCount - 7; iRow++) {
					reuseSearchPage(iRow);
					execute_Actions(testsuiteID, testData, arrCasename.get(i), null, null, null, null, null,
							arrCourseName.get(i), arrResult.get(i));
				}
			}
			if (i == 4) {
				for (int iRow = 15; iRow <= rowCount; iRow++) {
					reuseSearchPage(iRow);
					execute_Actions(testsuiteID, testData, arrCasename.get(i), null, null, null, null, null,
							arrCourseName.get(i), arrResult.get(i));
				}
			}

		}
		RecordVideo.stopRecord();
		reportInConsole();
	}

	public void reuseSearchPage(int iRow) {
		testsuiteID = ExcelUtils.getCellData("SearchPage", iRow, 1);
		Description = ExcelUtils.getCellData("SearchPage", iRow, 2);
		sActionKeyword = ExcelUtils.getCellData("SearchPage", iRow, 3);
		locatorType = ExcelUtils.getCellData("SearchPage", iRow, 4);
		locatorValue = ExcelUtils.getCellData("SearchPage", iRow, 5);
		testData = ExcelUtils.getCellData("SearchPage", iRow, 6);

	}

	@Ignore
	@Test
	public void TestScriptCartPage() throws Exception {
		ExcelUtils.setExcelFile(sPath, "CartPage");
		Sheet sheet = ExcelUtils.getSheet("CartPage");
		int rowCount = sheet.getLastRowNum();
		RecordVideo.startRecord("TestScriptCartPage");

		// Ko lay hang tieu de dau tien
		for (int iRow = 1; iRow <= rowCount; iRow++) {
			testsuiteID = ExcelUtils.getCellData("CartPage", iRow, 1);
			Description = ExcelUtils.getCellData("CartPage", iRow, 2);
			sActionKeyword = ExcelUtils.getCellData("CartPage", iRow, 3);
			locatorType = ExcelUtils.getCellData("CartPage", iRow, 4);
			locatorValue = ExcelUtils.getCellData("CartPage", iRow, 5);
			testData = ExcelUtils.getCellData("CartPage", iRow, 6);
			execute_Actions(testsuiteID, testData, testsuiteID, null, null, null, null, null, null, null);
		}

		RecordVideo.stopRecord();
		reportInConsole();
	}

	@Ignore
	@Test
	public void TestScriptPayPage() throws Exception {
		ExcelUtils.setExcelFile(sPath, "PayPage");
		Sheet sheet = ExcelUtils.getSheet("PayPage");
		int rowCount = sheet.getLastRowNum();
		RecordVideo.startRecord("TestScriptPayPage");

		// Ko lay hang tieu de dau tien
		for (int iRow = 1; iRow <= rowCount; iRow++) {
			testsuiteID = ExcelUtils.getCellData("PayPage", iRow, 1);
			Description = ExcelUtils.getCellData("PayPage", iRow, 2);
			sActionKeyword = ExcelUtils.getCellData("PayPage", iRow, 3);
			locatorType = ExcelUtils.getCellData("PayPage", iRow, 4);
			locatorValue = ExcelUtils.getCellData("PayPage", iRow, 5);
			testData = ExcelUtils.getCellData("PayPage", iRow, 6);
			execute_Actions(testsuiteID, testData, testsuiteID, null, null, null, null, null, null, null);
		}

		RecordVideo.stopRecord();
		reportInConsole();
	}

	public void execute_Actions(String testSuiteID, String testData, String CaseName, String sName, String sEmail,
			String sPhoneNumber, String sPassword, String sPasswordConfirm, String sCourseName, String sResult)
			throws Exception {

		switch (sActionKeyword) {
		case "openBrowser":
			if (CaseName != null) {
				LogUtils.info("--------------Thực thi Test Case ID: " + CaseName + "--------------");
			}
			ExtentTestManager.saveToReport(CaseName, "");

			try {
				ActionKeywords.openBrowser(testData);
				LogUtils.info("Executing: Open browser: " + testData);
				ExtentTestManager.logMessage(Status.PASS, "Open browser: " + testData);
			} catch (Exception e) {
				LogUtils.error("Executing: Open browser: " + testData + " FAIL");
				ExtentTestManager.logMessage(Status.FAIL, "Can't open browser: " + testData);
			}
			break;

		case "navigate":
			try {
				ActionKeywords.navigate(testData);
				LogUtils.info("Executing: Open URL: " + testData);
				ExtentTestManager.logMessage(Status.PASS, "Open URL: " + testData);
			} catch (Exception e) {
				LogUtils.error("Executing: Open URL: " + testData + " FAIL");
				ExtentTestManager.logMessage(Status.FAIL, "Can't open URL: " + testData);
			}

			break;
		case "setText":
			if (testData.equalsIgnoreCase("varEmail")) {
				try {
					ActionKeywords.setText(locatorType, locatorValue, sEmail);
					LogUtils.info("Executing: Enter email: " + sEmail);
					ExtentTestManager.logMessage(Status.PASS, "Enter email: " + sEmail);
				} catch (NoSuchElementException e) {
					LogUtils.error("SendKeys:" + locatorType + "=" + locatorValue + " not found to sendKeys| "
							+ e.getMessage());
					ExtentTestManager.logMessage(Status.FAIL, "Can't enter email: " + sEmail);
				}
			} else {
				if (testData.equalsIgnoreCase("varPassword")) {
					try {
						ActionKeywords.setText(locatorType, locatorValue, sPassword);
						LogUtils.info("Executing: Enter password: " + sPassword);
						ExtentTestManager.logMessage(Status.PASS, "Enter password: " + sPassword);
					} catch (NoSuchElementException e) {
						LogUtils.error("SendKeys:" + locatorType + "=" + locatorValue + " not found to sendKeys| "
								+ e.getMessage());
						ExtentTestManager.logMessage(Status.FAIL, "Can't enter password: " + sPassword);
					}
				} else {
					if (testData.equalsIgnoreCase("varName")) {
						try {
		 					ActionKeywords.setText(locatorType, locatorValue, sName);
							LogUtils.info("Executing: Enter name: " + sName);
							ExtentTestManager.logMessage(Status.PASS, "Enter name: " + sName);
						} catch (NoSuchElementException e) {
							LogUtils.error("SendKeys:" + locatorType + "=" + locatorValue + " not found to sendKeys| "
									+ e.getMessage());
							ExtentTestManager.logMessage(Status.FAIL, "Can't enter name: " + sName);
						}
					} else {
						if (testData.equalsIgnoreCase("varPhoneNumber")) {
							try {
								ActionKeywords.setText(locatorType, locatorValue, sPhoneNumber);
								LogUtils.info("Executing: Enter phone number: " + sPhoneNumber);
								ExtentTestManager.logMessage(Status.PASS, "Enter phone number: " + sPhoneNumber);
							} catch (NoSuchElementException e) {
								LogUtils.error("SendKeys:" + locatorType + "=" + locatorValue
										+ " not found to sendKeys| " + e.getMessage());
								ExtentTestManager.logMessage(Status.FAIL, "Can't enter phone number: " + sPhoneNumber);
							}
						} else {
							if (testData.equalsIgnoreCase("varPasswordConfirm")) {
								try {
									ActionKeywords.setText(locatorType, locatorValue, sPasswordConfirm);
									LogUtils.info("Executing: Enter password confirm: " + sPasswordConfirm);
									ExtentTestManager.logMessage(Status.PASS,
											"Enter password confirm: " + sPasswordConfirm);
								} catch (NoSuchElementException e) {
									LogUtils.error("SendKeys:" + locatorType + "=" + locatorValue
											+ " not found to sendKeys| " + e.getMessage());
									ExtentTestManager.logMessage(Status.FAIL,
											"Can't enter password confirm: " + sPasswordConfirm);
								}
							} else {
								if (testData.equalsIgnoreCase("varCourseName")) {
									try {
										ActionKeywords.setText(locatorType, locatorValue, sCourseName);
										LogUtils.info("Executing: Enter course name: " + sCourseName);
										ExtentTestManager.logMessage(Status.PASS, "Enter course name: " + sCourseName);
									} catch (NoSuchElementException e) {
										LogUtils.error("SendKeys:" + locatorType + "=" + locatorValue
												+ " not found to sendKeys| " + e.getMessage());
										ExtentTestManager.logMessage(Status.FAIL,
												"Can't enter course name: " + sCourseName);
									}

								} else {
									try {
										ActionKeywords.setText(locatorType, locatorValue, testData);
										LogUtils.info("Executing: Enter text: " + testData);
										ExtentTestManager.logMessage(Status.PASS, Description);
									} catch (NoSuchElementException e) {
										LogUtils.error("SendKeys:" + locatorType + "=" + locatorValue
												+ " not found to sendKeys| " + e.getMessage());
										ExtentTestManager.logMessage(Status.FAIL, Description);
									}
								}
							}

						}
					}
				}
			}
			break;

		case "click":
			try {
				ActionKeywords.clickElement(locatorType, locatorValue);
				ExtentTestManager.logMessage(Status.PASS, "Click : " + locatorValue);
			} catch (NoSuchElementException e) {
				ExtentTestManager.logMessage(Status.FAIL, "Can't click : " + locatorValue);
			}
			break;
		case "clickButton":
			try {
				ActionKeywords.clickButton(locatorType, locatorValue);
				ExtentTestManager.logMessage(Status.PASS, "Click button: " + locatorValue);
			} catch (NoSuchElementException e) {
				ExtentTestManager.logMessage(Status.FAIL, "Can't click button: " + locatorValue);
			}
			break;
		case "clickElement":
			try {
				ActionKeywords.clickElement(locatorType, locatorValue);
				ExtentTestManager.logMessage(Status.PASS, "Click: " + locatorValue);
			} catch (NoSuchElementException e) {
				ExtentTestManager.logMessage(Status.FAIL, " Can't click: " + locatorValue);
			}
			break;
		case "verifyElementText":
			if (ActionKeywords.verifyElementText(locatorType, locatorValue, testData)) {
				CasePass++;
				LogUtils.info("Same result ---> Pass");
				ExtentTestManager.logMessage(Status.PASS, "The actual is as same as the expected");
			} else {
				CaseFail++;
				LogUtils.error("Different result ---> Fail");
				ExtentTestManager.logMessage(Status.FAIL, "The actual is different from the expected");
			}
			break;
		case "verifyUrl":
			if (ActionKeywords.verifyUrl(sResult)) {
				CasePass++;
				LogUtils.info("Same result ---> Pass");
				ExtentTestManager.logMessage(Status.PASS, "The actual is as same as the expected");
			} else {
				CaseFail++;
				LogUtils.error("Different result ---> Fail");
				ExtentTestManager.logMessage(Status.FAIL, "The actual is different from the expected");
			}
			break;
		case "verifyAlertCourseNameofSearchPage":
			if (ActionKeywords.verifyAlertCourseNameofSearchPage(sResult)) {
				CasePass++;
				LogUtils.info("Same result ---> Pass");
				ExtentTestManager.logMessage(Status.PASS, "The actual is as same as the expected");
			} else {
				CaseFail++;
				LogUtils.error("Different result ---> Fail");
				ExtentTestManager.logMessage(Status.FAIL, "The actual is different from the expected");
			}
			break;
		case "verifyElementTextCourseNameofSearchPage":
			if (ActionKeywords.verifyElementTextCourseNameofSearchPage(sResult)) {
				CasePass++;
				LogUtils.info("Same result ---> Pass");
				ExtentTestManager.logMessage(Status.PASS, "The actual is as same as the expected");
			} else {
				CaseFail++;
				LogUtils.error("Different result ---> Fail");
				ExtentTestManager.logMessage(Status.FAIL, "The actual is different from the expected");
			}
			break;

		case "verifyToast":
			if (ActionKeywords.verifyToast(sResult)) {
				CasePass++;
				LogUtils.info("Same result ---> Pass");
				ExtentTestManager.logMessage(Status.PASS, "The actual is as same as the expected");
			} else {

				CaseFail++;
				LogUtils.error("Different result ---> Fail");
				ExtentTestManager.logMessage(Status.FAIL, "The actual is different from the expected");
			}

			break;

		case "screenShot":
			if (testSuiteID.equalsIgnoreCase("varTestCaseID")) {
				try {
					ActionKeywords.screenShot(CaseName);
					LogUtils.info("Executing: Screenshot taken: " + CaseName);
					ExtentTestManager.logMessage(Status.PASS, "Screenshot taken: " + CaseName);
				} catch (Exception e) {
					LogUtils.error("Executing: Screenshot taken: " + CaseName + "FAIL");
					ExtentTestManager.logMessage(Status.FAIL, "Can't screenshot taken: " + CaseName);
				}
			} else {
				try {
					ActionKeywords.screenShot(CaseName);
					LogUtils.info("Executing: Screenshot taken: " + CaseName);
					ExtentTestManager.logMessage(Status.PASS, "Screenshot taken: " + CaseName);
				} catch (Exception e) {
					LogUtils.error("Executing: Screenshot taken: " + CaseName + "FAIL");
					ExtentTestManager.logMessage(Status.FAIL, "Can't screenshot taken: " + CaseName);
				}
			}
			break;

		case "verifyAlertNameofSignUpPage":
			if (ActionKeywords.verifyAlertNameofSignUpPage(sResult)) {
				CasePass++;
				LogUtils.info("Same result ---> Pass");
				ExtentTestManager.logMessage(Status.PASS, "The actual is as same as the expected");
			} else {
				CaseFail++;
				LogUtils.error("Different result ---> Fail");
				ExtentTestManager.logMessage(Status.FAIL, "The actual is different from the expected");
			}
			break;

		case "verifyAlertEmailofSignUpPage":
			if (ActionKeywords.verifyAlertEmailofSignUpPage(sResult)) {
				CasePass++;
				LogUtils.info("Same result ---> Pass");
				ExtentTestManager.logMessage(Status.PASS, "The actual is as same as the expected");
			} else {
				CaseFail++;
				LogUtils.error("Different result ---> Fail");
				ExtentTestManager.logMessage(Status.FAIL, "The actual is different from the expected");
			}

			break;
		case "verifyAlertPhoneNumberofSignUpPage":
			if (ActionKeywords.verifyAlertPhoneNumberofSignUpPage(sResult)) {
				CasePass++;
				LogUtils.info("Same result ---> Pass");
				ExtentTestManager.logMessage(Status.PASS, "The actual is as same as the expected");
			} else {
				CaseFail++;
				LogUtils.error("Different result ---> Fail");
				ExtentTestManager.logMessage(Status.FAIL, "The actual is different from the expected");
			}

			break;
		case "verifyElementTextPhoneNumberofSignUpPage":
			if (ActionKeywords.verifyElementTextPhoneNumberofSignUpPage(sResult)) {
				CasePass++;
				LogUtils.info("Same result ---> Pass");
				ExtentTestManager.logMessage(Status.PASS, "The actual is as same as the expected");
			} else {
				CaseFail++;
				LogUtils.error("Different result ---> Fail");
				ExtentTestManager.logMessage(Status.FAIL, "The actual is different from the expected");
			}

			break;

		case "verifyAlertPasswordofSignUpPage":
			if (ActionKeywords.verifyAlertPasswordofSignUpPage(sResult)) {
				CasePass++;
				LogUtils.info("Same result ---> Pass");
				ExtentTestManager.logMessage(Status.PASS, "The actual is as same as the expected");
			} else {
				CaseFail++;
				LogUtils.error("Different result ---> Fail");
				ExtentTestManager.logMessage(Status.FAIL, "The actual is different from the expected");
			}

			break;
		case "ScrollDownAndClick":
			try {
				ActionKeywords.clickElementWithJs(locatorType, locatorValue);
				ExtentTestManager.logMessage(Status.PASS, Description);
			} catch (NoSuchElementException e) {
				ExtentTestManager.logMessage(Status.FAIL, Description);
			}
			break;
//		  case "dismissAlert":
//              try {
//                  ActionKeywords.dismissAlert(locatorType,locatorValue);
//                  ExtentTestManager.logMessage(Status.PASS, Description);
//              }
//              catch (Exception e)
//              {
//                  ExtentTestManager.logMessage(Status.FAIL, Description);
//              }
//              break;

		case "closeBrowser":
			try {
				ActionKeywords.closeBrowser();
				ExtentTestManager.logMessage(Status.PASS, "Close browser");
			} catch (Exception e) {
				ExtentTestManager.logMessage(Status.FAIL, "Can't close browser");
			}
			break;
		}
		ExtentManager.getExtentReports().flush();

	}

	public void reportInConsole() throws Exception {
		java.util.Date date = new java.util.Date();
		System.out.println("==========================================================");
		System.out.println("-----------" + date + "--------------");
		System.out.println("Total number of Testcases run: " + (CasePass + CaseFail + CaseSkip));
		System.out.println("Total number of passed Testcases: " + CasePass);
		System.out.println("Total number of failed Testcases: " + CaseFail);
		System.out.println("Total number of skip Testcases: " + CaseSkip);
		System.out.println("==========================================================");
		EmailSendUtils.sendEmail(CasePass + CaseFail + CaseSkip, CasePass, CaseFail, CaseSkip);
	}
	
}
