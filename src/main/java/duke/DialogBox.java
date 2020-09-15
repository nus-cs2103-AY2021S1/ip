package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * DialogBox constructor
     * @param l
     * @param iv
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets the user dialog.
     * @param l
     * @param iv
     * @return the resulting user DialogBox
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Gets the user dialog.
     * @param l
     * @param iv
     * @return the resulting duke DialogBox
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    public static DialogBox getIntro(ImageView img) {
        String intro = "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "Here are some of the commands i take:\n"
                + "list\n"
                + "todo <task> - to add a todo\n"
                + "deadline <task> /by <date and time (YYYY-MM-DD TTTT)>\n"
                + "event <task> /by <date and time (YYYY-MM-DD TTTT)>\n"
                + "done <index of task to be deleted>\n"
                + "delete <index of task to be deleted>\n"
                + "bye\n"
                + "Also, to make a task recurring, simply add \"recurring\" after todo/deadline/event\n";
        Label introPara = new Label(intro);
        return new DialogBox(introPara, img);
    }
}
