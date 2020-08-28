//@@author {FooJingYi}-reused
//Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with minor modifications

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(String taskName, LocalDateTime dateTime)  {
        super(taskName);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, h.m a"));
    }
}