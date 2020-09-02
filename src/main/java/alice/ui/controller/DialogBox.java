package alice.ui.controller;

import java.io.IOException;

import alice.storage.SaveStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    // Image is created by u/Osmeromar as part of "Me" telegram sticker pack.
    public static final Image USER_AVATAR = new Image(DialogBox.class.getResourceAsStream("/images/robo.png"));
    // Image is created by u/Percangelo as part of "Alice Zuberg" telegram sticker pack.
    public static final Image ALICE_AVATAR = new Image(DialogBox.class.getResourceAsStream("/images/alice.png"));

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox chatBubble;
    @FXML
    private HBox chatRow;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> nodes = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(nodes);
        getChildren().setAll(nodes);
        chatBubble.setAlignment(Pos.TOP_LEFT);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Creates a user dialog box with the given string input as dialog text.
     *
     * @param text the dialog string given by the user.
     * @return the corresponding DialogBox with the user string input.
     */
    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, USER_AVATAR);
    }

    /**
     * Creates a Alice dialog box with the given string input as dialog text.
     *
     * @param text the dialog string output by Alice.
     * @return the corresponding DialogBox representing Alice's feedback to the user.
     */
    public static DialogBox getAliceDialog(String text) {
        DialogBox db = new DialogBox(text, ALICE_AVATAR);
        db.flip();
        db.chatBubble.setStyle("-fx-background-color: darkslateblue; -fx-background-radius: 15");
        HBox.setMargin(db.chatBubble, new Insets(0, 15, 0, 5));
        db.dialog.setTextFill(Paint.valueOf("white"));
        return db;
    }

    /**
     * Adds an appropriate save status icon at the bottom of the dialog box.
     * The icon is equipped with a tooltip which displays when hovered upon.
     * The icon is not added if the saveStatus is of type SAVE_NOT_APPLICABLE
     * as no save process was attempted.
     *
     * @param saveStatus the SaveStatus of the command execution.
     */
    public void addSaveIndicator(SaveStatus saveStatus) {
        if (saveStatus == SaveStatus.SAVE_NOT_APPLICABLE) {
            // do not add save indicator
            return;
        }

        VBox statusRow = new VBox();
        statusRow.setAlignment(Pos.TOP_RIGHT);

        Circle circle = new Circle();
        circle.setRadius(4);
        circle.setStrokeWidth(0.0);
        Tooltip tooltip = new Tooltip("Saved failed");
        if (saveStatus == SaveStatus.SAVE_SUCCESS) {
            circle.setFill(Paint.valueOf("lawngreen"));
            tooltip.setText("Save successful");
        }

        if (saveStatus == SaveStatus.SAVE_FAILED) {
            circle.setFill(Paint.valueOf("red"));
            tooltip.setText("Save failed");
        }

        // Add tooltip to circle. Tooltip appears on hover with some delay
        Tooltip.install(circle, tooltip);
        statusRow.getChildren().add(circle);
        chatBubble.getChildren().add(statusRow);
        chatBubble.setPadding(new Insets(15, 15, 10, 15));
    }
}
