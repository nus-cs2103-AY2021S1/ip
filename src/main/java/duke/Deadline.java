package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
	/** The deadline of the task. */
	protected final String by;
	/** Deadline of the task as java.time.LocalDate. */
	protected LocalDate date;

	/**
	 * Constructor for Deadline.
	 * @param description the description of the task.
	 * @param by the deadline of the task.
	 */
	public Deadline(String description, String by) {
		super(description);
		this.by = by;
		try {
			this.date = LocalDate.parse(by);
		} catch (DateTimeParseException ex){
			this.date = null;
		}
	}

	/**
	 * Constructor for Deadline.
	 * @param description the description of the task.
	 * @param isDone boolean to indicates whether the task has been done or not.
	 * @param by the deadline of the task.
	 */
	public Deadline(String description, boolean isDone, String by) {
		super(description, isDone);
		this.by = by;
		try {
			this.date = LocalDate.parse(by);
		} catch (DateTimeParseException ex){
			this.date = null;
		}
	}

	/**
	 * Marks the Deadline as done.
	 * @return Deadline with updated status (done).
	 */
	@Override
	public Deadline completeTask() {
		return new Deadline(description, true, by);
	}

	/**
	 * Gets the format of the Deadline to be saved in hard disk.
	 * @return Deadline object in specified format.
	 */
	@Override
	public String getData() {
		return "DEADLINE#" + description + "#" + String.valueOf(isDone) + "#" + by;
	}

	/**
	 * Gets the string representation of the Deadline object.
	 * @return the string representation of Deadline.
	 */
	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + (date != null ?
			(date.getDayOfWeek() + ", " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) : by) + ")";
	}
}
