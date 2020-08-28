package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskException;
import duke.task.Task;
import duke.task.TaskFactory;
import duke.task.TaskList;
import duke.task.TaskType;

// Handles all the logic behind any "task" command from the userz
public class TaskCommand {
    /**
     * Executes any "task" command issued by the user.
     * Adds the task specified by the user to the taskList and updates save file after updating.
     *
     * @param in String "task" command issued by user
     * @param taskList TaskList list that contains tasks added by the user
     * @return String response message to user
     * @throws DukeException If the task command provided does not fit the specified format
     */
    public static String execute(String in, TaskList taskList) throws DukeException {
        TaskType taskType = CommandParser.parseTaskType(in);
        String taskDetails = in.replaceFirst(taskType.toString().toLowerCase(), "").trim();
        if (taskType == TaskType.Invalid) {
            throw new InvalidCommandException("Something went wrong during the execution of the command. :-(");
        }
        return createTask(taskType, taskDetails, taskList, false, true);
    }

    /**
     * Creates the specific task type based on the taskType parameter and adds it to the taskList.
     * Updates save file after updating.
     *
     * @param taskType TaskType the task type
     * @param details String the task details
     * @param taskList TaskList list that contains tasks added by the user
     * @return String response message to user
     * @throws InvalidTaskException If the task command provided does not fit the specified format
     */
    private static String createTask(
            TaskType taskType, String details, TaskList taskList, boolean isDone, boolean shouldUpdateStorage)
            throws DukeException {
        Task task = TaskFactory.createTask(taskType, details);
        taskList.add(task, shouldUpdateStorage);
        if (isDone) {
            task.markAsDone();
        }
        int len = taskList.size();
        return "Got it. I've added this task: \n"
                + "  " + task.toString() + "\n"
                + "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list.";
    }

    public static void loadSavedTasks(String in, TaskList taskList) throws DukeException {
        boolean isDone = in.charAt(0) == '1';
        String taskCommand = in.substring(1);
        TaskType taskType = CommandParser.parseTaskType(taskCommand);
        String taskDetails = taskCommand.replaceFirst(taskType.toString().toLowerCase(), "").trim();
        if (taskType == TaskType.Invalid) {
            throw new InvalidCommandException("Something went wrong during the execution of the command. :-(");
        }
        createTask(taskType, taskDetails, taskList, isDone, false);
    }
}
