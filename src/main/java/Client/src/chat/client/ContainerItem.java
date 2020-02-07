package chat.client;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

//import java.beans.EventHandler;

public class ContainerItem implements ItemsMetaData {

    final String description = "Container";

    public String getDescription() {
        return description;
    }


    public void process() {

        throw new RuntimeException("Метод process класса ContainerItem не определён.");
    }
}
