package duke.command;

import java.util.List;
import java.util.stream.IntStream;
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
            case FINDMATCHINGTASK:
                dukeResponse = findMatchingTasks(tasks, taskInfo);
                break;
            case DELETETASK:
                dukeResponse =  tasks.deleteTask(taskInfo);
                break;
            case DUPLICATE:
                List<String> duplicateList = tasks.detectDuplicates();
                dukeResponse =  findDuplicateTasks(duplicateList);
                break;
            case REMOVEDUPLICATES:
                dukeResponse = removeDuplicates(tasks);
                break;
            case CLEAR:
                dukeResponse = clearTaskList(tasks);
                break;
            case HELP:
                dukeResponse = printHelp();
                break;
            default:
                throw new DukeInvalidCommandException("Sorry Poppins but I'm not sure about this command :)");
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
            assert dukeResponse != null;
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
            toPrint += String.format("\n\t\t%d. %s", i + 1, tasks.get(i));
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
        String done = "done ";
        String requiredTask = taskInfo.replace("done", "").trim();
        if (taskInfo.length() <= done.length()) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
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
        assert index >= 0 : "Index should not be lesser than 0";
        Task task = tasks.remove(index).doneTask();
        assert task != null : "Task should not be null";
        tasks.add(index, task);
        return "\tNice! I've marked this task as done:" + "\n\t\t" + task;
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
            throw new DukeInvalidCommandException("The command is incomplete Poppins :D");
        }
        String requiredTask = taskInfo.replace("todo", "").trim();
        assert taskInfo.length() > 0 : "taskInfo should not be empty";
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
            throw new DukeInvalidCommandException("The command is incomplete Poppins :D");
        }
        String requiredTask = stringArr[0].trim();
        String by = stringArr[1].trim();
        if (requiredTask.length() == 0 || by.length() == 0) {
            throw new DukeInvalidCommandException("The command is incomplete Poppins :D");
        }
        assert requiredTask.length() > 0 : "taskInfo should not be empty";
        assert by.length() > 0 : "by should not be empty";
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
            throw new DukeInvalidCommandException("The command is incomplete Poppins :D");
        }
        String requiredTask = stringArr[0].trim();
        String at = stringArr[1].trim();
        if (requiredTask.length() == 0 || at.length() == 0) {
            throw new DukeInvalidCommandException("The command is incomplete Poppins :D");
        }
        assert requiredTask.length() > 0 : "taskInfo should not be empty";
        assert at.length() > 0 : "at should not be empty";
        return tasks.addTask(new Events(requiredTask, at));
    }

    /**
     * Prints out the task if user input matches any word from task.
     *
     * @param tasks    List of user's tasks.
     * @param taskInfo Description of todos task.
     * @return List of matching tasks if any.
     */
    private String findMatchingTasks(TaskList tasks, String taskInfo) {
        String taskInfoFind = taskInfo.replace("find", "");
        String[] taskInfos = taskInfoFind.trim().split(" ");
        assert taskInfos.length > 0 : "taskInfos length should be >= 1";
        List<Task> matchList = tasks.returnMatchingTasks(taskInfos);
        assert matchList != null : "matchList should not be null";
        String dukeResponse = matchList.size() == 0
            ? "Sorry Poppins I am unable to find any matches."
            : "\tHere are the matching tasks in your list:";
        String matches = IntStream
            .range(0, matchList.size())
            .mapToObj(i -> String.format("\n\t\t%d. %s", i + 1, matchList.get(i)))
            .reduce("", (prevStr, nextStr) -> prevStr + nextStr);
        dukeResponse += matches;
        return dukeResponse;
    }

    /**
     * Returns string of duke response of list containing duplicate tasks.
     *
     * @param duplicateTaskList List of duplicate tasks.
     * @return String of duke response containing duplicate tasks.
     */
    private String findDuplicateTasks(List<String> duplicateTaskList) {
        String dukeResponse = "\tHere are the duplicate tasks:";
        String duplicates = IntStream.range(0, duplicateTaskList.size())
            .mapToObj(i -> String.format("\n\t\t%d. %s", i + 1, duplicateTaskList.get(i)))
            .reduce("", (prevStr, nextStr) -> prevStr + nextStr);
        return dukeResponse += duplicates;
    }

    /**
     * Removes all duplicate tasks except for the first entry.
     *
     * @param tasks List of tasks.
     * @return String of duke response containing new task list.
     */
    private String removeDuplicates(TaskList tasks) {
        tasks.removeDuplicatesExceptFirst();
        String dukeResponse = "\tDuplicates removed. Only first copies remain.\n";
        dukeResponse += printList(tasks);
        return dukeResponse;
    }

    /**
     * Clears the taskList.
     * @param tasks The list of tasks to be cleared.
     * @return String of duke response containing new empty task list.
     */
    private String clearTaskList(TaskList tasks) {
        tasks.clear();
        String dukeResponse = "\tList cleared.\n";
        dukeResponse += printList(tasks);
        return dukeResponse;
    }

    /**
     * Prints the commands that duke understands.
     * @return String of duke response containing all the commands duke knows.
     */
    private String printHelp() {
        String dukeResponse = "\tI can only understand these human commands :(\n";
        dukeResponse += "\n\n\t'help' : I will tell you the only human commands I know.";
        dukeResponse += "\n\n\t'list' : I will list you all the tasks you told me.";
        dukeResponse += "\n\n\t'todo (homework)' : I will add (homework) to the list.";
        dukeResponse += "\n\n\t'deadline (homework) /by (2020-12-25 1600)' : I will " +
            "add (homework) to the list with the (specified deadline)";
        dukeResponse += "\n\n\t'event (Christmas) /at (2020-12-25 1600)' : I will " +
            "add (Christmas) event to the list at the (specified time)";
        dukeResponse += "\n\n\t'find book' : I will find any task that matches book.";
        dukeResponse += "\n\n\t'delete 2' : I will delete task 2 from the list.";
        dukeResponse += "\n\n\t'done 3' : I will mark task 3 in the list as complete.";
        dukeResponse += "\n\n\t'duplicate' : I will list you all the duplicate tasks.";
        dukeResponse += "\n\n\t'remove duplicates' : I will remove all duplicate tasks except the first copy of each task.";
        dukeResponse += "\n\n\t'clear please' : I will clear the tasks list for you.";
        dukeResponse += "\n\n\t'bye' : I will go away ... quietly :(";
        return dukeResponse;
    }
}
