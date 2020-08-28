import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate date;
    LocalTime time;
    String preposition;

    Deadline(String title, String preposition, LocalDate date, LocalTime time)
            throws WrongUsageException {
        super(title);
        this.name = "deadline";
        this.usage = "deadline [TaskToBeDone] /by [DD/MM/YYYY] [HH:MM]";
        this.description = "Stores a task in the list marked as a deadline";
        if (title.isEmpty() || preposition.isEmpty()) {
            throw new WrongUsageException(this.name, this.usage);
        }
        this.preposition = preposition;
        this.date = date;
        this.time = time;
        this.saveRep = "[D] " + super.toString() + " (" + preposition + ": " +
                date + " " +
                time + ")";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (" + preposition + ": " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                time.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}
