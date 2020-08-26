package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskException;
import duke.task.Task;
import duke.task.TaskFactory;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.util.Parser;
import duke.util.Storage;

// Handles all the logic behind any "task" command from the user
public class TaskCommand {
    /**
     * Executes any "task" command issued by the user.
     * Adds the task specified by the user to the taskList and updates save file after updating.
     *
     * @param in String "task" command issued by user
     * @param taskList TaskList list that contains tasks added by the user
     * @param storage Storage object to help with updating the save file
     * @return String response message to user
     * @throws DukeException If the task command provided does not fit the specified format
     */
    public static String execute(String in, TaskList taskList, Storage storage) throws DukeException {
        TaskType taskType = Parser.parseTaskType(in);
        String taskDetails =
                in.replaceFirst(taskType.toString().toLowerCase(), "").trim();
        return createTask(taskType, taskDetails, taskList, storage);
    }

    /**
     * Creates the specific task type based on the taskType parameter and adds it to the taskList.
     * Updates save file after updating.
     *
     * @param taskType TaskType the task type
     * @param details String the task details
     * @param taskList TaskList list that contains tasks added by the user
     * @param storage Storage object to help with updating the save file
     * @return String response message to user
     * @throws InvalidTaskException If the task command provided does not fit the specified format
     */
    private static String createTask(TaskType taskType, String details, TaskList taskList, Storage storage)
            throws DukeException {
        Task task = TaskFactory.createTask(taskType, details);
        taskList.add(task);
        storage.updateSaveFile(taskList);
        int len = taskList.size();
        return
            "Got it. I've added this task: \n" +
            "  " + task.toString() + "\n" +
            "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list.";
    }
}
