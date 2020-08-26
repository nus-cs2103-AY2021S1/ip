package duke.task;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list in which all the tasks are stored while Duke is running.
 */
public class TaskList {
    private List<Task> taskList;
    
    public TaskList(List<Task> list) {
        this.taskList = list;
    }
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * A function to add a task to the list.
     * @param task The task that should be added to the list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * A function to get a specific task from the list.
     * @param index the number representing the task in the list.
     * @return a Task from the list.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * A function to get the number of tasks in the list.
     * @return an integer that represents the number of tasks in the list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * A function to remove a task from the list.
     * @param index the number representing the task that should be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * A function to print all of the tasks currently in the list.
     */
    public void printTaskList() {
        for (int i = 0; i < taskList.size(); i++) {
            String num = (i + 1) + ". ";
            Task current = taskList.get(i);
            System.out.println(num + current);
        }
    }
}

