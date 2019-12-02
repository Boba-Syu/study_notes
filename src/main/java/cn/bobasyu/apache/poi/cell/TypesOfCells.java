package cn.bobasyu.apache.poi.cell;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Date;

/**
 * 在电子表中创建不同类型的单元格
 *
 * @author Boba
 */
public class TypesOfCells {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("cell types");

        Row row = sheet.createRow(2);
        row.createCell(0).setCellValue("Type of Cell");
        row.createCell(1);

        row = sheet.createRow(3);
        row.createCell(0).setCellValue("set cell type BLANK");
        row.createCell(1);

        row = sheet.createRow(4);
        row.createCell(0).setCellValue("set cell type BOOLEAN");
        row.createCell(1).setCellValue(true);

        row = sheet.createRow(5);
        row.createCell(0).setCellValue("set cell type date");
        row.createCell(1).setCellValue(new Date());

        row = sheet.createRow(6);
        row.createCell(0).setCellValue("set cell type mumeric");
        row.createCell(1).setCellValue(20);

        row = sheet.createRow(7);
        row.createCell(0).setCellValue("set cell type string");
        row.createCell(1).setCellValue("A String");

        String path = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\cell\\typesOfCells.xlsx";
        OutputStream out = new FileOutputStream(new File(path));
        workbook.write(out);
        out.close();
        System.out.println("Done.");
    }
}
