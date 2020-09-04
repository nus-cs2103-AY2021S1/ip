package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/** An agent to process incoming command and return the feedback */
public class CommandAgent {
    private static TaskList taskList;

    public CommandAgent(TaskList taskList) {
        CommandAgent.taskList = taskList;
    }

    public static int listSize() {
        return taskList.getSize();
    }

    /**
     * Handles the command taken from user input, execute it
     * and save the updated task list data to hard disk.
     *
     * @param c The command parsed from user input.
     * @param ui The user interface where response is sent to.
     * @param storage The data storage handler.
     */
    public void handleCommand(Command c, Ui ui, Storage storage) {
        String response = executeCommand(c);
        storage.save(taskList);
        ui.showResponse(response);
    }

    /**
     * Overloads command handler for final product.
     * Execute the command, store the updated task list and
     * send a response string to the dialog box.
     *
     * @param c The command parsed from user input.
     * @param storage The data storage handler.
     * @return The response string to be displayed in dialog box.
     */
    public String handleCommand(Command c, Storage storage) {
        String response = executeCommand(c);
        storage.save(taskList);
        return response;
    }

    /**
     * Takes in the command and execute it based on the request from the command.
     *
     * @param command The command parsed from user input.
     * @return A String response for the user.
     */
    public static String executeCommand(Command command) {
        String commandRequest = command.sendRequest();
        List<String> commandContents = command.getContent();
        try {
            switch (commandRequest) {
            case "end":
                return generateEndResponse();
            case "create":
                executeCreateTask(commandContents);
                return generateCreateResponse();
            case "retrieval":
                return generateRetrievalResponse();
            case "update":
                int taskId = executeUpdateTask(commandContents);
                return generateUpdateResponse(taskId);
            case "delete":
                Task deletedTask = executeDeleteTask(commandContents);
                return generateDeleteResponse(deletedTask);
            case "search":
                String matchedTasks = executeSearchTask(commandContents);
                return generateSearchResponse(matchedTasks);
            default:
                throw new DukeException("Something is wrong. The command you input is not processed.");
            }
        } catch (DateTimeParseException | DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Executes the command which requests the bot to create a task and store it to task list.
     *
     * @param commandContents a list of task information used to create a new task.
     * @throws DateTimeParseException thrown from createTask process.
     * @throws DukeException if thrown from createTask process.
     */
    public static void executeCreateTask(List<String> commandContents) throws DateTimeParseException, DukeException {
        Task newTask = createTask(commandContents);
        taskList = taskList.addTask(newTask);
    }

    /**
     * Executes the command which requests the bot to update a task as done.
     *
     * @param commandContents a list of task information used to update the task.
     * @return An integer indicating the task getting updated.
     * @throws DukeException if thrown from markAsDone process.
     */
    public static int executeUpdateTask(List<String> commandContents) throws DukeException {
        int taskId = Integer.parseInt(commandContents.get(0));
        taskList = taskList.markAsDone(taskId);
        return taskId;
    }

    /**
     * Executes the command which requests the bot to delete a task.
     *
     * @param commandContents a list of task information used to delete the task.
     * @return The task getting deleted.
     */
    public static Task executeDeleteTask(List<String> commandContents) {
        int taskId = Integer.parseInt(commandContents.get(0));
        Task deletedTask = taskList.getTaskById(taskId);
        taskList = taskList.deleteTask(taskId);
        return deletedTask;
    }

    /**
     * Executes the command which requests the bot to search for tasks related to a particular keyword.
     *
     * @param commandContents a list of task information used to search for the related tasks.
     * @return The string including all the matching tasks related to the keyword.
     */
    public static String executeSearchTask(List<String> commandContents) {
        String keyword = commandContents.get(0);
        return taskList.findTasksByKeyword(keyword);
    }

    /**
     * Creates different types of tasks based on the identifier stored in the taskInfo.
     * The Parser has ensured no erroneous keyword will be sent to agent.
     *
     * @param taskInfo A list of String containing all the relevant information for the task.
     * @return A correct type of Task object.
     * @throws DateTimeParseException If any schedule cannot be parsed by the LocalDate formatter.
     * @throws DukeException If the a task with the same name is already stored in the list or
     * the identifier is none of "E", "D" or "T".
     */
    public static Task createTask(List<String> taskInfo) throws DateTimeParseException, DukeException {
        assert taskInfo.size() >= 2 : "the list of task information should have at least 2 parts";

        String identifier = taskInfo.get(0);
        String name = taskInfo.get(1);
        String schedule;

        assert identifier.equals("E") | identifier.equals("D") | identifier.equals("T") : "identifier is invalid";


        if (!taskList.findTasksByKeyword(name).equals("")) {
            throw new DukeException("☹ OOPS!!! This task has already been stored in the list!");
        }

        switch (identifier) {
        case "E":
            schedule = taskInfo.get(2);
            LocalDate eventTime = LocalDate.parse(schedule);
            return new Event(name, false, eventTime);
        case "D":
            schedule = taskInfo.get(2);
            LocalDate deadlineTime = LocalDate.parse(schedule);
            return new Deadline(name, false, deadlineTime);
        case "T":
            return new Todo(name, false);
        default:
            throw new DukeException("☹ OOPS!!! This type of task cannot be created by me!");
        }
    }

    /**
     * Informs the user the program is ended.
     * @return a message telling the user his/her exit message ends the program successfully.
     */
    public static String generateEndResponse() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generates the response for a task creation.
     *
     * @return A String suggesting the completion of task creation.
     */
    public static String generateCreateResponse() {
        int taskId = taskList.getSize();
        Task currentTask = taskList.getTaskById(taskId);
        String result = "Got it. I've added this task:\n  ";
        result += currentTask;
        result += String.format("\nNow you have %d tasks in the list.", taskId);
        return result;
    }

    /**
     * Generates the response for an update of task status.
     *
     * @param taskId The displayed id in the taskList.
     * @return A String suggesting the completion of task update.
     */
    public static String generateUpdateResponse(int taskId) {
        assert taskId > 0 : "task id should be greater than 0";

        Task currentTask = taskList.getTaskById(taskId);
        return "Nice! I've marked this task as done:\n  " + currentTask;
    }

    /**
     * Generates the response for a retrieval of tasks information.
     *
     * @return A String showing all the task information.
     */
    public static String generateRetrievalResponse() {
        String result = "Here are the tasks in your list:";
        result += taskList.printTasks();
        return result;
    }

    /**
     * Generates the response for a delete of task from the task list.
     *
     * @param deletedTask The delete task.
     * @return A String suggesting the task has been deleted.
     */
    public static String generateDeleteResponse(Task deletedTask) {
        String result = "Noted. I've removed this task:\n  ";
        result += deletedTask;
        result += String.format("\nNow you have %d tasks in the list.", listSize());
        return result;
    }

    /**
     * Generates the response for the searching of all the tasks containing the specified keyword.
     *
     * @param matchedTasks The keyword which must be present in the desired task name.
     * @return A String showing the response to the user with all the tasks desired.
     */
    public static String generateSearchResponse(String matchedTasks) {
        String result = "Here are the matching tasks in your list:";
        result += matchedTasks;
        return result;
    }
}
