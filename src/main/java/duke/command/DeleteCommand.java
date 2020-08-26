package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int num;

    public DeleteCommand(String command, int num) {
        super(command, false);
        this.num = num;
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            if (num < 0 || num > list.size()) {
                throw new DukeException("☹ OOPS!!! there is no such task");
            } else {
                Task deleted = list.delete(num - 1);
                ui.deleteMessage(deleted.toString(), list.size());
                storage.deleteTask(num);
            }
        } catch (DukeException | IOException e) {
            ui.errorEncounter(e);
        }
    }
}
