package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The HelpCommand class implements a HelpCommand command
 * executable to list out all the formats in which user
 * can follow to input commands.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class HelpCommand extends Command {
    public HelpCommand() {
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
        return "__Task formats__\n"
                + "Todo: 'todo <task description>'\n"
                + "(e.g. todo visit new theme park)\n\n"
                + "Deadline: 'deadline <task description>"
                + " /by <yyyy-mm-dd hhmm>'\n(e.g. deadline submit report /by 2019-11-10 1500)\n\n"
                + "Event: 'event <task description>"
                + " /at <yyyy-mm-dd hhmm-hhmm>'\n"
                + "(e.g. event team project meeting /at 2019-10-02 1400-1500)\n\n"
                + "\n__Additional commands__"
                + "\nTo mark a task as done: 'done <task number>'."
                + "\nTo delete a task from your list: 'delete <task number>'."
                + "\nTo find a task in your list: 'find <keyword>'."
                + "\nTo set the priority of task to high/mid/low: "
                + "'priority <high/mid/low> <task number>'."
                + "\nTo close Dino, enter 'bye'.";
    }
}
