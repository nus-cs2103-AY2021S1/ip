package duke;

import java.util.Scanner;

import duke.command.CommandType;
import duke.command.ResetCommand;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Starts Duke which a user can give
 * text commands to.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage("./data/tasks.txt");
        taskList = TaskList.generateTaskList(storage);
    }

    /**
     * Creates a Duke object
     *
     * @param filePath  Location of file where data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage(filePath);
        taskList = TaskList.generateTaskList(storage);
    }

    /**
     * Gets response for a user input.
     *
     * @param input User input.
     * @return Returns a Duke response as a String.
     */
    public DukeResponse getResponse(String input) {
        return DukeResponse.getDukeResponse(input, ui, taskList);
    }
}
