package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskException;
import duke.task.Task;
import duke.task.TaskFactory;
import duke.task.TaskList;
import duke.task.TaskType;

public class TaskCommand {
    public static String execute(String in, TaskList taskList) throws DukeException {
        TaskType taskType = CommandParser.parseTaskType(in);
        String taskDetails = in.replaceFirst(taskType.toString().toLowerCase(), "").trim();
        if (taskType == TaskType.Invalid) {
            throw new InvalidCommandException("Something went wrong during the execution of the command. :-(");
        }
        return createTask(taskType, taskDetails, taskList, false, true);
    }

    private static String createTask(
            TaskType taskType, String details, TaskList taskList, boolean isDone, boolean shouldUpdateStorage)
            throws InvalidTaskException {
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
        TaskType taskType = CommandParser.parseTaskType(in.substring(1));
        String taskDetails = in.replaceFirst(taskType.toString().toLowerCase(), "").trim();
        if (taskType == TaskType.Invalid) {
            throw new InvalidCommandException("Something went wrong during the execution of the command. :-(");
        }
        createTask(taskType, taskDetails, taskList, isDone, false);
    }
}
