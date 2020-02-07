package chat.client;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

//import java.beans.EventHandler;

public class DeleteItem implements ItemsMetaData {

    final String description = "Delete";
    Node itemToDelete;

    public String getDescription() {
        return description;
    }

    public DeleteItem(Node node) {
        itemToDelete = node;
    }

    public void process() {
        Node outerNode = itemToDelete.getParent();
        while( outerNode != null ) {
            ItemsMetaData itemMetaData = (ItemsMetaData) outerNode.getUserData();
            outerNode = itemToDelete.getParent();
            if( itemMetaData == null ) continue;
            if( itemMetaData.getDescription().equals("Container") && outerNode instanceof Pane ) {
                ((Pane) outerNode).getChildren().remove(itemToDelete);
                return;
            }
        }
        throw new RuntimeException("Не удалось удалить элемент");
    }
}
