import java.io.IOException;

import duke.command.DukeException;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;



/**
 * <h1> DUKE CLASS </h1>
 * The Duke Class contains the method to
 * initialize the DukeChat bot. Different commands will
 * result in different course of action.
 *
 * Currently Duke only supports Todo, Deadline, Event, Done, Delete, List, Find
 * commands as a Task Tracker.
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/photo1.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/photo2.jpg"));

    /**
     * Instantiate a Duke Object with the filepath as that name
     * of the file which the lists will be saved to.
     *
     */
    public Duke() {
        this.taskList = TaskList.createTaskList();
        this.storage = Storage.createDukeFile("Saved");
    }

//    /**
//     * Initiates the process of DukeBot that
//     * accepts user commands and processes
//     * the commands to create the Chatbot Task list.
//     *
//     * @author Lee Penn Han.
//     */
//    public void run() {
//        Ui.welcomeMessage();
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String cmd = sc.nextLine().trim().toLowerCase();
//            if (!cmd.equals("bye") && cmd.length() != 0) {
//                try {
//                    Parser.process(cmd, this.taskList, this.storage);
//                } catch (DukeException e) {
//                    Ui.showError(e.getMessage());
//                }
//            } else {
//                try {
//                    Ui.showSaving();
//                    this.storage.saveToFile();
//                } catch (IOException e) {
//                    Ui.showError(e.getMessage());
//                }
//                Ui.goodbyeMessage();
//                break;
//            }
//        }
//
//    }

//    @Override
//    public void start(Stage stage) {
//
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setTitle("Duke");
//        stage.setResizable(true);
//        stage.setMinHeight(600);
//        stage.setMinWidth(400);
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        //String cmd = sc.nextLine().trim().toLowerCase();
        String finalString;
        if (!input.equals("bye") && input.length() != 0) {
            try {
                finalString = Parser.process(input, this.taskList, this.storage);
            } catch (DukeException e) {
                finalString = Ui.showError(e.getMessage());
            }
        } else {
            try {
                System.out.println(Ui.showSaving());
                this.storage.saveToFile();
            } catch (IOException e) {
                System.out.println(Ui.showError(e.getMessage()));
            }
            finalString = Ui.goodbyeMessage();
        }
        return finalString;
    }

}
