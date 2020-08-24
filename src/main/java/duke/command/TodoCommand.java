package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Todo;
import duke.Ui;

public class TodoCommand extends Command {
    Todo todo;
    
    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.todo);
        storage.save(taskList);
        
        int size = taskList.size();
        ui.printAddConfirmation(taskList.show(size - 1), size);
    }
}
