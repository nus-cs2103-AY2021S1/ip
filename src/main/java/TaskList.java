import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public Task addTask(String taskType, String taskDescription) {
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new Todo(taskDescription);
        } else if (taskType.equals("event")) {
            String[] desAndDate = taskDescription.split("/at");
            String date = desAndDate[1];
            taskDescription = desAndDate[0] ;
            newTask = new Event(taskDescription, LocalDateTime.parse(date.strip(), formatter));
        } else {
            String[] desAndDate = taskDescription.split("/by");
            String date = desAndDate[1];
            taskDescription = desAndDate[0] ;
            newTask = new Deadline(taskDescription, LocalDateTime.parse(date.strip(), formatter));
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

    public String getTaskDueOn(String dueDate){
        String output = "";

        for (Task task : this.taskList) {
            if (task.isDueOn(LocalDate.parse(dueDate.strip(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))) {
                output += task.toString() + "\n";
            }
        }
        return (output == "" ? "None\n" : output) ;
    }

    public boolean isEmpty() { return this.taskList.isEmpty();}

    public boolean allDone() {
        for (Task task : this.taskList) {
            if (!task.isDone()) {
                return false;
            }
        }
        return true;
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
