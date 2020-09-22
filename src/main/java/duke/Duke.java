package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private boolean isFinished;

    /**
     * Default constructor for Duke.
     * Attempts to load from existing storage, if any.
     */
    public Duke() {
        this.storage = new Storage();
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
        this.isFinished = false;
    }

    public String getResponse(String echo) {
        Command command = null;
        try {
            command = Parser.parseCommand(echo, tasks);
            String output = command.execute(tasks);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }


}
