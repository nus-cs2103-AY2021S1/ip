package duke.command;

import java.util.ArrayList;
import java.util.regex.Pattern;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;

public class ClearallCommand extends Command {
    public ClearallCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException {
        Pattern pattern = Pattern.compile("clearall");
        if (!pattern.matcher(getInputCommand()).matches()) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'clearall' command format!");
        } else {
            storage.deleteStorage();
            list.setList(new ArrayList<>());
            ui.printMessage("Alright. All tasks are removed!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
