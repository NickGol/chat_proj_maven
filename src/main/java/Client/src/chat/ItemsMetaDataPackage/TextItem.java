package chat.ItemsMetaDataPackage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class TextItem implements ItemsMetaData, MessageItemInterface {

    final String description = "Container";
    private Path path;
    private FileData fileData;

    public TextItem(Path path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public String getFilePath() {
        return path.toString();
    }


    public void process() {
    }

    @Override
    public void saveToDataBase() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextItem that = (TextItem) o;
        return description.equals(that.description) &&
                path.equals(that.path) &&
                fileData.equals(that.fileData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, path, fileData);
    }
}
