/** 
 * This class keeps track of, and manages the User's current list
*/
public class Planner {

    private String[] list;  // Stores and displays text added by the User
    private int currIndex;  // Keeps track of current index for the list

    public Planner () {
        this.list = new String[100];  // Size-of-100 as specified by task-requirements 
        this.currIndex = 0;
    }

    // This method is responsible for inserting text into list, and updating current index
    public void addItem (String item) {
        this.list[currIndex++] = item;
    }

    // Overrides toString() method of Object Class to display contents of list neatly
    @Override
    public String toString() {
        String msg = ". . . list is currently empty . . .";  // Default message given empty list
        if (this.currIndex != 0) {
            msg = "";
            for (int i = 0; i < this.currIndex; i++) {
                msg += String.format("\t" + (i+1) + ". " + this.list[i] + "\n");
            }
        }
        return msg;
    }
}