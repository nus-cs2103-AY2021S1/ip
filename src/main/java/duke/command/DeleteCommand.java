package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.task.Task;

public class DeleteCommand {
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
