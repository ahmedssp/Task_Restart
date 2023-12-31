package DataMethod;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

import static Listeners.testNg_listeners_simple.PassMessege;
import static Listeners.testNg_listeners_simple.failledMessege;

public class ApachePOI {
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSheetName() {
        return SheetName;
    }

    public void setSheetName(String sheetName) {
        SheetName = sheetName;
    }

    public String path;
    public String SheetName;

    public static Object[][] Read_ExcelSheet(String path_new, String SheetName_new) {

        try {
            //path=getPath();SheetName=getSheetName();
            //0- addd dependency apachi-poi>> https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
            //1- read file
            File x = new File(System.getProperty("user.dir") + path_new);
            //2-read sheet
            XSSFSheet sheet = null;
            try {
                sheet = new XSSFWorkbook(x).getSheet(SheetName_new);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidFormatException e) {
                throw new RuntimeException(e);
            }
            //3-define number of Rows and coulums
            int rows = sheet.getPhysicalNumberOfRows();
            int colums = sheet.getRow(0).getLastCellNum();
            //4- define Two dimentianl array
            Object[][] data;
            data = new String[rows - 1][colums];
            //5- read valuse form cells rows an coulums
            DataFormatter formatter = new DataFormatter();
            for (int x1 = 1; x1 < rows; x1++) {
                for (int y = 0; y < colums; y++) {
                    XSSFRow R_IN = sheet.getRow(x1);
                    data[x1 - 1][y] = formatter.formatCellValue(R_IN.getCell(y));
                }
            }
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + PassMessege);

            return data;

        } catch (Exception e) {
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + failledMessege);

            throw e;
        }
    }

    @DataProvider(name = "Visiting_Request_data")
    public Object[][] Visiting_Request_2() {
        //0- addd dependency apachi-poi>> https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
        //1- read file
        File x = new File(System.getProperty("user.dir") + "/resources/Visiting_Request_DataProvider.xlsx");
        //2-read sheet
        XSSFSheet sheet = null;
        try {
            sheet = new XSSFWorkbook(x).getSheet("sheet1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        //3-define number of Rows and coulums
        int rows = sheet.getPhysicalNumberOfRows();
        int colums = sheet.getRow(0).getLastCellNum();
        //4- define Two dimentianl array
        Object[][] data;
        data = new String[rows - 1][colums];
        //5- read valuse form cells rows an coulums
        DataFormatter formatter = new DataFormatter();
        for (int x1 = 1; x1 < rows; x1++) {
            for (int y = 0; y < colums; y++) {
                XSSFRow R_IN = sheet.getRow(x1);
                data[x1 - 1][y] = formatter.formatCellValue(R_IN.getCell(y));
            }
        }
        return data;
    }

    @DataProvider(name = "exceldata")
    public Object[][] dataexcelm() {
        //0- addd dependency apachi-poi>> https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
        //1- read file
        File x = new File(System.getProperty("user.dir") + "/resources/Data.xlsx");
        //2-read sheet
        XSSFSheet sheet = null;
        try {
            sheet = new XSSFWorkbook(x).getSheet("sheet1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        //3-define number of Rows and coulums
        int rows = sheet.getPhysicalNumberOfRows();
        int colums = sheet.getRow(0).getLastCellNum();
        //4- define Two dimentianl array
        Object[][] data;
        data = new String[rows - 1][colums];
        //5- read valuse form cells rows an coulums
        DataFormatter formatter = new DataFormatter();
        for (int x1 = 1; x1 < rows; x1++) {
            for (int y = 0; y < colums; y++) {
                XSSFRow R_IN = sheet.getRow(x1);
                data[x1 - 1][y] = formatter.formatCellValue(R_IN.getCell(y));
            }
        }
        return data;
    }

    @DataProvider(name = "Dataentry")
    public Object[][] dataexcelm2() {
        //0- addd dependency apachi-poi>> https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
        //1- read file
        File x = new File(System.getProperty("user.dir") + "/resources/DataEntry_CRUD.xlsx");
        //2-read sheet
        XSSFSheet sheet = null;
        try {
            sheet = new XSSFWorkbook(x).getSheet("sheet1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        //3-define number of Rows and coulums
        int rows = sheet.getPhysicalNumberOfRows();
        int colums = sheet.getRow(0).getLastCellNum();
        //4- define Two dimentianl array
        Object[][] data;
        data = new String[rows - 1][colums];
        //5- read valuse form cells rows an coulums
        DataFormatter formatter = new DataFormatter();
        for (int x1 = 1; x1 < rows; x1++) {
            for (int y = 0; y < colums; y++) {
                XSSFRow R_IN = sheet.getRow(x1);
                data[x1 - 1][y] = formatter.formatCellValue(R_IN.getCell(y));
            }
        }
        return data;
    }

}