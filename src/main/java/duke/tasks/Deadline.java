package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a deadline task.
     *
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Marks the deadline task as done/
     *
     * @return
     */
    @Override
    public Deadline markDone() {
        super.markDone();
        return this;
    }

    /**
     * Gets the type of the task.
     *
     * @return String representation of the type.
     */
    @Override
    public String getType() {
        return "deadline";
    }

    /**
     * Checks whether the tasks is on today.
     *
     * @return is today or not.
     */
    @Override
    public boolean isOnTheDate(LocalDate date) {
        return this.by.toLocalDate().isEqual(date);
    }

    /**
     * Adds the task into the list and print the added message.
     * Writes the changes into the file.
     *
     * @param tasklist
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.add(this);
        storage.writeData(tasklist.getTaskList());
        return ui.showAddedMessage(tasklist, tasklist.getNumOfTasks() - 1);
    }

    /**
     * Shows string representation of the deadline task.
     *
     * @return
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Shows formatted String representation of the task.
     *
     * @return
     */
    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "D | " + doneOrNot + " | " + this.name + " | "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

}
