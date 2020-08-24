package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {

    public DoneCommand(String[] stringArray) {
        super(stringArray);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (super.isFirstIndexEmpty()) {
            throw new DukeException("Oops, please enter a task number after your command!");
        }
        try {
            int taskNumber = Integer.parseInt(getFirstIndex());
            if (taskNumber <= 0 || !tasks.isTaskPresent(taskNumber - 1)) {
                throw new DukeException("Oops, enter a task number that already exists in the list "
                        + "(starting from 1 to " + tasks.totalTask() + ").");
            }
            Task taskToMarkDone = tasks.getTask(taskNumber - 1);
            taskToMarkDone.markDone();
            ui.showDone(taskToMarkDone);
        } catch (NumberFormatException e) {
            throw new DukeException("Task Number to mark done must be an Integer!");
        }
        storage.write(tasks);
    }
}

