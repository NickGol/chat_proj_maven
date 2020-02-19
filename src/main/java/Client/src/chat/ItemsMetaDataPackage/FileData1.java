package chat.ItemsMetaDataPackage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Objects;
import java.util.UUID;

/*@Component
@Scope(value = "prototype")
@Entity
@Table*/
public class FileData1 {

    /*transient Path path;
    transient File file;
    transient BasicFileAttributes basicFileAttributes;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String fileName;
    String filePath;
    UUID uuid = UUID.randomUUID();
    String uuidString = uuid.toString();
    byte[] imageByteArray;

    @Value("${ui.title:JavaFX приложение}")//
    private String windowTitle;

    transient FileTime creationTime;
    transient FileTime modifyingTime;

    //@PostConstruct
    public FileData1() {
        this.fileName = "fileName";
        this.filePath = "filePath";

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

    public FileData1(Path path) {
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
*/
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
