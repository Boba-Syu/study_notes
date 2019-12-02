package cn.bobasyu.apache.poi.workbook;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 创建工作簿
 *
 * @author Boba
 */
public class CreateWorkbook {
    public static void main(String[] args) throws IOException {
        String path = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\workbook\\createWorkbook.xlsx";
        Workbook workbook = new XSSFWorkbook();
        workbook.createSheet("sheet1");
        OutputStream out = new FileOutputStream(new File(path));
        workbook.write(out);
        out.close();
        System.out.println("Done.");
    }
}
