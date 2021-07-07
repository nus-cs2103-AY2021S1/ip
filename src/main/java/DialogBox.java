// Solution below are adapted from https://se-education.org/guides/tutorials/javaFx.html
import java.io.IOException;
import java.util.Collections;

import duke.command.InvalidCommandException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    @FXML
    private Polygon triangle;

    /**
     * Creates a dialog box component with the given label and image.
     * @param l the label with the text for the dialog box
     * @param iv the imageview of the image of the speaker of the dialog box
     */
    public DialogBox(Label l, ImageView iv, boolean isUserDialog) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(l.getText());
        displayPicture.setImage(iv.getImage());
        dialog.setFont(Font.font("Ayuthaya", 13));
        if (isUserDialog) {
            styleUserDialog();
        } else {
            styleIfException();
        }
        dialog = l;
        displayPicture = iv;

        dialog.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setPadding(new Insets(10, 10, 10, 10));
        setProperHeight(l);
    }

    private void styleUserDialog() {
        dialog.setStyle("-fx-background-color: #001935; -fx-text-fill: #e6fbff; -fx-label-padding:5;"
                + " -fx-border-radius: 5; -fx-background-radius: 5;");
        triangle.getPoints().setAll(
                10.0, 44.0,
                10.0, 53.0,
                20.0, 49.0
        );
        triangle.setTranslateY(10);
        triangle.setLayoutY(30);
    }

    private void styleIfException() {
        if (dialog.getText().startsWith(InvalidCommandException.INVALID_COMMAND_EXCEPTION_PREFIX)) {
            dialog.setTextFill(Color.rgb(140, 10, 10));
        }
    }

    private void setProperHeight(Label l) {
        int count = l.getText().endsWith("\n") ? 1 : 0;
        String[] ss = l.getText().split("\n");
        count += ss.length + 1;
        for (String s : ss) {
            count += s.length() / 32;
        }
        this.setMinHeight(count * 15 + 30);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        if (dialog.getText().startsWith(InvalidCommandException.INVALID_COMMAND_EXCEPTION_PREFIX)) {
            setBackground(new Background(new BackgroundFill(Color.rgb(246, 217, 217, 0.3),
                    CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox res = new DialogBox(l, iv, true);
        res.dialog.setBackground(new Background(new BackgroundFill(Color.rgb(246, 217, 217),
                CornerRadii.EMPTY, Insets.EMPTY)));
        return res;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, false);
        db.flip();
        return db;
    }
}
