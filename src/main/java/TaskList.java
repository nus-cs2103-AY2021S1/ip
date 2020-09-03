import java.util.ArrayList;

// contains the task list
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void completeTask(int i) throws DukeException {
        try {
            taskList.get(i - 1).markComplete();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please give a valid index. \""
                    + i + "\" is not a valid index.\n");
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        try {
            taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please give a valid index. \""
                    + index + "\" is not a valid index.\n");
        }
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }
}
