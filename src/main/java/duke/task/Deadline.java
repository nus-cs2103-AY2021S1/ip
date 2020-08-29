package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public static Deadline load(String loadTask) {
        String[] splitTask = loadTask.split(" \\| ", 4);
        Deadline deadline = new Deadline(splitTask[2], splitTask[3]);
        if (splitTask[1].equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String save(int isFinished) {
        return "D | " + super.save(isFinished) + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}