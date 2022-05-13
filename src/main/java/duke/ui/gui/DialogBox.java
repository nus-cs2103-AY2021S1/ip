package duke.ui.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import duke.ui.gui.markdown.Markdown;
import duke.utils.UtilFunction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextFlow;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private TextFlow dialog;
    @FXML
    private Label name;
    @FXML
    private Circle displayPicture;
    @FXML
    private VBox nameAndDialog;
    @FXML
    private HBox fullInfo;
    @FXML
    private Label timeStamp;

    private DialogBox(List<Markdown> markdownList, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isUser) {
            name.setText("You");
            dialog.setStyle("-fx-background-color: #66ff33");
            nameAndDialog.setAlignment(Pos.TOP_RIGHT);
        } else {
            name.setText("Duke");
            dialog.setStyle("-fx-background-color: #ffffff");
        }

        for (Markdown markdown : markdownList) {
            dialog.getChildren().add(markdown.create());
        }
        dialog.setMinHeight(Label.USE_PREF_SIZE);
        dialog.setLineSpacing(2);
        timeStamp.setText(UtilFunction.getCurrentTime());
        //move to fxml
        displayPicture.setRadius(20);
        displayPicture.setFill(new ImagePattern(img));

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        ObservableList<Node> timeAndDialog = FXCollections.observableArrayList(fullInfo.getChildren());
        //flip the avatar and the dialog
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        //flip the time stamp and the dialog
        setAlignment(Pos.TOP_LEFT);
        Collections.reverse(timeAndDialog);
        fullInfo.getChildren().setAll(timeAndDialog);

    }

    public static DialogBox getUserDialog(List<Markdown> text, Image img) {
        return new DialogBox(text, img, true);
    }

    public static DialogBox getDukeDialog(List<Markdown> text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}
