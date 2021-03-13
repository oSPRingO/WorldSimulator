package ospringo.worldsimulator.loader;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.jar.*;
import com.google.gson.Gson;

public class JarLoader {

    public static Meta load(String jarPath) throws IOException, MalformedParameterizedTypeException {
        boolean result = loadJar(jarPath);
        if (result) {
            JarFile file = new JarFile(jarPath);
            JarEntry entry = (JarEntry) file.getEntry("META-INF/plugin.json");
            InputStream in = file.getInputStream(entry);
            byte[] b = new byte[Math.toIntExact(entry.getSize())];
            in.read(b);
            String s = new String(b);
            Gson gson = new Gson();
            Meta meta = gson.fromJson(s, Meta.class);
            return meta;
        } else {
            return null;
        }
    }

    /**
     * 加载JAR包
     * @param jarPath JAR包位置
     * @return 状态
     * @throws IOException
     * @throws MalformedParameterizedTypeException
     */
    private static boolean loadJar(String jarPath) throws IOException, MalformedParameterizedTypeException {
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
