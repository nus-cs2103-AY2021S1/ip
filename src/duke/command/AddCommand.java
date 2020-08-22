package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import main.java.*;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.append(newTask);
        tasks.add(newTask);

        String output = "\t Got it. I've added this task:\n" +
                "\t   " + newTask + "\n" +
                "\t Now you have " + tasks.size() + " tasks in the list.\n" ;

        ui.showMessage(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
