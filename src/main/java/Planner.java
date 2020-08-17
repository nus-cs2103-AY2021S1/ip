/** 
 * This class keeps track of, and manages the User's current list
*/
public class Planner {

    private Task[] list;  // Stores and displays text added by the User
    private int currIndex;  // Keeps track of current index for the list

    public Planner () {
        this.list = new Task[100];  // Size-of-100 as specified by task-requirements 
        this.currIndex = 0;
    }

    // This method is responsible for inserting text into list, and updating current index
    public void addTask (Task t) {
        this.list[currIndex++] = t;
    }

    public void completeTask (int i) {
        this.list[i-1].markAsDone();
    }

    public Task getTask (int i) {
        return this.list[i-1];
    }

    // Overrides toString() method of Object Class to display contents of list neatly
    @Override
    public String toString() {
        String msg = ". . . list is currently empty . . .";  // Default message given empty list
        if (this.currIndex != 0) {
            msg = "";
            for (int i = 0; i < this.currIndex; i++) {
                Task t = this.list[i];
                msg += String.format("\t" + (i+1) + ". " + t + "\n");
            }
        }
        return msg;
    }
}