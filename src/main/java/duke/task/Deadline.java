package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate ;
    }

    public static Deadline load(String taskDetails) {
        String[] splitTaskDetails = taskDetails.strip().split(" \\| ", 4);
        Deadline deadline = new Deadline(splitTaskDetails[2], LocalDate.parse(splitTaskDetails[3]));
        if (splitTaskDetails[1].equals("true")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String saveAs() {
        return "D | " + super.saveAs() + " | " + dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(
                DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}