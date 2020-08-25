package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.UI;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;

public final class ByeCommand extends Command {
    /**
     * Creates the bye command with arguments.
     * @param arguments Arguments for bye command
     */
    public ByeCommand(final String arguments) {
        super(true, arguments);
    }

    /**
     * Execute the instructions for bye command.
     * @param taskList Task list which holds the task
     * @param ui       UI for Ultron
     * @param storage  Storage for the task
     * @throws UltronException
     */
    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) throws UltronException {

        if (this.getArguments().trim().length() > 0) {
            throw new UltronException("bye", ExceptionType.TOO_MUCH_ARGUMENTS);
        }

        //Save the storage
        storage.writeAll(taskList.getList());

        ui.printEnd();
    }
}
