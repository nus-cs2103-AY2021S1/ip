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
public class Duke {
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

    public String greet() {
        return this.ui.printGreeting();
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

    /**
     * main method of duke.java where duke is runned
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("ellowoo");
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in); //scans for input
        String userInput;

        while (duke.isRunning()) {
            userInput = sc.nextLine();
            try {
                duke.run(userInput);
            } catch (IllegalArgumentException e) {
                DukeExceptions.printUnrecognizableCommandError();
            }
        }
    }

    public String returnUnrecognizableCommandError() {
        return DukeExceptions.printUnrecognizableCommandError();
    }
}
