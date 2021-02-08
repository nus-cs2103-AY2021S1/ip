package nite.command;

import nite.storage.Storage;
import nite.task.TaskList;
import nite.ui.Ui;

/**
 * Represents a HelpCommand where user wants a list of possible commands.
 */
public class HelpCommand extends Command {

    /**
     * Creates a HelpCommand.
     */
    public HelpCommand() {
    }

    /**
     * Executes the command to list the possible duke.Duke commands.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @return String displaying completion of Command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String commandsList =
                "  These are the Nite commands!!\n"
                + "  add a task\n    todo <description>\n"
                + "    deadline <description> /by <YYYY-MM-DD HHmm>\n"
                + "    event <description> /at <YYYY-MM-DD HHmm> to <YYYY-MM-DD HHmm>\n"
                + "  delete a task\n    delete <task number>\n"
                + "  mark task as done\n    done <task number>\n"
                + "  list all tasks\n    list\n"
                + "  find a task\n    find <keyword>\n"
                + "  sort tasks\n    sort <taskType> <taskType> <taskType>\n"
                + "  exit Nite\n    bye\n";
        return ui.showAction(commandsList);
    }
}
