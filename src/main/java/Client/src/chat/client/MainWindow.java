package chat.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Observable;


public class MainWindow extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    Scene scene;
    BorderPane border;
    VBox userFriendsItemsContainer;
    ScrollPane userFriendsItemsScrollPane;



    @Override
    public void start(Stage primaryStage) throws Exception {

        scene = createScene1();
        scene.getStylesheets().add("UI/Styles.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Отладка информации отображения о пользователе");
        primaryStage.setWidth(800);
        //primaryStage.setHeight(500);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(535);
        //primaryStage.setMaximized(true);
        //primaryStage.getScene().getStylesheets().add("polynomial/Styles.css");

        primaryStage.show();
    }
    VBox vBox;
    private Scene createScene1() {
        // Use a border pane as the root for scene
        border = new BorderPane();
        Button addFilesButton = new Button("Прикрепить файлы");
        border.setRight(addFilesButton);
        MessageBuilder messageBuilder = MessageBuilder.getMessageBuilder();
        vBox = new VBox();
        /*ObservableList<Node> list1 = messageBuilder.getEmptyMessage();*/
        messageBuilder.setObservableList(vBox.getChildren());
        messageBuilder.getEmptyMessage();
        //vBox.getChildren() = messageBuilder.getEmptyMessage();
        addFilesButton.setOnMouseReleased( (value) -> {
            messageBuilder.addFiles();
            System.out.println("Append files");

        });
        //vBox.getChildren().addAll( messageBuilder.addFiles() );

        border.setCenter( vBox );

        return new Scene(border);
    }

    private Scene createScene() {
        // Use a border pane as the root for scene
        border = new BorderPane();
        UserFriendItemInfo userFriendItemInfo = new UserFriendItemInfo("usersPhoto/512.png",
                "В сети", "Пользователь Пользователь", "Голосовое сообщение",
                "20.20.2020");
        userFriendsItemsContainer = new VBox();
        userFriendsItemsScrollPane = new ScrollPane(userFriendsItemsContainer);
        UserFriendItem userFriendItem = new UserFriendItem();
        //userFriendItem.createUserFriendItem(userFriendItemInfo);
        border.setLeft(userFriendsItemsScrollPane);
        for (int i = 0; i <15 ; i++) {
            userFriendsItemsContainer.getChildren().add(userFriendItem.createUserFriendItem(userFriendItemInfo));
        }

        //userFriendsItemsContainer.getStylesheets().add("UI/Styles.css");
        //userFriendsItemsContainer.getStyleClass().add("user_item");
        /*for(int i=0; i<10; i++) {
            hBox.getChildren().add(addUserFriendItem());
            hBox.getChildren().add(addUserNameAndStatus());
        }
        border.setLeft(hBox);*/
        //border.setCenter( testImageWork() );
        FlowPane flowPane = new FlowPane();
        border.setCenter( flowPane );
        AddDocumentsToPanel addDocumentsToPanel = new AddDocumentsToPanel();
        try {
            flowPane.getChildren().addAll( addDocumentsToPanel.selectFiles() );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        flowPane.setHgap(5);
        flowPane.setVgap(5);
        //flowPane.setUserData( new FileData("qqqqq") );
        flowPane.setOnMouseReleased( (value)-> {
            System.out.println(value);
            //value.getPickResult().
            /*if( value.getTarget() instanceof ImageView) {
                ImageView imageView = (ImageView)value.getTarget();
                FileData fileData = (FileData) imageView.getUserData();
                System.out.println(fileData);
            }*/
            Node node = (Node)value.getTarget();
            while( node != null && node.getUserData() == null ) {
                node = node.getParent();
            }
            if(node != null) {
                FileData fileData = (FileData)node.getUserData();
                fileData.process(value);
            }

            System.out.println(value);
        });
        //border.setCenter(addLineChartToCenter());
        return new Scene(border);
    }

    private HBox addUserFriendItem() {

        System.out.println( System.getProperty("java.class.path") );
        HBox userFriendItemBox = new HBox();
        userFriendItemBox.setMinHeight(70);
        userFriendItemBox.setMaxHeight(70);
        userFriendItemBox.setPrefSize(325, 70);
        File file = new File("src\\main\\resources\\usersPhoto\\iconfinder_1_avatar_2754574.png");
        Image image = new Image(file.toURI().toString());
        Image image1 = new Image("usersPhoto\\square.png", 50, 50, false, false);
        //FileInputStream input = new FileInputStream("resources/images/iconmonstr-home-6-48.png");
        ImageView photo = new ImageView();

        Circle circle = new Circle(24, 24, 24);

        ImagePattern pattern = new ImagePattern(
                new Image("file:usersPhoto/square.png", 280, 180, false, false) // Resizing
        );

        //rectangle.setFill(pattern);
        //rectangle.setEffect(new DropShadow(20, Color.BLACK));  // Shadow

        photo.setImage(image1);
        photo.setClip(circle);

        userFriendItemBox.getChildren().add(photo);
        //userFriendItemBox.getStylesheets().add("UI/Styles.css");
        userFriendItemBox.getStyleClass().add("user_item");
        System.out.println( photo.getStyleClass() );
        userFriendItemBox.setAlignment(Pos.CENTER_LEFT);
        //userFriendItemBox.setPadding(new Insets(0, 0, 0, 10));
        return userFriendItemBox;
    }

    private ImageView testImageWork() {
        Image image1 = new Image("usersPhoto/512.png",
                200, 200, true, true);
        ImageView photo = new ImageView();
        photo.setImage(image1);
        return photo;
    }
    private VBox addUserNameAndStatus() {
        VBox vBox = new VBox();
        Text userName = new Text("Пользователь Пользователь");
        Text userInteractionInfo = new Text("Звонок завершён - 55 с.");
        vBox.getChildren().addAll(userName, userInteractionInfo);
        return vBox;
    }
}

