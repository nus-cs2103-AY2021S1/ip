package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;


    public DialogBox(Label label, ImageView iv, Color color) {
        this.text = label;
        this.displayPicture = iv;

        text.setWrapText(true);
        text.setBackground(new Background(new BackgroundFill(color,
                new CornerRadii(5.0), new Insets(-5.0))));
        displayPicture.setFitHeight(100.0);
        displayPicture.setFitWidth(100.0);


        this.setSpacing(10.0);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(temp);
        this.getChildren().setAll(temp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, Color.GREENYELLOW);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox box = new DialogBox(l, iv, Color.WHITE);
        box.flip();
        return box;
    }
}