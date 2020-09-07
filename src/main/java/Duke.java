import java.util.ArrayList;

import commands.Command;
import data.exception.DukeException;
import data.exception.DukeInvalidUserInputException;
import data.task.TaskList;
import parser.Parser;
import storage.Storage;
import ui.Ui;
/**
 * Entry point of the Posh Duke Chat Bot.
 * Drives the entire process of Duke from start to end.
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public Duke() {
    }

    Duke(String file) {
        this.ui = new Ui();
        this.taskList = new TaskList(new ArrayList<>());
        this.storage = new Storage(file);
        this.parser = new Parser(this.taskList, this.storage, this.ui);
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userInput) {
        try {
            Command userCommand = this.parser.parseCommand(userInput);
            return userCommand.execute();
        } catch (DukeException e) {
            return this.ui.showDukeError(e);
        }
    }

    /**
     * Initialises Duke.
     * @return welcome message.
     */
    public String initDuke() {
        try {
            this.storage.loadTaskList(this.taskList);
        } catch (DukeInvalidUserInputException e) {
            return this.ui.showDukeError(e);
        }
        return Ui.showGreeting();
    }
}
