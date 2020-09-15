package com.siawsam.duke.controllers;

import java.io.IOException;
import java.util.Collections;

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
    
    private DialogBox(String text, Image img, String id) {
        assert text.length() > 0 : "Dialogbox cannot have empty text";
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.setId(id);
        dialog.setText(text);
        displayPicture.setImage(img);
    }
    
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
    
    /**
     * Creates a new DialogBox representing user input.
     *
     * @param text The text content of the DialogBox.
     * @param img The image to display as the avatar in the DialogBox.
     * @return A DialogBox with the given text content and avatar image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "userDialogBox");
    }
    
    /**
     * Creates a new DialogBox representing a duke reply.
     *
     * @param text The text content of the DialogBox.
     * @param img The image to display as the avatar in the DialogBox.
     * @return A DialogBox with the given text content and avatar image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "dukeDialogBox");
        db.flip();
        return db;
    }
}
