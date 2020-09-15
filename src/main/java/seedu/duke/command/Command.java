package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.Ui;

/**
 * Class that represents user commands.
 */
public abstract class Command {
    protected String[] words;

    public Command() {}

    /**
     * Overloaded constructor for commands that have followups, eg. delete, find.
     * @param words The rest of the user input, apart from the command.
     */
    public Command(String[] words) {
        this.words = words;
    }

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList ls, Ui ui) throws DukeException;
}
