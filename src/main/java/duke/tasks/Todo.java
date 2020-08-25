package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.Ui;

/**
 * Represent a to do task.
 */
public class Todo extends Task{
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * mark the task as done.
     * @return
     */
    @Override
    public Todo markDone() {
        super.markDone();
        return this;
    }

    /**
     * Add the task into the list and print the added message.
     * Write the changes into the file.
     * @param tasklist
     * @param ui
     * @param storage
     */
    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.add(this);
        ui.showAddedMessage(tasklist, tasklist.getNumOfTasks() - 1);
        storage.writeData(tasklist.taskList);
    }

    /**
     * String representation of the deadline task.
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formatted String representation of the task.
     * @return
     */
    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "T | " + doneOrNot + " | " + this.name;
    }
}
