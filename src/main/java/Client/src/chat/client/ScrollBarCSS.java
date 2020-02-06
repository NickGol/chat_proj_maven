package chat.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScrollBarCSS extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //ScrollPane scrollPane = new ScrollPane();
        //scrollPane.getStylesheets().add("UI/style.css");
        //scrollPane.getStyleClass().add("my_text_area");
        BorderPane border = new BorderPane();
        border.getStylesheets().add("UI/style.css");

        TextArea textArea = new TextArea();
        textArea.getStyleClass().add("message_text_area");
        textArea.getStyleClass().add("my_text_area");
        VBox vBox = new VBox();
        /*Pane emptyPane = new Pane();
        emptyPane.setPrefSize(600, 600);
*/
        //scrollPane.setContent(textArea);
        vBox.getChildren().add(textArea);
        border.setCenter(vBox);

        stage.setScene(new Scene(border, 500, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}