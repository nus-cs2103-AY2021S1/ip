import java.util.ArrayList;

/**
 * Implements methods to manipulate the array containing all of the tasks.
 */

public class TaskList {
    protected ArrayList<Task> arrayOfTasks;

    /**
     * Instantiates TaskList object.
     */
    public TaskList() {
        this.arrayOfTasks = new ArrayList<>();
    }

    /**
     * Instantiates TaskList object with specified ArrayList.
     * @param arrayOfTasks Parsed array of tasks read from input file.
     */
    public TaskList(ArrayList<Task> arrayOfTasks) {
        this.arrayOfTasks = arrayOfTasks;
    }

    /**
     * Adds task to the ArrayList.
     * @param newTask New task to add.
     */
    public void addTask(Task newTask) {
        arrayOfTasks.add(newTask);
    }

    /**
     * Removes task from the ArrayList.
     * @param taskIndex Task to remove.
     */
    public void deleteTask(int taskIndex) {
        arrayOfTasks.remove(taskIndex);
        Task.totalTasks--;
    }

    /**
     * Retrieves this specific task from the ArrayList.
     * @param taskIndex Task to retrieve.
     * @return Task object specified.
     */
    public Task get(int taskIndex) {
        return arrayOfTasks.get(taskIndex);
    }

    /**
     * Retrieves size of ArrayList.
     * @return Size of ArrayList as int.
     */
    public int taskArraySize() {
        return arrayOfTasks.size();
    }

    /**
     * Gets the list of tasks.
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return arrayOfTasks;
    }

    /**
     * Overrides toString method so as to customize output string format.
     * @return String in our desired format.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int size = arrayOfTasks.size();
        int index = 0;
        while (index < size) {
            Task curr = arrayOfTasks.get(index);
            output.append(index + 1);
            output.append(". ");
            output.append(curr.toString());
            output.append("\n");
            index++;
        }
        String finalOutput = output.toString().trim();
        return finalOutput;
    }
}
