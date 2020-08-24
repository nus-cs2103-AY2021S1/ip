package Ultron.Commands;

import Ultron.Exceptions.ExceptionType;
import Ultron.Exceptions.UltronException;
import Ultron.Storage;
import Ultron.TaskList;
import Ultron.Tasks.Task;
import Ultron.UI;

public class TaskAllocator extends Command{

    private final TaskCommand taskCommand;

    public TaskAllocator(String command, String arguments) throws UltronException {

        //Call the superclass
        super(false, arguments);

        try {
            //Get the state
            this.taskCommand = TaskCommand.valueOf(command.toLowerCase());

        } catch (IllegalArgumentException e) {

            //Throw a Duke exception
            throw new UltronException(command,
                ExceptionType.INVALID_COMMAND);
        }
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws UltronException {

        //Init the enum states
        Task task;
        String command = taskCommand.name();

        //Trim the args
        if (getArguments().trim().length() == 0) {

            //Throw an exception when there is nothing supplied
            throw new UltronException(command,
                    ExceptionType.NO_ARGUMENTS_SUPPLIED);
        }

        try {
            //Create a new task
            task = taskCommand.createTask(getArguments());

        } catch (IllegalStateException e) {

            //Throw a Duke exception
            throw new UltronException(command,
                    ExceptionType.INVALID_COMMAND);
        }

        //Add the task to the task list
        taskList.add(task);

        //Print out the message
        ui.print(String.format("Can't you keep track of '%s' yourself?\n"
                        + "Now you have %d burdens%n",
                task, taskList.size()));
    }
}
