package taskbot.command;

import taskbot.task.TaskList;

/**
 * Encapsulates a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Gives instructions on how to use the list command.
     * @return A string of instructions to use the command.
     */
    public static String getInstruction() {
        return "Lists the tasks in the order of when they were added.\n"
                + "Format: list\n";
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.listTasks();
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        /* Check if obj is an instance of this class.
           All ListCommand instances are equal.
         */
        return obj instanceof ListCommand;
    }
}
