import java.util.ArrayList;

/**
 *  Represents Tasks in a TaskList object and stores the various Tasks in a TaskList.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates a TaskList object that stores the tasks.
     * @param taskList an ArrayList of tasks to be stored
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the list of tasks stored.
     * 
     * @return an ArrayList of tasks stored
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the task in the list based on the index given.
     * 
     * @param index the index of the task in the list of tasks
     * @return the task requested
     * @throws PandaBotOutOfRangeException If the index given is negative or
     * greater than the number of tasks stored
     */
    public Task getTaskAt(int index) throws PandaBotOutOfRangeException
    {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new PandaBotOutOfRangeException();
        }
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return the number of tasks in the list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Deletes the task from the list at the given index.
     * 
     * @param index the index of the task in the list of tasks
     * @throws PandaBotOutOfRangeException If the index given is negative or 
     * greater than the number of tasks stored
     */
    public void deleteTask(int index) throws PandaBotOutOfRangeException {
        try {
            taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new PandaBotOutOfRangeException();
        }
    }

    /**
     * Adds a task to the list
     * 
     * @param task the task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Finds the tasks among the list of tasks, where their descriptions
     * contains the given input to match.
     * 
     * @param toMatch the word used in the search for tasks with matching descriptions 
     * @return a TaskList containing the list of tasks with matching descriptions
     */
    public TaskList FindTask(String toMatch) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        
        for(Task t : taskList) {
            if (t.getDescription().contains(toMatch)) {
                matchingTasks.add(t);
            }
        }
        
        return new TaskList(matchingTasks);
    }
}
