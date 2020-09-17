package duke.gui;

import duke.Duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private VBox dialogContainer;
	@FXML
	private TextField userInput;
	@FXML
	private Button sendButton;

	private Duke duke;

	private Image userImage = new Image(this.getClass().getResourceAsStream("/images/chickuser.png"));
	private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/secretarybird.png"));

	private boolean isExit = false;

	@FXML
	public void initialize() {
		scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
	}

	public void setDuke(Duke d) {
		duke = d;
		showWelcome();
	}

	@FXML
	private void showWelcome() {
		String response = duke.welcomeGui();
		dialogContainer.getChildren().add(
				DialogBox.getDukeDialog(response, dukeImage)
		);
	}

	/**
	 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
	 * the dialog container. Clears the user input after processing.
	 */
	@FXML
	private void handleUserInput() {
		handleExit();
		String input = userInput.getText();
		String response = duke.getResponse(input);
		if(input.equals("bye")) {
			isExit = true;
		}
		dialogContainer.getChildren().addAll(
				DialogBox.getUserDialog("[in chick language]\n" + input, userImage),
				DialogBox.getDukeDialog("[in bird language]\n" +response, dukeImage)
		);
		userInput.clear();
	}

	@FXML
	private void handleExit() {
		if (isExit) {
			Stage stage = (Stage) scrollPane.getScene().getWindow();
			stage.close();
		}
	}
}
