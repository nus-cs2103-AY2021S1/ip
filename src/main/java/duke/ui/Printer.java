package duke.ui;

import duke.tasks.Task;
import duke.utils.Constants;
import duke.utils.UtilFunction;

import java.util.List;

public class Printer {
    //This is not meant to be initiated.
    private Printer () {}

    /**
     * Print the add task.
     * @param task the task added to the database
     * @param numOfTask the total number of task in the database
     */
    public static void printAdd(Task task, int numOfTask) {
        UtilFunction.printLimit("Got it. I've added this task:\n" +
                "   " + task +  '\n' + "Now you have " + numOfTask +
                (numOfTask > 1 ? "duke/tasks " : " task ") + "in the list.");
    }

    /**
     * Print the delete task.
     * @param task the task deleted from the database
     * @param numOfTask the total number of task in the database
     */
    public static void printDelete(Task task, int numOfTask) {
        UtilFunction.printLimit("Noted. I've removed this task:\n" + "   " +
                task + "\n" + "Now you have " + numOfTask  + " duke.tasks in the list.");
    }

    /**
     * Print the reminder when list command is used but the database is empty.
     */
    public static void printNoTaskReminder() {
        UtilFunction.printLimit("😝You don't have any task in the schedule yet~~\n" +
                "use todo/deadline/event command to create your tasks~");
    }

    /**
     * Print the task that has been done.
     * @param task the task that has been done
     */
    public static void printDoneTask(Task task) {
        UtilFunction.printLimit("Très bien!I have helped you marked task " + task.getTask() + " as done\n"
                + task);
    }

    /**
     * Print all the tasks.
     * @param tasks the tasks to print
     */
    public static void printAllTask(List<Task> tasks) {
        for(int i =1; i< tasks.size()+1; i++){
            UtilFunction.printLimit(i + ". " + tasks.get(i-1));
        }
    }

    static void printGreeting() {
        System.out.println(Constants.DIVIDER);
        System.out.println(Constants.GREETING);
        System.out.println(Constants.DIVIDER);
    }
}
