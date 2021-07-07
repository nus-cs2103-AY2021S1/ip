package command;

import duke.Storage;
import duke.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Insert a new todo into current task list
 */
public class TodoCommand extends Command {
    private Todo todo;

    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    /**
     * Insert a new todo into the current task list and save it to the storage file
     *
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.todo);
        storage.save(tasks);

        int size = tasks.size();
        return new CommandResult(ui.printAddConfirmation(tasks.show(size - 1), size));
    }
}
