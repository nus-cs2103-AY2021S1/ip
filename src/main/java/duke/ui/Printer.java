package duke.ui;

import java.util.List;

import duke.tasks.Task;
import duke.utils.Constants;
import duke.utils.UtilFunction;

public class Printer {
    //This is not meant to be initiated.
    private Printer () {}

    /**
     * Print the add task.
     * @param task the task added to the database
     * @param numOfTask the total number of task in the database
     */
    public static String printAdd(Task task, int numOfTask) {
        String message = "Got it. I've added this task:\n"
                + "   " + task + '\n' + "Now you have " + numOfTask
                + (numOfTask > 1 ? " tasks " : " task ") + "in the list.";
        UtilFunction.printLimit(message);
        return message;
    }

    /**
     * Print the delete task.
     * @param task the task deleted from the database
     * @param numOfTask the total number of task in the database
     */
    public static String printDelete(Task task, int numOfTask) {
        String message = "Noted. I've removed this task:\n" + "   "
                + task + "\n" + "Now you have " + numOfTask + " duke.tasks in the list.";
        UtilFunction.printLimit(message);
        return message;
    }

    /**
     * Print the reminder when list command is used but the database is empty.
     */
    public static String printNoTaskReminder() {
        String message = "😝You don't have any task in the schedule yet~~\n"
                + "use todo/deadline/event command to create your tasks~";
        UtilFunction.printLimit(message);
        return message;
    }

    /**
     * Print the task that has been done.
     * @param task the task that has been done
     */
    public static String printDoneTask(Task task) {
        String message = "Très bien!I have helped you marked task " + task.getTask() + " as done\n"
                + task;
        UtilFunction.printLimit(message);
        return message;
    }

    /**
     * Print all the tasks.
     * @param tasks the tasks to print
     */
    public static String printAllTask(List<Task> tasks, boolean withLabel) {
        String message = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            message += (withLabel ? (i + ". ") : ("   ")) + tasks.get(i - 1) + "\n";
        }
        UtilFunction.printLimit(message);
        return message;
    }

    static void printGreeting() {
        System.out.println(Constants.DIVIDER);
        System.out.println(Constants.GREETING);
        System.out.println(Constants.DIVIDER);
    }

    /**
     * Print bye message.
     * @return bye message
     */
    public static String printBye() {
        String message = "Farewell/再見/さようなら～～";
        System.out.println(message);
        return message;
    }

    /**
     * Print all available commands for Duke.
     * @return all available commands
     */
    public static String printListCommands() {
        String commandList = "help: list out all the available commands\n"
                + "done <task number>: Mark the task as done. \n"
                + "todo <task content>: Create a todo task. A todo has no deadline.\n"
                + "deadline <task content> /<" + Constants.DF_LOCAL_TIME
                + ">: Create a deadline task. Remember to input your date of deadline.\n"
                + "event <task content> /<" + Constants.DF_LOCAL_TIME
                + ">: Create an event task. An event has a duration.\n"
                + "find <query key>: find the task by your query key.\n"
                + "delete <task number>: delete your task.\n"
                + "li?st?: list out all the tasks that you have.\n"
                + "bye: turn off the Duke.\n";
        System.out.println(commandList);
        return commandList;
    }

    /**
     * Print the division line.
     */
    public static void printDivision() {
        System.out.println(Constants.DIVIDER);
    }
}
