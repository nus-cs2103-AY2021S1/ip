package duke.command;

import duke.exception.InvalidCommandException;
import duke.response.NormalResponse;
import duke.response.Response;
import duke.task.Task;
import duke.task.TaskList;
// Handles all the logic behind any "delete" command from the user
public class DeleteCommand extends Command {
    /**
     * Executes any "delete" command issued by the user.
     * Removes the task specified by the user from taskList and updates save file after deletion.
     *
     * @param in String "delete" command issued by user.
     * @param taskList TaskList list that contains tasks added by the user.
     * @return Response response message to user.
     * @throws InvalidCommandException If an invalid index is provided.
     */
    public static Response execute(String in, TaskList taskList) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(in.replaceFirst("delete", "").trim());
            Task task = taskList.remove(index - 1);
            int len = taskList.size();

            String response = "Noted. I've removed this task:\n"
                    + "  " + task.toString() + "\n"
                    + "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list.";
            return new NormalResponse(response);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Please input a valid index.");
        }
    }
}
