package duke.command;

import duke.DukeException;
import duke.History;
import duke.Storage;
import duke.task.TaskList;

/**
 * Command that displays all the commands that Duke is able to understand to the user.
 */
public class HelpCommand extends Command {
    /**
     * Displays all the commands that Duke supports to the user.
     *
     * @param taskList the List of all the Tasks that Duke has.
     * @param storage the database of Tasks that is saved to the user's local storage.
     * @param history the state of all changes made to Duke's TaskList.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Storage storage, History history) throws DukeException {
        return "Here are all the commands that you can give me:\n"
                + "- 'list' to see all the tasks\n"
                + "- 'todo <description>' to add a new todo task\n"
                + "- 'event <description> /at <YYYY/MM/DD> <HH:MM>' to add a new todo task\n"
                + "- 'todo <description> /by <YYYY/MM/DD> <HH:MM>' to add a new todo task\n"
                + "- 'delete <taskId>' to delete the task with the given ID\n"
                + "- 'done <taskId>' to mark the task with the given ID as completed\n"
                + "- 'find <keyword>' to find all tasks with the descriptions matching the given keyword\n"
                + "- 'undo' to undo the previous command given to Duke\n"
                + "- 'help' to view this list again\n"
                + "- 'bye' to exit the program\n";
    }
}
