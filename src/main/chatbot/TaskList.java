import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Iterator;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
        return true;
    }

    public Task removeTask(int index) throws ChatbotException {
        Task removed;
        try {
            removed = this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException("That item does not exist!");
        }
        return removed;
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

    public Task markAsDone(int index) throws ChatbotException {
        Task taskDone;
        try {
            taskDone = getTask(index).markDone();
            this.tasks.set(index, taskDone);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException("That item does not exist!");
        }
        return taskDone;
    }
}
