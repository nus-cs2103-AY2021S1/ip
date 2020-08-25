package duke.command;

import duke.task.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Display all items in taskList
 */
public class ListCommand implements Command {

    private final List<Task> taskList;

    public ListCommand(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {

        if (taskList.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        AtomicInteger index = new AtomicInteger(0);
        taskList.stream()
                .map((task) -> index.incrementAndGet() + ". " + task.toString() )
                .forEach(System.out::println);

    }

}
