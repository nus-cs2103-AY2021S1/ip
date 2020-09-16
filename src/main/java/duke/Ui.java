package duke;
import java.util.ArrayList;

/**
 * Creates an ui interface for interactions with the product user.
 */
public class Ui {
    /**
     * Prints out "no problem" messages to the user.
     */
    public String printSuccessMessage() {
        return "     No problem! I've added this task to the list:\n";
    }
    
    /**
     * Prints out "mark as done" messages to the user.
     */
    public String markAsDoneMessage(Task thisTask) {
        return "     Nice! I've marked this task as done:\n"
                + "       " + thisTask.toString() + "\n";
    }

    /**
     * Prints out "deleted task" messages to the user.
     */
    public String deletedTaskMessage(Task thisTask, ArrayList<Task> list) {
        assert !list.isEmpty() : "list is empty";
        return "     Sure! I've removed this task for you:\n"
                + "       " + thisTask.toString() + "\n"
                + "     Now you have " + list.size() + " tasks in the list.\n";
    }

    /**
     * Prints out "updated task" messages to the user.
     * 
     * @param list The list of tasks.
     */
    public String updatedTaskMessage(ArrayList<Task> list) {
        assert !list.isEmpty() : "list is empty";
        return "       " + list.get(list.size() - 1) + "\n"
                + "     Now you have " + list.size() + " tasks in the list.\n";
    }

    public String printListMessage(ArrayList<Task> list) {
        assert !list.isEmpty() : "list is empty";
        Task thisTask;
        String output = "     Here are the tasks in your list:\n";
        for (int i = 1; i <= list.size(); i++) {
            thisTask = list.get(i - 1);
            output = output + "     " + i + "." + thisTask.toString() + "\n";
        }
        return output;
    }

    /**
     * Search the tasks that contain the keyword in the list of tasks provided.
     * 
     * @param keyword The keyword for searching the tasks.
     * @param list The list of tasks to be searched.
     */
    public String printFoundTaskMessage(String keyword, ArrayList<Task> list) {
        assert !list.isEmpty() : "list is empty";
        Task thisTask;
        String output = "     Here are the matching tasks in your list:\n";
        for (int i = 1, j = 1; i <= list.size(); i++) {
            thisTask = list.get(i - 1);
            if (thisTask.taskName.contains(keyword)) {
                output = output + "     " + j + "." + thisTask.toString() + "\n";
                j++;
            }
        }
        return output;
    }
    
    /**
     * Prints out the error messages to the user.
     */
    public String printErrorMessage(DukeException ex) {
        return ex.getMessage() + "\n";
    }
    
    /**
     * Prints out bye bye messages to the user.
     */
    public String byeMessage() {
        return "    Bye bye~ See ya!\n";
    }
}
