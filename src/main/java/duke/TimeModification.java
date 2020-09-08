package duke;

import java.time.temporal.ChronoUnit;

/**
 * Container class for taskIndex, amount and timeUnit.
 */
public class TimeModification {

    private int taskIndex;
    private int amount;
    private ChronoUnit timeUnit;

    /**
     * Constructor for TimeModification.
     * @param amount
     * @param timeUnit
     */
    public TimeModification(int taskIndex, int amount, ChronoUnit timeUnit) {
        this.taskIndex = taskIndex;
        this.amount = amount;
        this.timeUnit = timeUnit;
    }

    public int getAmount() {
        return amount;
    }

    public ChronoUnit getTimeUnit() {
        return timeUnit;
    }

    public int getTaskIndex() {
        return taskIndex;
    }
}
