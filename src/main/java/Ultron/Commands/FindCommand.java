package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.tasks.Task;
import ultron.ui.UI;

public class FindCommand extends Command {

    /**
     * The find command for search function.
     *
     * @param arguments String to find
     */
    public FindCommand(final String arguments) {
        super(false, arguments);
    }

    /**
     * Execute the command of Find command.
     *
     * @param taskList List of tasks
     * @param ui       UI for Ultron
     * @param storage  Storage for Ultron
     */
    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) {

        boolean printed = false;
        int count = 1;
        StringBuilder message = new StringBuilder();
        ui.print("Why do you always bothering me?\n");
        for (Task task : taskList.getList()) {
            if (task.getMessage().contains(getArguments())) {
                message.append((String.format("%d. %s\n", count++, task.toString())));
                printed = true;
            }
        }

        if (!printed) {
            message.append("There is literally nothing here\n");
            return;
        }

        ui.setMessage(message.toString());

    }
}
