package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.task.Task;

// Handles all the logic behind any "delete" command from the user
public class DeleteCommand {
    /**
     * Executes any "delete" command issued by the user.
     * Removes the task specified by the user from taskList and updates save file after deletion.
     *
     * @param in String "delete" command issued by user
     * @param taskList TaskList list that contains tasks added by the user
     * @param storage Storage object to help with updating the save file
     * @return String response message to user
     * @throws InvalidCommandException If an invalid index is provided
     */
    public static String execute(String in, TaskList taskList, Storage storage) throws InvalidCommandException {
        try {
            int index =
                Integer.parseInt(
                    in.replaceFirst("delete", "").trim()
                );
            Task task = taskList.remove(index - 1);
            storage.updateSaveFile(taskList);
            int len = taskList.size();
            return
                "Noted. I've removed this task:\n" +
                "  " + task.toString() + "\n" +
                "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list.";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Please input a valid index.");
        }
    }
}
