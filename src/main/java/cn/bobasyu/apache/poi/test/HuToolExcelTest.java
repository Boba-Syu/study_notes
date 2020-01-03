package cn.bobasyu.apache.poi.test;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.opencsv.CSVReader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Boba
 */
public class HuToolExcelTest {
    public static void main(String[] args) {
        HuToolExcelTest huToolExcelTest = new HuToolExcelTest();
        huToolExcelTest.copyExcel();
    }

    public void copyExcel() {
        ExcelReader reader = ExcelUtil.getReader("C:\\IdeaProjects\\study_notes\\src\\main\\java\\cn\\bobasyu\\apache\\poi\\test\\flagger_template_new.xlsx",2);
        List<List<Object>> readAll = reader.read();
        ExcelWriter writer = ExcelUtil.getWriterWithSheet("C:\\IdeaProjects\\study_notes\\src\\main\\java\\cn\\bobasyu\\apache\\poi\\test\\new.xlsx");
        List<List<String>> msgList = readCsv();
        writer.write(readAll, true);
        writer.close();
    }

    /**
     * 读取CSV中的信息
     *
     * @return 读取到的CSV文件中的值
     */
    public List<List<String>> readCsv() {
        List<List<String>> msgList = null;
        String file = "src\\main\\java\\cn\\bobasyu\\apache\\poi\\test\\MP Flagger.csv";
        try (InputStream input = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            List<String[]> msg = reader.readAll();
            msgList = msg.stream()
                    .map(strings -> new ArrayList<>(Arrays.asList(strings)))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgList;
    }
}
