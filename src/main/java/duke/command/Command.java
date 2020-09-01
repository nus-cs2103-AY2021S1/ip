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
                    dukeResponse = "\tSorry handsome but I'm not sure about this command :)";
                    throw new DukeInvalidCommandException("Sorry handsome but I'm not sure about this command :)");
                } else {
                    dukeResponse = foundMatchingTasks(tasks, taskInfo);
                }
            }
            storage.saveTaskList(tasks);
        } catch (DukeInvalidCommandException err) {
            System.out.println("\t" + err.getMessage());
        } catch (DukeIndexOutOfBoundsException err) {
            System.out.println("\t" + err.getMessage());
        } catch (DukeIncompleteCommandException err) {
            System.out.println("\t" + err.getMessage());
        } catch (DukeIOException err) {
            System.out.println("\t" + err.getMessage());
        } catch (DukeDateTimeParseException err) {
            System.out.println("\t" + err.getMessage());
        } catch (DukeNumberFormatException err) {
            System.out.println("\t" + err.getMessage());
        } finally {
            return dukeResponse;
        }
    }

    /**
     * Says goodbye to user.
     *
     * @return String of farewell.
     */
    public String bidFarewell() {
        return "\tBye. Hope to see you again soon!";
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks List of tasks to print.
     * @return List of tasks in taskList.
     */
    public String printList(TaskList tasks) {
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
    public String markTaskDone(TaskList tasks, String taskInfo) throws DukeIndexOutOfBoundsException {
        if (taskInfo.length() <= 5) {
            throw new DukeIndexOutOfBoundsException("\tThe task you want to mark is invalid");
        }
        taskInfo = taskInfo.replace("done", "").trim();
        int taskNo;
        try {
            taskNo = Integer.parseInt(taskInfo);
        } catch (NumberFormatException err) {
            throw new DukeIndexOutOfBoundsException("\tThe task you want to mark is invalid");
        }
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new DukeIndexOutOfBoundsException("\tThe task you want to mark is invalid");
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
    public String handleToDo(TaskList tasks, String taskInfo) throws DukeInvalidCommandException {
        if (taskInfo.trim().equals("todo")) {
            throw new DukeInvalidCommandException("\tThe command is incomplete handsome :D");
        }
        taskInfo = taskInfo.replace("todo", "").trim();
        return tasks.addTask(new ToDos(taskInfo));
    }

    /**
     * Adds deadline task to list of tasks.
     *
     * @param tasks    List of user's tasks.
     * @param taskInfo Description of deadline task.
     * @return String of words Duke say in response.
     */
    public String handleDeadLine(TaskList tasks, String taskInfo)
        throws DukeInvalidCommandException, DukeDateTimeParseException {
        taskInfo = taskInfo.replace("deadline", "");
        String[] stringArr = taskInfo.split("/by", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("\tThe command is incomplete handsome :D");
        }
        taskInfo = stringArr[0].trim();
        String by = stringArr[1].trim();
        return tasks.addTask(new Deadlines(taskInfo, by));
    }

    /**
     * Adds event task to list of tasks.
     *
     * @param tasks    List of user's tasks.
     * @param taskInfo Description of event task.
     * @return String of words Duke say in response.
     */
    public String handleEvent(TaskList tasks, String taskInfo)
        throws DukeInvalidCommandException, DukeDateTimeParseException {
        taskInfo = taskInfo.replace("event", "");
        String[] stringArr = taskInfo.split("/at", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("\tThe command is incomplete handsome :D");
        }
        taskInfo = stringArr[0].trim();
        String at = stringArr[1].trim();
        return tasks.addTask(new Events(taskInfo, at));
    }

    /**
     * Prints out the task if user input matches any word from task.
     *
     * @param tasks    List of user's tasks.
     * @param taskInfo Description of todos task.
     * @return List of matching tasks if any.
     */
    public String foundMatchingTasks(TaskList tasks, String taskInfo) {
        String[] taskInfos = taskInfo.trim().split(" ");
        List<Task> matchList = tasks.returnMatchingTasks(taskInfos);
        String dukeResponse = "";
        for (int i = 0; i < matchList.size(); i++) {
            if (i == 0) {
                dukeResponse = "\n\tHere are the matching tasks in your list:";
            }
            dukeResponse += String.format("\n\t%d. %s", i + 1, matchList.get(i));
        }
        return dukeResponse;
    }
}
