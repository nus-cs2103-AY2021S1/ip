package duke.command;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


public class DoneCommand extends Command {
    private final int entryDone;


    public DoneCommand(int entryDone) {
        this.entryDone = entryDone;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            ui.displayThis("Nice! I've marked this task as done: \n        " + tasks.done(entryDone));
        } catch (Exception ex) {
            throw new DukeException("This task does not exist");
        }
    }
}