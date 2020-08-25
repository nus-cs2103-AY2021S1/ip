import java.util.List;

public class TaskList {
    public List<Task> taskList;
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

    public int size() {
        return taskList.size();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        if (isEmpty()) {
            result = new StringBuilder("you haven't added any tasks to the list yet!");
        } else {
            result.append("1. ").append(taskList.get(0));
            for (int i = 2; i <= taskList.size(); i++) {
                result.append(" ✰\n✰ ").append(i).append(". ").append(taskList.get(i - 1));
            }
        }
        return result.toString();
    }

}
