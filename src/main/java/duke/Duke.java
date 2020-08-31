package duke;

import duke.command.Command;
import duke.tasks.TaskList;

/**
 * Duke main class
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        storage = new Storage("src/main/data.txt");
        tasks = new TaskList(storage.getTasks());
        ui = new Ui();
    }

    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                output = (e.getMessage());
            }
        return output;
    }

    public String getWelcomeMessage() {
        return ui.showWelcome();
    }
}
