package duke.Gui;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

    /**
     * Constructor for DialogBox instance
     * @param text message to be shown to user
     * @param img image of user to be shown in display window
     * @param isAssistant boolean to check if is assistant or user
     */
    private DialogBox(String text, Image img, boolean isAssistant, boolean isError) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //font
        dialog.setFont(Font.font("Verdana", 13));

        //makeshift padding
        dialog.setText(text);
        if (!isAssistant) {
            dialog.setText(text + "  ");
        }
        displayPicture.setImage(img);

        //@@author GeNiaaz-reused
        //Reused from lll-jy / ip ... DialogBox.java

        this.setBorder(new Border(new BorderStroke(Color.DARKBLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        if (isAssistant && !isError) {
            this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (isAssistant && isError) {
            this.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        int count = dialog.getText().endsWith("\n") ? 1 : 0;
        String[] stringList = dialog.getText().split("\n");
        count += stringList.length + 1;
        for (String substring : stringList) {
            count += substring.length() / 32;
        }
        this.setMinHeight(count * 15 + 30);

        //@@author
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
     * returns new instance of dialogbox
     * @param text message to be shown to user
     * @param img image of user to be shown in display window
     * @return new instance of dialogbox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false, false);
    }

    /**
     * returns new instance of dialogbox
     * @param text message to be shown to user
     * @param img image of user to be shown in display window
     * @return new instance of dialogbox
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        String prefix = text.substring(1, 6);
        var db = new DialogBox(text, img, true, false);
        if (prefix.equals("OH NO")) {
            db = new DialogBox(text, img, true, true);
        }
        db.flip();
        return db;
    }
}