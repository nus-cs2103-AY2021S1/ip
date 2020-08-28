package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.UI;
import ultron.exceptions.UltronException;
import ultron.tasks.Task;

public class FindCommand extends Command {

    /**
     * The find command for search function.
     * @param arguments String to find
     */
    public FindCommand(final String arguments) {
        super(false, arguments);
    }
    /**
     * Execute the command of Find command.
     * @param taskList List of tasks
     * @param ui       UI for Ultron
     * @param storage  Storage for Ultron
     * @throws UltronException
     */
    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) throws UltronException {

        boolean printed = false;
        int count = 1;
        ui.print("Why do you always bothering me?\n");
        for (Task task : taskList.getList()) {
            if (task.getMessage().contains(getArguments())) {
                ui.print(String.format("%d. %s\n", count++, task.toString()));
                printed = true;
            }
        }

        if (!printed) {
            ui.print("There is literally nothing here\n");
            return;
        }


    }
}
