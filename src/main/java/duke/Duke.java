package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.commands.Command;

public class Duke {
    
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean isExit;
    
    public Duke() {
        storage = new Storage();
        tasks = new TaskList();
        storage.retrieve(tasks);
        this.ui = new Ui();
    }

    // activate the duke.Duke Bot
    public void echo() {
        ui.greetings();
        isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.getExitStatus();
            } catch (DukeException e) {
                ui.printException(e.getMessage());
            }
        }
    }

}
