package Duke;

import Duke.Commands.Command;
import Duke.Errors.DukeException;
import Duke.Helpers.Parser;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;
/**
 * This Duke class is the main class that prints out the relevant outputs by including all the subclasses of Task and
 * taking in the input.
 */
public class Duke {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    public Duke(){}
   // @Override
   /* public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        // more code to be added here later
    }
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }*/
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
   /* private void handleUserInput() {
            Label userText = new Label(userInput.getText());
            Label dukeText = new Label(getResponse(userInput.getText()));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
            );
            userInput.clear();
    }*/
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input)  {
        Command c = Parser.parse(input);
        try {
            return c.execute(tasks, new Ui(), storage);
        }catch (DukeException e){
            return e.getMessage();
        }

    }
    /**
     * assigns the above member variables with the appropriate values, and throws certain exceptions if file in
     * the filePath mentioned is empty or absent
     * @param filePath represents where the filepath of where the file may exist.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }
    public Duke(String filePath, String input){
        storage = new Storage(filePath);
        try {
            ui = new Ui(input);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }
    /**
     * gives main logic of the App,
     * where exceptions are caught and printed and if bye is there code stops. also starts with hello
     */
    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /**
     *
     * @param args of type String[]
     * reads input using scan() and adds it to todos.
     *  Then, prints out relevant information using the output() func.
     */
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("src/main/java/tasks.txt");
        String s = duke.getResponse("todo read book");
        /*PrintStream fileOut = new PrintStream("src/main/java/output.txt");
        System.setOut(fileOut);
        FileWriter fw = new FileWriter("src/main/java/tasks.txt");
        fw.write("");
        fw.close();
        Duke duke = new Duke("src/main/java/tasks.txt", "src/main/java/input.txt");
        duke.run();*/
    }
}

