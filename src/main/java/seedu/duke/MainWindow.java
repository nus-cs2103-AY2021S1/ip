package seedu.duke;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainWindow extends BorderPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Label welcomeLabel;

    Duke duke;

    void setDuke (Duke duke) {
        this.duke = duke;
    }

    @FXML
    public void initialize() {
        welcomeLabel.setText(Message.getWelcome().getText());
        welcomeLabel.setFont(new Font("Courier New", 12));
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    @FXML
    public void handleUserInput() {
        userInput.setOnAction((event) -> {
            String input = userInput.getText();
            if (!input.trim().equals("")) {
                dialogContainer.getChildren().addAll(
                        getInputLabel(input),
                        getResponseLabel(input));
            }
            userInput.clear();
        });
    }

    private Label getInputLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private Label getResponseLabel(String text) {
        String response = duke.getResponse(text);

        Label textToAdd = new Label(response);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}
