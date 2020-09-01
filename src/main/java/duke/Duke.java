package duke;

import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import command.ByeCommand;
import command.Command;
import command.DateCommand;
import command.DeadLineCommand;
import command.DelCommand;
import command.DoneCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.TodoCommand;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import task.Task;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * The Dukebot programme implements an application that assists the user in managing their tasks.
 *
 * @author  Ryan Lim
 */
public class Duke extends Application {
    /** variable to check if duke is still running */
    private boolean isRunning = true;
    /** ArrayList to hold all tasks keyed in by the user for the session */
    private TaskList taskList;
    /** UI where the user will interact with the Dukebot */
    private final Ui ui;
    /** Parser which handles user input */
    private final Parser parser;
    /** Storage which manages the saving of tasks from the session into the hard disk  */
    private final Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * initialises Duke bot
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage("./data/data.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.printLoadingError();
            this.taskList = new TaskList();
        }
        this.ui.printGreeting();
    }


    /**
     * Handles Tasks which are marked done and updates it in both hard disk accordingly as well as the task list.
     *
     * @param parameters the index of the task to be marked done.
     * @throws DukeExceptions.NoUndoneTaskException
     */


    private String doneHandler(String[] parameters) throws DukeExceptions.NoUndoneTaskException {
        if (!this.taskList.isEmpty() || this.taskList.allDone()) {
            int index = Integer.parseInt(parameters[0].strip()) - 1;
            this.taskList.completeTask(index);
            this.updateFile();
            return this.ui.printDoneTask(this.taskList.getTask(index));
        } else {
            throw new DukeExceptions.NoUndoneTaskException();
        }
    }


    /**
     * Handles addition of new Tasks and updates it in both hard disk accordingly as well as the task list.
     *
     * @param command the command which indicates which type of task to create.
     * @throws DukeExceptions.IncompleteCommandException
     */


    private String addTaskHandler(Command command) throws DukeExceptions.IncompleteCommandException {
        if (!command.isEmpty()) {
            Task newTask = this.taskList.addTask(command);
            this.updateFile();
            return this.ui.printAddedNewTask(newTask, this.taskList.getNoTask());
        } else {
            throw new DukeExceptions.IncompleteCommandException(command.getClass().toString());
        }
    }


    /**
     * Handles addition of new Tasks and updates it in both hard disk accordingly as well as the task list.
     *
     * @param parameters index of task to delete.
     * @throws DukeExceptions.NoTaskToDeleteException if tasklist is empty
     */

    private String deleteTaskHandler(String[] parameters) throws DukeExceptions.NoTaskToDeleteException {
        if (!this.taskList.isEmpty()) {
            int index = Integer.parseInt(parameters[0].strip()) - 1;
            Task task = this.taskList.deleteTask(index);
            this.updateFile();
            return this.ui.printDeleteTask(task, this.taskList.getNoTask());
        } else {
            throw new DukeExceptions.NoTaskToDeleteException();
        }
    }

    /**
     * Returns True if Duke bot is still running.
     *
     * @return boolean value indicating if Duke bot is still running or not.
     */
    public boolean isRunning() {
        return this.isRunning;
    }


    /**
     * saves tasks to hard disk
     */
    private void updateFile() {
        try {
            this.storage.save(this.taskList);
        } catch (IOException e) {
            this.ui.printErrorInSaving();
        }
    }

    /**
     * Takes in a user input as a string, parses it and gets fed the appropriate commands to be handled.
     *
     * @param userInput commands and parameters that the user inputs through the user interface
     */
    public String run(String userInput) {
        Command command = this.parser.parse(userInput);
        if (command.getClass() == ByeCommand.class) {
            this.isRunning = false;
            return this.ui.printFarewell();
        } else if (command.getClass() == ListCommand.class) {
            return this.ui.printTaskList(this.taskList);
        } else if (command.getClass() == DoneCommand.class) {
            try {
                return this.doneHandler(command.getParameters());
            } catch (DukeExceptions.NoUndoneTaskException e) {
                return DukeExceptions.printNoUndoneTaskError();
            } catch (IndexOutOfBoundsException e) {
                return DukeExceptions.printIndexSizeMismatchError();
            } catch (NumberFormatException e) {
                return DukeExceptions.noIndexKeyedError();
            }
        } else if (command.getClass() == TodoCommand.class
                || command.getClass() == EventCommand.class
                || command.getClass() == DeadLineCommand.class) {
            try {
                return this.addTaskHandler(command);
            } catch (DukeExceptions.IncompleteCommandException e) {
                return DukeExceptions.printIncompleteCommandError();
            } catch (ArrayIndexOutOfBoundsException e) {
                return DukeExceptions.printNoDateInput();
            } catch (DateTimeParseException e) {
                return DukeExceptions.printIncorrectDateFormatError();
            }
        } else if (command.getClass() == DelCommand.class) {
            try {
                return this.deleteTaskHandler(command.getParameters());
            } catch (DukeExceptions.NoTaskToDeleteException e) {
                return DukeExceptions.printNoTaskToDeleteError();
            } catch (IndexOutOfBoundsException e) {
                return DukeExceptions.printIndexSizeMismatchError();
            } catch (NumberFormatException e) {
                return DukeExceptions.noIndexKeyedError();
            }
        } else if (command.getClass() == DateCommand.class) {
            return ui.printGetTaskOnDThisDate(command.getParameters()[0], this.taskList);
        } else if (command.getClass() == FindCommand.class) {
            return ui.printFindKeyword(command.getParameters()[0], this.taskList);
        }
        return "";
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText;
        try {
            dukeText = new Label(this.run(userInput.getText()));
        } catch (IllegalArgumentException e) {
            dukeText = new Label("sorry master i do not recognize that command");
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    @Override
    public void start(Stage stage) {
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
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

//    /**
//     * main method of duke.java where duke is runned
//     * @param args
//     */
//    public static void main(String[] args) {
//
//        Duke duke = new Duke("./data/data.txt");
//        Scanner sc = new Scanner(System.in); //scans for input
//        String userInput;
//
//        while (duke.isRunning()) {
//            userInput = sc.nextLine();
//            try {
//                duke.run(userInput);
//            } catch (IllegalArgumentException e) {
//                DukeExceptions.printUnrecognizableCommandError();
//            }
//        }
//    }
}
