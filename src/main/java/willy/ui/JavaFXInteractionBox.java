package willy.ui;

import java.awt.*;

import javax.swing.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 * In charge of creating the interactionBox which consist of the displaying of user inputs and bot's response.
 */
public class JavaFXInteractionBox {
    public static Label userInput;
    public static Text botResponse;

    public JavaFXInteractionBox() {
        this.userInput = new Label();
        this.botResponse = new Text(provideHelp());
    }

    /**
     * Produce the formats of the various commands available.
     * @return Summary of the command formats in String form.
     */
    public static String provideHelp() {
        String commands = "Commands:" + "\n" + "1. todo [TASK]"
                + "\n" + "2. deadline [TASK] /by [DATE] [TIME]"
                + "\n" + "3. event [TASK] /at [DATE] [TIME]"
                + "\n" + "4. done [TASK NUMBER]" + "\n" + "5. delete [TASK NUMBER]"
                + "\n" + "6. edit [TASK NUMBER] > [TASK DETAILS]"
                + "\n" + "7. find [KEYWORD(s)]" + "\n" + "8. list"
                + "\n" + "Type 'help' for more info on each command";
        return commands;
    }

    /**
     * Creates the interaction box which consist of user inputs and bot's response.
     * @return A VBox representing an interaction box.
     */
    public VBox interactionBoxCreator() {

        StackPane userInputStack = new StackPane();
        // Code below adapted from https://www.youtube.com/watch?v=UzlXUlTD1Lo
        userInput.prefWidthProperty().bind(userInputStack.widthProperty());
        // Code below adapted from https://www.programcreek.com/java-api-examples/?class=javafx.scene.control.Label&method=setBackground
        userInput.setBackground(new Background(new BackgroundFill(Color.rgb(180, 157, 253), new CornerRadii(2), new Insets(-3.5,0,-5,0) )));
        userInput.setAlignment(Pos.CENTER_RIGHT);
        userInput.setTextFill(Color.WHITE);
        // Code below from https://stackoverflow.com/questions/12341672/make-portion-of-a-text-bold-in-a-javafx-label
        // -or-text
        userInput.setStyle("-fx-font-weight: bold");
        userInput.setTextAlignment(TextAlignment.RIGHT);
        userInput.setWrapText(true);
        userInputStack.getChildren().addAll(userInput);
        userInputStack.setAlignment(userInput, Pos.CENTER_RIGHT);

        // Responsible for BotResponse
        // Code below adapted from https://www.jackrutorial
        // .com/2020/04/how-to-set-background-color-ofscrollpane-in-javafx.html
        ScrollPane botResponseContainer = new ScrollPane();
        botResponseContainer.setStyle("-fx-background: #EBE9F7; -fx-border-color: #262626;");
        botResponseContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        botResponseContainer.setPrefViewportHeight(160);
        botResponseContainer.setPrefViewportWidth(190);
        TextFlow text = new TextFlow(botResponse);
        text.setPrefWidth(300);
        text.setPadding(new Insets(7, 20, 5, 20));
        botResponseContainer.setContent(text);

        // Bot and User Interaction Container
        VBox interactionBox = new VBox();
        interactionBox.setPadding(new Insets(5, 20, 10, 20));
        interactionBox.setSpacing(10);
        interactionBox.setPrefHeight(200);
        interactionBox.getChildren().addAll(userInputStack, botResponseContainer);

        return interactionBox;
    }
}
