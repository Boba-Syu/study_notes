package cn.bobasyu.apache.poi.sxssf;

import javafx.scene.control.Cell;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;

/**
 * @author Boba
 */
public class SXSSCTest {
    public static void main(String[] args) {
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        SXSSFSheet sheet = workbook.createSheet();
        int num = 1000;
        for (int rowNum = 0; rowNum < num; rowNum++) {
            SXSSFRow row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < num/10; cellNum++) {
                SXSSFCell cell = row.createCell(cellNum);
                cell.setCellValue((rowNum + 1) * (cellNum + 1));
            }
        }
        try (FileOutputStream fos = new FileOutputStream("src\\main\\java\\cn\\bobasyu\\apache\\poi\\sxssf\\test.xlsx")) {
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
