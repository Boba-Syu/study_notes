package cn.bobasyu.apache.poi.clone;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 从一个workbook中复制一个sheet到另一个workbook中
 *
 * @author Boba
 */
public class CloneSheet {

    public static void main(String[] args) {
        String path = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\clone\\test.xlsx";
        String result = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\clone\\result.xlsx";
        File file = new File(path);
        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(result)) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
            SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook);
            SXSSFSheet sheet = workbook.getSheetAt(2);
            SXSSFWorkbook workbook1 = new SXSSFWorkbook(100);
            SXSSFSheet sheet1 = workbook1.createSheet("All");
            List<Row> rows = new ArrayList<>();
            sheet.iterator().forEachRemaining(row -> rows.add(copyRow(row, sheet1)));
            System.out.println(rows);
            workbook1.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 克隆行Row
     *
     * @param row   被克隆的Row
     * @param sheet 克隆的Row所在的Sheet
     */
    private static Row copyRow(Row row, Sheet sheet) {
        Row resultRow = sheet.createRow(sheet.getLastRowNum());
        int i = 0;
        Iterator<Cell> iterator = row.cellIterator();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            Cell resultCell = resultRow.createCell(i);
            resultCell.getCellStyle().cloneStyleFrom(cell.getCellStyle());
            switch (cell.getCellType()) {
                case BOOLEAN:
                    resultCell.setCellValue(cell.getBooleanCellValue());
                    break;
                case STRING:
                    resultCell.setCellValue(cell.getStringCellValue());
                    break;
                case NUMERIC:
                    resultCell.setCellValue(cell.getNumericCellValue());
                    break;
                default:
                    break;
            }
            i++;
        }
        return resultRow;
    }
}
