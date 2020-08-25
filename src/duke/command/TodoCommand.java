package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class TodoCommand extends Command {

    public TodoCommand(String task) {
        super(task);
    }

    /**
     * Executes any command corresponding to Todo keyword.
     * @param taskList List of tasks.
     * @param ui UI of the bot.
     * @param storage Storage managing the file in hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        processTodo(this.task, taskList, ui, storage);
    }

    /**
     * Processes all the todo command to determine the correct output.
     * @param theRest Parsed string containing task details.
     * @param taskList List containing all the task(s).
     * @param ui UI of the bot
     * @param storage Storage managing the file in hard disk.
     */
    public void processTodo(String theRest, TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(theRest);
        taskList.saveToList(todo);
        Storage.updateData(taskList.getTasks());
    }

    /**
     * Evaluates whether this and other object if this and
     * other object is the same or of the same type and task details.
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
