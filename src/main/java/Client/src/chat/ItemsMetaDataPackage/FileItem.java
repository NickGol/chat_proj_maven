package chat.ItemsMetaDataPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Component
public class FileItem implements ItemsMetaData, MessageItemInterface {

    final String description = "Container";
    private Path path;
    private FileData fileData;
    private UUID uuidMessage = UUID.randomUUID(); //TODO: Create setter method

    @Autowired
    private FileDataNewRepository fileDataNewRepository;
    @Autowired
    private MessageItemDataRepository messageItemDataRepository;


    @Autowired
    FileData fileDataNew;
    @Autowired
    MessageItemData messageItemData;

    //@Autowired

    public FileItem() {
    }

    public FileItem(Path path/*, FileDataNewRepository repository*/) {
        this.path = path;
        /*this.fileDataNewRepository = repository;*/
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
        fileDataNewRepository.save(fileDataNew);

        messageItemData.setChatName("Chat 55");
        messageItemData.setSender("Me");
        Calendar  calendar = new GregorianCalendar();
        messageItemData.setTimeOfMessage( calendar.getTime().toString() );
        messageItemData.setUuidMessage(uuidMessage);
        messageItemData.setValue("File");
        messageItemDataRepository.save(messageItemData);
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

    public void setPath(Path path) {
        this.path = path;
    }
}
