
package com.example.runners;

import com.example.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;

public class ExcelDataProvider {
    @DataProvider(name = "excelData")
    public static Iterator<Object[]> excelData() throws Exception {
        List<String[]> rows = ExcelUtils.readSheet("src/test/resources/testdata/LoginData.xlsx","LoginData");
        return rows.stream().map(r -> new Object[]{r[0], r[1]}).iterator();
    }
}
