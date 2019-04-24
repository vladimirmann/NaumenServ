package Creation;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static String  pathPDF=System.getenv("PathPDFConfig");
    private static FileInputStream fileInputStream;
    private static Properties PROPERTIES;
    static {
        try {
System.out.println();
            fileInputStream = new FileInputStream(pathPDF);
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getTestProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
