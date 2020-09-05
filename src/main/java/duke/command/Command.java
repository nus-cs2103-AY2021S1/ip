package duke.command;

import java.util.List;

import duke.storage.DukeIOException;
import duke.storage.Storage;
import duke.task.Deadlines;
import duke.task.DukeDateTimeParseException;
import duke.task.DukeNumberFormatException;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

/**
 * The class Command denotes a command object.
 *
 * @author Alvin Chee
 */
public class Command {

    private CommandType commandType;
    private String taskInfo;

    /**
     * Constructs a command
     *
     * @param commandType Type of command.
     * @param taskInfo    Description of task.
     */
    public Command(CommandType commandType, String taskInfo) {
        this.commandType = commandType;
        this.taskInfo = taskInfo;
    }

    /**
     * Executes a command
     *
     * @param tasks   The list of tasks for user.
     * @param storage Storage object to store task list.
     */
    public String execute(TaskList tasks, Storage storage) {
        String dukeResponse = "";
        try {
            switch (commandType) {
            case BYE:
                dukeResponse = bidFarewell();
                break;
            case PRINT:
                dukeResponse =  printList(tasks);
                break;
            case MARKTASKDONE:
                dukeResponse =  markTaskDone(tasks, taskInfo);
                break;
            case HANDLETODO:
                dukeResponse =  handleToDo(tasks, taskInfo);
                break;
            case HANDLEDEADLINE:
                dukeResponse =  handleDeadLine(tasks, taskInfo);
                break;
            case HANDLEVENT:
                dukeResponse =  handleEvent(tasks, taskInfo);
                break;
            case DELETETASK:
                dukeResponse =  tasks.deleteTask(taskInfo);
                break;
            default:
                if (foundMatchingTasks(tasks, taskInfo).length() <= 0) {
                    throw new DukeInvalidCommandException("Sorry handsome but I'm not sure about this command :)");
                } else {
                    dukeResponse = foundMatchingTasks(tasks, taskInfo);
                }
            }
            storage.saveTaskList(tasks);
        } catch (DukeInvalidCommandException err) {
            dukeResponse = "\t" + err.getMessage();
        } catch (DukeIndexOutOfBoundsException err) {
            dukeResponse = "\t" + err.getMessage();
        } catch (DukeIncompleteCommandException err) {
            dukeResponse = "\t" + err.getMessage();
        } catch (DukeIOException err) {
            dukeResponse = "\t" + err.getMessage();
        } catch (DukeDateTimeParseException err) {
            dukeResponse = "\t" + err.getMessage();
        } catch (DukeNumberFormatException err) {
            dukeResponse = "\t" + err.getMessage();
        } finally {
            return dukeResponse;
        }
    }

    /**
     * Says goodbye to user.
     *
     * @return String of farewell.
     */
    private String bidFarewell() {
        return "\tBye. Hope to see you again soon!";
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks List of tasks to print.
     * @return List of tasks in taskList.
     */
    private String printList(TaskList tasks) {
        String toPrint = "\tHere are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            toPrint += String.format("\n\t%d. %s", i + 1, tasks.get(i));
        }
        return toPrint;
    }

    /**
     * Marks task with task index in taskInfo as done.
     *
     * @param tasks    List of user's tasks.
     * @param taskInfo Description of task.
     * @return String of words Duke say in response.
     */
    private String markTaskDone(TaskList tasks, String taskInfo) throws DukeIndexOutOfBoundsException {
        String requiredTask = taskInfo.replace("done", "").trim();
        int taskNo;
        try {
            taskNo = Integer.parseInt(requiredTask);
        } catch (NumberFormatException err) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        int index = taskNo - 1;
        Task task = tasks.remove(index).doneTask();
        tasks.add(index, task);
        return "\tNice! I've marked this task as done:" + "\n\t" + task;
    }

    /**
     * Adds todos task to list of tasks.
     *
     * @param tasks    List of user's tasks.
     * @param taskInfo Description of todos task.
     * @return String of words Duke say in response.
     */
    private String handleToDo(TaskList tasks, String taskInfo) throws DukeInvalidCommandException {
        if (taskInfo.trim().equals("todo")) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        String requiredTask = taskInfo.replace("todo", "").trim();
        return tasks.addTask(new ToDos(requiredTask));
    }

    /**
     * Adds deadline task to list of tasks.
     *
     * @param tasks    List of user's tasks.
     * @param taskInfo Description of deadline task.
     * @return String of words Duke say in response.
     */
    private String handleDeadLine(TaskList tasks, String taskInfo)
        throws DukeInvalidCommandException, DukeDateTimeParseException {
        String taskWithBy = taskInfo.replace("deadline", "");
        // splits task to 2 segments - task information and date
        String[] stringArr = taskWithBy.split("/by", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        String requiredTask = stringArr[0].trim();
        String by = stringArr[1].trim();
        return tasks.addTask(new Deadlines(requiredTask, by));
    }

    /**
     * Adds event task to list of tasks.
     *
     * @param tasks    List of user's tasks.
     * @param taskInfo Description of event task.
     * @return String of words Duke say in response.
     */
    private String handleEvent(TaskList tasks, String taskInfo)
        throws DukeInvalidCommandException, DukeDateTimeParseException {
        String taskWithAt = taskInfo.replace("event", "");
        // splits task to 2 segments - task information and date
        String[] stringArr = taskWithAt.split("/at", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        String requiredTask = stringArr[0].trim();
        String at = stringArr[1].trim();
        return tasks.addTask(new Events(requiredTask, at));
    }

    /**
     * Prints out the task if user input matches any word from task.
     *
     * @param tasks    List of user's tasks.
     * @param taskInfo Description of todos task.
     * @return List of matching tasks if any.
     */
    private String foundMatchingTasks(TaskList tasks, String taskInfo) {
        String[] taskInfos = taskInfo.trim().split(" ");
        List<Task> matchList = tasks.returnMatchingTasks(taskInfos);
        String dukeResponse = "";
        for (int i = 0; i < matchList.size(); i++) {
            if (i == 0) {
                dukeResponse = "\n\t\tHere are the matching tasks in your list:";
            }
            dukeResponse += String.format("\n\t\t%d. %s", i + 1, matchList.get(i));
        }
        return dukeResponse.trim();
    }
}
