package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.Ui;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        ui.printMessage(String.format("Okay! I've added the following task: \n %s", todo.toString()));
        storage.updateTasks(tasks.getListOfTasks());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof TodoCommand) {
            return this.description.equals(((TodoCommand) other).description);
        } else {
            return false;
        }
    }
}
