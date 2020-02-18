package chat.ItemsMetaDataPackage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.UUID;

@Component
@Scope("prototype")
@Entity
@Table
public class FileData {

    transient private Path path;
    transient private BasicFileAttributes basicFileAttributes;
    @Value("${constant.filePathRepoWindows : default value}")
    private String filePathRepoWindows;
    @Value("${constant.filePathRepoLinux : default value}")
    private String filePathRepoLinux;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String fileNameOriginal;
    private String filePathOriginal;
    private String fileNameRepo;
    private String filePathRepo;
    private String uuidMessageString;
    private UUID uuidMessage;
    private byte[] imageByteArray = null;
    private String creationTime;
    private String modifyingTime;

    public FileData() {
    }

    public void setPath(Path path) {
        this.path = path;
        try {
            basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            creationTime = basicFileAttributes.creationTime().toString();
            modifyingTime = basicFileAttributes.lastModifiedTime().toString();
            fileNameOriginal = path.getFileName().toString();
            filePathOriginal = path.getParent().toString();
            uuidMessage = UUID.randomUUID();
            uuidMessageString = uuidMessage.toString();
            fileNameRepo = uuidMessageString;
            defineTempFolder();
        } catch (IOException e) {
            e.printStackTrace(); // TODO: Rewrite exception handler
        }
    }

    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }

    private void defineTempFolder() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("windows")) {
            filePathRepo = filePathRepoWindows;
        } else if (os.equals("linux")) {
            filePathRepo = filePathRepoLinux;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileData fileData = (FileData) o;
        return fileNameOriginal.equals(fileData.fileNameOriginal) &&
                filePathOriginal.equals(fileData.filePathOriginal) &&
                creationTime.equals(fileData.creationTime) &&
                modifyingTime.equals(fileData.modifyingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileNameOriginal, filePathOriginal, creationTime, modifyingTime);
    }
}
