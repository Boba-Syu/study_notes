package cn.bobasyu.apache.poi.test;

import com.opencsv.CSVReader;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 根据Excel模板灌入CSV数据
 *
 * @author Boba
 */
public class FlaggedTemplateExcel {
    /**
     * Highlight页表头前的信息
     */
    private final List<String> headers;
    private int colNumber;

    public FlaggedTemplateExcel() {
        headers = new ArrayList<>();
        headers.add("Best Companies (Y/Y trends have been accelerating for the latest 2 quarters)");
        headers.add("Note: Excludes stocks with annualized sales of less than 100m RMB");
        headers.add("Worst Companies (Y/Y trends have been decelerating for the latest 2 quarters)");
        headers.add("Note: Excludes stocks with annualized sales of less than 100m RMB");
    }

    public static void main(String[] args) {
        String fromExcel = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\test\\flagger_template.xlsx";
        String newExcel = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\test\\new.xlsx";
        FlaggedTemplateExcel flaggedTemplateExcel = new FlaggedTemplateExcel();
        List<String[]> msg = flaggedTemplateExcel.readCsv();
        flaggedTemplateExcel.inputMsg(msg, fromExcel, newExcel);
    }

    /**
     * 将表头换成红底白字
     *
     * @param workbook 已打开的workbook
     * @param n        表头所在行数
     */
    private void addHeaderColor(Workbook workbook, int[] n) {
        int num = this.colNumber;
        Sheet spreadsheet = workbook.getSheet("Highlight");
        CellStyle cellsStyle = workbook.createCellStyle();
        cellsStyle.cloneStyleFrom(workbook.getSheet("All").getRow(0).getCell(0).getCellStyle());

        for (int r : n) {
            Row row = spreadsheet.getRow(r);
            IntStream.rangeClosed(0, num - 1).forEach(i -> {
                Cell cell = row.getCell(i);
                cell.setCellStyle(cellsStyle);
            });
        }
    }

    /**
     * 灌入数据
     *
     * @param msg       数据列表
     * @param fromExcel 模板文件名
     * @param newExcel  生成的文件名
     */
    private void inputMsg(List<String[]> msg, String fromExcel, String newExcel) {
        String[] header = msg.get(0);
        int colNumber = header.length;
        try (FileInputStream fis = new FileInputStream(fromExcel); FileOutputStream fos = new FileOutputStream(newExcel)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            List<String[]> msg2 = this.readCsv();
            int n = pageHighLight(msg2, colNumber, workbook);
            pageAll(msg, colNumber, workbook);
            addHeaderColor(workbook, new int[]{2, n});
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
    private int pageHighLight(List<String[]> msg, int colNumber, XSSFWorkbook workbook) {
        this.colNumber = colNumber;
        XSSFSheet spreadsheet = workbook.getSheet("Highlight");
        List<String[]> upMsg = new ArrayList<>();
        upMsg.add(msg.get(0));
        upMsg.addAll(msg.stream()
                .filter(strings -> "↑".equals(strings[colNumber - 1]) && "↑".equals(strings[colNumber - 2]))
                .collect(Collectors.toList()));

        List<String[]> downMsg = new ArrayList<>();
        downMsg.add(msg.get(0));
        downMsg.addAll(msg.stream()
                .filter(strings -> "↓".equals(strings[colNumber - 1]) && "↓".equals(strings[colNumber - 2]))
                .collect(Collectors.toList()));

        try {
            input(upMsg, colNumber, spreadsheet, 2, workbook);
            addHeader(spreadsheet, upMsg.size(), workbook);
            input(downMsg, colNumber, spreadsheet, upMsg.size() + 5, workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return upMsg.size() + 5;
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
        // upMsg表头前信息
        row = spreadsheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(headers.get(1));
        // 空行
        row = spreadsheet.createRow(2 + len);
        row.createCell(0).setCellValue("");
        // downMsg表头前信息
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
    private void pageAll(List<String[]> msg, int colNumber, XSSFWorkbook workbook) {
        try {
            XSSFSheet spreadsheet = workbook.getSheet("All");
            input(msg, colNumber, spreadsheet, 0, workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据
     *
     * @param msg         要插入的信息
     * @param colNumber   列的数目
     * @param spreadsheet 要插入的sheet
     * @param startRow    从sheet的第startRow行开始插入
     */
    private void input(List<String[]> msg, int colNumber, XSSFSheet spreadsheet, int startRow, Workbook workbook) {
        IntStream.rangeClosed(startRow, startRow + msg.size() - 1).forEach(i -> {
            Cell cell;
            String[] rowMsg;
            Row row = spreadsheet.getRow(i);
            if (row == null) {
                row = spreadsheet.createRow(i);
            }
            rowMsg = msg.get(i - startRow);
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
                    // 百分比类型的数字
                    if (rowMsg[j].charAt(rowMsg[j].length() - 1) == '%') {
                        double temp = Double.parseDouble(rowMsg[j].replaceAll("%", "")) / 100;
                        cell.setCellValue(temp);
                    }
                    cell.setCellStyle(style);
                }
            }

            boolean flag = "n/a".equals(rowMsg[colNumber - 6].toLowerCase()) || "nan".equals(rowMsg[colNumber - 6].toLowerCase());

            if (i != startRow) {
                // 字体颜色
                for (int j = 0; j < colNumber; j++) {
                    cell = row.getCell(j);
                    CellStyle style = workbook.createCellStyle();
                    style.cloneStyleFrom(cell.getCellStyle());

                    if (flag) {
                        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    } else {
                        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                    }

                    if ("↑".equals(rowMsg[j])) {
                        Font font = workbook.createFont();
                        font.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
                        font.setFontName("等线");
                        style.setFont(font);
                    } else if ("↓".equals(rowMsg[j])) {
                        Font font = workbook.createFont();
                        font.setFontName("等线");
                        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
                        style.setFont(font);
                    }
                    cell.setCellStyle(style);
                }
            }
        });
    }

    /**
     * 读取CSV中的信息
     *
     * @return 读取到的CSV文件中的值
     */
    private List<String[]> readCsv() {
        List<String[]> msg = null;
        String file = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\test\\jesse—test.csv";
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
        return IntStream.rangeClosed(0, str.length() - 1).allMatch(i -> {
            char dot = '.';
            return str.charAt(i) == dot || Character.isDigit(str.charAt(i));
        });
    }
}
