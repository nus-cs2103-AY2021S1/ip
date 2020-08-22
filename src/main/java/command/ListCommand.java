package command;

import task.Task;

import java.util.List;

/**
 * Display all items in taskList
 */
public class ListCommand extends Command {

    private final List<Task> taskList;

    public ListCommand(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        int i = 0;

        for (Task item : taskList) {
            System.out.println(++i + ". " + item.toString());
        }

        if (i == 0) {
            System.out.println("List is empty!");
        }
    }

    @Override
    public void undo() {
        // Operation unsupported
        System.out.println("Undo: ListCommand");
    }
}
