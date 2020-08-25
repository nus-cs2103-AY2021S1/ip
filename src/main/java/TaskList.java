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

    public void find(String str) {
        int counter = 1;
        System.out.println("-------------------------");
        for (Task task : this.list) {
            if (task.getDescription().contains(str)) {
                System.out.println(counter + ". [" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
                counter++;
            }
        }
        if (counter == 1) {
            System.out.println("There are no tasks that match your search!");
        }
        System.out.println("-------------------------");
    }
}
