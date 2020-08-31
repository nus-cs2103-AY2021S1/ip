package viscount.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import viscount.Main;
import viscount.Viscount;

import java.io.IOException;

public class MainWindow extends AnchorPane {
    private static final String DATA_DIRECTORY_PATH = System.getProperty("user.dir") + "/data/";

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

    public MainWindow() {
        try {
            this.viscount = new Viscount(DATA_DIRECTORY_PATH);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String viscountWelcome = viscount.getUi().getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getViscountDialog(viscountWelcome, viscountImage));
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.equals("bye")) {
            Platform.exit();
        } else {
            String response = viscount.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getViscountDialog(response, viscountImage)
            );
        }

        userInput.clear();
    }
}
