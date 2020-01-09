package chat.client;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UserFriendItemInfo {

    private String userPhotoPath;
    private String userStatus;
    private String userName;
    private String interconnectionInfo;
    private String timeOfInterconnection;

    public UserFriendItemInfo(String userPhotoPath, String userStatus, String userName,
                              String interconnectionInfo, String timeOfInterconnection) {
        this.userPhotoPath = userPhotoPath;
        this.userStatus = userStatus;
        this.userName = userName;
        this.interconnectionInfo = interconnectionInfo;
        this.timeOfInterconnection = timeOfInterconnection;
    }

    public String getUserPhotoPath() {
        return userPhotoPath;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getUserName() {
        return userName;
    }

    public String getInterconnectionInfo() {
        return interconnectionInfo;
    }

    public String getTimeOfInterconnection() {
        return timeOfInterconnection;
    }
}
