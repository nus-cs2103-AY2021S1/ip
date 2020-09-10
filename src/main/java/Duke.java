import Duke.DukeException;
import Duke.Parser;
import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import Duke.Ui;
import Duke.DialogBox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class Duke extends Application{

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Steve.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Alex.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private boolean isExit = false;

    public void init (String dest, String fileName){
        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage(dest, fileName);
            taskList = new TaskList(storage.load());

        } catch (DukeException | IOException e) {
            ui.showError(e);
        }


    }

    @Override
    public void start(Stage stage) {
        init("data", "duke.txt");

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

        //RESIZE
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();

        if (isExit) {
            System.exit(0);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        StringBuilder output = new StringBuilder("");
        assert output.length()>0 : "Invalid Argument";
        try {
            Parser.Mode mode = parser.mode(input);
            if (mode == Parser.Mode.TODO || mode == Parser.Mode.DEADLINE ||
                    mode == Parser.Mode.EVENT) {
                // Create and add task to task list
                Task task = parser.task(input);
                taskList.add(task);

                // Write data
                storage.addData(task);

                // System output
                output.append(ui.showAddTask(task) + "\n")
                        .append(ui.showTotalTasks(taskList.getSize()));

            } else if (mode == Parser.Mode.DONE) {
                // Mark Task as Done
                int order = parser.order(input);
                Task task = taskList.markDone(order);
                storage.markDoneData(order);
                output.append(ui.showDoneTask(task));

            } else if (mode == Parser.Mode.DELETE) {
                // Delete Task
                int order = parser.order(input);
                Task task = taskList.remove(order);
                storage.deleteData(order);
                output.append(ui.showRemovedTask(task)).append("\n")
                        .append(ui.showTotalTasks(taskList.getSize()));

            } else if (mode == Parser.Mode.LIST) {
                // List all tasks
                output.append(taskList.listToString());

            } else if (mode == Parser.Mode.BYE) {
                // Exit now
                isExit = true;
                output.append(ui.goodbye());
            } else if (mode == Parser.Mode.FIND) {
                // Find tasks
                String subName = parser.name(input);
                String result = taskList.find(subName);
                output.append(ui.showFindTask(result));
            }
        } catch (DukeException | IOException e) {

            output.append(ui.showError(e));
        }
        return output.toString();
    }
}