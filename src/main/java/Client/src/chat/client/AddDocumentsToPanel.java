package chat.client;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddDocumentsToPanel {

    FileChooser fileChooser;
    List<File> selectedFiles;
    static final List<String> imageExtensions = Arrays.asList("jpg", "jpeg", "bmp", "gif", "png");

    public static void main(String[] args) {
        AddDocumentsToPanel addDocumentsToPanel = new AddDocumentsToPanel();
        //final File folder = new File("out/production/Client/FileIcons/png");
        //addDocumentsToPanel.listFilesInFolder(folder);
        //addDocumentsToPanel.selectFiles();
    }
    public AddDocumentsToPanel() {

    }

    public List<ImageView> selectFiles() throws MalformedURLException {
        List<ImageView> list = new LinkedList<>();
        fileChooser = new FileChooser();
        selectedFiles = fileChooser.showOpenMultipleDialog(null);
        for( File file : selectedFiles) {
            //selectImageToShow(file);
            list.add(  loadUserPhoto( selectImageToShow(file) )  );
        }
        return list;
    }

    private ImageView loadUserPhoto(URL userPhotoPath) {

        //File file = new File(userPhotoPath);
        //Image image = new Image("file:/" + userPhotoPath);
        Image image = new Image(userPhotoPath.toString(), 100, 100, true, true);
        System.out.println( image.getHeight() );
        System.out.println( image.getWidth() );
        ImageView photo = new ImageView(image);
        return photo;
    }

    private URL selectImageToShow(File fileToShow) throws MalformedURLException {

        int dotIndex = fileToShow.getPath().lastIndexOf(".");
        String extension = fileToShow.getPath().substring(dotIndex+1);
        if(imageExtensions.contains(extension))
            return fileToShow.toURI().toURL();

        File directoryWithIcons = new File("out/production/Client/FileIcons/png");
        File unknownFormat = new File("out/production/Client/FileIcons/png/unknownFormat.png");
        List<File> filesList = Arrays.asList( directoryWithIcons.listFiles() );

        for ( File file : filesList) {
            if ( file.getName().equals(extension+".png") ) {
                return file.toURI().toURL();
            }
        }
        return unknownFormat.toURI().toURL();

    }

    private void readRegisteredTypesOfDocuments() {

    }

    public void listFilesInFolder(final File folder) {
        List<String> filesArray = Arrays.asList( folder.list() );
        System.out.println( filesArray.contains("doc.png") );
    }
}
