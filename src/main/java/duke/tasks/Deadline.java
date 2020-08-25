package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Mark the deadline task as done,
     * @return
     */
    @Override
    public Deadline markDone() {
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
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Formatted String representation of the task.
     * @return
     */
    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "D | " + doneOrNot + " | " + this.name + " | " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

}
