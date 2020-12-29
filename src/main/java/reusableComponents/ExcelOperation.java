package reusableComponents;

import java.io.File;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import testBase.TestBase;

public class ExcelOperation extends TestBase {

	String filePath;
	Sheet sh;

	// specifing location in Constructor Method
	public ExcelOperation(String sheetName)  {
		/*
		 * if (num == 0) { try {
		 */
				try {
					filePath = System.getProperty("user.dir") + ConfigPropExtractData.getPropValueByKey("testDataLocation");
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				/*
				 * } catch (Exception e) { e.printStackTrace(); } } else if (num == 1) { try {
				 * filePath = System.getProperty("user.dir") +
				 * ConfigPropExtractData.getPropValueByKey("testDataLocation1"); } catch
				 * (Exception e) { e.printStackTrace(); }
				 */
		//}

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
	}

	
	public HashMap<String, String> getTestDataIntoMap(int rowNumber) throws Exception {
		// create a map for storing our data
		HashMap<String, String> hm = new HashMap<>();
		/*
		 * read data row by row convert into a String and stored into a map
		 * sh.getRow(0).getCell(i).toString; ----> will be the KEY
		 * sh.getRow(rowNumer).getCell(i).toString; ----> will be the VALUE
		 */
		for (int i = 0; i < sh.getRow(0).getLastCellNum(); i++) {
			if (sh.getRow(rowNumber).getCell(i) == null) {
				hm.put(sh.getRow(0).getCell(i).toString(), "");
			} else {
				hm.put(sh.getRow(0).getCell(i).toString(), sh.getRow(rowNumber).getCell(i).toString());
			}
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