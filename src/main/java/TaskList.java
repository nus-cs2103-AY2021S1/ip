import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public void markTaskAsDone(int index) {
        taskList.get(index).setDone();
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
