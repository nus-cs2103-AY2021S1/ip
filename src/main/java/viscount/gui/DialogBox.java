package viscount.gui;

import java.io.IOException;

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

/**
 * Represents a dialog box component within the main window.
 */
public class DialogBox extends HBox {
    private static final Image USER_IMAGE =
            new Image(DialogBox.class.getResourceAsStream("/images/userIcon.png"));
    private static final Image VISCOUNT_IMAGE =
            new Image(DialogBox.class.getResourceAsStream("/images/viscountIcon.png"));

    private static final Color USER_TEXT_BOX_COLOR = Color.rgb(222, 241, 253);
    private static final Color VISCOUNT_TEXT_BOX_COLOR = Color.rgb(255, 255, 255);
    private static final Color VISCOUNT_TEXT_BOX_EXCEPTION_COLOR = Color.rgb(255, 192, 193);

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Instantiates a new dialog box component.
     *
     * @param text Text inside the dialog box.
     * @param image Image of the entity chatting the text inside the dialog box.
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Gets a new dialog box component representing a user's chat message.
     *
     * @param text Text inside the dialog box.
     * @return A new dialog box component representing a user's chat message.
     */
    public static DialogBox getUserDialog(String text) {
        DialogBox dialogBox = new DialogBox(text, USER_IMAGE);
        dialogBox.dialog.setBackground(
                new Background(new BackgroundFill(USER_TEXT_BOX_COLOR, new CornerRadii(5.0), new Insets(-5.0))));

        return dialogBox;
    }

    /**
     * Gets a new dialog box component representing Viscount's chat response.
     *
     * @param text Text inside the dialog box.
     * @return Gets a new dialog box component representing Viscount's chat response.
     */
    public static DialogBox getViscountDialog(String text, boolean isException) {
        DialogBox dialogBox = new DialogBox(text, VISCOUNT_IMAGE);
        dialogBox.flip();

        dialogBox.dialog.setBackground(new Background(new BackgroundFill(
                isException ? VISCOUNT_TEXT_BOX_EXCEPTION_COLOR : VISCOUNT_TEXT_BOX_COLOR,
                new CornerRadii(5.0),
                new Insets(-5.0))));

        return dialogBox;
    }

    /**
     * Flips the dialog box component on the vertical axis.
     */
    private void flip() {
        ObservableList<Node> nodes = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(nodes);
        this.getChildren().setAll(nodes);
        this.setAlignment(Pos.TOP_LEFT);
    }
}
