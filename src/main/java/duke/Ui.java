package duke;
import java.util.ArrayList;

public class Ui {
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
    
    public void noProblem() {
        System.out.println("     No problem! I've added this task to the list:");
    }
    
    public void markAsDone(Task thisTask) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + thisTask.toString());
    }
    
    public void deletedTask(Task thisTask, ArrayList<Task> list) {
        System.out.println("     Sure! I've removed this task for you:");
        System.out.println("       " + thisTask.toString());
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }
    
    public void updatedTask(ArrayList<Task> list) {
        System.out.println("       " + list.get(list.size() - 1));
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }
    
    public void printList(ArrayList<Task> list) {
        Task thisTask;
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            thisTask = list.get(i - 1);
            System.out.println("     " + i + "." + thisTask.toString());
        }
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
    
    public void printLine() {
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
    }
    
    public void printError(DukeException ex) {
        System.out.println(ex.getMessage());
    }
    
    public void bye() {
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
        System.out.println("    Bye bye~ See ya!");
        System.out.println("    -x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
    }
}
