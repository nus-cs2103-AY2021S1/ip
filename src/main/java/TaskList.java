import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * TaskList stores a list of tasks and
 * the corresponding operations on it.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a new task to the list of tasks.
     *
     * @param newTask the new task to be added.
     * @return a new TaskList containing all the old tasks and
     * the new task.
     */
    public TaskList addTask(Task newTask) {
        List<Task> newTaskList = tasks;
        newTaskList.add(newTask);
        return new TaskList(newTaskList);
    }

    /**
     * Print all the tasks.
     *
     * @return a String shows all takes in a formatted way.
     */
    public String printTasks() {
        String result = "";

        result += IntStream.range(0, tasks.size())
                .mapToObj((id) -> String.format("%d.%s\n", id + 1, tasks.get(id)))
                .reduce((a, b) -> a + b)
                .get();

        return result;
    }

}
