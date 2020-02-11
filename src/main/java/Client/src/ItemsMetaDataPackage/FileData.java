package ItemsMetaDataPackage;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Objects;

public class FileData {

    transient Path path;
    transient File file;
    transient BasicFileAttributes basicFileAttributes;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    String fileName;
    String filePath;
    FileTime creationTime;
    FileTime modifyingTime;

    public FileData() {
        this.fileName = "fileName";
        this.filePath = "filePath";
    }

    public FileData(Path path) {
        this.path = path;
        BasicFileAttributes attr;
        try {
            basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            creationTime = basicFileAttributes.creationTime();
            modifyingTime = basicFileAttributes.lastModifiedTime();
            fileName = path.getFileName().toString();
            filePath = path.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileData fileData = (FileData) o;
        return fileName.equals(fileData.fileName) &&
                filePath.equals(fileData.filePath) &&
                creationTime.equals(fileData.creationTime) &&
                modifyingTime.equals(fileData.modifyingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, filePath, creationTime, modifyingTime);
    }

}
