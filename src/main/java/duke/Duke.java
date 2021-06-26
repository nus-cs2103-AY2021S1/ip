package duke;

import duke.parser.Parser;
import duke.storage.TaskListStorage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class that ties together commands and ui.
 */
public class Duke {
    /**
     * Creates a new instance of Duke.
     *
     * @param ui Ui for Duke to interact with.
     */
    public Duke(Ui ui) {
        TaskList taskList = new TaskListStorage("data/tasks.txt").load(ui);
        ui.say("Hello, I'm Duke. What can I do for you?");
        ui.setInputHandler((String input) -> {
            Parser.parse(input).execute(ui, taskList);
        });
    }
}
