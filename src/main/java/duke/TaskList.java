package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    /**
     * Class Constructor with no arguments.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Class Constructor with specified ArrayList of Tasks.
     * @param list the specified ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the ArrayList of Tasks.
     * @return the current Tasks ArrayList
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Finds the Tasks stored in the array that corresponds to the specified String.
     * @param str input String specified
     */
    public void find(String str) {
        int counter = 1;
        System.out.println("-------------------------");
        for (Task task : this.list) {
            if (task.getDescription().contains(str)) {
                System.out.println(counter + ". [" + task.getType() + "]["
                        + task.getStatusIcon() + "] " + task.getDescription());
                counter++;
            }
        }
        if (counter == 1) {
            System.out.println("There are no tasks that match your search!");
        }
        System.out.println("-------------------------");
    }

    /**
     * Finds the Tasks stored in the array that corresponds to the specified String.
     * @param str input String specified
     */
    public String finder(String str) {
        assert this.list.size() >= 1 : "The current TaskList is Empty";

        String result = "";
        int counter = 1;

        for (Task task : this.list) {
            if (task.getDescription().contains(str)) {
                result = result + counter + ". [" + task.getType() + "]["
                        + task.getStatusIcon() + "] " + task.getDescription() + "\n";
                counter++;
            }
        }

        if (counter == 1) {
            result = "There are no tasks that match your search!";
        }

        return result;
    }
}
