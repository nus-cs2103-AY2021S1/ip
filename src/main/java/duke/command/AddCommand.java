package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class AddCommand extends Command {
    private Task task;
    
    public AddCommand(Task task) {
        this.task = task;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.showAddMessage(this.task, tasks);
        storage.writeFile(tasks);
    }
    
    public boolean isExit() {
        return false;
    }
    
    @Override
    public String toString() {
        return task.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AddCommand)) {
            return false;
        }

        AddCommand command = (AddCommand) obj;

        return command.toString().equals(toString());
    }
    
}
