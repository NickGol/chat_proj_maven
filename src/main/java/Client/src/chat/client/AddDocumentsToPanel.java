package chat.client;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class AddDocumentsToPanel {

    FileChooser fileChooser;
    List<File> prawnXMLFilesNew;

    public AddDocumentsToPanel() {
        fileChooser = new FileChooser();
        prawnXMLFilesNew = fileChooser.showOpenMultipleDialog(null);
        loadUserPhoto(prawnXMLFilesNew.get(0).getPath());
    }

    private ImageView loadUserPhoto(String userPhotoPath) {
        File file = new File(userPhotoPath);
        Image image = new Image("file:/" + userPhotoPath);
        System.out.println( image.getHeight() );
        System.out.println( image.getWidth() );
        ImageView photo = new ImageView(image);
        return photo;
    }
}
