package chat.client;

import ItemsMetaDataPackage.ItemsMetaData;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class SearchMetaData implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        Node node = (Node)event.getTarget();
        while( node != null && node.getUserData() == null ) {
            node = node.getParent();
        }
        if(node != null) {
            ItemsMetaData itemMetaData = (ItemsMetaData) node.getUserData();
            itemMetaData.process();
        }
    }
}
