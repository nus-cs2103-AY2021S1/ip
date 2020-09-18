package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;

public class TodoCommand extends Command {
    private String taskName;

    public TodoCommand(String taskName) {
        super();
        this.cmd = CMD.TODO;
        this.taskName = taskName;
    }

    @Override
    public String getResponse(TaskList tasklist, Storage storage) throws DukeException {
        String msg = "I PUT NEW TING IN DA LIST\n  " + tasklist.addTodo(this.taskName)
                    + "\nNAO U HAS " + tasklist.getNumberOfTasks() + " FINGS IN DA LIST LULZIES";
        storage.save(tasklist);
        return msg;
    }

    @Override
    public String toString() {
        return this.cmd.toString() + ": " + this.taskName;
    }
}
