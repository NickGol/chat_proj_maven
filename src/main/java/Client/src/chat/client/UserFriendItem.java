package chat.client;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class UserFriendItem {

    HBox userFriendContainer;
    StackPane userPhotoAndStatusContainer;
    ImageView userPhoto;
    Label userStatus;
    VBox userNameAndInterconnectionContainer;
    Label userName;
    Label emptyBox;
    Label interactionInfo;
    VBox extraInterconnectionDataContainer;
    Label timeOfInteraction;

    public HBox createUserFriendItem(UserFriendItemInfo userFriendItemInfo) {
        initUserFriendItem(userFriendItemInfo);
        return userFriendContainer;
    }

    private void initUserFriendItem(UserFriendItemInfo userFriendItemInfo) {
        userPhoto = loadUserPhoto( userFriendItemInfo.getUserPhotoPath() );
        userStatus = new Label("50"/* userFriendItemInfo.getUserStatus()*/ );
        userPhotoAndStatusContainer = new StackPane(userPhoto, userStatus);
        userName = new Label( userFriendItemInfo.getUserName() );
        emptyBox = new Label();
        interactionInfo = new Label( userFriendItemInfo.getInterconnectionInfo() );
        userNameAndInterconnectionContainer = new VBox(userName, emptyBox, interactionInfo);
        //userNameAndInterconnectionContainer.getChildren().add(userName);
        timeOfInteraction = new Label( userFriendItemInfo.getTimeOfInterconnection() );
        extraInterconnectionDataContainer = new VBox(timeOfInteraction, GlyphsDude.createIcon(FontAwesomeIcon.LIST, "12pt"));
        userFriendContainer = new HBox( userPhotoAndStatusContainer,
                userNameAndInterconnectionContainer, extraInterconnectionDataContainer);
        //GlyphsDude.createIcon(FontAwesomeIcon.NAVICON, "hello");
        useDefaultViewSettings();
    }

    private ImageView loadUserPhoto(String userPhotoPath) {
        Image image = new Image(userPhotoPath,50, 50,
                true, true);
        ImageView photo = new ImageView(image);
        Circle circle = new Circle(25, 25, 25);
        photo.setClip(circle);
        return photo;
    }

    private void useDefaultViewSettings() {
        //userNameAndInterconnectionContainer.getStylesheets().add("UI/qqqqq1.css");
        //List<String> list = userNameAndInterconnectionContainer.getStylesheets();
        //userPhotoAndStatusContainer.setPrefSize(50, 50);
        //userStatus.getStylesheets().add("UI/qqqqq1.css");
        userStatus.getStyleClass().add("round-lbl");
        userPhotoAndStatusContainer.setMargin(userStatus, new Insets(30, 0, 0, 30));

        userName.getStyleClass().add("text-user_name");
        emptyBox.getStyleClass().add("empty-lbl");
        interactionInfo.getStyleClass().add("text-user_interaction");
        timeOfInteraction.getStyleClass().add("text-user_timeOfInteraction");
        userFriendContainer.getStyleClass().add("hbox-user_item");

        System.out.println( interactionInfo.getFont() );

        userNameAndInterconnectionContainer.getStyleClass().add("vbox123");
        //VBox.setVgrow(userNameAndInterconnectionContainer, Priority.ALWAYS);

    }
}
