package chat.client;

import ItemsMetaDataPackage.*;
import com.sun.istack.internal.NotNull;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        textArea.textProperty();
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


    /*public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }*/

    private void searchPlaceAndAddFiles( List<Node> listOfFiles) {
        int listSize = messageStructureList.size();
        boolean duplicate = false;
        if( listSize > 0 && messageStructureList.get(listSize-1) instanceof FlowPane ) {
            Node qqq = searchForMetaData( (Pane)messageStructureList.get(listSize-1), OpenFileItem.class );
            // delete duplicated files
            Set<String> addedFilesPathList = ((Pane) qqq).getChildren().stream()
                    .map(x->((OpenFileItem)x.getUserData()).getFilePath())
                    .collect(Collectors.toSet());

            for (int i = 0; i < listOfFiles.size(); i++) {
                String newNodeFilePath = ((OpenFileItem) listOfFiles.get(i).getUserData()).getFilePath();
                if( addedFilesPathList.add(newNodeFilePath) ) {
                    ((Pane) messageStructureList.get(listSize-1)).getChildren().add(listOfFiles.get(i));
                }
            }
        } else {
            if( listOfFiles.size()>0 ) {
                FlowPane flowPane = new FlowPane();
                flowPane.setUserData(new ContainerItem());
                flowPane.getChildren().addAll(listOfFiles);
                messageStructureList.add(flowPane);
            }
        }
    }

    private Node searchForMetaData(@NotNull Pane root, @NotNull Class<? extends ItemsMetaData> clazz) {
        if( root != null && clazz != null ) {
            if(  root.getUserData()!=null && clazz.isAssignableFrom( root.getUserData().getClass() )  ) {
                System.out.println("Item was founded");
                return root;
            }
            for (int i = 0; i < root.getChildren().size(); i++) {
                if( root.getChildren().get(i) instanceof Pane) {
                    Node node = searchForMetaData( (Pane)root.getChildren().get(i),clazz );
                    if( node!=null ) return node;
                }
            }
            return null;
        }
        System.out.println("Элемент не найден");
        //return null;
        throw new RuntimeException("В элементе " + root + " Не удалось найти элемент UserData типа " + clazz.getName());
    }
}
