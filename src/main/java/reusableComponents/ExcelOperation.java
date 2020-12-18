package reusableComponents;

import java.io.File;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.aventstack.extentreports.Status;

import testBase.ExtentFactory;
import testBase.TestBase;

public class ExcelOperation extends TestBase {

	String filePath;
	Sheet sh;

	// specifing location in Constructor Method
	public ExcelOperation(String sheetName) {
		try {
			filePath = System.getProperty("user.dir") + ConfigPropExtractData.getPropValueByKey("testDataLocation");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// creating a object of File class
		File testDataFile = new File(filePath);
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(testDataFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// specifing sheet name
		sh = wb.getSheet(sheetName);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Test Data was uploaded from ["+sheetName+"]");
		System.out.println("TestData was uploaded");
		
	}

	public HashMap<String, String> getTestDataIntoMap(int rowNumer) throws Exception {
		// read data row by row in put into a map 
		HashMap<String, String> hm = new HashMap<>();

		// get data from CELLs in our hash map
		// sh.getRow(0).getCell(i).toString; ----> will be the KEY
		// sh.getRow(rowNumer).getCell(i).toString; ----> will be the VALUE

		for (int i = 0; i < sh.getRow(0).getLastCellNum(); i++) {
			sh.getRow(rowNumer).getCell(i).setCellType(CellType.STRING);
			hm.put(sh.getRow(0).getCell(i).toString(), sh.getRow(rowNumer).getCell(i).toString());
		}
		return hm;
	}

	public int rowCount() {
		return sh.getLastRowNum();

	}

	public int colCount() {
		return sh.getRow(0).getLastCellNum();
	}

}
