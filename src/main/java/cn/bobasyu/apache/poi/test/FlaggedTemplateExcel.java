package cn.bobasyu.apache.poi.test;

import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据Excel模板灌入CSV数据
 *
 * @author Boba
 */
public class FlaggedTemplateExcel {
    public final List<String> headers;

    public FlaggedTemplateExcel() {
        headers = new ArrayList<>();
        headers.add("Best Companies (Y/Y trends have been accelerating for the latest 2 quarters)");
        headers.add("Note: Excludes stocks with annualized sales of less than 100m RMB");
        headers.add("Worst Companies (Y/Y trends have been decelerating for the latest 2 quarters)");
        headers.add("Note: Excludes stocks with annualized sales of less than 100m RMB");
    }

    public static void main(String[] args) {
        String fromExcel = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\test\\flagger_template_new.xlsx";
        String newExcel = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\test\\new.xlsx";
        FlaggedTemplateExcel flaggedTemplateExcel = new FlaggedTemplateExcel();
        List<String[]> msg = flaggedTemplateExcel.readCsv();
        flaggedTemplateExcel.inputMsg(msg, fromExcel, newExcel);
    }

    /**
     * 灌入数据
     *
     * @param msg       数据列表
     * @param fromExcel 模板文件名
     * @param newExcel  生成的文件名
     */
    public void inputMsg(List<String[]> msg, String fromExcel, String newExcel) {
        String[] header = msg.get(0);
        int colNumber = header.length;
        try (FileInputStream fis = new FileInputStream(fromExcel); FileOutputStream fos = new FileOutputStream(newExcel)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            List<String[]> msg2 = this.readCsv();
            pageHighLight(msg2, colNumber, workbook);
            pageAll(msg, colNumber, workbook);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 名为All的sheet中的数据灌入
     *
     * @param msg       数据列表
     * @param colNumber 文件列的数目
     * @param workbook  已打开的文件workbook
     */
    public void pageHighLight(List<String[]> msg, int colNumber, XSSFWorkbook workbook) {
        XSSFSheet spreadsheet = workbook.getSheet("Highlight");
        // 取消合并单元格
        List<String[]> upMsg = msg.stream()
                .filter(strings -> {
                    for (String str : strings) {
                        if ("N/A".equals(str) || "↓".equals(str)) {
                            return false;
                        }
                    }
                    return true;
                }).collect(Collectors.toList());
        List<String[]> downMsg = msg.stream()
                .filter(strings -> {
                    for (String str : strings) {
                        if ("N/A".equals(str) || "↑".equals(str)) {
                            return false;
                        }
                    }
                    return true;
                }).collect(Collectors.toList());

        try {
            input(upMsg, colNumber, spreadsheet, 2);
            addHeader(spreadsheet, upMsg.size(), workbook);
            input(downMsg, colNumber, spreadsheet, upMsg.size() + 5);
            //headColor(spreadsheet, upMsg.size(), colNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void headColor(Sheet spreadsheet, int len, int colNumber) {
        Cell cell;
        // 表头颜色
        for (int i = 0; i < colNumber; i++) {
            cell = spreadsheet.getRow(2).getCell(i);
            CellStyle style = cell.getCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            cell.setCellStyle(style);
        }
        for (int i = 0; i < colNumber; i++) {
            cell = spreadsheet.getRow(len + 5).getCell(i);
            CellStyle style = cell.getCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            cell.setCellStyle(style);
        }
    }

    /**
     * Highlight 添加表头信息
     *
     * @param spreadsheet 名为highlight的sheet
     * @param len         增加的数据的行数
     * @param workbook    workbook
     */
    private void addHeader(Sheet spreadsheet, int len, Workbook workbook) {

        // 字体样式
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 17);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        spreadsheet.getRow(0).getCell(0).setCellStyle(cellStyle);
        // 逐行添加表头前的信息
        Row row = spreadsheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(headers.get(0));
        cell.setCellStyle(cellStyle);

        row = spreadsheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(headers.get(1));

        row = spreadsheet.createRow(4 + len);
        row.createCell(0).setCellValue(headers.get(3));

        row = spreadsheet.createRow(2 + len);
        row.createCell(0).setCellValue("");

        row = spreadsheet.createRow(3 + len);
        cell = row.createCell(0);
        cell.setCellValue(headers.get(2));
        cell.setCellStyle(cellStyle);

        row = spreadsheet.createRow(4 + len);
        row.createCell(0).setCellValue(headers.get(3));
    }

    /**
     * 名为All的sheet中的数据灌入
     *
     * @param msg       数据列表
     * @param colNumber 文件列的数目
     * @param workbook  已打开的文件workbook
     */
    public void pageAll(List<String[]> msg, int colNumber, XSSFWorkbook workbook) {
        try {
            XSSFSheet spreadsheet = workbook.getSheet("All");
            input(msg, colNumber, spreadsheet, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void input(List<String[]> msg, int colNumber, XSSFSheet spreadsheet, int startRow) {
        Row row;
        Cell cell;
        String[] rowMsg;
        for (int i = startRow; i < startRow + msg.size(); i++) {
            row = spreadsheet.getRow(i);
            if (row == null) {
                row = spreadsheet.createRow(i);
            }
            rowMsg = msg.get(i - startRow);
            boolean flag = false;
            for (int j = 0; j < colNumber; j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                if (isNumeric(rowMsg[j])) {
                    // 若为数字类型
                    double temp = Double.parseDouble(rowMsg[j]);
                    cell.setCellValue(temp);
                    CellStyle style = cell.getCellStyle();
                    cell.setCellStyle(style);
                    // 解决科学计数法问题
                    DecimalFormat df = new DecimalFormat("0");
                    df.format(cell.getNumericCellValue());
                } else {
                    //不是数字类型
                    cell.setCellValue(rowMsg[j]);
                    CellStyle style = cell.getCellStyle();
                    if (rowMsg[j].charAt(rowMsg[j].length() - 1) == '%') {
                        double temp = Double.parseDouble(rowMsg[j].replaceAll("%", "")) / 100;
                        cell.setCellValue(temp);
                    }
                    cell.setCellStyle(style);
                }
                if ("N/A".equals(rowMsg[j])) {
                    flag = true;
                }
            }
            if (i != startRow) {
                if (flag) {
                    // 字体颜色
                    for (int j = 0; j < colNumber; j++) {
                        cell = row.getCell(j);
                        CellStyle style = cell.getCellStyle();
                        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                        cell.setCellStyle(style);
                    }
                } else {
                    for (int j = 0; j < colNumber; j++) {
                        cell = row.getCell(j);
                        CellStyle style = cell.getCellStyle();
                        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                        cell.setCellStyle(style);
                    }
                }
            }
        }
    }

    /**
     * 读取CSV中的信息
     *
     * @return 读取到的CSV文件中的值
     */
    public List<String[]> readCsv() {
        List<String[]> msg = null;
        String file = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\test\\MP Flagger.csv";
        try (InputStream input = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            msg = reader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 判断字符串是不是数字
     *
     * @param str 需要判断的字符串
     * @return 若为数字则返回true, 否则为false
     */
    public boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                if (str.charAt(i) != '.') {
                    return false;
                }
            }
        }
        return true;
    }
}
