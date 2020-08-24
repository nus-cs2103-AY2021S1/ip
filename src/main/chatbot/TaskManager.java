import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Iterator;

public class TaskManager {

    private TaskPrinter taskPrinter;
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskPrinter = new TaskPrinter();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int count() {
        return this.tasks.size();
    }

    public boolean addTask(Task task) {
        if (task == null) {
            return false;
        }
        tasks.add(task);
        taskPrinter.display("Got it. I've added this task:\n        " + task +
                String.format("\n    Now you have %d task(s) in the list.", count()));
        return true;
    }

    public boolean removeTask(int index) throws ChatbotException {
        try {
            Task removed = this.tasks.remove(index);
            taskPrinter.display("Alright. I've removed this task:\n        " + removed +
                    String.format("\n    Now you have %d task(s) in the list.", count()));
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException("That item does not exist!");
        }
        return true;
    }

    public ArrayList<Task> retrieveTasksOnDate(LocalDate date) {
        Iterator<Task> iter = this.tasks.iterator();
        ArrayList<Task> tasks = new ArrayList<>();
        while (iter.hasNext()) {
            Task tsk = iter.next();
            LocalDate taskDate = tsk.getDate();
            if (taskDate != null && taskDate.equals(date)) {
                tasks.add(tsk);
            }
        }
        return tasks;
    }

    public boolean markAsDone(int index) {
        Task taskDone = getTask(index).markDone();
        try {
            this.tasks.set(index, taskDone);
            taskPrinter.display("Nice! I've marked this task as done:\n    " +
                    "    " + taskDone);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
}
