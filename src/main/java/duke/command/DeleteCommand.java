package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for DeleteCommand class.
     * @param args
     * @param tasks
     * @param storage
     */
    public DeleteCommand(String[] args, TaskList tasks, Storage storage) {
        super.args = args;
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public String execute() throws DukeException {
        if (args.length < 2) {
            return new ErrorCommand("OOPS!!! The description of a delete cannot be empty.").execute();
        }

        int inputNumber;

        try {
            inputNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            return new ErrorCommand("OOPS!!! Argument must be an integer.").execute();
        }

        if (inputNumber <= 0) {
            return new ErrorCommand(("OOPS!!! Invalid argument.")).execute();
        }

        if (inputNumber > tasks.size()) {
            return new ErrorCommand("OOPS!!! There is only " + tasks.size() + " tasks in the list.").execute();
        }

        int index = inputNumber - 1;
        Task targetTask = tasks.remove(index, storage);
        return "Noted. I've removed this task: \n\t" + targetTask + "\nNow you have " + tasks.size()
                + " tasks in the list.";
    }
}
