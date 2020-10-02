package duke;

import java.util.Scanner;

/**
 * Supports the creation of Duke which a user can give commands to.
 */
public class Duke {

    /**
     * Storage location of the tasks.
     */
    private Storage storage;

    /**
     * List of tasks
     */
    private TaskList taskList;

    /**
     * Ui associated with Duke.
     */
    private Ui ui;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage("./data/tasks.txt");
        taskList = TaskList.generateTaskList(storage);
    }

    /**
     * Creates a Duke object
     * @param filePath Location of file where data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage(filePath);
        taskList = TaskList.generateTaskList(storage);
    }

    /**
     * Gets a response for user input.
     * @param input User input.
     * @return A response object containing the necessary information to update the GUI.
     */
    public DukeResponse getResponse(String input) {
        return DukeResponse.getDukeResponse(input, ui, taskList);
    }
}
