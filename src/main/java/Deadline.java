import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public LocalDateTime time;
    public Deadline(String description, int index, boolean isDone) throws DukeInvalidTimeException {
        super(description, index, isDone);
        super.type = TaskType.DEADLINE;
        int idx = this.description.indexOf('/');
        try {
            this.time = LocalDateTime.parse(this.description.substring(idx + 4, idx + 20),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e){
            throw new DukeInvalidTimeException();
        }
    }
    @Override
    public String getStatusWithIndex() {
        int idx = this.description.indexOf('/');
        String task = this.description.substring(0, idx);
        String end = String.format("by: %s", this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
        return String.format("%s. %s%s%s(%s)", index, super.type, isDone ? super.done : super.start, task, end);
    }
    @Override
    public String toString() {
        int idx = this.description.indexOf('/');
        String task = this.description.substring(0, idx);
        String end = new StringBuilder(this.description.substring(idx + 1)).insert(2,':').toString();
        return String.format("%s%s%s(%s)", super.type, isDone ? super.done : super.start, task, end);
    }
}
