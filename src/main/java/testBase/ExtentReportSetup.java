package testBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import reusableComponents.ConfigPropExtractData;

public class ExtentReportSetup {
	
	static ExtentReports extent;
		
	public static ExtentReports extentReportsSetUp() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		String reportPath = System.getProperty("user.dir")+"/Reports/ExecutionReport_"
				+actualDate+".html";
		
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		
		sparkReport.config().setDocumentTitle("DocumentTitle");
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("ReportName");
		
		String url = ConfigPropExtractData.getPropValueByKey("url");
		String browser = ConfigPropExtractData.getPropValueByKey("browser");
		
		extent.setSystemInfo("Executed on envirnoment: ", url);
		extent.setSystemInfo("Executed on browser: ", browser);
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
		
		
		return extent;
		
	}

}
