package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents an add command.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD_TODO = "todo";
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";
    private static final String MESSAGE_ADD_ACKNOWLEDGEMENT = "*Gobble gobble* the following has been eated OwO:";
    private static final String MESSAGE_ADD_UPDATE = "Number of thing(s) in my belly has now become ";
    private Task task;

    /**
     * Creates an AddCommand instance
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to current task list, saves the task file in hard disk
     * and displays acknowledgement message that task has been added to user.
     *
     * @param tasks Task list representing current tasks.
     * @param ui User interface interacting with user.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @return A string representing Duke's response after executing command.
     * @throws DukeException If unable to either add task or save task file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.saveTaskList(task.saveToString());
        return String.format("%s\n%s\n%s%d!", MESSAGE_ADD_ACKNOWLEDGEMENT, task.toString(),
                MESSAGE_ADD_UPDATE, tasks.getTaskCount());
    }



}
