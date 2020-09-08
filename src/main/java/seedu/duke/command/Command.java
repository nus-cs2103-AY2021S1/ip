package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;

/**
 * Class that represents user commands.
 */
public class Command {
    protected String[] words;

    public Command() {}

    public Command(String[] words) {
        this.words = words;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList ls, Ui ui) throws DukeException {}
}
