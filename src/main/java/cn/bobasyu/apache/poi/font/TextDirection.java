package cn.bobasyu.apache.poi.font;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文字方向
 *
 * @author Boba
 */
public class TextDirection {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Text Direction");
        Row row = sheet.createRow(2);
        CellStyle myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 0);
        Cell cell = row.createCell(1);
        cell.setCellValue("0D angle");
        cell.setCellStyle(myStyle);
        //30 degrees
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 30);
        cell = row.createCell(3);
        cell.setCellValue("30D angle");
        cell.setCellStyle(myStyle);
        //90 degrees
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 90);
        cell = row.createCell(5);
        cell.setCellValue("90D angle");
        cell.setCellStyle(myStyle);
        //120 degrees
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 120);
        cell = row.createCell(7);
        cell.setCellValue("120D angle");
        cell.setCellStyle(myStyle);
        //270 degrees
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 270);
        cell = row.createCell(9);
        cell.setCellValue("270D angle");
        cell.setCellStyle(myStyle);
        //360 degrees
        myStyle = workbook.createCellStyle();
        myStyle.setRotation((short) 360);
        cell = row.createCell(12);
        cell.setCellValue("360D angle");
        cell.setCellStyle(myStyle);

        String path = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\font\\textDirection.xlsx";
        FileOutputStream out = new FileOutputStream(new File(path));
        workbook.write(out);
        out.close();
        System.out.println("Done.");
    }
}

