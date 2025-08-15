
package com.example.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static List<String[]> readSheet(String filePath, String sheetName) throws Exception {
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            List<String[]> rows = new ArrayList<>();
            int rowsCount = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rowsCount; i++) {
                int cols = sheet.getRow(i).getLastCellNum();
                String[] data = new String[cols];
                for (int c = 0; c < cols; c++) {
                    data[c] = sheet.getRow(i).getCell(c).toString();
                }
                rows.add(data);
            }
            return rows;
        }
    }
}
