package duke;

import duke.task.TaskList;
import duke.tool.Command;
import duke.tool.Parser;
import duke.tool.Storage;
import duke.ui.Ui;

/**
 * Represents duke.Duke, a task scheduling bot.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Command command;

    /**
     * Constructs a duck object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/data.txt");
        parser = new Parser();
        command = new Command();
        taskList = new TaskList(storage.load());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parse(storage, ui, taskList, command, input);
    }

    public Ui getUi() {
        return ui;
    }
}
