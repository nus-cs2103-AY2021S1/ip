package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * A class that handles user interface interactions.
 */
public class Ui {
    public static String greetings = "Hello! I'm Mr. Duke, your personal assistant\n     What can I do for you? : )";
    public static String farewell = "Bye. Hope to see you again soon!";
    public static String doneAlert = "Nice! I've marked this task as done:";
    public static String addTaskFrontAlert = "Got it. I've added this task for you:";
    public static String addTaskTailAlert = "Now you have %d tasks in the list.";
    public static String deleteTaskFrondAlert = "Noted. I've removed this task:";
    public static String findTaskFrontAlert = "Here are the matching tasks in your list:";

    /**
     * Prints out greetings at the beginning.
     */
    public void greetings() {
        printAnswer("", greetings, "");
    }

    /**
     * Prints out reminders when adding a task to the list.
     *
     * @param result the TaskList of all tasks.
     */
    public void addTaskAlert(TaskList result) {
        printAnswer(addTaskFrontAlert, "   " + result.get(result.getSize() - 1).toString(), String.format(addTaskTailAlert, result.getSize()));
    }

    /**
     * Prints out the reminders when done with a task.
     *
     * @param tempDone the task object that has been done.
     */
    public void doneAlert(Task tempDone) {
        printAnswer(doneAlert, "   " + tempDone.toString(), "");
    }

    /**
     * Prints out a reminder when a task has been deleted.
     *
     * @param tempDelete the task object that has been deleted.
     * @param result the TaskList of all the tasks.
     */
    public void deleteTaskAlert(Task tempDelete, TaskList result) {
        printAnswer(deleteTaskFrondAlert, "   " + tempDelete.toString(), String.format(addTaskTailAlert, result.getSize()));
    }

    /**
     * Prints out a list of tasks.
     * @param result the TaskList of task to be printed.
     */
    public void showList(TaskList result) {
        printList(":) Here are all the tasks in your list:", result, "");
    }

    /**
     * Prints a farewell message when exiting the application.
     */
    public void farewell() {
        printAnswer("", farewell, "");
    }

    /**
     * Prints out a list of tasks when finding by keywords.
     *
     * @param result the list of tasks that are found by a keyword.
     */
    public void findTaskAlert(TaskList result) {
        printList(findTaskFrontAlert, result, "");
    }

    /**
     * Prints out answers according to commands with front reminder, and tail reminder.
     *
     * @param FrontGuidance a string representing reminders at the front.
     * @param answer a string representing the answer to the user command.
     * @param TailGuidance a string representing reminders at the tail.
     */
    public static void printAnswer(String FrontGuidance, String answer, String TailGuidance) {
        String line = "___________________________________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";

        System.out.println(smallSpace + line);
        if (FrontGuidance.length() != 0) {
            System.out.println(bigSpace + FrontGuidance);
        }
        System.out.println(bigSpace + answer);
        if (TailGuidance.length() != 0) {
            System.out.println(bigSpace + TailGuidance);
        }
        System.out.println(smallSpace + line + "\n");
    }

    /**
     * Prints out a list according to commands with front reminder, and tail reminder.
     *
     * @param FrontGuidance a string representing reminders at the front.
     * @param result a TaskList to be printed.
     * @param TailGuidance a string representing reminders at the tail.
     */
    public static void printList(String FrontGuidance, TaskList result, String TailGuidance) {
        String line = "___________________________________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";

        System.out.println(smallSpace + line);
        if (FrontGuidance.length() != 0) {
            System.out.println(bigSpace + FrontGuidance);
        }

        for (int i = 0; i < result.getSize(); i++) {
            System.out.println(bigSpace + (i + 1) + ". " + result.get(i));
        }

        if (TailGuidance.length() != 0) {
            System.out.println(bigSpace + TailGuidance);
        }

        System.out.println(smallSpace + line + "\n");
    }

    /**
     * Prints out the error message when encountering exceptions.
     *
     * @param e the exception message to be printed.
     */
    public void showError(Exception e) {
        System.out.println(e.toString());
    }

}
