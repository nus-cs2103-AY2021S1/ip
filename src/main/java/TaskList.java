import java.util.ArrayList;

/**
 * The TaskList class represents the running list of Tasks. It uses the ArrayList to store Tasks.
 * Note that the list uses one-based numbering.
 * @author Jaya Rengam
 */
public class TaskList {
    private ArrayList<Task> taskArrayList;

    TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Adds a Task to the end of the list.
     */
    public void addTask(Task newTask) {
        taskArrayList.add(newTask);
    }

    /**
     * Removes the Task at the specified position in the list.
     */
    public void deleteTask(int taskNum) {
        taskArrayList.remove(taskNum - 1);
    }

    /**
     * Gets the Task at the specified position in the list.
     */
    public Task getTask(int taskNum) {
        return this.taskArrayList.get(taskNum - 1);
    }

    /**
     * Marks the Task at the specified position as done.
     * @throws CartonaException if the given task is already done
     */
    public void completeTask(int taskNum) throws CartonaException {
        Task toComplete = taskArrayList.get(taskNum - 1);

        if (toComplete.checkIfDone()) {
            throw new CartonaException(String.format("Error: Task %d is already done.", taskNum));
        }

        toComplete.complete();
    }

    @Override
    public String toString() {
        int counter = 0;
        String printedListString = "";
        for (Task task: taskArrayList) {
            counter++;
            printedListString += String.format("     %d.%s%n", counter, task);
        }
        return printedListString;
    }

    /**
     * Gets the String representation of the TaskList for storage.
     */
    public String getListForStorage() {
        String stringToWrite = "";
        for (int i = 1; i <= this.getSize(); i++) {
            Task task = this.getTask(i);
            stringToWrite += task.getAbbreviatedString() + "\n";
        }

        return stringToWrite;
    }

    /**
     * Gets the current size of the TaskList.
     */
    public int getSize() {
        return this.taskArrayList.size();
    }
}