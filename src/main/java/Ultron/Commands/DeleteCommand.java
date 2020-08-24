package ultron.commands;

import ultron.Parser;
import ultron.Storage;
import ultron.TaskList;
import ultron.UI;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;
import ultron.tasks.Task;


public final class DeleteCommand extends Command {

    public DeleteCommand(final String arguments) {
        super(false, arguments);
    }

    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) throws UltronException {

        //Initialise index
        int index = Parser.parseInteger(this.getArguments());

        //Check if the index is out of range
        if (index >= taskList.size()) {

            //Throw an Ultron exception if it is out of range
            throw new UltronException("delete",
                    Integer.toString(index + 1),
                    ExceptionType.INVALID_ARGUMENT);
        }

        //Get the task
        Task tsk = taskList.get(index);

        //Remove the task
        taskList.remove(index);

        //Print the delete message
        ui.print(String.format("What are you doing removing this?!?!\n  "
                        + "%s\nNow you have %d burdens%n",
                tsk,
                taskList.size()));
    }
}
