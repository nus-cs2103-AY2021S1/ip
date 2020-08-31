package seedu.duke;

import java.security.InvalidParameterException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Main part of logic of Duke.
 */
public class Duke {
    /**
     * Holds the storage of data
     */
    private Storage storage;

    /**
     * Manages the list of tasks
     */
    private TaskList taskList;

    /**
     * Parses input strings from user to task objects
     */
    private Parser parser;

    /**
     * Holds the user interface part
     */
    private Ui ui;

    /**
     * Initializes Duke object.
     * Finishes essential settings.
     */
    public Duke() {
        storage = new Storage("./data/duke.txt");
        taskList = new TaskList(storage);
        parser = new Parser();
        ui = new Ui();
    }

    public TaskList getHistoryList() {
        return this.taskList;
    }

    /**
     * Holds management of operations.
     */
    public String run(String userInput) {
        String result;
        if (userInput.equals("bye")) {
            result = ui.goodBye();
        } else if (userInput.equals("list")) {
            result = ui.listTask(taskList);
        } else if (userInput.startsWith("done")) {
            try {
                int doneNumber = parser.parseDoneOrder(userInput, taskList);
                taskList.markDone(doneNumber);
                result = ui.taskDone(taskList.getTask(doneNumber));
            } catch (IndexOutOfBoundsException e) {
                result = ui.taskDoesNotExist();
            } catch (Exception e) {
                result = ui.invalidDoneOrder();
            }
        } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput
                .startsWith("event")) {
            try {
                Task newTask = parser.parseTask(userInput);
                taskList.addNewTask(newTask);
                result = ui.newTaskAdded(newTask, taskList);
            } catch (InvalidParameterException e) {
                result = ui.parseFail(userInput);
            }
        } else if (userInput.startsWith("delete")) {
            try {
                int deletingIndex = parser.parseDeleteOrder(userInput, taskList);
                Task deletingTask = taskList.getTask(deletingIndex);
                taskList.deleteTask(deletingIndex);
                result = ui.taskDeleted(taskList, deletingTask);
            } catch (IndexOutOfBoundsException e) {
                result = ui.taskDoesNotExist();
            } catch (Exception e) {
                result = ui.invalidDeleteOrder();
            }
        } else if (userInput.startsWith("find")) {
            try {
                String keyWord = parser.parseFindOrder(userInput);
                result = ui.showFindResult(taskList.find(keyWord));
            } catch (InvalidParameterException e) {
                result = ui.invalidFindOrder();
            }
        } else {
            result = ui.generalError();
        }
        return result;
    }
}
