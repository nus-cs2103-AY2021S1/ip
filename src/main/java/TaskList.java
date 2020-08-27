import java.util.ArrayList;

public class TaskList {

    protected final ArrayList<Task> tasks;

    // constructor
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /*
     this function takes in the input from the user and adds it to the list of tasks Duke is tracking
     helper function
    */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    // this function deletes the task per requested by the user
    public void deleteTask(int taskNumber) {
        Task t = this.tasks.get(taskNumber);
        this.tasks.remove(taskNumber);
        System.out.println("       " + t.toString());
    }

    // this function prints the task that is completed
    public void done(int taskNumber) {
        Task t = this.tasks.get(taskNumber);
        t.markAsDone();
    }

}

