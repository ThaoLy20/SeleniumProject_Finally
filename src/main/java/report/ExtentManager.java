package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReports/ExtentReport.html");
        reporter.config().setReportName("Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Project final with Selenium Java Framework | Phạm Thảo Ly");
        extentReports.setSystemInfo("Author", "Phạm Thảo Ly");
        return extentReports;
    }
}