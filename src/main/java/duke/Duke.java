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

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
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
        new Duke().run();
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
