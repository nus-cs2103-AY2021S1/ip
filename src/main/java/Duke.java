import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Represents a Duke class, which is an interactive chat bot which allows you to track tasks.
 */
public class Duke{
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private PopupControl mainLayout;

//    @Override
//    public void start(Stage stage) {
//        //Step 1. Formatting the window to look as expected.
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
//        stage.setScene(scene);
//        stage.show();
//        //...
//
//        //Step 2. Formatting the window to look as expected
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
//        // more code to be added here later
////        sendButton.setOnMouseClicked((event) -> {
////            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
////            userInput.clear();
////        });
////
////        userInput.setOnAction((event) -> {
////            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
////            userInput.clear();
////        });
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//    }
//
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
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public static void main(String[] args) {

        Parser parser = new Parser();
        String currentDirectory = System.getProperty("user.dir");
        Storage storage = new Storage(currentDirectory + "/data/duke.txt");
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        if (storage.getExisted()) {
            taskList = new TaskList(storage.getTaskList());
        }
        Ui.messagePrint(
                "Hello! I'm Duke\n" +
                "What can I do for you?");

        while (scanner.hasNext()) {
            try {
                String msg = scanner.nextLine();
                if (parser.parse(msg) == Parser.Command.BYE) {
                    // Exit program
                    Ui.messagePrint("Bye. Hope to see you again soon!");
                    break;

                } else if (parser.parse(msg) == Parser.Command.LIST) {
                    // Print list to User
                    String listMessage = "";
                    for (int i = 0; i < taskList.size(); i++) {
                        listMessage += (i + 1) + ". " + taskList.get(i).toString();

                        // If is not last object, add a new line at the end of the item
                        if (i != taskList.size() - 1) {
                            listMessage += "\n";
                        }
                    }
                    Ui.messagePrint(listMessage);

                } else if (parser.parse(msg) == Parser.Command.DONE) {
                    /* Update status of Task to completed. */
                    int updateTaskIndex = Integer.valueOf(msg.substring(5)) - 1;
                    if (updateTaskIndex >= taskList.size() || updateTaskIndex <= 0) {
                        throw new DukeException(DukeExceptionType.TASK_NOT_FOUND);
                    }
                    Task taskToUpdate = taskList.get(updateTaskIndex);
                    taskToUpdate.updateStatus(true);
                    taskList.set(updateTaskIndex, taskToUpdate);
                    String completedMessage = "Nice! I've marked this task as done:\n" + "  " + taskList.get(updateTaskIndex).toString();
                    Ui.messagePrint(completedMessage);
                    storage.write(taskList);

                } else if (parser.parse(msg) == Parser.Command.DELETE) {
                    // Delete task.
                    int updateTaskIndex = Integer.valueOf(msg.substring(7)) - 1;
                    if (updateTaskIndex >= taskList.size() || updateTaskIndex < 0) {
                        throw new DukeException(DukeExceptionType.TASK_NOT_FOUND);
                    }
                    Task taskToUpdate = taskList.get(updateTaskIndex);
                    taskList.remove(updateTaskIndex);
                    String deletedMessage = "Noted. I've removed this task:\n" +
                           "  " + taskToUpdate.toString() + "\n" +
                                "Now you have " + taskList.size() + " tasks in the list.";
                    Ui.messagePrint(deletedMessage);
                    storage.write(taskList);

                } else if (parser.parse(msg) == Parser.Command.FIND) {
                    //Find task according to query message.
                    String query = msg.substring(5);
                    String output = "Here are the matching tasks in your list: \n";
                    TaskList searchedTaskList = taskList.find(query);
                    for (int i = 1; i <= searchedTaskList.size(); i++) {
                        output = output + i + "." + searchedTaskList.get(i - 1).toString();

                        if (i != searchedTaskList.size()) {
                            output += "\n";
                        }
                    }
                    Ui.messagePrint(output);
                } else {
                    // Creating new tasks.
                    Task newTask;
                    if (parser.parse(msg) == Parser.Command.DEADLINE) {
                        // Create deadline.
                        int byIndex = msg.indexOf("/by");
                        String task = msg.substring(9, byIndex - 1); //Number 9 = starting index of deadline string.
                        String dateString = msg.substring(byIndex + 4);
                        try {
                            LocalDate date = LocalDate.parse(dateString);
                            newTask = new Deadline(task, date);
                        } catch (DateTimeParseException e) {
                            newTask = new Deadline(task, dateString);
                        }

                    } else if (parser.parse(msg) == Parser.Command.EVENT) {
                        // Create event.
                        int atIndex = msg.indexOf("/at");
                        String task = msg.substring(6, atIndex - 1); //Number 6 = starting index of event string.
                        String dateString = msg.substring(atIndex + 4);
                        try {
                            LocalDate date = LocalDate.parse(dateString);
                            newTask = new Event(task, date);
                        } catch (DateTimeParseException e) {
                            newTask = new Event(task, dateString);
                        }

                    } else if (parser.parse(msg) == Parser.Command.TODO) {
                        // Create ToDo
                        String task = msg.substring(5); //Number 5 = starting index of todo string.
                        newTask = new ToDo(task);

                    } else if (parser.parse(msg) == Parser.Command.EMPTY_TASK_TODO) {
                        // Checks for empty task in a new ToDo
                        throw new DukeException(DukeExceptionType.EMPTY_TASK_TODO);

                    } else if (parser.parse(msg) == Parser.Command.EMPTY_TASK_EVENT_DEADLINE) {
                        // Checks for empty task in a new deadline or event.
                        throw new DukeException(DukeExceptionType.EMPTY_TASK_EVENT_DEADLINE);

                    } else if (parser.parse(msg) == Parser.Command.EMPTY_DATE) {
                        // Checks for empty date in a new deadline or event.
                        throw new DukeException(DukeExceptionType.EMPTY_DATE);
                    }

                    else {
                        // Else if input is unrecognized, return null.
                        throw new DukeException(DukeExceptionType.INVALID_INPUT);
                    }

                    taskList = taskList.add(newTask);
                    String newTaskMsg = String.format(
                            "Got it. I've added this task:\n" +
                                    "  %s\n" +
                                    "Now you have %d tasks in the list.", newTask.toString(), taskList.size());
                    Ui.messagePrint(newTaskMsg);
                    storage.write(taskList);
                }
            } catch (DukeException e) {
                Ui.messagePrint(e.toString());
            }
        }
    }


}
