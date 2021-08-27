package duke.command;

import java.util.regex.Pattern;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.exception.DukeStorageException;
import duke.task.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException, DukeStorageException {
        Pattern pattern = Pattern.compile("delete [1-9][0-9]{0,}");
        if (!pattern.matcher(getInputCommand()).matches()) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'delete' command format!");
        } else {
            int index = Integer.parseInt("" + getInputCommand().charAt(7)) - 1;
            if (list.getList().size() > index && index >= 0) {
                Task taskToPrint = list.getList().get(index);
                list.getList().remove(index);

                ui.printMessage("Noted. I've removed this task:");
                ui.printMessage(taskToPrint.toString());

                storage.save(list);
            } else {
                throw new DukeCommandException("\u2639 OOPS!!! There isn't a task with that index!");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
