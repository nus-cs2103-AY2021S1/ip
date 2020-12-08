package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.StorageException;
import duke.task.Todo;

public class TodoCommand extends Command {

    public TodoCommand(String task) {
        super(task);
    }

    /**
     * Executes any command corresponding to Todo keyword.
     *
     * @param taskList List of tasks.
     * @param ui       UI of the bot.
     * @param storage  Storage managing the file in hard disk.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return processTodo(this.task, taskList, ui, storage);
        } catch (StorageException s) {
            return s.getMessage();
        }
    }

    /**
     * Processes all the todo command to determine the correct output.
     *
     * @param theRest  Parsed string containing task details.
     * @param taskList List containing all the task(s).
     * @param ui       UI of the bot
     * @param storage  Storage managing the file in hard disk.
     */
    public String processTodo(
        String theRest, TaskList taskList, Ui ui, Storage storage) throws StorageException {
        Todo todo = new Todo(theRest);
        Storage.updateData(taskList.getTasks());
        return taskList.saveToList(todo);
    }

    /**
     * Evaluates whether this and other object if this and
     * other object is the same or of the same type and task details.
     *
     * @param other Other object to compare.
     * @return True if this object
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof TodoCommand) {
            TodoCommand todoCommand = (TodoCommand) other;
            return this.task.equals(todoCommand.getTask());
        }
        return false;
    }
}
