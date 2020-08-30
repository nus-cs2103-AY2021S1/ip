package viscount.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import viscount.Viscount;

public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/userIcon.png"));
    private final Image viscountImage = new Image(this.getClass().getResourceAsStream("/images/viscountIcon.png"));
    
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    
    private Viscount viscount;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setViscount(Viscount viscount) {
        this.viscount = viscount;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = viscount.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getViscountDialog(response, viscountImage)
        );
        userInput.clear();
    }
}
