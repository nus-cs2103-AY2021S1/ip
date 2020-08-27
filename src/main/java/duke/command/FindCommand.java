package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.exception.DukeStorageException;
import duke.task.Task;

import java.util.regex.Pattern;

public class FindCommand extends Command{
    public FindCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException, DukeStorageException {
        Pattern pattern = Pattern.compile("find ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*)");
        if(!pattern.matcher(getInputCommand()).matches()) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'find' command format!");
        } else {
            String matchingString = getInputCommand().substring(5);

            if(list.getList().size() == 0) {
                ui.printMessage("There is nothing in match!");
            } else {
                ui.printMessage("Here are the matching tasks in your list:");

                int ctr = 1;
                for(Task task: list.getList()) {
                    if(task.getDescription().contains(matchingString)) {
                        ui.printMessage("" + ctr + "." + task);
                        ctr++;
                    }
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
