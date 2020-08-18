import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    public Task addTask(String taskType, String taskDescription) {
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new Todo(taskDescription);
        } else if (taskType.equals("event")) {
            newTask = new Event(taskDescription);
        } else {
            newTask = new Deadline(taskDescription);
        }
        taskList.add(newTask);
        return newTask;
    }


    public void completeTask(int index){
         taskList.get(index).markDone();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getNoTask() { return  taskList.size(); }

    public String toString() {
        String output = "";
        if (taskList.size() > 0) {
            output = "Here are the tasks in your list:\n";
            for (int i = 1; i < taskList.size() + 1; i++) {
                output += String.valueOf(i) + ". " + taskList.get(i - 1).toString() + "\n";
            }
        }
        return output;
    }
}
