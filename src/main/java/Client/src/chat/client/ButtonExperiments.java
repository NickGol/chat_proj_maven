package chat.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ButtonExperiments extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HBox Experiment 1");

        Button button = new Button("My Button");
        Button button1 = new Button("My Button");
        button.setText("Click me if you dare!");
        button.setMaxWidth(100);
        button.setMaxHeight(200);
        button1.setMaxSize(100, 200);

        Scene scene = new Scene(button, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}