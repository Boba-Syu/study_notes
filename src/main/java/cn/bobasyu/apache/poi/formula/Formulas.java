package cn.bobasyu.apache.poi.formula;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 公式操作
 *
 * @author Boba
 */
public class Formulas {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Formulas");

        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("A =");
        cell = row.createCell(2);
        cell.setCellValue(2);

        row = sheet.createRow(2);
        cell = row.createCell(1);
        cell.setCellValue("B =");
        cell = row.createCell(2);
        cell.setCellValue(4);

        // 求和
        row = sheet.createRow(3);
        cell = row.createCell(1);
        cell.setCellValue("Total =");
        cell = row.createCell(2);
        cell.setCellFormula("SUM(C2:C3)");
        cell = row.createCell(3);
        cell.setCellValue("SUM(C2:C3)");

        // 乘方
        row = sheet.createRow(4);
        cell = row.createCell(1);
        cell.setCellValue("POWER =");
        cell = row.createCell(2);
        cell.setCellFormula("POWER(C2,C3)");
        cell = row.createCell(3);
        cell.setCellValue("POWER(C2,C3)");

        // 最大值
        row = sheet.createRow(5);
        cell = row.createCell(1);
        cell.setCellValue("MAX =");
        cell = row.createCell(2);
        cell.setCellFormula("MAX(C2,C3)");
        cell = row.createCell(3);
        cell.setCellValue("MAX(C2,C3)");

        // 阶乘
        row = sheet.createRow(6);
        cell = row.createCell(1);
        cell.setCellValue("FACT =");
        cell = row.createCell(2);
        cell.setCellFormula("FACT(C3)");
        cell = row.createCell(3);
        cell.setCellValue("FACT(C3)");

        // 开平方
        row = sheet.createRow(7);
        cell = row.createCell(1);
        cell.setCellValue("SQRT =");
        cell = row.createCell(2);
        cell.setCellFormula("SQRT(C5)");
        cell = row.createCell(3);
        cell.setCellValue("SQRT(C5)");

        workbook.getCreationHelper()
                .createFormulaEvaluator()
                .evaluateAll();

        String path = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\formula\\formula.xlsx";
        FileOutputStream out = new FileOutputStream(new File(path));
        workbook.write(out);
        out.close();
        System.out.println("Done.");
    }
}
