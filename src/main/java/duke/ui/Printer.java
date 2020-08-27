package duke.ui;

import duke.tasks.Task;
import duke.utils.Constants;
import duke.utils.UtilFunction;

import java.util.List;

public class Printer {
    private Printer () {}

    public static void printAdd(Task task, int numOfTask) {
        UtilFunction.printLimit("Got it. I've added this task:\n" +
                "   " + task +  '\n' + "Now you have " + numOfTask +
                (numOfTask > 1 ? "duke/tasks " : " task ") + "in the list.");
    }

    public static void printDelete(Task task, int numOfTask) {
        UtilFunction.printLimit("Noted. I've removed this task:\n" + "   " +
                task + "\n" + "Now you have " + numOfTask  + " duke.tasks in the list.");
    }

    public static void printNoTaskReminder() {
        UtilFunction.printLimit("😝You don't have any task in the schedule yet~~\n" +
                "use todo/deadline/event command to create your tasks~");
    }

    public static void printDoneTask(Task task) {
        UtilFunction.printLimit("Très bien!I have helped you marked task " + task.getTask() + " as done\n"
                + task);
    }

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
