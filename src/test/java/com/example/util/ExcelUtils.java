package com.example.util;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
	
	private  static String path = "AddBeneficiaryWithinADCB";
    private static  String  filepath= "/Users/adcbkony/Downloads/AutomationFramework_Full/TestData/"+path+".xlsx";


    
    public static List<Map<String, String>> readExcelData(String filepath) {
        List<Map<String, String>> dataList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filepath))) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Map<String, String> dataMap = new HashMap<>();
                for (int j = 0; j < colCount; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell cell = row.getCell(j);
                    String header = headerCell != null ? headerCell.toString() : "";
                    String value = cell != null ? cell.toString() : "";
                    dataMap.put(header, value);
                }
                dataList.add(dataMap);
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static void writeExcelData(String filePath, List<Map<String, String>> dataList) {
        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(filePath)));
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();
            // Remove all rows except header
            for (int i = sheet.getLastRowNum(); i > 0; i--) {
                Row row = sheet.getRow(i);
                if (row != null) sheet.removeRow(row);
            }
            // Write new data
            for (int i = 0; i < dataList.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Map<String, String> dataMap = dataList.get(i);
                for (int j = 0; j < colCount; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    String header = headerCell != null ? headerCell.toString() : "";
                    String value = dataMap.getOrDefault(header, "");
                    Cell cell = row.createCell(j);
                    cell.setCellValue(value);
                }
            }
            workbook.write(new java.io.FileOutputStream(filePath));
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Reads all data from the Excel file as a list of maps, or all values from a specific column if columnName is provided.
     * @param filepath Path to the Excel file
     * @param columnName Name of the column to read values from (if null, reads all data)
     * @return List of maps for all data, or list of strings for column values
     */
    public static Object readExcelColumnName(String columnName) {
        if (columnName == null || columnName.isEmpty()) {
            return readExcelData(filepath);
        } else {
            List<String> columnValues = new ArrayList<>();
            DataFormatter formatter = new DataFormatter();
            try (FileInputStream fis = new FileInputStream(new File(filepath))) {
                Workbook workbook = WorkbookFactory.create(fis);
                Sheet sheet = workbook.getSheetAt(0);
                Row headerRow = sheet.getRow(0);
                int colCount = headerRow.getLastCellNum();
                int targetCol = -1;
                for (int j = 0; j < colCount; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    String header = headerCell != null ? formatter.formatCellValue(headerCell) : "";
                    if (header.equalsIgnoreCase(columnName)) {
                        targetCol = j;
                        break;
                    }
                }
                if (targetCol == -1) return columnValues; // Column not found
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;
                    Cell cell = row.getCell(targetCol);
                    String value = cell != null ? formatter.formatCellValue(cell) : "";
                    columnValues.add(value);
                }
                workbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return columnValues;
        }
    }
    /**
     * Reads the first value from a specific column in the Excel file and returns it as a String.
     * @param columnName Name of the column to read value from
     * @return First value of the column as String, or empty string if not found
     */
    public static String readExcelColumnNameAsString(String columnName) {
        Object result = readExcelColumnName(columnName);
        if (result instanceof java.util.List && !((java.util.List<?>) result).isEmpty()) {
            Object first = ((java.util.List<?>) result).get(0);
            return first != null ? first.toString() : "";
        }
        return "";
    }
  

    /**
     * Writes a runtime value to DataStorage.xlsx with feature file name, field name, username, and runtime value.
     * If the columns or row do not exist, they will be created.
     * @param featureFileName Name of the feature file (used as row identifier)
     * @param fieldName Name of the field (used as column identifier)
     * @param runtimeValue The runtime value to write
     */
    public static void writeRuntimeValueToDataStorage(String featureFileName, String fieldName, String runtimeValue) {
        String dataStoragePath = "/Users/adcbkony/Downloads/AutomationFramework_Full/DataStorage/DataStorage.xlsx";
        try (FileInputStream fis = new FileInputStream(new File(dataStoragePath))) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();
            int featureCol = -1, fieldCol = -1, valueCol = -1;
            for (int j = 0; j < colCount; j++) {
                Cell headerCell = headerRow.getCell(j);
                String header = headerCell != null ? headerCell.toString() : "";
                if (header.equalsIgnoreCase("FeatureFileName")) featureCol = j;
                if (header.equalsIgnoreCase("FieldName")) fieldCol = j;
                if (header.equalsIgnoreCase("RuntimeValue")) valueCol = j;
            }
            if (featureCol == -1 || fieldCol == -1 || valueCol == -1) {
                throw new RuntimeException("Required columns not found in DataStorage.xlsx");
            }
            int targetRowNum = -1;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String fName = row.getCell(featureCol) != null ? row.getCell(featureCol).toString() : "";
                    String fField = row.getCell(fieldCol) != null ? row.getCell(fieldCol).toString() : "";
                    if (fName.equalsIgnoreCase(featureFileName) && fField.equalsIgnoreCase(fieldName)) {
                        targetRowNum = i;
                        break;
                    }
                }
            }
            Row targetRow;
            if (targetRowNum == -1) {
                targetRow = sheet.createRow(sheet.getLastRowNum() + 1);
                targetRow.createCell(featureCol).setCellValue(featureFileName);
                targetRow.createCell(fieldCol).setCellValue(fieldName);
            } else {
                targetRow = sheet.getRow(targetRowNum);
                targetRow.getCell(featureCol).setCellValue(featureFileName);
                targetRow.getCell(fieldCol).setCellValue(fieldName);
            }
            Cell valueCell = targetRow.getCell(valueCol);
            if (valueCell == null) valueCell = targetRow.createCell(valueCol);
            valueCell.setCellValue(runtimeValue);
            try (java.io.FileOutputStream fos = new java.io.FileOutputStream(dataStoragePath)) {
                workbook.write(fos);
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Reads a runtime value from DataStorage.xlsx for the given feature file name and field name.
     * @param featureFileName Name of the feature file (row identifier)
     * @param fieldName Name of the field (column identifier)
     * @return The runtime value if found, otherwise null
     */
    public static String readRuntimeValueFromDataStorage(String featureFileName, String fieldName) {
        String dataStoragePath = "/Users/adcbkony/Downloads/AutomationFramework_Full/DataStorage/DataStorage.xlsx";
        try (FileInputStream fis = new FileInputStream(new File(dataStoragePath))) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();
            int featureCol = -1, fieldCol = -1, valueCol = -1;
            for (int j = 0; j < colCount; j++) {
                Cell headerCell = headerRow.getCell(j);
                String header = headerCell != null ? headerCell.toString() : "";
                if (header.equalsIgnoreCase("FeatureFileName")) featureCol = j;
                if (header.equalsIgnoreCase("FieldName")) fieldCol = j;
                if (header.equalsIgnoreCase("RuntimeValue")) valueCol = j;
            }
            if (featureCol == -1 || fieldCol == -1 || valueCol == -1) {
                throw new RuntimeException("Required columns not found in DataStorage.xlsx");
            }
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String fName = row.getCell(featureCol) != null ? row.getCell(featureCol).toString() : "";
                    String fField = row.getCell(fieldCol) != null ? row.getCell(fieldCol).toString() : "";
                    if (fName.equalsIgnoreCase(featureFileName) && fField.equalsIgnoreCase(fieldName)) {
                        Cell valueCell = row.getCell(valueCol);
                        String value = valueCell != null ? valueCell.toString() : null;
                        workbook.close();
                        return value;
                    }
                }
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}