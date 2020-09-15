package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.comparator.TaskCompareDate;
import seedu.duke.exception.DukeCommandException;
import seedu.duke.exception.DukeNotSureException;
import seedu.duke.comparator.TaskCompareDone;
import seedu.duke.ui.Ui;
import java.util.Collections;

public class SortCommand extends Command {
    public SortCommand(String[] words) {
        super(words);
    }

    /**
     * Sorts the tasklist according to the completion status of tasks.
     * @param ls The current tasklist.
     */
    public void sortByDone(TaskList ls) {
        TaskCompareDone taskComparator = new TaskCompareDone();
        Collections.sort(ls.getList(), taskComparator);
    }

    /**
     * Sorts the tasklist according to the dates of tasks.
     * @param ls The current tasklist.
     */
    public void sortByDate(TaskList ls) {
        TaskCompareDate taskComparator = new TaskCompareDate();
        Collections.sort(ls.getList(), taskComparator);
    }

    /**
     * Executes the sort command and prints out the new sorted list.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     * @throws DukeCommandException If the user did not write anything after the command.
     * @throws DukeNotSureException If the user did not specify the sorting method.
     */
    @Override
    public void execute(TaskList ls, Ui ui) throws DukeCommandException, DukeNotSureException {
        try {
            String[] input = words[1].split("by ");
            assert input.length > 0 : "Input should not be empty.";

            String requirement = input[1];
            switch (requirement) {
                case "done": {
                    sortByDone(ls);
                    break;
                }
                case "date": {
                    sortByDate(ls);
                    break;
                }
                default: {
                    throw new DukeNotSureException("What do you even want?? Sort your stuff properly.");
                }
            }

            ListCommand makeList = new ListCommand();
            makeList.execute(ls, ui);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeCommandException("Write something after the command, gee.");
        }
    }
}
