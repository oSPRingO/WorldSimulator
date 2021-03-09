package ospringo.worldsimulator.loader;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.jar.*;

public class JarLoader {

    /**
     * 加载JAR包
     * @param jarPath JAR包位置
     * @return 状态
     * @throws IOException
     * @throws MalformedParameterizedTypeException
     */
    public static boolean loadJar(String jarPath) throws IOException, MalformedParameterizedTypeException {
        // JAR文件存在状态
        File file = new File(jarPath);
        if (!file.exists()) {
            throw new IOException("JAR file not found");
        }

        // 添加URL以便使用
        Method method;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (Exception e) {
            return false;
        }

        boolean accessible = method.isAccessible();
        StringBuilder loaderName = new StringBuilder();
        InputStream is = null;
        try {
            // 设置允许状态
            if (!accessible) {
                method.setAccessible(true);
            }

            // 准备加载所用数据
            URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            URL url = file.toURI().toURL();

            // 加载
            method.invoke(loader, url);
            return true;
        } catch (Exception ignore) { } finally {
            // 设回允许状态
            method.setAccessible(accessible);
        }
        return false;
    }
}
