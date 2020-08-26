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

public class TaskCommand extends Command {
    public static String execute(String in, TaskList taskList, Storage storage) throws DukeException {
        TaskType taskType = Parser.parseTaskType(in);
        String taskDetails =
                in.replaceFirst(taskType.toString().toLowerCase(), "").trim();
        if (taskType == TaskType.Invalid) {
            throw new InvalidCommandException("Something went wrong during the execution of the command. :-(");
        }
        return createTask(taskType, taskDetails, taskList, storage);
    }

    private static String createTask(TaskType taskType, String details, TaskList taskList, Storage storage)
            throws InvalidTaskException {
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
