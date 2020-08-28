package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskList;

// Handles all the logic behind any "done" command from the user
public class DoneCommand extends Command {
    /**
     * Executes any "done" command issued by the user.
     * Marks the task in the taskList specified by the user as done.
     *
     * @param in String "done" command issued by user
     * @param taskList TaskList list that contains tasks added by the user
     * @return String response message to user
     * @throws InvalidCommandException If an invalid index is provided
     */
    public static String execute(String in, TaskList taskList) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(in.replaceFirst("done ", "")) - 1;
            Task task = taskList.get(index);
            task.markAsDone();
            taskList.update(index);
            return "Nice! I've marked this task as done\n  " + task.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Please input a valid index");
        }
    }
}
