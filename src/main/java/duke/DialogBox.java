package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.Collections;


/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog; //displays String given by user or Duke
    @FXML
    private Circle circleDisplayPicture; //display picture of Duke or user

    /**
     * Constructor for DialogBox used to initialize DialogBox object
     *
     * @param text is input placed in dialog
     * @param img used in the image for circleDisplayPicture
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setBackground(new Background(new BackgroundFill(Color.THISTLE, new CornerRadii(5), null)));
        circleDisplayPicture.setRadius(50);
        circleDisplayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gives the DialogBox of the user
     *
     * @param text input by the user
     * @param img user image
     * @return DialogBox of user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setBackground(new Background(new BackgroundFill(Color.MISTYROSE, new CornerRadii(20), null)));
        //sets background to MISTYROSE
        db.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED,
                new CornerRadii(20), null))); //sets dotted border
        return db;
    }

    /**
     * Gives the DialogBox of Duke
     *
     * @param response input by duke in response to the input given by user
     * @param img Duke image
     * @return DialogBox of Duke
     */
    public static DialogBox getDukeDialog(Response response, Image img) {
        String text = response.getOutput();
        var db = new DialogBox(text, img);
        db.dialog.setFont(Font.font("Times new Roman"));
        db.setBackground(new Background(new BackgroundFill(Color.SALMON, new CornerRadii(20), null)));
        //sets background to salmon
        db.flip(); //to have a Duke appear on different side
        if (response.getIsException()) {
            db.dialog.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 12));
            //when the response given is an exception, it is emphasized with BOLD and italic
        } else { }
        return db;
    }
}
