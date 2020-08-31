package duke.command;

import duke.task.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * List all items in taskList
 */
public class ListCommand implements Command {

    private final List<Task> taskList;

    /**
     * Create a ListCommand which list all tasks in specified taskList
     * @param taskList A specified taskList of interest
     */
    public ListCommand(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * List all items in taskList
     */
    @Override
    public void execute() {

        // Case1 : taskList is empty
        // Inform user and return
        if (taskList.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        // Case 2: taskList is not empty
        // Iterate and print all items in taskList
        AtomicInteger index = new AtomicInteger(0);
        taskList.stream()
                .map((task) -> index.incrementAndGet() + ". " + task.toString())
                .forEach(System.out::println);

    }

}
