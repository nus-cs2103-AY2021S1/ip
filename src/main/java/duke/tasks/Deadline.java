package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Ui;
import duke.tool.Storage;
import duke.tool.TaskList;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a deadline task.
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Marks the deadline task as done,
     * @return
     */
    @Override
    public Deadline markDone() {
        super.markDone();
        return this;
    }

    /**
     * Adds the task into the list and print the added message.
     * Writes the changes into the file.
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
     * Shows string representation of the deadline task.
     * @return
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Shows formatted String representation of the task.
     * @return
     */
    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "D | " + doneOrNot + " | " + this.name + " | "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

}
