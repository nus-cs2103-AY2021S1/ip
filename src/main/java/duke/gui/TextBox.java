package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

public class TextBox extends VBox {

    private TextBox(String text, boolean left) {
        //Create text surrounded by a background with rounded corners
        Label textLabel = new Label(text);
        textLabel.setWrapText(true);
        CornerRadii cornerRadii = left
                ? new CornerRadii(0, 10, 10, 10, false)
                : new CornerRadii(10, 0, 10, 10, false);
        BackgroundFill backgroundFill = new BackgroundFill(Color.SKYBLUE,
                cornerRadii,
                new Insets(0));
        Background textBackground = new Background(backgroundFill);
        textLabel.setBackground(textBackground);
        textLabel.setPadding(new Insets(5, 10, 5, 10));

        //Adds a small triangle to the appropriate side to mimic a text box
        HBox hBox = new HBox();
        SVGPath lowerTriangle = new SVGPath();
        lowerTriangle.setFill(Color.SKYBLUE);
        if (left) {
            lowerTriangle.setContent("M0,0 L10,10 L10,0 L0,0");
            hBox.getChildren().addAll(lowerTriangle, textLabel);
        } else {
            lowerTriangle.setContent("M0,0 L-10,10 L-10,0 L0,0");
            hBox.getChildren().addAll(textLabel, lowerTriangle);
        }
        hBox.setSpacing(-1);

        Rectangle verticalGap = new Rectangle(0, 10);
        getChildren().addAll(verticalGap, hBox);

    }

    public static TextBox leftwardTextBox(String text) {
        return new TextBox(text, true);
    }

    public static TextBox rightwardTextBox(String text) {
        return new TextBox(text, false);
    }


}
