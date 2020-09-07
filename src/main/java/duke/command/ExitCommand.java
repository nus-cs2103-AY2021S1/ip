package duke.command;

import duke.Main;
import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.ArrayList;


public class ExitCommand extends Command {

    public ExitCommand() {
        aliases = new ArrayList<>();
        aliases.add("ex");

    }

    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            fileHandler.writeToFile(tasks.getList());
            Main.getWindow().closeWindow();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DukeException("Error saving your file");
        }
        ui.displayThis("Bye. Hope to see you again soon!");
    }


}
