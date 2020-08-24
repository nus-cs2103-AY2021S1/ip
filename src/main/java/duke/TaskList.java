package duke;

import java.util.List;

public class TaskList {

    List<Task> lstOfTask;

    public TaskList(List<Task> lstOfTask) {
        this.lstOfTask = lstOfTask;
    }

    public int getNumOfTask() {
        return lstOfTask.size();
    }

    public List<Task> getLstOfTask() {
        return this.lstOfTask;
    }

    public void add(Task task) {
        this.lstOfTask.add(task);
    }
}
