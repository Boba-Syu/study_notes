package cn.bobasyu.serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;

/**
 * 序列化学习
 *
 * @author Boba
 */
@Data
@AllArgsConstructor
public class SerializableTest implements Serializable {
    private static final long serialVersionUID = 1204288856570130195L;
    private int id;
    private String name;

    /**
     * 序列化操作
     */
    public static void out() {
        String filePath = "C:\\IdeaProjects\\study_notes\\src\\main\\java\\cn\\bobasyu\\serializable\\result.txt";
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            SerializableTest test = new SerializableTest(1, "boba");
            oos.writeObject(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void in() {
        String filePath = "C:\\IdeaProjects\\study_notes\\src\\main\\java\\cn\\bobasyu\\serializable\\result.txt";
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            SerializableTest test = (SerializableTest) ois.readObject();
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        out();
        in();
    }
}
