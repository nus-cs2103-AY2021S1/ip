package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
	protected final String by;
	protected LocalDate date;

	public Deadline(String description, String by) {
		super(description);
		this.by = by;
		try {
			this.date = LocalDate.parse(by);
		} catch (DateTimeParseException ex){
			this.date = null;
		}
	}

	public Deadline(String description, boolean isDone, String by) {
		super(description, isDone);
		this.by = by;
		try {
			this.date = LocalDate.parse(by);
		} catch (DateTimeParseException ex){
			this.date = null;
		}
	}

	@Override
	public Deadline completeTask() {
		return new Deadline(description, true, by);
	}

	@Override
	public String getData() {
		return "DEADLINE#" + description + "#" + String.valueOf(isDone) + "#" + by;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + (date != null ?
			(date.getDayOfWeek() + ", " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) : by) + ")";
	}
}
