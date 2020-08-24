package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class TodoCommand extends Command {
    Todo todo;

    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.todo);
        storage.save(tasks);

        int size = tasks.size();
        ui.printAddConfirmation(tasks.show(size - 1), size);
    }
}
