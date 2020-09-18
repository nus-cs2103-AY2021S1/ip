package willy.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import willy.command.Parser;

/**
 *     // Put together the interactionBox which consist of the displaying of user inputs and bot's response
 */
public class JAVAFXInteractionBox {
    public static Label userInput;
    public static Text botResponse;

    JAVAFXInteractionBox() {
        this.userInput = new Label();
        this.botResponse = new Text(provideHelp());
    }

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

    public VBox interactionBoxCreator() {

        Rectangle userInputContainer = new Rectangle(330, 40);
        userInputContainer.setFill(Color.rgb(180, 157, 253));
        StackPane userInputStack = new StackPane();
        userInput.setTextFill(Color.WHITE);
        // Code below from https://stackoverflow.com/questions/12341672/make-portion-of-a-text-bold-in-a-javafx-label
        // -or-text
        userInput.setStyle("-fx-font-weight: bold");
        userInputStack.getChildren().addAll(userInputContainer, userInput);
        userInputStack.setAlignment(userInput, Pos.CENTER_RIGHT);

        // Responsible for BotResponse
        // Code below adapted from https://www.jackrutorial
        // .com/2020/04/how-to-set-background-color-ofscrollpane-in-javafx.html
        ScrollPane botResponseContainer = new ScrollPane();
        botResponseContainer.setStyle("-fx-background: #EBE9F7; -fx-border-color: #262626;");
        botResponseContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        botResponseContainer.setPrefViewportHeight(160);
        botResponseContainer.setPrefViewportWidth(180);
        TextFlow text = new TextFlow(botResponse);
        text.setPrefWidth(300);
        text.setPadding(new Insets(7, 20, 5, 20));
        botResponseContainer.setContent(text);

        // Bot and User Interaction Container
        VBox interactionBox = new VBox();
        interactionBox.setPadding(new Insets(5, 20, 10, 30));
        interactionBox.setSpacing(10);
        interactionBox.setMinHeight(200);
        interactionBox.getChildren().addAll(userInputStack, botResponseContainer);

        return interactionBox;
    }
}
