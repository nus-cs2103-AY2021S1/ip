package seedu.duke.command;

import seedu.duke.*;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeNotSureException;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

/**
 * Class that represents marking a task as done.
 */
public class DoneCommand extends Command {
    public DoneCommand(String[] words) {
        super(words);
    }

    /**
     * Marks the given task as done.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     * @throws DukeNotSureException If the given number does not correspond to a task in the list.
     */
    @Override
    public void execute(TaskList ls, Ui ui) throws DukeNotSureException {
        try {
            int number = Integer.parseInt(words[1]);
            Task oldTask = ls.get(number - 1);
            oldTask.checkTask();
            ui.printResult(("Did you really finish it? Eh, good for you I guess. Marked:" + "\n"
                    + oldTask.getStatus()));
        } catch(IndexOutOfBoundsException e) {
            throw new DukeNotSureException("This task doesn't seem to exist? Pick a proper task, good god.");
        }
    }
}
