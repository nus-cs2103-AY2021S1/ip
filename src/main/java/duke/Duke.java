package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke extends Application {

    private UserInterface ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    public Duke() {} // empty constructor needed for javaFX

    public Duke(String filePath) {
        this.ui = new UserInterface();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.taskList = new TaskList(new ArrayList<>());
    }

    private void startup() {
        try {
            storage.populateToLstOfTask(taskList.getLstOfTask());
            ui.greetUser();
        } catch (IOException e) {
            System.out.println(e.toString() + " Error trying to load tasks");
        }
    }

    private void processCommand(String[] parsedUserInput) {
        try {
            String cmd = parsedUserInput[0];
            Command checkedCommand = Command.valueOfUserCommand(cmd);
            if (checkedCommand == null) {
                ui.showInvalidCommandMessage();
            } else {
                switch (checkedCommand) {
                case LIST:
                    ui.listTask(taskList.getLstOfTask());
                    break;
                case BYE:
                    exit();
                    ui.showExitMessage();
                    break;
                case DONE:
                    done(parsedUserInput);
                    break;
                case TODO:
                    addToDo(parsedUserInput);
                    break;
                case EVENT:
                    addEvent(parsedUserInput);
                    break;
                case DEADLINE:
                    addDeadline(parsedUserInput);
                    break;
                case DELETE:
                    delete(parsedUserInput);
                    break;
                case FIND:
                    search(parsedUserInput);
                    break;
                }
            }

        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidCommandMessage();
        }
    }

    private void exit() {
        storage.saveTaskContents(taskList.getLstOfTask());
    }

    private void done(String[] parsedUserInput) {

        try {
            String doneTask = parsedUserInput[1];
            int doneTaskNumber = Integer.parseInt(doneTask);
            int identifierNumberInArrayList = doneTaskNumber - 1;
            Task task = taskList.getLstOfTask()
                    .get(identifierNumberInArrayList);
            task.markAsDone();

        } catch (IndexOutOfBoundsException e1) {
            ui.showInvalidDoneCommand();
        }
    }

    private void delete(String[] parsedUserInput) {
        try {
            String deleteTask = parsedUserInput[1];
            int doneTaskNumber = Integer.parseInt(deleteTask);
            int identifierNumberInArrayList = doneTaskNumber - 1;
            List<Task> lstOfTask = taskList.getLstOfTask();
            Task task = lstOfTask
                    .get(identifierNumberInArrayList);
            lstOfTask.remove(identifierNumberInArrayList);
            ui.showDeleteTaskMessage(task, taskList.getNumOfTask());
        } catch (IndexOutOfBoundsException e1) {
            ui.showInvalidDeleteCommand();
        }
    }

    private void addToDo(String[] parsedUserInput) {
        try {
            String test = parsedUserInput[1];
            String taskDescription = "";
            for (int i = 1; i < parsedUserInput.length; i++) {
                if (i == parsedUserInput.length - 1) {
                    taskDescription += parsedUserInput[i];
                } else {
                    taskDescription += parsedUserInput[i] + " ";
                }
            }
            ToDo td = new ToDo(taskDescription);
            taskList.add(td);
            ui.showAddedTaskMessage(td, taskList.getNumOfTask());
        } catch (IndexOutOfBoundsException e1) {
            ui.showInvalidTodoCommand();
        }
    }

    private void addEvent(String[] parsedUserInput) {
        String taskDescription = "";
        for (int i = 1; i < parsedUserInput.length; i++) {
            if (i == parsedUserInput.length - 1) {
                taskDescription += parsedUserInput[i];
            } else {
                taskDescription += parsedUserInput[i] + " ";
            }
        }

        String[] eventArray = taskDescription.split(" /at ");
        String description = eventArray[0];
        String date = eventArray[1];

        Event event = new Event(description, date.trim());
        taskList.add(event);
        ui.showAddedTaskMessage(event, taskList.getNumOfTask());
    }

    private void addDeadline(String[] parsedUserInput) {
        try {
            String taskDescription = "";
            for (int i = 1; i < parsedUserInput.length; i++) {
                if (i == parsedUserInput.length - 1) {
                    taskDescription += parsedUserInput[i];
                } else {
                    taskDescription += parsedUserInput[i] + " ";
                }
            }


            String[] deadlineArray = taskDescription.split(" /by ");
            String description = deadlineArray[0];
            String date = deadlineArray[1];


            LocalDateTime d1 = parser.parseDateAndTime(date);
            Deadline deadline = new Deadline(description, d1);
            taskList.add(deadline);
            ui.showAddedTaskMessage(deadline, taskList.getNumOfTask());
        } catch (DateTimeParseException e) {
            ui.showInvalidDateFormatGiven();
        }
    }

    private void search(String[] parsedUserInput) {
        try {
            StringBuilder keyword = new StringBuilder(parsedUserInput[1]);
            for (int i = 2; i < parsedUserInput.length; i++) {
                keyword.append(parsedUserInput[i]);
            }

            List<Task> lst = taskList.getLstOfTask();
            List<Task> resultList = new ArrayList<>();
            for (int j = 0; j < taskList.getNumOfTask(); j++) {
                Task t = lst.get(j);
                String description = t.getDescription();
                if (description.contains(keyword)) {
                    resultList.add(t);
                }

            }

            if (resultList.isEmpty()) {
                ui.showNoSearchResult();
            } else {
                ui.showSearchResults(resultList);
            }

        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidSearchCommand();
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke("Tasks.txt");
        Scanner sc = new Scanner(System.in);
        duke.startup();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] parsedString = duke.parser.parseString(input);
            duke.processCommand(parsedString);
            if (input.equals("bye")) {
                break;
            }
        }


    }

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    @Override
    public void start(Stage stage) throws Exception {
        /*
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
        */

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

        //Step 2. Formatting the window to look as expected
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
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
