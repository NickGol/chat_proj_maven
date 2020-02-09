package ItemsMetaDataPackage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class OpenFileItem implements ItemsMetaData {

    final String description = "Container";
    private Path path;
    private FileData fileData;

    public OpenFileItem(Path path) {
        this.path = path;
        fileData = new FileData(path);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenFileItem that = (OpenFileItem) o;
        return description.equals(that.description) &&
                path.equals(that.path) &&
                fileData.equals(that.fileData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, path, fileData);
    }
}
