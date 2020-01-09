package chat.client;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.List;

public class UserFriendItem {

    HBox userFriendContainer;
    StackPane userPhotoAndStatusContainer;
    ImageView userPhoto;
    Label userStatus;
    VBox userNameAndInterconnectionContainer;
    Label userName;
    Label interconnectionInfo;
    VBox extraInterconnectionDataContainer;
    Label timeOfInterconnection;

    public HBox createUserFriendItem(UserFriendItemInfo userFriendItemInfo) {
        initUserFriendItem(userFriendItemInfo);
        return userFriendContainer;
    }

    private void initUserFriendItem(UserFriendItemInfo userFriendItemInfo) {
        userPhoto = loadUserPhoto( userFriendItemInfo.getUserPhotoPath() );
        userStatus = new Label("50"/* userFriendItemInfo.getUserStatus()*/ );
        userPhotoAndStatusContainer = new StackPane(userPhoto, userStatus);
        userName = new Label( userFriendItemInfo.getUserName() );
        interconnectionInfo = new Label( userFriendItemInfo.getInterconnectionInfo() );
        userNameAndInterconnectionContainer = new VBox(userName, interconnectionInfo);
        timeOfInterconnection = new Label( userFriendItemInfo.getTimeOfInterconnection() );
        extraInterconnectionDataContainer = new VBox(timeOfInterconnection, GlyphsDude.createIcon(FontAwesomeIcon.LIST, "12pt"));
        userFriendContainer = new HBox(3, userPhotoAndStatusContainer,
                userNameAndInterconnectionContainer, extraInterconnectionDataContainer);
        GlyphsDude.createIcon(FontAwesomeIcon.NAVICON, "hello");
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
        userPhotoAndStatusContainer.setPrefSize(50, 50);
        //userStatus.getStylesheets().add("UI/qqqqq1.css");
        userStatus.getStyleClass().add("round-lbl");
        userPhotoAndStatusContainer.setMargin(userStatus, new Insets(30, 0, 0, 30));

        userName.getStyleClass().add("text-user_name");
        interconnectionInfo.getStyleClass().add("text-user_interaction");
        timeOfInterconnection.getStyleClass().add("text-user_timeOfInterconnection");
        //userFriendContainer.getStyleClass().add("hbox-user_item");

    }
}
