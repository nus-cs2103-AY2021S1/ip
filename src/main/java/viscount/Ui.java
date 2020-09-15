package viscount;

import java.util.Arrays;
import java.util.List;

import viscount.task.Task;
import viscount.task.TaskType;

/**
 * Represents Viscount's User Interface.
 *
 * Handles logic pertaining to interactions with the user.
 */
public class Ui {

    /**
     * Instantiates a new Ui object.
     */
    public Ui() {

    }

    /**
     * Gets Viscount's welcome message.
     *
     * @return Viscount's welcome message.
     */
    public String getWelcomeMessage() {
        return "Good day to you! I'm Viscount.\nWhat can I do for you on this blessed day?";
    }

    /**
     * Gets the list response given a list of tasks and query modifiers.
     *
     * @param tasks List of tasks listed.
     * @param modifier Modifier of list command.
     * @param dateString Date argument of list command.
     * @return The appropriate list response.
     */
    public String getListResponse(List<Task> tasks, String modifier, String dateString, String findString) {
        String[] validModifiers = {"", "todo", "deadline", "event"};
        assert Arrays.asList(validModifiers).contains(modifier)
                : String.format("Invalid modifier '%s'", modifier);

        String finalDateString = dateString.isEmpty()
                ? dateString
                : ("occurring " + (dateString.equals("today")
                        ? dateString
                        : "on " + dateString) + " ");

        String finalFindString = findString.isEmpty()
                ? findString
                : String.format("containing '%s' ", findString);

        return String.format("Here are the %ss %s%sin your list:\n%s",
                modifier.isEmpty() ? "task" : modifier,
                finalDateString,
                finalFindString,
                convertTaskListToString(tasks));
    }

    public String getEmptyListResponse() {
        return "Your task list is empty! Add a new task to get started.";
    }

    /**
     * Gets the add task response.
     *
     * @param task Task added.
     * @param tasksSize Size of task list after adding new task.
     * @return The appropriate add task response.
     */
    public String getAddResponse(Task task, int tasksSize) {
        assert tasksSize >= 0 : "Size of task list should be non-negative";

        return String.format("Very well. I've added this %s:\n%s\nNow you have %d tasks in the list.",
                task.getTaskType().name().toLowerCase(),
                task.toString(),
                tasksSize);
    }

    /**
     * Gets the mark task as done response.
     *
     * @param task Task marked as done.
     * @return The appropriate mark task as done response.
     */
    public String getDoneResponse(Task task) {
        return String.format("Very good! I have marked this %s as done:\n%s",
                task.getTaskType().name().toLowerCase(),
                task.toString());
    }

    /**
     * Gets the mark all task as done response.
     *
     * @param tasks Tasks marked as done.
     * @return The appropriate mark all task as done response.
     */
    public String getDoneAllResponse(List<Task> tasks) {
        return String.format("Very good! I have marked all these tasks as done:\n%s",
                convertTaskListToString(tasks));
    }

    /**
     * Gets the edit description response.
     *
     * @param task Task edited.
     * @return The appropriate edit description response.
     */
    public String getEditDescriptionResponse(Task task) {
        return String.format("Very well. I've edited the description of this %s:\n%s",
                task.getTaskType().name().toLowerCase(),
                task.toString());
    }

    /**
     * Gets the edit date time response.
     *
     * @param task Task edited.
     * @return The appropriate edit date time response.
     */
    public String getEditDateTimeResponse(Task task) {
        String dateTimeDescription = task.getTaskType().equals(TaskType.DEADLINE)
                ? "due date"
                : "event time";

        return String.format("Very well. I've edited the %s of this %s:\n%s",
                dateTimeDescription,
                task.getTaskType().name().toLowerCase(),
                task.toString());
    }

    /**
     * Gets the delete task response.
     *
     * @param task Task deleted.
     * @param tasksSize Size of task list after deleting new task.
     * @return The appropriate delete task response.
     */
    public String getDeleteResponse(Task task, int tasksSize) {
        assert tasksSize >= 0 : "Size of task list should be non-negative";

        return String.format("Very well. I've removed this %s:\n%s\nNow you have %d tasks in the list.",
                task.getTaskType().name().toLowerCase(),
                task.toString(),
                tasksSize);
    }

    /**
     * Gets the delete all task response.
     *
     * @param tasks Tasks deleted.
     * @return The appropriate delete all task response.
     */
    public String getDeleteAllResponse(List<Task> tasks) {
        return String.format("Very well. I've removed all the tasks in the list:\n%s\nThe list is now empty.",
                convertTaskListToString(tasks));
    }

    /**
     * Gets the delete all done tasks response.
     *
     * @param tasks Tasks deleted.
     * @return The appropriate delete all done tasks response.
     */
    public String getDeleteAllDoneResponse(List<Task> tasks, int tasksSize) {
        return String.format(
                "Very well. I've removed all the done tasks in the list:\n%s\nNow you have %d tasks in the list.",
                convertTaskListToString(tasks),
                tasksSize);
    }

    /**
     * Converts task list to String format.
     *
     * @param tasks Task list to be converted.
     * @return String representation of the task list.
     */
    private String convertTaskListToString(List<Task> tasks) {
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            result += (i == tasks.size() - 1)
                    ? String.format("%d.%s", i + 1, tasks.get(i).toString())
                    : String.format("%d.%s\n", i + 1, tasks.get(i).toString());
        }

        return result;
    }
}
