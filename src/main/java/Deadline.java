import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String date;
    private LocalDateTime durationFormatted;

    public Deadline(String task, String date) {
        super(task);
        this.date = date;
        this.durationFormatted = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                durationFormatted.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")));
    }

}
