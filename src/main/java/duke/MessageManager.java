package duke;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.tasks.Task;

/**
 * Represents a MessageManager.
 * A MessageManager handles the different messages that Duke
 * sends in the UI.
 */
public class MessageManager {

    /**
     * Returns a list of tasks and remaining number of tasks
     * from specified taskManager in message form.
     *
     * @param taskManager the taskManager to retrieve all tasks from
     * @return the message containing list of tasks
     * and remaining number of tasks
     */
    public static String getListMessage(TaskManager taskManager) {
        String listStringFormat = "Here are the tasks in your list:\n"
                + "%s\n"
                + "You have %s task(s) in your list.";
        return String.format(
                listStringFormat,
                taskManager.toString(),
                taskManager.getTaskCount());
    }


    /**
     * Returns the deleted task and remaining number of tasks in message form.
     *
     * @param task the deleted task
     * @param taskManager the taskManager to retrieve all tasks from
     * @return the message containing the deleted task
     * and remaining number of tasks
     */
    public static String getDeleteSuccessMessage(Task task, TaskManager taskManager) {
        String successfulTaskDeleteStringFormat = "Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d task(s) in the list.";
        return String.format(
                successfulTaskDeleteStringFormat,
                task,
                taskManager.getTaskCount());
    }

    /**
     * Returns the greeting message.
     *
     * @return the greeting message
     */
    public static String getGreetMessage() {
        String greetMessage = "Hello. What can I do for you?";
        return greetMessage;
    }

    /**
     * Returns the exit message.
     *
     * @return the exit message
     */
    public static String getByeMessage() {
        String byeMessage = "Bye. Hope to see you again soon!";
        return byeMessage;
    }

    /**
     * Returns the completed task.
     *
     * @param task the completed task.
     * @return the message containing the completed task
     */
    public static String getCompleteSuccessMessage(Task task) {
        String successfulTaskCompleteStringFormat = "Nice! I've marked this task as done:\n"
                + "%s";
        return String.format(
                successfulTaskCompleteStringFormat,
                task);
    }

    /**
     * Returns the added task and remaining number of tasks in message form.
     *
     * @param task the added task
     * @param taskManager the taskManager to retrieve all tasks from
     * @return the message containing the added task
     * and remaining number of tasks
     */
    public static String getAddSuccessMessage(Task task, TaskManager taskManager) {
        String successfulTaskAddStringFormat = "Got it. I've added this task:\n"
                + "%s\n"
                + "Now you have %d task(s) in the list.";
        return String.format(
                successfulTaskAddStringFormat,
                task,
                taskManager.getTaskCount());
    }

    /**
     * Returns the list of tasks in the format of a find command response message.
     *
     * @param taskList the list of tasks to be included in the message
     * @return the list of tasks in find command response format.
     */
    public static String getFindAllContainingMessage(List<Task> taskList) {
        String findAllContainingStringFormat = "Here are the matching tasks in your list:\n%s";
        String matchingTaskString = IntStream.range(0, taskList.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, taskList.get(i).toString()))
                .collect(Collectors.joining("\n"));
        return String.format(findAllContainingStringFormat, matchingTaskString);
    }

}
