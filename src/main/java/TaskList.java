import java.util.ArrayList;
import java.util.List;

/**
 * Stores the list of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Creates a new TaskList with the given pre-existing tasks.
     * @param read The list of tasks read from the save file.
     */
    public TaskList(List<Task> read) {
        this.taskList = read;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void add(Task toAdd) {
        taskList.add(toAdd);
    }

    public void delete(int toDelete) {
        taskList.remove(toDelete);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Converts tasks in taskList to String format.
     * @return String representation of the tasks in list, or a message if it is empty.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (isEmpty()) {
            result = new StringBuilder("this list doesn't contain any tasks!");
        } else {
            result.append("1. ").append(taskList.get(0));
            for (int i = 2; i <= taskList.size(); i++) {
                result.append(" ✰\n✰ ").append(i).append(". ").append(taskList.get(i - 1));
            }
        }
        return result.toString();
    }

    public TaskList findTasks(String keyword) {
        List<Task> temp = new ArrayList<Task>();
        for (Task t : taskList) {
            if (t.task.contains(keyword)) {
                temp.add(t);
            }
        }
        return new TaskList(temp);
    }

}
