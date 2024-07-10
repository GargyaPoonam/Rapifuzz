package browserUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;



public class ExcelDataDriven {
    WebDriver driver;
    
    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
        File file = new File("/Users/poonamsharma/eclipse-workspace1/HrmLogin/input data.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        Object[][] data = new Object[sheet.getLastRowNum()][2];

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String Uname = row.getCell(0).getStringCellValue();
            String PSW = row.getCell(1).getStringCellValue();
            data[i - 1][0] = Uname;
            data[i - 1][1] = PSW;
        }

        wb.close();
        fis.close();
        return data;
    }
}
