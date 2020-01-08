package chat.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class AuthorizationUI extends Application {

//    public static void main(String[] args) {
//        //launch(args);
//    }
    private boolean showPassword = false;
    private boolean loginOk = false, passwordOk = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AuthEnterButton;

    @FXML
    private TextField AuthLogin;

    @FXML
    private PasswordField AuthPassword;

    @FXML
    private Label AuthRegisterLabel;

    @FXML
    private TextField AuthPasswordText;

    @FXML
    private ImageView AuthShowPassword;

    @FXML
    private Label AuthPasswordLabel;

    @FXML
    private Label AuthLoginLabel;

    @FXML
    private Label AuthEnterButtonLabel;

    Authorization authorization = new Authorization();

    @FXML
    void initialize() {
        AuthPasswordLabel.setVisible(false);
        AuthShowPassword.setImage(new Image("UI/eye_green.png"));
        AuthPasswordText.setVisible(false);
        AuthPasswordLabel.setVisible(false);
        AuthLoginLabel.setVisible(false);
        AuthEnterButtonLabel.setVisible(false);
        AuthEnterButton.setMinWidth(0.5);
        authEnterButtonState();
        //uthLoginLabel.setMinWidth(Region.USE_COMPUTED_SIZE);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("authorization.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("UI/qqqqq1.css");
        primaryStage.setTitle("Authorization");
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    void AuthLogin_OnKeyReleased(KeyEvent event) {
        String text;
        text = AuthLogin.getText();
        if( text.length() < 3 ) text += "123";
        checkLogin(text);
    }

    @FXML
    void AuthPassword_OnKeyTyped(KeyEvent event) {

        String text;

        if( event.getSource().equals(AuthPassword) ) {
            AuthPasswordText.setText(AuthPassword.getText() + event.getCharacter());
            text = AuthPasswordText.getText();
        } else {
            AuthPassword.setText(AuthPasswordText.getText() + event.getCharacter());
            text = AuthPassword.getText();
        }
        if( text.length() < 3 ) text += "123";
        checkPassword(text);
    }

    @FXML
    void AuthShowPassword(MouseEvent event) {
        showPassword = !showPassword;
        TextField visibleObject = null;
        if(showPassword) {
            AuthShowPassword.setImage(new Image("UI/eye_gray.png"));
            visibleObject = AuthPasswordText;
        } else {
            AuthShowPassword.setImage(new Image("UI/eye_green.png"));
            visibleObject = AuthPassword;
        }
        AuthPasswordText.setVisible(showPassword);
        AuthPassword.setVisible(!showPassword);
        visibleObject.requestFocus();
        visibleObject.selectPositionCaret(AuthPasswordText.getText().length());
    }

    @FXML
    void AuthEnterButton_OnMouseReleased(MouseEvent event) {

        String message = "Ошибка при проверке данных пользователя.";
        String password = AuthPassword.getText();
        String login = AuthLogin.getText();
        // Check login and password with regular expression.
        checkPassword(password);
        checkLogin(login);
        // Search for current combination of login and password in base.
        if( loginOk && passwordOk ) {
            try {
                authorization.readAuthData();
                if( !authorization.checkPairLoginPassword(login, password) ) {
                    message = "Пользователя с таким именем и паролем нет в базе.";
                    throw new IOException(message);
                }
                AuthEnterButtonLabel.setVisible(false);
// TODO: Open chat window
            } catch (IOException e) {
                if( AuthEnterButtonLabel.getStyleClass().contains("label_message") )
                    AuthEnterButtonLabel.getStyleClass().remove("label_message");
                AuthEnterButtonLabel.setText(message);
                AuthEnterButtonLabel.setVisible(true);
            }
        }

    }


    private void checkLogin(String text) {
        boolean textCorrectness;
        textCorrectness = authorization.checkLoginCorrectness(text);
        if( /*text.length()<3 ||*/ textCorrectness ) {
            AuthLogin.getStyleClass().remove("text-field_my_error");
            AuthLoginLabel.setVisible(false);
            loginOk = true;
            authEnterButtonState();
        } else if( !textCorrectness ) {
            AuthLoginLabel.setText("Имя пользователя должено содержать от 3 до 25 символов без пробела");
            AuthLoginLabel.setVisible(true);
            if( !AuthLogin.getStyleClass().contains("text-field_my_error") )
                AuthLogin.getStyleClass().add("text-field_my_error");
            AuthLogin.requestFocus();
            AuthLogin.selectPositionCaret(AuthPasswordText.getText().length());
            loginOk = false;
            authEnterButtonState();
        }
    }

    private void checkPassword(String text) {
        boolean textCorrectness;
        TextField visibleObject = AuthPassword.isVisible() ? AuthPassword : AuthPasswordText;

        textCorrectness = authorization.checkLoginCorrectness(text);
        if (/*text.length() < 3 ||*/ textCorrectness) {
            AuthPasswordText.getStyleClass().remove("text-field_my_error");
            AuthPassword.getStyleClass().remove("text-field_my_error");
            AuthPasswordLabel.setVisible(false);
            passwordOk = true;
            authEnterButtonState();
        } else if (!textCorrectness) {
            AuthPasswordLabel.setText("Пароль должен содержать от 3 до 25 символов без пробела");
            AuthPasswordLabel.setVisible(true);
            if (!AuthPasswordText.getStyleClass().contains("text-field_my_error"))
                AuthPasswordText.getStyleClass().add("text-field_my_error");
            if (!AuthPassword.getStyleClass().contains("text-field_my_error"))
                AuthPassword.getStyleClass().add("text-field_my_error");
            visibleObject.requestFocus();
            visibleObject.selectPositionCaret(AuthPasswordText.getText().length());
            passwordOk = false;
            authEnterButtonState();
        }
    }

    private void authEnterButtonState() {
        if( loginOk && passwordOk ) AuthEnterButton.setDisable(false);
            else AuthEnterButton.setDisable(true);
    }

}



