package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TodoTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Command to add a
 * new TodoTask.
 */
public class TodoCommand implements Command {
    String description;

    /**
     * Initializes a TodoCommand.
     * @param description The description of the TodoTask.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a new TodoTask.
     * @param ui The ui of Duke.
     * @param storage The storage object.
     * @param tasks The taskList.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        Task todoTask = new TodoTask(description);
        tasks.add(todoTask);
        ui.printWithWrapper(new ArrayList<>(List.of(
                "Sure! I have added the following todo task to your list: ",
                todoTask.toString(),
                tasks.getListStatus())), false, false);
    }
}
