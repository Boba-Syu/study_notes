package cn.bobasyu.file;

import org.joda.time.LocalDate;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 递归查找目录下的文件, 并获取其修改时间
 *
 * @author Boba
 */
public class CheckFixedTime {
    public static void main(String[] args) {
        // filePath = "C:\\Users\\19148\\Dropbox\\SandalwoodFileCenter\\1. SFTP";
        String filePath = "C:\\Program Files (x86)";
        List<File> fileList = new ArrayList<>();
        getFileList(filePath, fileList);
        fileList.parallelStream().filter(file -> {
            LocalDate date = new LocalDate(file.lastModified());
            LocalDate today = LocalDate.now();
            // 同年大于六个月
            return (today.getMonthOfYear() - date.getMonthOfYear() >= 6 && date.getYear() == today.getYear())
                    // 差一年大于六个月
                    || (today.getMonthOfYear() + 12 - date.getMonthOfYear() >= 6 && today.getYear() - date.getYear() == 1)
                    // 相差超过一年
                    || (today.getYear() - date.getYear() > 1);
        }).forEach(file -> System.out.println(file.getName() + "\t" + new LocalDate(file.lastModified())));
    }

    /**
     * 递归获取路径下的所有文件
     *
     * @param path     文件路径
     * @param fileList 返回的文件列表
     */
    public static void getFileList(String path, List<File> fileList) {
        File dir = new File(path);
        // 获取文件夹下的文件列表
        File[] files = dir.listFiles();
        if (files != null) {
            List<File> list = new ArrayList<>(Arrays.asList(files));
            for (File file : list) {
                if (file.isDirectory()) {
                    getFileList(file.getAbsolutePath(), fileList);
                } else {
                    fileList.add(file);
                }
            }
        }
    }
}
