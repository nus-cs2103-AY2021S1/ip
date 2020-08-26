package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String[] nextCommandArr;
    
    public TodoCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Todo newTodo = new Todo(nextCommandArr[1]);
            tasks.add(newTodo);
            ui.addTaskText(newTodo, tasks);
        } catch (Exception e) {
            throw new DukeException("The description of a todo cannot be empty~");
        }
        
    }

    @Override
    public boolean continueRunning() {
        return true;
    }
}
