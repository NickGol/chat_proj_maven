package chat.client;

import chat.ItemsMetaDataPackage.DeleteItem;
import chat.ItemsMetaDataPackage.FileItem;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
view VBox
        StackPane
            Label
                IconView (Labeled)
            Label (close)
 */
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

    public List<Node> selectFiles() throws MalformedURLException {
        List<Node> list = new LinkedList<>();
        Node vBox = null;
        Path path = null;
        fileChooser = new FileChooser();
        selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if( selectedFiles != null) {
            for (File file : selectedFiles) {
                BasicFileAttributes attr;
                Map<String, Object> map = null;

                path = file.toPath();
                try {
                    attr = Files.readAttributes(path, BasicFileAttributes.class);
                    //map = Files.readAttributes(path, String.valueOf(String.class));
                    System.out.println(String.valueOf(String.class));
                    System.out.println(attr.creationTime());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //selectImageToShow(file);
                vBox = wrapInVBox(file, loadUserPhoto(selectImageToShow(file)));
                //vBox.setUserData(new FileData(path, file));
                vBox.setUserData( new FileItem(path) );
                list.add(vBox);
                //list.add(  wrapInVBox( file, loadUserPhoto( selectImageToShow(file) ) )  );
            }
        }
        return list;
    }

    private ImageView loadUserPhoto(URL userPhotoPath) {

        //File file = new File(userPhotoPath);
        //Image image = new Image("file:/" + userPhotoPath);
        Image image = new Image(userPhotoPath.toString(), 75, 75, false, true);
        System.out.println( image.getHeight() );
        System.out.println( image.getWidth() );
        //saveToFile(image);

        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        File file = new File("file.png");
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            fos.write( byteArrayOutputStream.toByteArray() );
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }





        ImageView photo = new ImageView(image);
        //photo.setUserData( new FileData( userPhotoPath, "qqqqq") );
        return photo;
    }

    public static void saveToFile(Image image) {
        File outputFile = new File("swing.png");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        //WritableRaster raster = bImage.getRaster();
        //DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        /*vBox
        switch (o instanceof Object )
            case */


    }

    private VBox wrapInVBox(File file, ImageView fileIcon) {

        VBox itemExternalСontainer = new VBox();
        StackPane stackPane = new StackPane();
        Label deleteLabel = new Label("X");
        Label iconLabel = new Label(file.getName(), fileIcon);

        iconLabel.setContentDisplay(ContentDisplay.TOP);
        iconLabel.setWrapText(true);
        iconLabel.getStyleClass().add("user-file-stack");

        deleteLabel.getStyleClass().add("file-delete-label");
        deleteLabel.setAlignment(Pos.CENTER);
        deleteLabel.setUserData( new DeleteItem( itemExternalСontainer ) );

        stackPane.setAlignment(Pos.TOP_RIGHT);
        stackPane.getChildren().addAll(iconLabel, deleteLabel);

        itemExternalСontainer.setMaxWidth(105);
        itemExternalСontainer.getStylesheets().add("UI/Styles.css");
        itemExternalСontainer.getStyleClass().add("user-file-item");
        itemExternalСontainer.setAlignment(Pos.TOP_CENTER);
        itemExternalСontainer.getChildren().add(stackPane);
        return itemExternalСontainer;
    }
    private void readRegisteredTypesOfDocuments() {

    }

    public void listFilesInFolder(final File folder) {
        List<String> filesArray = Arrays.asList( folder.list() );
        System.out.println( filesArray.contains("doc.png") );
    }
}
