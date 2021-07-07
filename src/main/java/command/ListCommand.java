package command;

import duke.Storage;
import duke.TaskList;
import ui.Ui;

/**
 * List all tasks in the current task list
 */
public class ListCommand extends Command {
    /**
     * List all tasks saved in the current task list
     *
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Here are the tasks in your list:");
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            ui.print(String.format("%d. %s", i + 1, tasks.show(i)));
            response.append(String.format("%d. %s%n", i + 1, tasks.show(i)));
        }
        return new CommandResult(response.toString());
    }
}
