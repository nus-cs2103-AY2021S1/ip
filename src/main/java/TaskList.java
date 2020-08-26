import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public ArrayList<Task> getTasksList() {
        return this.tasksList;
    }

    public Task markAsDone(int idx) throws DukeException {
        if (idx < 0 || idx > getSize() - 1) {
            throw new DukeException("Sorry, the task does not exist");
        } else {
            Task task = tasksList.get(idx);
            task.setDone();
            return task;
        }
    }

    public void addTask(Task task) {
        tasksList.add(task);
    }

    public Task deleteTask(int idx) throws DukeException {
        if (idx < 0 || idx > getSize() - 1) {
            throw new DukeException("Sorry, the task does not exist");
        } else {
            Task task = tasksList.get(idx);
            tasksList.remove(idx);
            return task;
        }
    }

    public int getSize() {
        return tasksList.size();
    }
}
