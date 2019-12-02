package cn.bobasyu.apache.poi.cell;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * 单元格格式
 *
 * @author Boba
 */
public class CellsStyle {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("cellStyle");

        Row row = sheet.createRow(1);
        row.setHeight((short) 800);

        // 合并单元格
        Cell cell = row.createCell(1);
        cell.setCellValue("test of merging");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));

        // 单元格对齐
        row = sheet.createRow(5);
        cell = row.createCell(0);
        row.setHeight((short) 800);

        // 左上对齐
        CellStyle cellStyle1 = workbook.createCellStyle();
        sheet.setColumnWidth(0, 8000);
        cellStyle1.setAlignment(HorizontalAlignment.LEFT);
        cellStyle1.setVerticalAlignment(VerticalAlignment.TOP);
        cell.setCellValue("Top Left");
        cell.setCellStyle(cellStyle1);

        row = sheet.createRow(6);
        cell = row.createCell(0);
        row.setHeight((short) 700);

        //居中对齐
        CellStyle cellStyle2 = workbook.createCellStyle();
        sheet.setColumnWidth(0, 8000);
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellValue("Center");
        cell.setCellStyle(cellStyle2);

        String path = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\cell\\cellsStyle.xlsx";
        OutputStream out = new FileOutputStream(new File(path));
        workbook.write(out);
        out.close();
        System.out.println("Done.");
    }
}
