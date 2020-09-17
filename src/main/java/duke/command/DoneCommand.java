package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for DoneCommand class.
     * @param args
     * @param tasks
     * @param storage
     */
    public DoneCommand(String[] args, TaskList tasks, Storage storage) {
        super.args = args;
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public String execute() throws DukeException {
        if (args.length < 2) {
            throw new DukeException("OOPS!!! The description of a done cannot be empty.");
        }

        int inputNumber;

        try {
            inputNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Argument must be an integer.");
        }

        if (inputNumber <= 0) {
            throw new DukeException(("OOPS!!! Invalid argument."));
        }

        if (inputNumber > tasks.size()) {
            throw new DukeException("OOPS!!! There is only " + tasks.size() + " tasks in the list.");
        }

        int index = inputNumber - 1;

        assert index < tasks.size();

        Task targetTask = tasks.get(index);

        if (targetTask.getStatus()) {
            throw new DukeException("OOPS!!! You've already done that task.");
        }

        targetTask.setDone();
        tasks.updateStorage(storage);

        return "Nice! I've marked this task as done: \n\t" + targetTask;
    }
}
