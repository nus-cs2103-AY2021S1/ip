package duke.component;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    /**
     * Function to send initial message to user
     * @return String of start message
     */
    public String chat() {
        String start = "Hello! I'm duke.Duke \nWhat can I do for you?";
        return start;
    }

    public static String print(String string) {
        return string;
    }

    /**
     * takes a List of tasks and prints element by element
     *
     * @param TaskList
     * @return String of lists of tasks in ArrayList
     */
    public static String printList(ArrayList<Task> TaskList) {
        String output = "";
        if (TaskList.isEmpty()) {
            return "Task List is empty!";
        }

        for (Task task : TaskList) {
            output += (TaskList.indexOf(task) + 1) + "." + task + "\n";
        }
        return output;
    }

    /**
     * Exit function for Duke
     *
     * @return String Goodbye message
     */
    public static String exit() {
        return "Goodbye! Hope to see you soon!";
    }



}
