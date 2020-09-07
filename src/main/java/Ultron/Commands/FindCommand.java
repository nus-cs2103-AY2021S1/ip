package ultron.commands;

import java.util.List;
import java.util.stream.Collectors;

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
        StringBuilder message = new StringBuilder();
        message.append("Why do you always bothering me?\n");
        List<Task> result = taskList.getTasks()
                .stream()
                .filter(task -> task.toString()
                .contains(getArgument()))
                .collect(Collectors.toList());

        if (result.size() == 0) {
            message.append("There is literally nothing here\n");
        } else {
            for (int i = 0; i < result.size(); ++i) {
                message.append(String.format("%d. %s\n", i + 1, result.get(i)));
            }
        }
        ui.setMessage(message.toString());
    }
}
