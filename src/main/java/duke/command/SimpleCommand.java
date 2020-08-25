package duke.command;

import duke.exception.*;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class SimpleCommand extends Command {

    private String input;
    private SimpleCommandType type;

    public SimpleCommand(String input, SimpleCommandType type) {
        this.input = input;
        this.type = type;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (checkIfNumber(input)) {
            int digit = Integer.parseInt(input);
            if (tasks.checkIfValid(digit)) {
                Task current = tasks.get(digit - 1);
                if (type == SimpleCommandType.DONE) {
                    if (current.isDone()) {
                        throw new TaskAlreadyDoneException();
                    } else {
                        current.markAsDone();
                        ui.markTaskAsDone(current);
                    }
                } else {
                    tasks.delete(digit - 1);
                    ui.deleteTask(current, tasks.size());
                }
            } else {
                throw new InvalidTaskNumberException(tasks.size());
            }
        } else {
            if (type == SimpleCommandType.DONE) {
                throw new InvalidDoneException();
            } else {
                throw new InvalidDeleteException();
            }
        }
        storage.update(tasks);
    }

    private static boolean checkIfNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
