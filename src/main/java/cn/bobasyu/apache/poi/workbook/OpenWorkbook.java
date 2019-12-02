package cn.bobasyu.apache.poi.workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 读取workboot
 * @author Boba
 */
public class OpenWorkbook {
    public static void main(String[] args) throws Exception {
        File file = new File("src\\main\\java\\cn\\bobasyu\\apache\\poi\\workbook\\createWorkbook.xlsx");
        InputStream in = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        if (file.isFile() && file.exists()) {
            System.out.println("Done.");
        } else {
            System.out.println("Error.");
        }
    }
}
