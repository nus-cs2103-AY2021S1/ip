package Mattbot.Command;

import Mattbot.Tasks.TaskManager;

/**
 * Represents a command that displays all existing tasks.
 */
public class ListCommand extends Command {
    /**
     * Prints all the existing task and its information.
     * Returns all the existing tasks.
     *
     * @return String current tracking list.
     */
    public static String execute2() {
        return TaskManager.listing2();
    }
}
