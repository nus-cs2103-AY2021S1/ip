import java.util.ArrayList;

/**
 * This class keeps track of, and manages the User's current list
 */
public class TaskList {

    private final ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return lst;
    }

    // This method is responsible for inserting text into list, and updating current index
    public void addTask(Task t) {
        this.lst.add(t);
    }

    public void completeTask(int i) {
        this.lst.get(i - 1).markAsDone();
    }

    public Task getTask(int i) {
        return this.lst.get(i - 1);
    }

    public int getNumTasks() {
        return this.lst.size();
    }

    public void deleteTask(int i) {
        this.lst.remove(i - 1);
    }

    @Override
    public String toString() {

        if (this.lst.isEmpty()) {
            return getDefaultMessage();
        } else {
            return getStringTaskList();
        }
    }

    private String getDefaultMessage() {
        return "List is currently empty";
    }

    private String getStringTaskList() {
        int taskNumber = 1;
        StringBuilder stringList = new StringBuilder();
        for (Task currTask : this.lst) {
            stringList.append(taskNumber++ + ". " + currTask + "\n");
        }

        return stringList.toString();
    }

    public String findTasksWith(String key) {
        StringBuilder tasks = new StringBuilder();
        int index = 1;
        for (Task currentTask : this.lst) {
            ifContainsThenAppend(currentTask, tasks, key, index++);
        }
        return tasks.toString();
    }

    private void ifContainsThenAppend(Task currentTask, StringBuilder tasks, String key, int index) {
        if (currentTask.containsKey(key)) {
            tasks.append(index + ". " + currentTask + "\n");
        }
    }
}
