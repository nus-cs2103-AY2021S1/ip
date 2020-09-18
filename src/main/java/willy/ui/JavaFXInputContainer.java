package willy.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * In charge of creating input Container for JavaFX.
 */
public class JavaFXInputContainer {

    /**
     * Put together the input Container which consist of the text field and the enter & clear buttons.
     *
     * @param inputField Field where user can type in the input commands.
     * @param enterButton Enter button to execute a command.
     * @param clearButton Clear button to clear the text in the text field.
     * @return Returns a HBox representing an input container.
     */
    public static HBox inputContainerCreator(TextField inputField, Button enterButton, Button clearButton) {
        // Settle buttons
        inputField.setPrefWidth(220);
        inputField.setPromptText("State tasks to track");

        // Putting together input components
        HBox inputContainer = new HBox();
        inputContainer.setSpacing(10);
        inputContainer.setPadding(new Insets(5, 20, 20, 30));
        inputContainer.getChildren().addAll(inputField, enterButton, clearButton);

        return inputContainer;
    }

}
