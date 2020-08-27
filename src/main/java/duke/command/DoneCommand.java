package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.exception.DukeStorageException;
import duke.task.Task;

import java.util.regex.Pattern;

public class DoneCommand extends Command{
    public DoneCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException, DukeStorageException {
        Pattern pattern = Pattern.compile("done [1-9][0-9]{0,}");
        if(!pattern.matcher(getInputCommand()).matches()) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'done' command format!");
        } else {
            int index = Integer.parseInt(""+getInputCommand().charAt(5)) - 1;
            if(list.getList().size() >= index && index >= 0) {
                Task tas = list.getList().get(index);
                tas.markAsDone();

                ui.printMessage("Nice! I've marked this task as done:");
                ui.printMessage(tas.toString());
                storage.save(list);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
