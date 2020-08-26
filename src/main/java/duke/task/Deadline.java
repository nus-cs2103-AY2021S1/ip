package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;


public class Deadline extends Task {
    protected LocalDate date;

    protected Deadline(String name, boolean isCompleted, LocalDate date) {
        super(name, isCompleted);
        this.date = date;
    }

    public static Deadline newDeadline(String name, LocalDate date){
        return new Deadline(name, false, date);
    }

    public static Deadline existingDeadline(String name, boolean isCompleted, LocalDate date){
        return new Deadline(name, isCompleted, date);
    }

    public boolean isToday(){
        return this.date.isEqual(LocalDate.now());
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ", " + date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()) + ")";
    }

    public String toSaveString(){
        return "D" + " | " + super.toSaveString() + " | " + this.date + "\n";
    }
}
