import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate time;
    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);;
    }

    public Deadline(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = LocalDate.parse(time);
    }

    String printTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getStatusIcon() {
        return String.format("[D]%s", super.getStatusIcon(), printTime());
    }

    @Override
    public String writeToFile() {
        int done = isDone ? 1 : 0;
        return String.format("D//%d//%s//%s\n", done, this.description, this.time );

    }

    @Override
    public String getOutput() {
        return String.format("%s %s(By: %s)", getStatusIcon(), this.description, printTime());
    }

}
