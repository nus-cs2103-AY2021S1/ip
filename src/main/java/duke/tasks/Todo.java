package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a to do task.
 */
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Marks the task as done.
     *
     * @return
     */
    @Override
    public Todo markDone() {
        super.markDone();
        return this;
    }

    /**
     * Adds the task into the list and print the added message.
     * Writes the changes into the file.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this);
        storage.writeData(taskList.getTaskList());
        return ui.showAddedMessage(taskList, taskList.getNumOfTasks() - 1);

    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the formatted String representation of the task.
     *
     * @return
     */
    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "T | " + doneOrNot + " | " + this.name;
    }
}
