package duke.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private static final Image userImage = new Image("/images/user.PNG");
    private static final Image botImage = new Image("/images/bot.PNG");

    @FXML
    private Label label;
    @FXML
    private ImageView imageView;

    /**
     * Creates a box displaying a message from either Duke or the user.
     *
     * @param text  message to be displayed
     * @param isBot true if the message is from Duke
     */
    public DialogBox(String text, boolean isBot) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        label.setText(text);
        if (isBot) {
            imageView.setImage(botImage);
            flip();
            label.getStyleClass().addAll("dialog-label", "bot-label");
        } else {
            imageView.setImage(userImage);
            this.setAlignment(Pos.BOTTOM_RIGHT);
            label.getStyleClass().addAll("dialog-label", "user-label");
        }
    }

    private void flip() {
        this.setAlignment(Pos.BOTTOM_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
