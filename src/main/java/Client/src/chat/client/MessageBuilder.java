package chat.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.FlowPane;

import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class MessageBuilder {
    private MessageBuilder() {}
    private static MessageBuilder messageBuilder = new MessageBuilder();
    private Map<Integer, List<Node>> messageStructureMap = null;
    private ObservableList<Node> messageStructureList = null;
    private Integer id = 0;
    private AddDocumentsToPanel addDocumentsToPanel = new AddDocumentsToPanel();

    public static MessageBuilder getMessageBuilder() {
        return messageBuilder;
    }


    public ObservableList<Node> addFiles(){
        deleteEmptyTextArea();
        try {
            //FlowPane flowPane = new FlowPane();
            //flowPane.getChildren().addAll(addDocumentsToPanel.selectFiles());
            //messageStructureList.add( flowPane );
            searchPlaceAndAddFiles( addDocumentsToPanel.selectFiles() );
        } catch (MalformedURLException e) {
            throw new RuntimeException("Не удалось прикрепить файлы для отправки");
        } finally {
            messageStructureList.add( addEmptyMessage() );
        }
        return messageStructureList;
    }

    public ObservableList<Node> getEmptyMessage() {
        if( messageStructureList == null )
            messageStructureList = FXCollections.observableArrayList();
        messageStructureList.add( addEmptyMessage() );
        return messageStructureList;//FXCollections.observableList( messageStructureList );
    }

    public void setObservableList(ObservableList<Node> list) {
        messageStructureList = list;
    }

    private Node addEmptyMessage() {
        TextArea textArea = new TextArea();
        textArea.getStyleClass().add("message_text_area");
        textArea.setPromptText("Введите сообщение.");
        return textArea;
    }

    private boolean deleteEmptyTextArea() {
        if( messageStructureList.get(messageStructureList.size()-1) instanceof TextInputControl)  {
            TextInputControl textInputControl = (TextInputControl) messageStructureList.get(messageStructureList.size()-1);
            if(textInputControl.getText().isEmpty()) {
                messageStructureList.remove(textInputControl);
                return true;
            }
        }
        return false;
    }

    private void searchPlaceAndAddFiles( List<Node> listOfFiles) {
        int listSize = messageStructureList.size();
        if( listSize > 0 && messageStructureList.get(listSize-1) instanceof FlowPane ) {
                ((FlowPane) messageStructureList.get(listSize-1)).getChildren().addAll(listOfFiles);
        } else {
            FlowPane flowPane = new FlowPane();
            flowPane.setUserData( new ContainerItem() );
            flowPane.getChildren().addAll(listOfFiles);
            messageStructureList.add(flowPane);
        }
    }
}
