package taskbot.command;

import taskbot.task.TaskList;

/**
 * Encapsulates a command to end the program.
 */
public class ExitCommand extends Command {
    /**
     * Creates an ExitCommand.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Gives instructions on how to use the exit command.
     * @return A string of instructions to use the command.
     */
    public static String getInstruction() {
        return "Exits the program.\n"
                + "Format: exit";
    }

    @Override
    public String execute(TaskList taskList) {
        // Returns a farewell message
        return "Goodbye, I await your next visit.";
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        /* Check if obj is an instance of this class.
           All ExitCommand instances are equal.
         */
        return obj instanceof ExitCommand;
    }
}
