package ospringo.worldsimulator.loader;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.jar.*;

public class JarLoader {

    /**
     * 加载JAR包并读出元数据
     * @param jarPath JAR包位置
     * @return 元数据及状态
     * @throws IOException
     * @throws MalformedParameterizedTypeException
     */
    public static Object[] loadJar(String jarPath) throws IOException, MalformedParameterizedTypeException {
        // JAR文件存在状态
        File file = new File(jarPath);
        if (!file.exists()) {
            throw new IOException("JAR file not found");
        }

        // 添加URL以便使用
        Method method;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e) {
            return new Object[]{false, null};
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

            // 读加载类
            JarFile jarFile = new JarFile(file);
            is = jarFile.getInputStream(jarFile.getEntry("META-INF/LOADER-NAME"));
            while (true) {
                byte[] r = new byte[1];
                is.read(r);
                loaderName.append(r[0]);
            }
        } catch (Exception e) {
            if (is != null) {
                return new Object[]{true, loaderName.toString()};
            }
        } finally {
            if (is != null) {
                is.close();
            }
            // 设回允许状态
            method.setAccessible(accessible);
        }
        return new Object[]{false, null};
    }
}
