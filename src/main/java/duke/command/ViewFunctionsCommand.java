package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a command to view a list of Duke's function and the function commands.
 */
public class ViewFunctionsCommand extends Command {

    /**
     * Prints all of Duke's functions and the commands for each function.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String commands = "  Below is a list of all the commands for my functions: \n\n"
                + "  1. Create a new task: \n\n"
                + "\t  1.1 Todo: 'todo' {task description}. For eg, todo eat \n"
                + "\t  1.2 Deadline: 'deadline' {task description} '/by' {deadline date}.\n\t\t"
                + "  Input the date using the format: 'dd/mm/yyyy hh:mm'. "
                + "For eg, deadline return book /by 12/2/2020 13:00 \n"
                + "\t  1.3 Event: 'event' {task description} '/at' {event date}.\n\t\t"
                + "  Input the date using the format: 'dd/mm/yyyy hh:mm'. "
                + "For eg, event project meeting /at 1/3/2020 12:00 \n"
                + "  \n  2. To display all tasks in your list: 'list' \n"
                + "  \n  3. To mark a task as completed: 'done' {task ID}. For eg, 'done 2' \n"
                + "  \n  4. To delete a task: 'delete' {task ID}. For eg, 'delete 2' \n"
                + "  \n  6. To search for a task by date: 'find_by_date' {date}. \n"
                + "     Input the date using the format: 'dd/mm/yyyy'. For eg, 'find_by_date 12/2/2020' \n"
                + "  \n  5. To end this chat: 'bye' \n";
        ui.printReply(commands);
    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
