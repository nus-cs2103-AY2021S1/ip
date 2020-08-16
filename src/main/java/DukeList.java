import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Arrays;


public class DukeList {

    private final ArrayList<Task> list;
    private static final int CAPACITY = 100;


    public DukeList() {
        this.list = new ArrayList<>(CAPACITY);
    }


    /**
     * Removes the type keyword at the start of the string.
     *
     * @param strArr Array of strings (originally split by spaces).
     * @return Substring with the keyword removed.
     */
    private static String getFormattedItemString(String[] strArr) {
        return String.join(" ", Arrays.copyOfRange(strArr, 1, strArr.length));
    }


    /**
     * Adds a new item to the list.
     *
     * @param itemString String to be added.
     * @return Status string to be printed.
     */
    public String add(String itemString) {
        // this.list.add(new Task(itemString));
        Task newTask;

        String[] strArr = itemString.split(" ");
        String keyword = strArr[0];

        String formattedItemString;
        switch (keyword) {
            case ("todo"):
                formattedItemString = DukeList.getFormattedItemString(strArr);
                newTask = new Todo(formattedItemString);
                break;

            case ("deadline"):
                formattedItemString = DukeList.getFormattedItemString(strArr);
                newTask = new Deadline(formattedItemString);
                break;

            case ("event"):
                formattedItemString = DukeList.getFormattedItemString(strArr);
                newTask = new Event(formattedItemString);
                break;

            default:
                return "Error adding";
        }

        this.list.add(newTask);

        return "Got it. I've added this task:\n" +
                String.format("\t%s\n", newTask.toString()) +
                String.format("%s", this.getListStats());

    }


    /**
     * Mark an item as done.
     *
     * @param index Index of item to be marked as done.
     *              !This index is the printed index, not the actual index in the list.
     * @return Status string to be printed.
     */
    public String markAsDone(int index) {
        try {
            Task targetTask = this.list.get(index - 1);
            targetTask.markAsDone();
            return String.format("Nice! I've marked this task as done:\n\t%s", targetTask.toString());
        } catch (NullPointerException e) {
            return "Index is invalid!";
        }
    }


    /**
     * Gets the string to display the stats of the list.
     *
     * @return stats string.
     */
    private String getListStats() {
        return String.format("Now you have %d tasks in the list.", this.list.size());
    }


    @Override
    public String toString() {
        if (this.list.size() == 0) {
            return "List is currently empty!";
        } else {
            StringBuilder outputString = new StringBuilder();
            outputString.append("Here are the tasks in your list:\n");

            for (int i = 0; i < this.list.size(); i++) {
                String currTaskStr = String.format("%d: %s", i + 1, this.list.get(i).toString());
                outputString.append(currTaskStr);

                // add new line and tab only if not at the end of the list
                if (i < list.size() - 1) {
                    outputString.append("\n");
                }
            }

            return outputString.toString();
        }
    }

}
