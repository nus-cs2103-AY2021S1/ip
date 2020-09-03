package duke;
import java.util.ArrayList;

/**
 * Creates an ui interface for interactions with the product user.
 */
public class Ui {
    /**
     * Prints out greeting messages to the user.
     */
    public void greet() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println("    Hello from\n" + logo);
        System.out.println("    Hey there! This is Duke here~");
        System.out.println("    How may I help you today?");
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
    }
    
    /**
     * Prints out "no problem" messages to the user.
     */
    public void noProblem() {
        System.out.println("     No problem! I've added this task to the list:");
    }

    public String noProblemMessage() {
        return "     No problem! I've added this task to the list:\n";
    }
    
    /**
     * Prints out "mark as done" messages to the user.
     */
    public void markAsDone(Task thisTask) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + thisTask.toString());
    }

    public String markAsDoneMessage(Task thisTask) {
        return "     Nice! I've marked this task as done:\n"
                + "       " + thisTask.toString() + "\n";
    }

    /**
     * Prints out "deleted task" messages to the user.
     */
    public void deletedTask(Task thisTask, ArrayList<Task> list) {
        System.out.println("     Sure! I've removed this task for you:");
        System.out.println("       " + thisTask.toString());
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }

    public String deletedTaskMessage(Task thisTask, ArrayList<Task> list) {
        return "     Sure! I've removed this task for you:\n"
                + "       " + thisTask.toString() + "\n"
                + "     Now you have " + list.size() + " tasks in the list.\n";
    }

    /**
     * Prints out "updated task" messages to the user.
     * 
     * @param list The list of tasks.
     */
    public void updatedTask(ArrayList<Task> list) {
        System.out.println("       " + list.get(list.size() - 1));
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }

    public String updatedTaskMessage(ArrayList<Task> list) {
        return "       " + list.get(list.size() - 1) + "\n"
                + "     Now you have " + list.size() + " tasks in the list.\n";
    }

    /**
     * Prints out a list of tasks to the user.
     */
    public void printList(ArrayList<Task> list) {
        Task thisTask;
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            thisTask = list.get(i - 1);
            System.out.println("     " + i + "." + thisTask.toString());
        }
    }

    public String printListMessage(ArrayList<Task> list) {
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
    public void printFoundTask(String keyword, ArrayList<Task> list) {
        Task thisTask;
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 1, j = 1; i <= list.size(); i++) {
            thisTask = list.get(i - 1);
            if (thisTask.taskname.contains(keyword)) {
                System.out.println("     " + j + "." + thisTask.toString());
                j++;
            }
        }
    }

    public String printFoundTaskMessage(String keyword, ArrayList<Task> list) {
        Task thisTask;
        String output = "     Here are the matching tasks in your list:\n";
        for (int i = 1, j = 1; i <= list.size(); i++) {
            thisTask = list.get(i - 1);
            if (thisTask.taskname.contains(keyword)) {
                output = output + "     " + j + "." + thisTask.toString() + "\n";
                j++;
            }
        }
        return output;
    }

    /**
     * Prints out the a line to the user.
     */
    public void printLine() {
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
    }

    /**
     * Prints out the error messages to the user.
     */
    public void printError(DukeException ex) {
        System.out.println(ex.getMessage());
    }

    public String printErrorMessage(DukeException ex) {
        return ex.getMessage() + "\n";
    }
    
    /**
     * Prints out bye bye messages to the user.
     */
    public void bye() {
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println("    Bye bye~ See ya!");
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
    }

    public String byeMessage() {
        return "    Bye bye~ See ya!\n";
    }
}
