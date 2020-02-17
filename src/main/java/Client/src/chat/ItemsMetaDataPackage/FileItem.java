package chat.ItemsMetaDataPackage;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class FileItem implements ItemsMetaData, MessageItemInterface {

    final String description = "Container";
    private Path path;
    private FileData fileData;

    @Autowired
    FileDataNew fileDataNew;

    public FileItem(Path path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public String getFilePath() {
        return path.toString();
    }


    public void process() {

        Runtime runtime = Runtime.getRuntime();
        try {
            String os = System.getProperty("os.name").toLowerCase();
            System.out.println(os);
            String[] cmd;
            if( os.startsWith("windows") ) {
                cmd = new String[] { "cmd /C " + path.toString() };
                System.out.println(Arrays.toString(cmd));
                runtime.exec(cmd[0]);
            }
            else if( os.equals("linux") ) {
                cmd = new String[] { "xdg-open", path.toString() };
                runtime.exec(cmd);
            }
            else throw new RuntimeException("Данная операционная система не рекомендуется для выполнения приложения");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveToDataBase() {
        fileDataNew.setPath(path);
        fileDataNew.setImageByteArray(null); //TODO: Add image data

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileItem that = (FileItem) o;
        return description.equals(that.description) &&
                path.equals(that.path) &&
                fileData.equals(that.fileData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, path, fileData);
    }
}
