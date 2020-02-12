package chat.client;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
    /**/ImageView userPhoto;
    /**/Label userStatus;
    VBox userNameAndInteractionContainer;
    /**/Label userName;
    /**/Label emptyBox;
    /**/Label interactionInfo;
    VBox extraInterconnectionDataContainer;
    /**/Label timeOfInterconnection;

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
        userNameAndInteractionContainer = new VBox(userName, emptyBox, interactionInfo);
        timeOfInterconnection = new Label( userFriendItemInfo.getTimeOfInterconnection() );
        extraInterconnectionDataContainer = new VBox(timeOfInterconnection, GlyphsDude.createIcon(FontAwesomeIcon.LIST, "12pt"));
        userFriendContainer = new HBox( userPhotoAndStatusContainer,
                userNameAndInteractionContainer, extraInterconnectionDataContainer);
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
        //userNameAndInterconnectionContainer.getStylesheets().add("UI/Styles.css");
        //List<String> list = userNameAndInterconnectionContainer.getStylesheets();
        userStatus.getStylesheets().add("UI/Styles.css");
        userFriendContainer.getStyleClass().add("user-friend-item");
        //userStatus.getStyleClass().add("user-friend-item-photo");
        userPhotoAndStatusContainer.getStyleClass().add("user-friend-item-stack-pane");
        userStatus.getStyleClass().add("user-friend-item-status");

        userName.getStyleClass().add("user-friend-item-user-name");
        emptyBox.getStyleClass().add("user-friend-item-empty-lbl");
        interactionInfo.getStyleClass().add("user-friend-item-user-interactionЫS");
        timeOfInterconnection.getStyleClass().add("user-friend-item-timeOfInteraction");
        
        //VBox.setVgrow(userNameAndInterconnectionContainer, Priority.ALWAYS);

    }
}
