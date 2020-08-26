import java.util.ArrayList;

public class TaskList {
    
    private final ArrayList<Task> myTaskList;
    
    TaskList() {
        myTaskList = new ArrayList<>();
    }
    
    TaskList(ArrayList<Task> taskList) {
        this.myTaskList = taskList;
    }
    
    ArrayList<Task> getMyTaskList() {
        return myTaskList;
    }
    
    int getListLength() {
        return myTaskList.size();
    }

    public void addNewTask(Task newTask) {
        myTaskList.add(newTask);
    }
    
    public Task doneTask(int taskIndex) {
        Task temp = myTaskList.get(taskIndex - 1);
        temp.markAsDone();
        return temp;
    }
    
    public Task deleteTask(int taskIndex) {
        Task temp = myTaskList.get(taskIndex - 1);
        myTaskList.remove(temp);
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder taskListString = new StringBuilder();
        for (int i = 1; i < myTaskList.size() + 1; i++) {
            taskListString.append("     ").append(i).append(".").append(myTaskList.get(i - 1)).append("\n");
        }
        taskListString.delete(taskListString.length() - 1, taskListString.length());
        return taskListString.toString();
    }
}
