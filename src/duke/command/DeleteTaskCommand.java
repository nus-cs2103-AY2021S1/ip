package duke.command;


import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String command) {
        super(command);
    }

    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 6) {
                throw new DukeException("☹ OOPS!!! Check delete formatting, include which task to delete.");
            } else if (Character.getNumericValue(this.command.charAt(7)) > list.size() || Character.getNumericValue(this.command.charAt(7)) == 0) {
                throw new DukeException("☹ OOPS!!! Task not in the list");
            }
            int index = Character.getNumericValue(this.command.charAt(7));
            Task toRemove = list.get(index-1);
            list.remove(index-1);
            ui.saySomthing("Noted. I've removed this task:\n" + toRemove.toString() + "\n" + String.format("Now you have %d tasks in the list.", list.size()));
            saveData.deleteTask(index);
        } catch (DukeException | IOException e) {
            ui.saySomthing(e.getMessage());
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }


}
