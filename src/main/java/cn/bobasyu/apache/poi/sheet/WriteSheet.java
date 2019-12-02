package cn.bobasyu.apache.poi.sheet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * 望数据表写入数据
 *
 * @author Boba
 */
public class WriteSheet {
    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(" Employee Info ");
        XSSFRow row;

        Map<String, Object[]> empinfo = new TreeMap<>();
        empinfo.put("1", new Object[]{"EMP ID", "EMP NAME", "DESIGNATION"});
        empinfo.put("2", new Object[]{"tp01", "Gopal", "Technical Manager"});
        empinfo.put("3", new Object[]{"tp02", "Manisha", "Proof Reader"});
        empinfo.put("4", new Object[]{"tp03", "Masthan", "Technical Writer"});
        empinfo.put("5", new Object[]{"tp04", "Satish", "Technical Writer"});
        empinfo.put("6", new Object[]{"tp05", "Krishna", "Technical Writer"});

        int rowid = 0;
        for (String key : empinfo.keySet()) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = empinfo.get(key);
            int cellid = 0;
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }

        String path = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\sheet\\writeSheet.xlsx";
        FileOutputStream out = new FileOutputStream(new File(path));
        workbook.write(out);
        out.close();
        System.out.println("Done.");
    }

}
