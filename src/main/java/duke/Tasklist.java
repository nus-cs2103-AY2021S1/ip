package duke;

import java.util.ArrayList;
import java.util.List;


public class Tasklist {

    private List<Task> taskList;

    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    public String toDisplayString() {
        if (taskList.isEmpty()) {
            return "You have no tasks!";
        } else {
            String taskListString = "";
            int taskNumber = 1;
            while (taskNumber < taskList.size() + 1) {
                Task task = taskList.get(taskNumber - 1);
                taskListString += taskNumber + "." + task.toDisplayString() + "\n";
                taskNumber++;
            }
            return taskListString.trim();
        }
    }

    public String matchedTasksOnly(String query) {
        int index = 1;
        String matchedTasks = "";
        for (Task task : taskList) {
            if (task.getDescription().contains(query)) {
                matchedTasks += index + "." + task.toDisplayString() + "\n";
                index++;
            }
        }
        return matchedTasks.trim();
    }

    public String toSavedString() {
        String taskListString = "";
        if (taskList.size() > 0) {
            int taskNumber = 1;
            while (taskNumber < taskList.size() + 1) {
                Task task = taskList.get(taskNumber - 1);
                taskListString += task.toSavedString() + "\n";
                taskNumber++;
            }
        }
        return taskListString;
    }

}
