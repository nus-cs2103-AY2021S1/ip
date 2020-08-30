package viscount.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;
    
    public DialogBox(Label label, ImageView imageView) {
        this.text = label;
        this.displayPicture = imageView;
        
        text.setWrapText(true);
        displayPicture.setFitHeight(50.0);
        displayPicture.setFitWidth(50.0);
        
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
    
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }
    
    public static DialogBox getViscountDialog(Label label, ImageView imageView) {
        DialogBox dialogBox = new DialogBox(label, imageView);
        dialogBox.flip();
        return dialogBox;
    }
    
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> nodes = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(nodes);
        this.getChildren().setAll(nodes);
    }
}
