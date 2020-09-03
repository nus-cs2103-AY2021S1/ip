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
    public static Storage storage;
    public static TaskList taskList;
    public static Ui ui;
    public static boolean exit;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
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
                ui.printResponse(response);
                this.exit = command.isExit();
            } catch (DukeException e) {
                ui.printResponse(e.getMessage());
            }
        }
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/duke.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.taskList, this.ui, this.storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
