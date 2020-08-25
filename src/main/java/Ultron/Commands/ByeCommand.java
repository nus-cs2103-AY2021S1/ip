package ultron.commands;

import ultron.exceptions.UltronException;
import ultron.exceptions.ExceptionType;
import ultron.TaskList;
import ultron.UI;
import ultron.Storage;

public class ByeCommand extends Command{
    /**
     * Creates the bye command with arguments.
     * @param arguments Arguments for bye command
     */
    public ByeCommand(String arguments){
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
    public void execute(TaskList taskList, UI ui, Storage storage) throws UltronException {

        if (this.getArguments().trim().length() > 0){
            throw new UltronException("bye", ExceptionType.TOO_MUCH_ARGUMENTS);
        }

        //Save the storage
        storage.writeAll(taskList.getList());

        ui.printEnd();
    }
}
