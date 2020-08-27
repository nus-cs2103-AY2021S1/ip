import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
	protected final String at;
	protected LocalDate date;


	public Event(String description, String at) {
		super(description);
		this.at = at;
		try {
			this.date = LocalDate.parse(at);
		} catch (DateTimeParseException ex){
			this.date = null;
		}
	}

	public Event(String description, boolean isDone, String at) {
		super(description, isDone);
		this.at = at;
		try {
			this.date = LocalDate.parse(at);
		} catch (DateTimeParseException ex){
			this.date = null;
		}
	}

	@Override
	public Event completeTask() {
		return new Event(description, true, at);
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + (date != null ?
			(date.getDayOfWeek() + ", " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) : at) + ")";
	}
}
