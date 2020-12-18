package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskException;
import duke.response.NormalResponse;
import duke.response.Response;
import duke.task.Task;
import duke.task.TaskFactory;
import duke.task.TaskList;
import duke.task.TaskType;

// Handles all the logic behind any "task" command from the user.
public class TaskCommand {
    private static final String INVALID_COMMAND_MSG = "Something went wrong during the execution of the command. :-(";

    /**
     * Executes any "task" command issued by the user.
     * Adds the task specified by the user to the taskList and updates save file after updating.
     *
     * @param in String "task" command issued by user.
     * @param taskList TaskList list that contains tasks added by the user.
     * @return Response response message to user.
     * @throws DukeException If the task command provided does not fit the specified format.
     */
    public static Response execute(String in, TaskList taskList) throws DukeException {
        TaskType taskType = CommandParser.parseTaskType(in);
        String taskDetails = in.replaceFirst(taskType.toString().toLowerCase(), "").trim();
        if (taskType == TaskType.Invalid) {
            throw new InvalidCommandException(INVALID_COMMAND_MSG);
        }
        String response = createTask(taskType, taskDetails, taskList, false, true);
        return new NormalResponse(response);
    }

    /**
     * Creates the specific task type based on the taskType parameter and adds it to the taskList.
     * Updates save file after updating.
     *
     * @param taskType TaskType the task type.
     * @param details String the task details.
     * @param taskList TaskList list that contains tasks added by the user.
     * @return String response message to user.
     * @throws InvalidTaskException If the task command provided does not fit the specified format.
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
        boolean hasSingleTask = len == 1;

        String response = "Got it. I've added this task: \n"
                + "  " + task.toString() + "\n"
                + "Now you have " + len + " task" + (hasSingleTask ? "" : "s") + " in the list.";
        return response;
    }

    /**
     * Parses and loads the save string into the task list.
     *
     * @param in String the save string.
     * @param taskList TaskList list that contains tasks added by the user.
     * @throws DukeException If save string is invalid.
     */
    public static void loadSavedTasks(String in, TaskList taskList) throws DukeException {
        boolean isDone = in.charAt(0) == '1';
        String[] details = in.substring(1).split(Task.TAGS_DELIMITER);
        String taskCommand = details[0].trim();

        TaskType taskType = CommandParser.parseTaskType(taskCommand);
        if (taskType == TaskType.Invalid) {
            throw new InvalidCommandException(INVALID_COMMAND_MSG);
        }

        String taskDetails = taskCommand.replaceFirst(taskType.toString().toLowerCase(), "").trim();
        createTask(taskType, taskDetails, taskList, isDone, false);

        boolean hasTags = details.length == 2;
        if (hasTags) {
            String[] tagsToAdd = details[1]
                    .replaceAll("#", "")
                    .replaceFirst(Task.TAGS_DELIMITER, "")
                    .trim()
                    .split(" ");
            taskList.get(taskList.size() - 1).addTags(tagsToAdd);
        }
    }
}
