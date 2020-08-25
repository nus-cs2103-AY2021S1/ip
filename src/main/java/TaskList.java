import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;
    private Ui ui;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
        this.ui = new Ui();
    }
    
    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
        this.taskCount = savedTasks.size();
        this.ui = new Ui();
    }
    
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    
    public void addTask(Task task) {
        this.tasks.add(task);
        this.taskCount += 1;
        ui.printMessage(" Your task has been recorded.",
                "   " + task,
                " You have " + this.taskCount + " tasks currently.");
    }
    
    public void deleteTask(int index) {
        if (index < 1 || this.taskCount < index) {
            ui.printMessage(" Sorry I cannot find your specified task :(");
        } else {
            Task removed = this.tasks.get(index - 1);
            this.tasks.remove(index - 1);
            this.taskCount -= 1;
            ui.printMessage(" Okay, I will remove this task for you",
                    "   " + removed,
                    " You have " + this.taskCount + " tasks currently.");
        }
    }
    
    public void listTasks() {
        if (this.taskCount == 0) {
            ui.printMessage(" You've got no tasks now.",
                    " If you want to get busy add more task.",
                    " I'll remember them for you :)");
        } else {
            ui.printMessage(" Let me list out all your tasks...");
            for (int i = 0; i < this.taskCount; i++) {
                ui.printMessage(" " + (i + 1) + "." + this.tasks.get(i));
            }
        }
    }
    
    public void markAsDone(int index) {
        if (index < 1 || this.taskCount < index) {
            ui.printMessage(" Sorry I cannot find your specified task :(");
        } else {
            this.tasks.get(index - 1).completeTask();
            ui.printMessage(" Congratulations for finishing this task!",
                    " Let me mark this as done for you.",
                    "   " + this.tasks.get(index - 1));
        }
    }
}
