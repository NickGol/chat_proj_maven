package chat.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;


public class MainWindow extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    Scene scene;
    BorderPane border;
    VBox userFriendsItemsContainer;



    @Override
    public void start(Stage primaryStage) throws Exception {

        scene = createScene();
        scene.getStylesheets().add("UI/qqqqq1.css");
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

    private Scene createScene() {
        // Use a border pane as the root for scene
        border = new BorderPane();
        UserFriendItemInfo userFriendItemInfo = new UserFriendItemInfo("usersPhoto/512.png",
                "В сети", "Пользователь Пользователь", "Голосовое сообщение",
                "20.20.2020");
        userFriendsItemsContainer = new VBox();
        UserFriendItem userFriendItem = new UserFriendItem();
        //userFriendItem.createUserFriendItem(userFriendItemInfo);
        border.setLeft(userFriendsItemsContainer);
        userFriendsItemsContainer.getChildren().add(userFriendItem.createUserFriendItem(userFriendItemInfo));
        userFriendsItemsContainer.getStylesheets().add("UI/qqqqq1.css");
        userFriendsItemsContainer.getStyleClass().add("user_item");
        /*for(int i=0; i<10; i++) {
            hBox.getChildren().add(addUserFriendItem());
            hBox.getChildren().add(addUserNameAndStatus());
        }
        border.setLeft(hBox);*/
        border.setCenter( testImageWork() );
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
        //userFriendItemBox.getStylesheets().add("UI/qqqqq1.css");
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

