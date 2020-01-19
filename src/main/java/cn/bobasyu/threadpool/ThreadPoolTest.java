package cn.bobasyu.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java线程池学习
 *
 * @author Boba
 */
public class ThreadPoolTest {
    public static void main(String[] args) {

    }

    public static ExecutorService cachedThreadPoolTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        return cachedThreadPool;
    }
}
