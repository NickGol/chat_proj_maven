package chat.client;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
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

    public List<VBox> selectFiles() throws MalformedURLException {
        List<VBox> list = new LinkedList<>();
        VBox vBox = null;
        fileChooser = new FileChooser();
        selectedFiles = fileChooser.showOpenMultipleDialog(null);
        for( File file : selectedFiles) {
            //selectImageToShow(file);
            vBox = wrapInVBox( file, loadUserPhoto( selectImageToShow(file) ) );
            vBox.setUserData(  new FileData( file.toURI().toURL(), file.getName(), file )  );
            list.add( vBox );
            //list.add(  wrapInVBox( file, loadUserPhoto( selectImageToShow(file) ) )  );
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
        //photo.setUserData( new FileData( userPhotoPath, "qqqqq") );
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

    private VBox wrapInVBox(File file, ImageView fileIcon) {
        VBox vBox = new VBox();
        vBox.setMaxWidth(105);
        vBox.getStylesheets().add("UI/Styles.css");
        vBox.getStyleClass().add("user-file-item");
        vBox.setAlignment(Pos.TOP_CENTER);
        /*Label fileName = new Label(file.getName());
        //fileName.setMaxWidth();
        //fileName.setPrefWidth(10);
        fileName.setWrapText(true);
        //fileName.setStyle("-fx-background-color : green");
        vBox.getChildren().addAll(fileIcon, fileName);*/

        Label label = new Label(file.getName(), fileIcon);
        label.setContentDisplay(ContentDisplay.TOP);
        label.setWrapText(true);
        vBox.getChildren().add(label);
        return vBox;
    }
    private void readRegisteredTypesOfDocuments() {

    }

    public void listFilesInFolder(final File folder) {
        List<String> filesArray = Arrays.asList( folder.list() );
        System.out.println( filesArray.contains("doc.png") );
    }
}
