package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.tasks.TaskManager;

public class CancelCommand extends Command {

    /**
     * Sets the duke response telling the user that the current command has been cancelled.
     * @param input the user input.
     * @return <code>true</code>
     */
    @Override
    public boolean execute(String input) {
        setResponse("I've cancelled your current actions");
        setDone();
        return true;
    }

    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse("There's nothing to cancel");
        setDone();
    }
}
