package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * A class that handles user interface interactions.
 */
public class Ui {
    private static String greetings = "Hello! I'm Mr. Duke, your personal assistant\n     What can I do for you? : )";
    private static String farewell = "Bye. Hope to see you again soon! This will automatically close in 3.3 seconds";
    private static String doneAlert = "Nice! I've marked this task as done:";
    private static String addTaskFrontAlert = "Got it. I've added this task for you:";
    private static String addTaskTailAlert = "Now you have %d tasks in the list.";
    private static String deleteTaskFrondAlert = "Noted. I've removed this task:";
    private static String findTaskFrontAlert = "Here are the matching tasks in your list:";
    private static String updateTaskFrontAlert = "Noted. I have updated the task.";

    /**
     * Returns greetings at the beginning.
     *
     * @return a string representing greetings at the beginning.
     */
    public String greetings() {
        return printAnswer("", greetings, "");
    }

    /**
     * Returns reminders when adding a task to the list.
     *
     * @param result the TaskList of all tasks.
     * @return a string representing reminders when adding a task to the list.
     */
    public String addTaskAlert(TaskList result) {
        return printAnswer(addTaskFrontAlert, "   " + result.get(result.getSize() - 1).toString(),
                String.format(addTaskTailAlert, result.getSize()));
    }

    /**
     * Returns the reminders when done with a task.
     *
     * @param tempDone the task object that has been done.
     * @return a string representing the reminders when done with a task.
     */
    public String doneAlert(Task tempDone) {
        return printAnswer(doneAlert, "   " + tempDone.toString(), "");
    }

    /**
     * Returns a reminder when a task has been deleted.
     *
     * @param tempDelete the task object that has been deleted.
     * @param result the TaskList of all the tasks.
     * @return a string representing a reminder when a task has been deleted.
     */
    public String deleteTaskAlert(Task tempDelete, TaskList result) {
        return printAnswer(deleteTaskFrondAlert, "   " + tempDelete.toString(),
                String.format(addTaskTailAlert, result.getSize()));
    }

    /**
     * Returns a reminder when a task has been udpated.
     *
     * @param tempUpdate the task object that has been deleted.
     * @param result the TaskList of all the tasks.
     * @return a string representing a reminder when a task has been deleted.
     */
    public String updateTaskAlert(Task tempUpdate, TaskList result) {
        return printAnswer(updateTaskFrontAlert, "   " + tempUpdate.toString(),
                String.format(addTaskTailAlert, result.getSize()));
    }

    /**
     * Returns a list of tasks.
     *
     * @param result the TaskList of task to be printed.
     * @return a string representing a list of tasks.
     */
    public String showList(TaskList result) {
        if (result.getSize() == 0) {
            return "Hi, sorry that the list is empty : )";
        } else {
            return printList(":) Here are all the tasks in your list:", result, "");
        }
    }

    /**
     * Returns a farewell message when exiting the application.
     *
     * @return a string representing a farewell message when exiting the application.
     */
    public String farewell() {
        return printAnswer("", farewell, "");
    }

    /**
     * Returns a list of tasks when finding by keywords.
     *
     * @param result the list of tasks that are found by a keyword.
     * @return a string representing a list of tasks when finding by keywords.
     */
    public String findTaskAlert(TaskList result) {
        if (result.getSize() == 0) {
            return "Hi, sorry that the list is empty : )";
        } else {
            return printList(findTaskFrontAlert, result, "");
        }
    }

    /**
     * Prints out answers according to commands with front reminder, and tail reminder.
     *
     * @param answers a vararg string parameter representing strings to be printed.
     * @return a string representing answers according to commands with front reminder, and tail reminder.
     */
    public static String printAnswer(String... answers) {
        String result = "";

        for (int i = 0; i < answers.length; i++) {
            result += answers[i] + "\n";
        }

        return result;
    }

    /**
     * Returns a list according to commands with front reminder, and tail reminder.
     *
     * @param frontGuidance a string representing reminders at the front.
     * @param result a TaskList to be printed.
     * @param tailGuidance a string representing reminders at the tail.
     * @return a string representing a list.
     */
    public static String printList(String frontGuidance, TaskList result, String tailGuidance) {
        String resultString = "";

        if (frontGuidance.length() != 0) {
            resultString += frontGuidance + "\n";
        }

        for (int i = 0; i < result.getSize(); i++) {
            resultString += (i + 1) + ". " + result.get(i) + "\n";
        }

        if (tailGuidance.length() != 0) {
            resultString += tailGuidance + "\n";
        }

        return resultString;
    }

    /**
     * Returns the error message when encountering exceptions.
     *
     * @param e the exception occurred.
     * @return a string representing the error message when encountering exceptions.
     */
    public String showError(Exception e) {
        return e.toString();
    }

}
