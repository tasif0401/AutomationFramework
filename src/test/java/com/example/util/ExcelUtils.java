package com.example.util;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import java.util.Map;

public class ExcelUtils {
	 public static Map<String, String> getRowData(String filePath, String sheetName, String testCaseId) {
	        Map<String, String> rowData = new HashMap<>();
	        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
	            Workbook workbook = WorkbookFactory.create(fis);
	            Sheet sheet = workbook.getSheet(sheetName);
	            Row headerRow = sheet.getRow(0);

	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	                Row currentRow = sheet.getRow(i);
	                if (currentRow.getCell(0).getStringCellValue().equalsIgnoreCase(testCaseId)) {
	                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
	                        String colName = headerRow.getCell(j).getStringCellValue();
	                        String cellValue = getCellValue(currentRow.getCell(j));
	                        rowData.put(colName, cellValue);
	                    }
	                    break;
	                }
	            }
	            workbook.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return rowData;
	    }

	    private static String getCellValue(Cell cell) {
	        if (cell == null) return "";
	        switch (cell.getCellType()) {
	            case STRING: return cell.getStringCellValue();
	            case NUMERIC: return String.valueOf((long) cell.getNumericCellValue());
	            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
	            default: return "";
	        }
	    }
}
