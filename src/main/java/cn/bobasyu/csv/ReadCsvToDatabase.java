package cn.bobasyu.csv;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取csv插入到数据库中
 *
 * @author Boba
 */
@Slf4j
public class ReadCsvToDatabase {

    /**
     * 获取数据库连接
     *
     * @return 得到的数据路连接
     */
    public static Connection getConnect() {
        String url = "jdbc:mysql://localhost/test?characterEncoding=utf-8";
        String user = "root";
        String password = "123456A";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对数据列表进行操作, 将数据分成5000条一组, 对分组后的数据进行操作操作数据
     *
     * @param msgList 需要操作的数据类表
     */
    public static void singleThreadTest(List<String[]> msgList) {
        List<List<String[]>> threadsMsg = new ArrayList<>();
        List<String[]> inputMsg = new ArrayList<>();
        int len = msgList.size();
        for (int i = 1; i < len; i++) {
            inputMsg.add(msgList.get(i));
            if (i % 5000 == 0) {
                threadsMsg.add(inputMsg);
                inputMsg = new ArrayList<>();
            }
        }
        threadsMsg.add(inputMsg);
        threadsMsg.parallelStream().forEach(ReadCsvToDatabase::inputMsgBySingleThread);
    }

    /**
     * 单个线程的操作信息
     * 将列表中的信息插入数据库
     *
     * @param msgList 线程操作的列表
     */
    public static void inputMsgBySingleThread(List<String[]> msgList) {
        String insert = "insert into test values(?,?,?,?,?,?,?,?,?,?,?,?)";
        int len = msgList.get(0).length;
        try (Connection conn = getConnect();
             PreparedStatement ps = conn.prepareStatement(insert)) {
            for (String[] msg : msgList) {
                for (int i = 0; i < len; i++) {
                    ps.setObject(i + 1, msg[i]);
                }
                ps.addBatch();
            }
            int[] rowNum = ps.executeBatch();
            log.info("已插入" + rowNum.length + " 条.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取CSV文件
     *
     * @param file CSV文件的相路径
     * @return 读取的文件信息
     */
    public static List<String[]> readCsv(String file) {
        List<String[]> fileMsg = null;
        try (InputStream input = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            fileMsg = reader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileMsg;
    }

    public static void main(String[] args) {
        log.info("start.");
        LocalTime start = LocalTime.now();
        String file = "src\\main\\java\\cn\\bobasyu\\csv\\Sandalwood_JD_All Sector_Data up to Dec 2019_20200114.csv";
        List<String[]> fileMsg = readCsv(file);
        singleThreadTest(fileMsg);
        LocalTime end = LocalTime.now();
        log.info("Done.");
        int hour = end.getHour() - start.getHour();
        int min = end.getMinute() - start.getMinute();
        int sec = end.getSecond() - start.getSecond();
        log.info("耗时为" + hour + "时" + min + "分" + sec + "秒.");
    }
}
