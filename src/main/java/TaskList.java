import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /*
     this function takes in the input from the user and adds it to the list of tasks Duke is tracking
     helper function
    */
        public void addTask(Task t) {
            this.tasks.add(t);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("     " + t.toString());
            System.out.println("     Now you have " + this.tasks.size() + " tasks in the list.");
        }

}

