package botbot.commands;

import botbot.tasks.TaskList;
import botbot.ui.Ui;
import botbot.utils.Storage;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_KEYWORD = "list";

    /**
     * Executes the list command.
     *
     * @param storage Storage of the bot.
     * @param tasks Task list to list.
     * @param ui Ui to show response of execution.
     * @return Response of execution.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.showListResponse(tasks);
    }
}
