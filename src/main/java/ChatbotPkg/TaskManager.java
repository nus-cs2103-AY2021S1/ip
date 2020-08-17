package ChatbotPkg;

import java.util.ArrayList;

public class TaskManager {

    private static TaskManager tskManager = null;
    private TaskPrinter taskPrinter;
    private int taskCount;
    private ArrayList<Task> tasks;

    private TaskManager() {
        this.taskCount = 0;
        this.tasks = new ArrayList<>();
        this.taskPrinter = new TaskPrinter();
    }

    public static TaskManager getInstance() {
        if (tskManager == null) {
            tskManager = new TaskManager();
        }
        return tskManager;
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskPrinter.display("Got it. I've added this task:\n        " + task +
                String.format("\n    Now you have %d task(s) in the list.", count()));
    }

    public void removeTask(int index) {
        Task removed = this.tasks.remove(index);
        taskPrinter.display("Alright. I've removed this task:\n        " + removed +
                String.format("\n    Now you have %d task(s) in the list.", count()));
    }

    public void listAll() {
        taskPrinter.list(this.tasks);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public boolean markAsDone(int index) {
        Task taskDone = getTask(index).markDone();
        if (this.tasks.set(index, taskDone) != null) {
            taskPrinter.display("Nice! I've marked this task as done:\n    " + "    " + taskDone);
            return true;
        }
        return false;
    }

    public int count() {
        return this.tasks.size();
    }
}
