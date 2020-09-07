package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;
import ultron.tasks.Task;
import ultron.ui.UI;

public class FindCommand extends Command {

    /**
     * The find command for search function.
     *
     * @param arguments String to find.
     */
    public FindCommand(final String arguments) {
        super(false, arguments);
    }

    /**
     * Execute the command of Find command.
     *
     * @param taskList List of tasks.
     * @param ui       UI for Ultron.
     * @param storage  Storage for Ultron.
     * @throws UltronException When there are no arguments.
     */
    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) throws UltronException {

        if (getArgument().length() == 0) {
            throw new UltronException("find", getArgument(), ExceptionType.NO_ARGUMENTS_SUPPLIED);
        }
        assert getArgument().length() > 0;
        boolean isPrinted = false;
        int count = 0;
        StringBuilder message = new StringBuilder();
        message.append("Why do you always bothering me?\n");
        for (Task task : taskList.getTasks()) {
            if (task.getMessage().contains(getArgument())) {
                message.append((String.format("%d. %s\n", ++count, task)));
                isPrinted = true;
            }
        }
        if (!isPrinted) {
            message.append("There is literally nothing here\n");
        }
        ui.setMessage(message.toString());
    }
}
