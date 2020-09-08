package botbot.commands;

import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param storage Storage of the bot.
     * @param tasks Task list to list.
     * @param ui Ui to print list.
     * @return Status of execution.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        if (tasks.size() <= 0) {
            return ui.printStatus("your list is empty!\n");
        } else {
            return ui.printStatus("here's your list:\n" + tasks);
        }
    }
}
