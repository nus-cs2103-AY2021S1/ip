package duke.command;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


public class ExitCommand extends Command {


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            fileHandler.writeToFile(tasks.getList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DukeException("Error saving your file");
        }
        ui.displayThis("Bye. Hope to see you again soon!");
    }


}