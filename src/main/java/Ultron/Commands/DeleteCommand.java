package ultron.commands;

import ultron.Parser;
import ultron.Storage;
import ultron.TaskList;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;
import ultron.ui.UI;

public final class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand.
     *
     * @param arguments Arguments for delete command.
     */
    public DeleteCommand(final String arguments) {
        super(false, arguments);
    }

    /**
     * Executes the Delete Command.
     *
     * @param taskList List of tasks.
     * @param ui       UI for Ultron.
     * @param storage  Storage for Ultron.
     * @throws UltronException if the arguments are invalid.
     */
    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) throws UltronException {

        //Initialise index
        int index = Parser.parseInteger(this.getArguments());

        //Check if the index is out of range
        if (index < 0 || index >= taskList.size()) {

            //Throw an Ultron exception if it is out of range
            throw new UltronException("delete",
                Integer.toString(index + 1),
                ExceptionType.INVALID_ARGUMENT);
        }
        ui.setMessage(String.format("What are you doing removing this?!?!\n  "
                + "%s\nNow you have %d burdens%n",
            taskList.get(index),
            taskList.size()));
        taskList.remove(index);
    }
}
