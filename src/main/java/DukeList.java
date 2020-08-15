import java.util.ArrayList;
import java.lang.StringBuilder;


public class DukeList {
    private ArrayList<Task> list;
    private static final int CAPACITY = 100;


    public DukeList() {
        this.list = new ArrayList<>(CAPACITY);
    }


    /**
     * Adds a new item to the list.
     *
     * @param itemString String to be added.
     * @return Status string to be printed.
     */
    public String add(String itemString) {
        this.list.add(new Task(itemString));
        return "added: " + itemString;
    }


    /**
     * Mark an item as done.
     * @param index Index of item to be marked as done.
     *              !This index is the printed index, not the actual index in the list.
     * @return Status string to be printed.
     */
    public String markAsDone(int index) {
        try {
            Task targetTask = this.list.get(index - 1);
            targetTask.markAsDone();
            return String.format("Nice! I've marked this task as done: \n\t\t%s", targetTask.toString());
        } catch (NullPointerException e) {
            return "Index is invalid!";
        }
    }


    @Override
    public String toString() {
        if (this.list.size() == 0) {
            return "List is currently empty!";
        } else {
            StringBuilder outputString = new StringBuilder();

            for (int i = 0; i < this.list.size(); i++) {
                String currString = String.format("%d: %s", i + 1, this.list.get(i));
                outputString.append(currString);

                // add new line and tab only if not at the end of the list
                if (i < list.size() - 1) {
                    outputString.append("\n\t");
                }
            }

            return outputString.toString();
        }
    }

}
