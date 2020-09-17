package duke;

import java.util.ArrayList;

/**
 * A list for Duke to store Tasks in.
 */
public class TaskList {
    private int count;
    private ArrayList<Task> list;

    private TaskList() {
        count = 0;
        list = new ArrayList<>();
    }

    /**
     * Creates an empty Task List.
     * @return An empty TaskList.
     */
    public static TaskList startList() {
        return new TaskList();
    }

    /**
     * Adds a specified Task to the existing Task List.
     * @param t Task to be added to the Task List.
     * @return
     */
    public String addToList(Task t) {
        this.count = this.count + 1;
        this.list.add(t);

        return "Got it. I've added this task:\n\t"
                + t.toString()
                + "\n\tNow you have " + count + " tasks in the list.";
    }

    /**
     * Marks a specified Task in the Task List as 'done'.
     * @param i Index of Task to be marked as 'done'.
     * @return A success message if successful.
     * @throws InvalidTaskNumberException If index specified is out of bounds.
     */
    public String markAsDone(int i) throws InvalidTaskNumberException {
        Task t;

        try {
            t = this.list.get(i - 1).taskDone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
        return "Nice! I've marked this task as done:\n\t"
                + "  " + t.toString();
    }

    /**
     * Removes a specified Task from the Task List.
     * @param i Index of Task to be removed.
     * @return A success message if successful.
     * @throws InvalidTaskNumberException If index specified is out of bounds.
     */
    public String remove(int i) throws InvalidTaskNumberException {
        Task t;

        try {
            t = this.list.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
        this.list.remove(i - 1);
        this.count = this.count - 1;
        return "Noted. I've removed this task:\n\t"
                + t.toString()
                + "\n\tNow you have " + count + " tasks in the list.";
    }

    /**
     * Finds Tasks containing the specified keyword
     * @param keyword Keyword to be searched for in Task description.
     * @return A String of all the matching Tasks.
     */
    public String find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : this.list) {
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }

        String msg = "Here are the matching tasks in your list:\n\t";
        int num = 1;
        for (Task s : matchingTasks) {
            msg = msg + num + ". " + s.toString() + "\n\t";
            num++;
        }
        return msg;

    }

    /**
     * Generates a String to be stored in the save file, duke.txt.
     * @return A String of all the Tasks in this Task List.
     */
    public String listToTxt() {
        String str = "";
        for (Task t : this.list) {
            String type = t.getType();
            boolean isDone = t.isDone();
            String desc = t.getDescription();
            String time = t.getDeadline();
            str = str + type + "@"
                    + (isDone ? "1" : "0") + "@"
                    + desc
                    + ((time == null) ? "" : "@" + time)
                    + System.getProperty("line.separator");
        }
        return str;
    }

    public int getCount() {
        return this.count;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        if (this.count == 0) {
            return "There are no tasks in your list";
        }

        String msg = "Here are the tasks in your list:\n\t";
        int num = 1;
        for (Task s : this.list) {
            msg = msg + num + ". " + s.toString() + "\n\t";
            num++;
        }
        return msg;
    }
}
