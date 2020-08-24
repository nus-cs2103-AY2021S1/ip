import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<Task>();

    public Task addTask(String taskType, String taskDescription) {
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new Todo(taskDescription);
        } else if (taskType.equals("event")) {
            String[] desAndDate = taskDescription.split("/at");
            String date = desAndDate[1];
            taskDescription = desAndDate[0] ;
            newTask = new Event(taskDescription, date);
        } else {
            String[] desAndDate = taskDescription.split("/by");
            String date = desAndDate[1];
            taskDescription = desAndDate[0] ;
            newTask = new Deadline(taskDescription, date);
        }
        this.taskList.add(newTask);
        return newTask;
    }

    public void completeTask(int index){
         this.taskList.get(index).markDone();
    }

    public Task deleteTask(int index) {
        Task task = this.taskList.get(index);
        this.taskList.remove(index);
        return task;
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int getNoTask() { return  this.taskList.size(); }

    public boolean isEmpty() { return this.taskList.isEmpty();}

    public boolean allDone() {
        for (Task task : this.taskList) {
            if (!task.isDone()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList getTaskList(){
        return this.taskList;
    }

    public String toString() {
        String output = "";
        if (this.taskList.size() > 0) {
            output = "Here are the tasks in your list:\n";
            for (int i = 1; i < this.taskList.size() + 1; i++) {
                output += String.valueOf(i) + ". " + this.taskList.get(i - 1).toString() + "\n";
            }
        }
        return output;
    }
}
