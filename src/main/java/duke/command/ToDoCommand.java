package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for ToDoCommand class.
     * @param args
     * @param tasks
     * @param storage
     */
    public ToDoCommand(String[] args, TaskList tasks, Storage storage) {
        super.args = args;
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public String execute() throws DukeException {
        if (args.length < 2 || args[1].isBlank()) {
            return new ErrorCommand("OOPS!!! The description of a todo cannot be empty.").execute();
        }

        ToDo newToDo = new ToDo(args[1]);

        tasks.add(newToDo, storage);
        return "Got it. I've added this task: \n\t" + newToDo + "\nNow you have " + tasks.size()
                + " tasks in the list.";
    }
}
