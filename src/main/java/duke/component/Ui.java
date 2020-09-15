package duke.component;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Class that handles system's interactions and output with user
 *
 */
public class Ui {

    /**
     * Function to greet and introduce Duke to user
     *
     * @return String of a greeting
     */
    public String greet() {
        String greeting = "Hello! I'm Duke. What can I do for you?";
        return greeting;

    }

    public static String print(String string) {
        return string;
    }

    /**
     * takes in an ArrayList of tasks and prints each task one by one
     *
     * @param TaskList
     * @return String of lists of tasks in ArrayList
     */
    public static String printList(ArrayList<Task> TaskList) {

        String output = "";
        if (TaskList.isEmpty()) {
            return "You have no tasks!";
        }

        for (Task task : TaskList) {
            output += (TaskList.indexOf(task) + 1) + "." + task + "\n";
        }
        return output;
    }

    /**
     * Exit function for Duke
     *
     * @return String of a bye greeting
     */
    public static String Bye() {
        String bye = "Bye! Hope to see you soon!";
        return bye;
    }

}
