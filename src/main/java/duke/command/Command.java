package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the program should exit.
     * Otherwise, the program should continue and returns false.
     *
     * @return True or False.
     */
    public boolean isExit() {
        return false;
    }
}
