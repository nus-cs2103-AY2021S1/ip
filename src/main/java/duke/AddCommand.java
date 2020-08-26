package duke;

import java.time.DateTimeException;


public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.add(task);
            storage.save(tasks);
            ui.display("Got it. I've added this task:\n"
                    + "  " + task.toDisplayString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list");
        } catch (DateTimeException dateTimeException) {
            throw new DukeException("Please enter a valid date and time in the format 'DD-MM-YYYY HHMM'!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
