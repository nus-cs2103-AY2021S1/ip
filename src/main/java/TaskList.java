import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task, String date, TaskType taskType) throws DukeException {

        Task t;
        switch (taskType) {
            case TODO: {
                t = new TodoTask(task);
                tasks.add(t);
                break;
            }
            case DEADLINE: {
                t = new DeadlineTask(task, date);
                tasks.add(t);
                break;
            }
            case EVENT: {
                t = new EventTask(task, date);
                tasks.add(t);
                break;
            }
            default:
                throw new DukeException("Invalid Task Type");
        }

        System.out.println("Got it. I've added this task:\n " + t);
    }

    void deleteTask (int index) {

        Task deletedTask = tasks.get(index - 1);
        tasks.remove(index - 1);

        System.out.println("Noted. I've removed this task:\n" + deletedTask);
    }

    void completeTask(int index) {

        Task completedTask = tasks.get(index - 1);
        completedTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n " + completedTask);

    }

    int size() {
        return tasks.size();
    }

    Task get(int i) {
        return tasks.get(i);
    }

    @Override
    public String toString() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }
}
