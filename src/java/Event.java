import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

	LocalDate time;

	Event(String name, LocalDate time) {
		super(name);
		this.time = time;
	}

	Event(String name, LocalDate time, String completed) {
		super(name, completed);
		this.time = time;
	}

	@Override
	public String toString() {
		return String.format("[E] %s (at: %s)", super.toString(), time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
	}


	@Override
	public String[] toArray() {
		String[] strings = new String[4];
		strings[0] = "[E]";
		strings[1] = completed ? "1" : "0";
		strings[2] = name;
		strings[3] = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
		return strings;
	}
}
