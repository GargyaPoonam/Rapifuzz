
package PageClass;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "txtStationFrom") WebElement from;
    @FindBy(id = "txtStationTo") WebElement to;
    @FindBy(xpath = "//input[@title='Select Departure date for availability']") WebElement targetdate;
    @FindBy(xpath = "(//strong[contains(text(),'Del')])[3]") WebElement selectele;

    public void inputStation_from(String input) throws InterruptedException {
        from.click();
        Thread.sleep(2000);
        from.clear();
        from.sendKeys(input);
        selectele.click();
    }

    public void stataionverify() throws IOException {
    	
    	File file = new File("/Users/poonamsharma/eclipse-workspace1/Erail.in/station.xlsx");
    
    	FileInputStream fis = new FileInputStream(file);
    	XSSFWorkbook wb = new XSSFWorkbook(fis);
    	XSSFSheet sheet =wb.getSheetAt(1);
    	String[] expectedStations = {"Delhi", "New delhi", "Delhi Indrapuri", "Delhi Azadpur","delhi Cantt"};

        for (int i = 0; i < expectedStations.length; i++) {
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(expectedStations[i]);
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(file)){
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Closing the workbook
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void inputDate() {
        LocalDate targetDate = LocalDate.now().plus(30, ChronoUnit.DAYS);
        targetdate.click(); 
 
        String day = String.valueOf(targetDate.getDayOfMonth());
        String monthYear = targetDate.format(DateTimeFormatter.ofPattern("MMM-yy"));
        String monthXPath = "//td[text()='" + monthYear + "']/ancestor::table[@class='Month']";
        String dayXPath = monthXPath + "//td[not(contains(@style,'color:#C0C0C0')) and text()='" + day + "']";
        WebElement dateElement = driver.findElement(By.xpath(dayXPath));
        dateElement.click();


    }
}
