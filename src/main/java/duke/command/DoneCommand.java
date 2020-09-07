package duke.command;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.ArrayList;


public class DoneCommand extends Command {
    private final int indexEntryDone;


    public DoneCommand(int indexEntryDone) {
        this.indexEntryDone = indexEntryDone;
        aliases = new ArrayList<>();
        aliases.add("d");
        aliases.add("done");

    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            ui.displayThis("Nice! I've marked this task as done: \n" + tasks.done(indexEntryDone));
            fileHandler.writeToFile(tasks.getList());
        } catch (Exception ex) {
            throw new DukeException("This task does not exist");
        }
    }
}
