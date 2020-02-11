package ItemsMetaDataPackage;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Objects;

@Entity
@Table
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

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public BasicFileAttributes getBasicFileAttributes() {
        return basicFileAttributes;
    }

    public void setBasicFileAttributes(BasicFileAttributes basicFileAttributes) {
        this.basicFileAttributes = basicFileAttributes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(FileTime creationTime) {
        this.creationTime = creationTime;
    }

    public FileTime getModifyingTime() {
        return modifyingTime;
    }

    public void setModifyingTime(FileTime modifyingTime) {
        this.modifyingTime = modifyingTime;
    }
}
