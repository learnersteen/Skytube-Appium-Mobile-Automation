package utilities;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
	public List<String> getColumnData(String sheetName) throws Exception {
		List<String> data = new ArrayList<>();
		String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
				+ "resources" + File.separator + "data" + File.separator + "SkyTubeData.xlsx";

		try (FileInputStream fis = new FileInputStream(new File(path));
				Workbook workbook = WorkbookFactory.create(fis)) {

			System.out.println("--- Debug: Available Sheets in Excel ---");
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				System.out.println("Found sheet: [" + workbook.getSheetName(i) + "]");
			}
			System.out.println("---------------------------------------");

			Sheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				throw new RuntimeException("Sheet '" + sheetName + "' not found! Check the debug list above.");
			}

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null && row.getCell(0) != null) {
					data.add(row.getCell(0).toString().trim());
				}
			}
		}
		return data;
	}
}