package duke.tasks;

import java.time.LocalDateTime;

import duke.Time;

/**
 * @author Damith C. Rajapakse
 * Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with modifications
 *
 * Class to initiate a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime activityTime;

    /**
     * Initializes Deadline.
     */
    public Deadline(String description, LocalDateTime activityTime) {
        super(description);
        this.activityTime = activityTime;
    }

    /**
     * Initializes deadline with an additional parameter compared to the first constructor.
     */
    public Deadline(String description, boolean isDone, LocalDateTime activityTime) {
        super(description, isDone);
        this.activityTime = activityTime;
    }

    public String getActivityTimeInString() {
        return Time.convertTimeToSave(activityTime);
    }

    public LocalDateTime getActivityTime() {
        return activityTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Time.toString(activityTime) + ")";
    }
}
