import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
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

public class Duke {
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    public Duke() throws IOException {
        Storage dukeStorage = Storage.initialiseStorage();
        dukeStorage.loadFromDisk();
        this.storage = dukeStorage;
        this.parser = new Parser();
        this.ui = new Ui();
    }

    public Duke(Storage storage, Parser parser, Ui ui) {
        this.storage = storage;
        this.parser = parser;
        this.ui = ui;
    }

    public static void main(String[] args) throws IOException {
        Storage dukeStorage = Storage.initialiseStorage();
        dukeStorage.loadFromDisk();
        Duke duke = new Duke(dukeStorage, new Parser(), new Ui());

        duke.ui.showWelcomeMessage();
    }

    private String response(String userInput, TaskList taskList) throws DukeException, IOException {
        if (userInput.equals("bye")) {
            return ui.showByeMessage();
        } else if (userInput.equals("list")) {
            return ui.showList(returnList());
        } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
            Task thisTask = parser.processAddTaskInput(userInput, taskList, ui);
            taskList.addTask(thisTask);
            storage.saveToDisk();
            return ui.showAddTaskMessage(thisTask, taskList);
        } else {
            TaskList originalTaskList = TaskList.copy(taskList);
            parser.processOtherActionInput(userInput, taskList, ui);
            storage.saveToDisk();
            return ui.showOtherActionMessage(userInput, originalTaskList);
        }
    }

    /**
     * Called by ui to give users a list view of all their tasks
     * @return list view of all tasks
     */
    public String returnList() {
        String returnString = "";
        int counter = 0;
        Iterator<Task> taskIterator = storage.taskList.getList().iterator();
        while (taskIterator.hasNext()) {
            Task thisTask = taskIterator.next();
            returnString += "\n" + (counter + 1) + ". " + thisTask.toString();
            counter++;
        }
        return returnString;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws IOException, DukeException {
        return response(input, storage.taskList);
    }
}