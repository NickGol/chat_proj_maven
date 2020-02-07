package chat.client;

import java.io.IOException;
import java.nio.file.Path;

public class OpenFileItem implements ItemsMetaData {

    final String description = "Container";
    Path path;

    public OpenFileItem(Path path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }


    public void process() {

        Runtime runtime = Runtime.getRuntime();
        try {
            String os = System.getProperty("os.name").toLowerCase();
            System.out.println(os);
            String[] cmd;
            if( os.startsWith("windows") ) {
                cmd = new String[] { "cmd /C ", path.toString() };
                runtime.exec(cmd);
            }
            if( os.equals("linux") ) {
                cmd = new String[] { "xdg-open", path.toString() };
                runtime.exec(cmd);
            }
            else throw new RuntimeException("Данная операционная система не рекомендуется для выполнения приложения");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
