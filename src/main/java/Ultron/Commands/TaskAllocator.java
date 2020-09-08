package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;
import ultron.tasks.Task;
import ultron.ui.UI;

public final class TaskAllocator extends Command {

    /**
     * Store TaskCommand corresponding to the command.
     */
    private final TaskCommand taskCommand;

    /**
     * Allocates the correct task.
     *
     * @param command   Command given.
     * @param arguments Arguments given.
     * @throws UltronException if the command is invalid.
     */
    public TaskAllocator(final String command,
                         final String arguments) throws UltronException {
        super(false, arguments);
        try {
            this.taskCommand = TaskCommand.valueOf(command.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new UltronException(command,
                    ExceptionType.INVALID_COMMAND);
        }
    }

    /**
     * Add the correct Task to the tasklist.
     *
     * @param taskList List of tasks.
     * @param ui       UI for Ultron.
     * @param storage  Storage for Ultron.
     * @throws UltronException if there are no arguments or.
     *                         if there was an error creating the task.
     */
    @Override
    public void execute(final TaskList taskList,
            final UI ui,
            final Storage storage) throws UltronException {
        Task task;
        String command = taskCommand.name();
        if (getArgument().trim().length() == 0) {
            throw new UltronException(command,
                    ExceptionType.NO_ARGUMENTS_SUPPLIED);
        }

        try {
            task = taskCommand.createTask(getArgument());
        } catch (IllegalStateException e) {
            throw new UltronException(command,
                    ExceptionType.INVALID_COMMAND);
        }
        taskList.add(task);
        ui.setMessage(String.format("Can't you keep track of '%s' yourself?\n"
                + "Now you have %d burdens%n",
                task, taskList.size()));
    }
}
