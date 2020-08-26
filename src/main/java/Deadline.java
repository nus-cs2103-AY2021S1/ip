import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
	private String byDateString;
	private LocalDate byDate;

	public Deadline(String taskContent, String byDateString) {
		super(taskContent);
		this.byDateString = byDateString;
	}
	public Deadline(String taskContent, LocalDate byDate) {
		super(taskContent);
		this.byDate = byDate;
	}

	@Override
	public String getType() {
		return "D";
	}
	@Override
    public String getDate() {
        if (byDateString != null) {
            return byDateString;
        } else {
            return byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }
	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + getDate() + ")";
	}
}