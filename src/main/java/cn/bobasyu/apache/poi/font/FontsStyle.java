package cn.bobasyu.apache.poi.font;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 字体设置
 *
 * @author Boba
 */
public class FontsStyle {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("FontStyle");
        Row row = sheet.createRow(2);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 30);
        // 字体名称
        font.setFontName("IMPACT");
        // 是否倾斜
        font.setItalic(true);
        // 字体颜色
        font.setColor(HSSFColor.HSSFColorPredefined.DARK_BLUE.getIndex());

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        Cell cell = row.createCell(1);
        cell.setCellValue("Font style");
        cell.setCellStyle(cellStyle);

        String path = "src/main/java/cn/bobasyu/apache/poi/font/fontsStyle.xlsx";
        OutputStream out = new FileOutputStream(new File(path));
        workbook.write(out);
        out.close();
        System.out.println("Done.");
    }
}
