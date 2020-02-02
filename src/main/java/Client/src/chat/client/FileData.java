package chat.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class FileData {

    URL url;
    String name;

    public FileData(URL url, String name) {
        this.url = url;
        this.name = name;
    }
    public FileData(String name) {
        this.name = name;
    }

    public void process() {
        Runtime re = Runtime.getRuntime();
        try {
            //String str = url.getPath().toString();
            //String str1 = url.toURI().toString();
            re.exec("cmd /C " + url.getPath().toString().substring(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
