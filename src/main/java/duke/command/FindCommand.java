package duke.command;

import java.util.regex.Pattern;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.exception.DukeStorageException;
import duke.task.Task;

public class FindCommand extends Command {
    public FindCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException, DukeStorageException {
        Pattern pattern = Pattern.compile("find ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*)");
        if (!pattern.matcher(getInputCommand()).matches()) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'find' command format!");
        } else {
            String matchingString = getInputCommand().substring(5);

            if (list.getList().size() == 0) {
                ui.printMessage("There is nothing in match!");
            } else {
                int taskIndexCounter = 1;
                StringBuilder stringBuilder = new StringBuilder();
                for (Task task: list.getList()) {
                    if (task.getDescription().contains(matchingString)) {
                        stringBuilder.append(taskIndexCounter).append(".").append(task).append("\n");
                        taskIndexCounter++;
                    }
                }

                if (stringBuilder.length() > 0) {
                    ui.printMessage("Here are the matching tasks in your list:");
                    ui.printMessage(stringBuilder.toString());
                } else {
                    ui.printMessage("No matching task found!");
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
