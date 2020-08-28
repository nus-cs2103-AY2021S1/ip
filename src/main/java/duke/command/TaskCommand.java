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
        return createTask(taskType, taskDetails, taskList);
    }

    private static String createTask(TaskType taskType, String details, TaskList taskList)
            throws InvalidTaskException {
        Task task = TaskFactory.createTask(taskType, details);
        taskList.add(task);
        int len = taskList.size();
        return "Got it. I've added this task: \n"
                + "  " + task.toString() + "\n"
                + "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list.";
    }
}
