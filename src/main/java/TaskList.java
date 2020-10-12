import java.util.ArrayList;
/**
 * A tasklist that handles corresponding task-related jobs.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Returns the task with corresponding keyword.
     * @param keyword The keyword to find corresponding task.
     * @return the String description with corresponding keyword.
     */
    public String findTask(String keyword) {
        ArrayList<Task> listOfFoundItems = new ArrayList<Task>();
        String output = "";
        for (Task s : tasks) {
            String[] items = s.description.split(" ");
            for (int i = 0; i < items.length; i++) {
                if (keyword.equals(items[i])) {
                    listOfFoundItems.add(s);
                    break;
                }
            }

        }

        if (listOfFoundItems.size() == 0) {
            return "No matching task is found!";
        }

        int iterator = 1;
        output += "Here are the matching tasks in your list:\n";

        assert listOfFoundItems.size()>0;

        for (Task s : listOfFoundItems) {
            output += iterator + "." + s.toString();
            iterator++;
        }
        return output;
    }

    /**
     * Returns the String with added task.
     * @param myTask The task being added.
     * @return the String for task added.
     */
    public String addTask(Task myTask) {
        this.tasks.add(myTask);
        return "added: " + myTask;
    }

    /**
     * Returns the String with deleted task.
     * @param index The task index.
     * @return the String for task deleted.
     */
    public String deleteTask(int index) {
        if (this.tasks.size() <= index) {
            return "The index you entered is too large!";
        }
        assert this.tasks.size() > index;
        Task myTask = this.tasks.get(index);
        this.tasks.remove(index);
        return "removed: " + myTask;
    }

    /**
     * Returns the list of tasks in TaskList.
     * @return a list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a String showing tasks in the tasklist.
     * @return a String description for a list of tasks.
     */
    public String showList() {
        int iterator = 1;
        String output = "Here are the tasks in your list:\n";
        for (Task s : tasks) {
            output += iterator + "." + s.toString() + "\n";
            iterator++;
        }
        return output;
    }

    /**
     * Returns the number of task completed.
     * @return an integer of number of task completed.
     */
    public String numberOfTaskCompleted() {
        int number = 0;
        String output = "Number of task completed: ";
        for (Task s : tasks) {
            if (s.checkDone()){
                number ++;
            }
        }
        output += number;
        return output;
    }
}
