package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String commandsList =
                "\t These are the common Nite commands used in various situations:\n"
                + "\t add a task\n\t\t todo <description>\n"
                + "\t\t deadline <description> /by <YYYY-MM-DD HH:mm>\n"
                + "\t\t event <description> /at <YYYY-MM-DD HH:mm> to <YYYY-MM-DD HH:mm>\n"
                + "\t delete a task\n\t\t delete <task number>\n"
                + "\t mark task as done\n\t\t done <task number>\n"
                + "\t list all tasks\n\t\t list\n"
                + "\t find a task\n\t\t find <keyword>\n"
                + "\t exit Nite\n\t\t bye\n";
        ui.showAction(commandsList);
    }
}
