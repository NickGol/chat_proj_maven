package chat.ItemsMetaDataPackage;

import javax.persistence.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table
public class FileDataNew {

    transient Path path;
    transient BasicFileAttributes basicFileAttributes;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String fileNameOriginal;
    String filePathOriginal;
    String fileNameRepo;
    String filePathRepo;
    String uuidMessageString;
    UUID uuidMessage;
    byte[] imageByteArray;
    String creationTime;
    String modifyingTime;

    public FileDataNew() {
        this.fileNameOriginal = path.getFileName().toString();
        this.filePathOriginal = path.getParent().toString();

        

        try(FileInputStream inputStream = new FileInputStream("/home/kolka/Downloads/foto.png")) {
            byte[] img = new byte[inputStream.available()];
            inputStream.read(img);
            imageByteArray = img;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileDataNew(Path path) {
        this.path = path;
        BasicFileAttributes attr;
        try {
            basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            creationTime = basicFileAttributes.creationTime();
            modifyingTime = basicFileAttributes.lastModifiedTime();
            fileNameOriginal = path.getFileName().toString();
            filePathOriginal = path.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileDataNew fileData = (FileDataNew) o;
        return fileNameOriginal.equals(fileData.fileNameOriginal) &&
                filePathOriginal.equals(fileData.filePathOriginal) &&
                creationTime.equals(fileData.creationTime) &&
                modifyingTime.equals(fileData.modifyingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileNameOriginal, filePathOriginal, creationTime, modifyingTime);
    }

    /*@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    String fileName;
    String filePath;


    public FileData() {
        this.fileName = "fileName";
        this.filePath = "filePath";
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

    /*public FileTime getCreationTime() {
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
    }*/
}
