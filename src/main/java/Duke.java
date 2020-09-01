import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application {
    private static TaskList taskList;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/SpongeBob.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Patrick.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * main function
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void duke() throws IOException, ClassNotFoundException {
        TextUi.printHello();
        Scanner sc = new Scanner(System.in);
        String input;
        taskList = Storage.load();

        while (true) {
            try {
                input = sc.nextLine();
                Command command = Parser.decideCategory(input);
                command.execute(taskList);
                Storage.store(taskList);
            } catch (IllegalArgumentException | DateTimeParseException exception) {
                TextUi.printError(exception);
            }
        }
    }

    @Override
    public void start(Stage stage) {
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // more code to be added here later
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * handles response from the user input
     * @param input
     * @return
     */
    public String getResponse(String input) {
        TaskList task = new TaskList();
        String category = Parser.getCategory(input);
        String description = Parser.getDescription(input);

        switch (category) {
        case "todo":
            if (description.equals("")) {
                return TextUi.printError(new IllegalArgumentException("The description of a todo cannot be empty."));
            } else {
                Command command = Parser.decideCategory(input);
                command.execute(task);
                return TextUi.printNewTasks(task.toString()) + TextUi.printTaskSummary(task.getTaskLength());
            }
        case "deadline":
            String[] descriptionArray = description.split("/by");
            if (descriptionArray[0].equals("")) {
                return TextUi.printError(new IllegalArgumentException("The description of a deadline cannot be empty. "));
            } else if (descriptionArray.length == 1) { //no "/at" present
                return TextUi.printError(new IllegalArgumentException("Invalid input, no deadline stated. "));
            } else if (descriptionArray.length > 2) {
                return TextUi.printError(new IllegalArgumentException("Invalid input, multiple deadlines stated. "));
            } else {
                Command command = Parser.decideCategory(input);
                command.execute(task);
                return TextUi.printNewTasks(task.toString()) + TextUi.printTaskSummary(task.getTaskLength());
            }
        case "event":
            String[] descriptionArray2 = description.split("/at");
            if (descriptionArray2[0].equals("")) {
                return TextUi.printError(new IllegalArgumentException("The description of an event cannot be empty. "));
            } else if (descriptionArray2.length == 1) { //no "/at" present
                return TextUi.printError(new IllegalArgumentException("Invalid input, no event time stated. "));
            } else if (descriptionArray2.length > 2) {
                return TextUi.printError(new IllegalArgumentException("Invalid input, multiple deadlines stated. "));
            } else {
                Command command = Parser.decideCategory(input);
                command.execute(task);
                return TextUi.printNewTasks(task.toString()) + TextUi.printTaskSummary(task.getTaskLength());
            }
        case "done":
            if (description.equals("")) {
                return TextUi.printError(new IllegalArgumentException("Not sure which task is to be indicated as done."));
            } else {
                Command command = Parser.decideCategory(input);
                command.execute(task);
                return TextUi.printMessage(task.toString());
            }
        case "delete":
            if (description.equals("")) {
                return TextUi.printError(new IllegalArgumentException("Not sure which task is to be deleted. "));
            } else {
                Command command = Parser.decideCategory(input);
                command.execute(task);
                return TextUi.printMessage(task.toString());
            }
        case "bye":
            if (!description.equals("")) {
                return TextUi.printError(new IllegalArgumentException(" Invalid input. "));
            } else {
                Command command = Parser.decideCategory(input);
                command.execute(task);
                return TextUi.printMessage(task.toString());
            }
        case "find":
            if (description.equals("")) {
                return TextUi.printError(new IllegalArgumentException("No keyword found. "));
            } else {
                return TextUi.printMessage(task.toString());
            }
        case "list":
            if (!description.equals("")) {
                return TextUi.printError(new IllegalArgumentException(" Invalid input. "));
            } else {
                return TextUi.printMessage(task.toString()) + task.toString();
            }
        default:
            return TextUi.printError(new IllegalArgumentException("Invalid input."));
        }
        }
}












