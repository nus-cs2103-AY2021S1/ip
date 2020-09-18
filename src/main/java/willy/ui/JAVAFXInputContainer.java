package willy.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class JAVAFXInputContainer {

    // Put together the input Container which consist of the text field and the enter & clear button
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
