package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    TaskList tasks;
    Storage storage;
    
    public DoneCommand(String[] args, TaskList tasks, Storage storage) {
        super.args = args;
        this.tasks = tasks;
        this.storage = storage;
    }
    
    @Override
    public String execute() throws DukeException {
        if (args.length < 2) {
            throw new DukeException("\t☹ OOPS!!! The description of a done cannot be empty.");
        }

        int inputNumber;

        try {
            inputNumber = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            throw new DukeException("\t☹ OOPS!!! Argument must be an integer.");
        }

        if (inputNumber <= 0) {
            throw new DukeException(("\t☹ OOPS!!! Invalid argument."));
        }

        if (inputNumber > tasks.size()) {
            throw new DukeException("\t☹ OOPS!!! There is only " + tasks.size() + " tasks in the list.");
        }

        int index = inputNumber - 1;
        Task targetTask = tasks.get(index);

        if (targetTask.getStatus()) {
            throw new DukeException("\t☹ OOPS!!! You've already done that task.");
        }

        targetTask.setDone();
        tasks.updateStorage(storage);
        
        return "\tNice! I've marked this task as done: \n\t\t" + targetTask;
    }
}
