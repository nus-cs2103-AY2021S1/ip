import java.util.ArrayList;

/**
 * This class keeps track of, and manages the User's current list
 */
public class TaskList {

    private ArrayList<Task> lst;

    public TaskList () {
        this.lst = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return lst;
    }

    // This method is responsible for inserting text into list, and updating current index
    public void addTask (Task t) {
        this.lst.add(t);
    }

    public void completeTask (int i) {
        this.lst.get(i-1).markAsDone();
    }

    public Task getTask (int i) {
        return this.lst.get(i-1);
    }

    public int getNumTasks () {
        return this.lst.size();
    }

    public void del (int i) {
        this.lst.remove(i-1);
    }

    // Overrides toString() method of Object Class to display contents of list neatly
    @Override
    public String toString() {
        String msg = ". . . list is currently empty . . .";  // Default message given empty list
        if (!this.lst.isEmpty()) {
            int i = 1;
            msg = ((i) + ". " + this.getTask(i) + "\n");
            for (Task t: this.lst) {
                // Task t = this.list[i];
                if (i == 1) {
                    i++;
                    continue;
                }
                msg += String.format("\t" + (i++) + ". " + t + "\n");
            }
        }
        return msg;
    }
}