package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.commands.Command;

/**
 * Duke is the name of this program. It acts as a CLI app that reads and save
 * the user inputs. You can use it to record down tasks and marking the progress
 * of it.
 */

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

    /**
     * Activates the duke bot.
     */
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
