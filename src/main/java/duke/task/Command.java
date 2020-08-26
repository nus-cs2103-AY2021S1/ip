package duke.task;

import duke.storage.DukeIOException;
import duke.storage.Storage;
import java.util.List;

/**
 * The class Command denotes a command object.
 *
 * @author Alvin Chee
 */
public class Command {

    private CommandType commandType;
    private String taskInfo;
    private boolean isExit;

    /**
     * Constructs a command
     *
     * @param commandType  Type of command.
     * @param taskInfo  Description of task.
     */
    public Command(CommandType commandType, String taskInfo) {
        this.commandType = commandType;
        this.taskInfo = taskInfo;
    }

    /**
     * Executes a command
     *
     * @param tasks  The list of tasks for user.
     * @param storage   Storage object to store task list.
     */
    public void execute(TaskList tasks, Storage storage) {
        try {
            switch (commandType) {
            case BYE:
                isExit = true;
                break;
            case PRINT:
                printList(tasks);
                break;
            case MARKTASKDONE:
                markTaskDone(tasks, taskInfo);
                storage.saveTaskList();
                break;
            case HANDLETODO:
                handleToDo(tasks, taskInfo);
                break;
            case HANDLEDEADLINE:
                handleDeadLine(tasks, taskInfo);
                break;
            case HANDLEVENT:
                handleEvent(tasks, taskInfo);
                break;
            default :
                isExit = false;
                if (!foundMatchingTasks(tasks, taskInfo)) {
                    throw new DukeInvalidCommandException("Sorry handsome but I'm not sure about this command :)");
                }
                break;
            }
        }  catch (DukeInvalidCommandException err) {
            System.out.println("\t" + err.getMessage());
        } catch (DukeIndexOutOfBoundsException err) {
            System.out.println("\t" + err.getMessage());
        } catch (DukeIncompleteCommandException err) {
            System.out.println("\t" + err.getMessage());
        } catch (DukeIOException err) {
            System.out.println("\t" + err.getMessage());
        } catch (DukeDateTimeParseException err) {
            System.out.println("\t" + err.getMessage());
        }
    }

    /**
     * Exits Duke programme.
     *
     * @return True if BYE command is called.
     */
    public boolean isExit() {
        if (isExit) {
            System.out.print("\tBye. Hope to see you again soon!");
        }
        return isExit;
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks  List of tasks to print.
     */
    public void printList(TaskList tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("\t%d. %s", i + 1, tasks.get(i)));
        }
    }

    /**
     * Marks task with task index in taskInfo as done.
     *
     * @param tasks  List of user's tasks.
     * @param taskInfo  Description of task.
     */
    public void markTaskDone(TaskList tasks, String taskInfo) throws DukeIndexOutOfBoundsException{
        if (taskInfo.length() <= 5) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        taskInfo = taskInfo.replace("done", "").trim();
        int taskNo;
        try {
            taskNo = Integer.parseInt(taskInfo);
        } catch (NumberFormatException err){
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        System.out.println("\tNice! I've marked this task as done:");
        int index = taskNo - 1;
        Task task = tasks.remove(index).doneTask();
        System.out.println("\t" + task);
        tasks.add(index, task);
    }

    /**
     * Adds todos task to list of tasks.
     *
     * @param tasks  List of user's tasks.
     * @param taskInfo  Description of todos task.
     */
    public void handleToDo(TaskList tasks, String taskInfo) throws DukeInvalidCommandException{
        if (taskInfo.trim().equals("todo")) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        taskInfo = taskInfo.replace("todo", "").trim();
        tasks.addTask(new ToDos(taskInfo));
    }

    /**
     * Adds deadline task to list of tasks.
     *
     * @param tasks  List of user's tasks.
     * @param taskInfo  Description of deadline task.
     */
    public void handleDeadLine(TaskList tasks, String taskInfo) throws DukeInvalidCommandException, DukeDateTimeParseException{
        taskInfo = taskInfo.replace("deadline", "");
        String[] stringArr = taskInfo.split("/by", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        taskInfo = stringArr[0].trim();
        String by = stringArr[1].trim();
        tasks.addTask(new Deadlines(taskInfo, by));
    }

    /**
     * Adds event task to list of tasks.
     *
     * @param tasks  List of user's tasks.
     * @param taskInfo  Description of event task.
     */
    public void handleEvent(TaskList tasks, String taskInfo) throws DukeInvalidCommandException, DukeDateTimeParseException{
        taskInfo = taskInfo.replace("event", "");
        String[] stringArr = taskInfo.split("/at", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        taskInfo = stringArr[0].trim();
        String at = stringArr[1].trim();
        tasks.addTask(new Events(taskInfo, at));
    }

    /**
     * Prints out the task if user input matches any word from task.
     *
     * @param tasks  List of user's tasks.
     * @param taskInfo  Description of todos task.
     * @return True if user input matches any word from any task in tasks list.
     */
    public boolean foundMatchingTasks(TaskList tasks, String taskInfo) {
        boolean matched = false;
        String[] taskInfos = taskInfo.trim().split(" ");
        List<Task> matchList = tasks.returnMatchingTasks(taskInfos);
        for(int i = 0; i < matchList.size(); i++) {
            if (i == 0) {
                System.out.println("\tHere are the matching tasks in your list:");
            }
            System.out.println(String.format("\t%d. %s", i + 1, matchList.get(i)));
            matched = true;
        }
        return matched;
    }
}
