package duke.control;

import duke.controller.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class DialogueBox extends HBox {
    @FXML
    private Label label;

    @FXML
    private ImageView picture;

    private DialogueBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        label.setText(text);

        Circle circle = new Circle(50, 50, 50);
        picture.setImage(img);
        picture.setClip(circle);
    }


    /*
    public DialogueBox(Label label, ImageView imageView) {
        this.dialogueLabel = label;
        this.picture = imageView;
        dialogueLabel.setWrapText(true);
        picture.setFitWidth(100.0);
        picture.setFitHeight(100.0);


        dialogueLabel.setPadding(new Insets(5));
        HBox.setMargin(imageView, new Insets(5));


        Circle circle = new Circle(50, 50, 50);

        this.picture.setClip(circle);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(label, picture);
        this.setPadding(new Insets(5));
    }
    */

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogueBox getUserDialogueBox(String string, Image image) {
        DialogueBox dialogueBox = new DialogueBox(string, image);
        dialogueBox.setBackground(new Background(
                new BackgroundFill(Color.GREEN, new CornerRadii(10), new Insets(5, 5, 5, 50))));
        HBox.setMargin(dialogueBox.label, new Insets(0, 0, 0, 50));
        return dialogueBox;
    }

    public static DialogueBox getDukeDialogueBox(String string, Image image) {
        DialogueBox dialogueBox = new DialogueBox(string, image);
        dialogueBox.flip();
        dialogueBox.setBackground(
                new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(10), new Insets(5, 50, 5, 5))));
        HBox.setMargin(dialogueBox.label, new Insets(0, 50, 0, 0));
        return dialogueBox;
    }
}
