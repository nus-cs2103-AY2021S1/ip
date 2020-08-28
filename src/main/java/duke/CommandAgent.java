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
        String response = "";
        int taskId;
        try {
            switch (command.sendRequest()) {
            case "end":
                response += "Bye. Hope to see you again soon!";
                break;
            case "create":
                List<String> taskInfo = command.getContent();
                Task newTask = createTask(taskInfo);
                taskList = taskList.addTask(newTask);
                response += generateCreateResponse();
                break;
            case "retrieval":
                response += generateRetrievalResponse();
                break;
            case "update":
                List<String> updateList = command.getContent();
                taskId = Integer.parseInt(updateList.get(0));
                taskList = taskList.markAsDone(taskId);
                response += generateUpdateResponse(taskId);
                break;
            case "delete":
                List<String> deleteList = command.getContent();
                taskId = Integer.parseInt(deleteList.get(0));
                Task deletedTask = taskList.getTaskById(taskId);
                taskList = taskList.deleteTask(taskId);
                response += generateDeleteResponse(deletedTask);
                break;
            case "search":
                List<String> keywordList = command.getContent();
                response += generateSearchResponse(keywordList.get(0));
                break;
            default:
                break;
            }
        } catch (DateTimeParseException | DukeException e) {
            response += e.getMessage();
        }
        return response;
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
        String identifier = taskInfo.get(0);
        String name = taskInfo.get(1);
        if (!taskList.findTasksByKeyword(name).equals("")) {
            throw new DukeException("☹ OOPS!!! This task has already been stored in the list!");
        } else {
            String schedule;
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
     * @param keyword The keyword which must be present in the desired task name.
     * @return A String showing the response to the user with all the tasks desired.
     */
    public static String generateSearchResponse(String keyword) {
        String result = "Here are the matching tasks in your list:";
        result += taskList.findTasksByKeyword(keyword);
        return result;
    }
}
