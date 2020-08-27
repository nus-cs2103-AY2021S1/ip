import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void deleteTask(Task t) {
        taskList.remove(t);
    }

    public void markTaskAsDone(Task t) {
        taskList.add(t);
    }

    @Override
    public String toString() {
        String listAsString = "";
        int count = 1;
        for (Task i : this.taskList) {
            listAsString = listAsString.concat("    "
                    + count + ". "
                    + i.toString()) + "\n";
            count++;
        }
        return listAsString;
    }
}
