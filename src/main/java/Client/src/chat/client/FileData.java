package chat.client;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

public class FileData {

    Path path;
    String name;
    File file;

    public FileData(Path path, File file) {
        this.path = path;
        this.file = file;
    }

    public void process() {
        Runtime runtime = Runtime.getRuntime();
        try {
            String os = System.getProperty("os.name").toLowerCase();
            System.out.println(os);
            String[] cmd;
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
        }
    }
}
