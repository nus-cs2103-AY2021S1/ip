package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Main class.
 */
public class Duke {
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;
    private static boolean exit;
    private boolean isResponseDukeException;

    public Duke(String pathName) {
        this.ui = new Ui();
        this.storage = new Storage(pathName);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
            ui.printResponse(e.getMessage());
        }
    }

    /**
     * Runs until the user commands for program to stop.
     */
    public void run() {
        ui.welcome();
        this.exit = false;
        while (!exit) {
            try {
                String userInput = ui.getUserInput();
                Command command = Parser.parse(userInput);
                String response = command.execute(this.taskList, this.ui, this.storage);
                assert response instanceof String : "Commands must return String type when executed";
                ui.printResponse(response);
                this.exit = command.isExit();
            } catch (DukeException e) {
                ui.printResponse(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/duke.txt").run();
    }

    /**
     * Takes user input and gets the Duke response.
     * @param input the user input.
     * @return Duke response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            this.isResponseDukeException = false;
            return command.execute(this.taskList, this.ui, this.storage);
        } catch (DukeException e) {
            this.isResponseDukeException = true;
            return e.getMessage();
        }
    }

    /**
     * Checks if Duke threw a DukeException.
     * @return boolean representing whether a DukeException was thrown.
     */
    public boolean getIsResponseDukeException() {
        return this.isResponseDukeException;
    }
}
