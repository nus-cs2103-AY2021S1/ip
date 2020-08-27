package Command;

import Tasks.TaskManager;

/**
 * Represents a command that displays all existing tasks.
 */
public class ListCommand extends Command{
    /**
     * Prints all the existing task and its information.
     */
    public static void execute(){
        TaskManager.listing();
    }
}
