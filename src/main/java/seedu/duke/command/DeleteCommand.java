package seedu.duke.command;

import seedu.duke.exception.DukeNotSureException;
import seedu.duke.task.Task;
import seedu.duke.TaskList;
import seedu.duke.ui.Ui;

/**
 * A command that deletes a task.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String[] words) {
        super(words);
    }

    /**
     * Deletes the task from the list of current tasks.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     * @throws DukeNotSureException If the given number does not correspond to a task in the list.
     */
    @Override
    public void execute(TaskList ls, Ui ui) throws DukeNotSureException {
        int number = Integer.parseInt(words[1]);
        if (number > ls.size() || number < 0) {
            throw new DukeNotSureException("This task doesn't seem to exist? Pick a proper task, good god.");
        } else {
            int originalSize = ls.size();
            Task oldTask = ls.get(number - 1);
            ls.remove(number - 1);
            assert ls.size() < originalSize : "Should have removed the task.";
            String thing = "Running away from your responsibilities huh. Deleted:" +
                    "\n" +
                    oldTask.getStatus().replaceAll("(?m)^", "\t") +
                    "\nNow you have " + ls.size() + " tasks in the list.";
            ui.printResult(thing);
        }
    }
}
