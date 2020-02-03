package chat.client;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class FileData {

    URL url;
    String name;
    File file;

    public FileData(URL url, String name, File file) {
        this.url = url;
        this.name = name;
        this.file = file;
    }

    public FileData(URL url, String name) {
        this.url = url;
        this.name = name;
    }
    public FileData(String name) {
        this.name = name;
    }

    public void process() {
        Runtime runtime = Runtime.getRuntime();
        try {
            String os = System.getProperty("os.name").toLowerCase();
            System.out.println(os);
            String[] cmd;
            //String str = url.getPath().toString();
            //String str1 = url.toURI().toString();
            //runtime.exec("cmd /C " + url.getPath().toString().substring(1));
            //runtime.exec("xdg-open " + url.getPath().toString());
            if( os.equals("win") ) {
                cmd = new String[] { "cmd /C ", file.getPath() };
                runtime.exec(cmd);
            }
            if( os.equals("linux") ) {
                cmd = new String[] { "xdg-open", file.getPath() };
                runtime.exec(cmd);
            }
            else throw new RuntimeException("Данная операционная система не рекомендуется для выполнения приложения");

        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
    }
}
