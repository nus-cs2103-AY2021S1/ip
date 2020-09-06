package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;
import ultron.ui.UI;

/**
 * Bye command.
 */
public final class ByeCommand extends Command {
    /**
     * Creates the bye command with arguments.
     *
     * @param arguments Arguments for bye command.
     */
    public ByeCommand(final String arguments) {
        super(true, arguments);
    }

    /**
     * Execute the instructions for bye command.
     *
     * @param taskList Task list which holds the task.
     * @param ui       UI for Ultron.
     * @param storage  Storage for the task.
     * @throws UltronException when there are too much arguments.
     */
    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) throws UltronException {

        if (this.getArguments().trim().length() > 0) {
            throw new UltronException("bye", ExceptionType.TOO_MUCH_ARGUMENTS);
        }
        assert this.getArguments().trim().length() == 0;
        storage.writeAll(taskList.getList());
    }
}
