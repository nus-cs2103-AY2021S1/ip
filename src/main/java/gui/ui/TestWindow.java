package gui.ui;

import duke.ChatbotApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class TestWindow extends AnchorPane {
//    Path PATH = Paths.get(System.getProperty("user.dir"), "src" , "main" , "images");
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    
    private ChatbotApplication chatbot = new ChatbotApplication("##" , System.getProperty("user.dir"));
    /**
     * Initialise the Mainwindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        initialiseProgram();
        introMessage();
    }

    private void initialiseProgram() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(chatbot.initialiseApplication(),duke)
        );
    }

    private void introMessage() {
        
        String input = userInput.getText();
        String response = chatbot.greeting();
        chatbot.setUserName(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
        
    }
    
    public void setChatbot(ChatbotApplication chatbot) {
        this.chatbot = chatbot;
    }

    /**
     *
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
//        Path taskFile = Paths.get(System.getProperty("user.dir"), "src" , "main" , "images");

        String input = userInput.getText();
        String response = chatbot.takeInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }
    

}