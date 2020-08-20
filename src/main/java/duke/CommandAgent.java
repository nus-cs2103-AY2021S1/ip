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

/**
 * An agent to process incoming command and return the feedback.
 */
public class CommandAgent {
    private static TaskList taskList;

    public CommandAgent(TaskList taskList) {
        CommandAgent.taskList = taskList;
    }

    public static int listSize() {
        return taskList.getSize();
    }

    /**
     * Handle the command taken from user input, execute it
     * and save the updated task list data to hard disk.
     *
     * @param c the command parsed from user input.
     * @param ui the user interface where response is sent to.
     * @param storage the data storage handler.
     */
    public void handleCommand(Command c, Ui ui, Storage storage) {
        String response = executeCommand(c);
        storage.save(taskList);
        ui.showResponse(response);
    }

    /**
     * Takes in the command and execute it based on the request from the command.
     *
     * @param command the command parsed from user input.
     * @return a String response for the user.
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
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            response += command.getContent();
            response += e.getMessage();
        }
        return response;
    }

    /**
     * Create different types of tasks based on the identifier stored in the taskInfo.
     * Set default option to Todo task.
     * The CommandReader has ensured no erroneous keyword will be sent to agent.
     *
     * @param taskInfo a list of String containing all the relevant information for the task.
     * @return a correct type of Task object.
     * @throws DateTimeParseException if any schedule cannot be parsed by the LocalDate formatter.
     */
    public static Task createTask(List<String> taskInfo) throws DateTimeParseException {
        String identifier = taskInfo.get(0);
        String name = taskInfo.get(1);
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
        default:
            return new Todo(name, false);
        }
    }

    /**
     * Generate the feedback for a task creation.
     * @return a String suggesting the completion of task creation.
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
     * Generate the feedback for an update of task status.
     *
     * @param taskId the displayed id in the taskList.
     * @return a String suggesting the completion of task update.
     */
    public static String generateUpdateResponse(int taskId) {
        Task currentTask = taskList.getTaskById(taskId);
        return "Nice! I've marked this task as done:\n  " + currentTask;
    }

    /**
     * Generate the feedback for a retrieval of tasks information.
     * @return a String showing all the task information.
     */
    public static String generateRetrievalResponse() {
        return taskList.printTasks();
    }

    /**
     * Generate the feedback for a delete of task from the task list.
     *
     * @param deletedTask the delete task.
     * @return a String suggesting the task has been deleted.
     */
    public static String generateDeleteResponse(Task deletedTask) {
        String result = "Noted. I've removed this task:\n  ";
        result += deletedTask;
        result += String.format("\nNow you have %d tasks in the list.", listSize());
        return result;
    }

    public static String generateSearchResponse(String keyword) {
        String result = "Here are the matching tasks in your list:";
        result += taskList.findTasksByKeyword(keyword);
        return result;
    }
}
