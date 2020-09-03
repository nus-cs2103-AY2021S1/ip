package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The FormatCommand class implements a
 * FormatCommand command executable to list
 * out all the formats in which user can follow
 * to input commands.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class FormatCommand extends Command {
    public FormatCommand() {
    }

    /**
     * Shows format that users can follow to input commands.
     *
     * @param storage Storage data of tasks in hard disk.
     * @param taskList TaskList where task actions are done.
     * @param ui Ui that shows format that users can follow to input commands.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showFormat();
    }

    /**
     * Returns string of format that users can follow to input commands.
     *
     * @param storage Storage data of tasks in hard disk.
     * @param taskList TaskList where task actions are done.
     * @param ui Ui that shows format that users can follow to input commands.
     * @return String of input format information
     */
    @Override
    public String executeToString(Storage storage, TaskList taskList, Ui ui) {
        return "Formats for the three task types Todo, Deadline and Event,"
                + " are shown below.\n"
                + "Todo: 'todo <task description>'"
                + " (e.g. todo visit new theme park)\n"
                + "Deadline: 'deadline <task description>"
                + " /by <yyyy-mm-dd hhmm>' (e.g. deadline submit report /by 2019-11-10 1500)\n"
                + "Event: 'event <task description>"
                + " /at <yyyy-mm-dd hhmm-hhmm>' "
                + "(e.g. event team project meeting /at 2019-10-02 1400-1500)\n"
                + "\nAdditional Information:"
                + "\nTo mark a task as done, input 'done <task number>'."
                + "\nTo delete a task from your list, input 'delete <task number>'."
                + "\nTo find a task in your list, input 'find <keyword>'."
                + "\nTo close Dino, enter 'bye'.";
    }
}
