package main.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;

import main.command.Option;
import main.exception.InvalidOptionException;

/**
 * Represents tasks with a deadline.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");
    private final LocalDateTime time;
    private Option recurrence = null;

    /**
     * Constructs a Deadline instance with the name of
     * task and time of deadline.
     * @param name the name of task.
     * @param time the deadline of task.
     * @param options the options of the task.
     * @param tags the tags associated with the task.
     */
    public Deadline(String name, LocalDateTime time,
                    HashSet<Option> options, String[] tags) {
        super(name, tags);
        this.time = time;

        for (Option option : options) {
            switch (option) {
            case RECURRING_DAILY:
            case RECURRING_WEEKLY:
            case RECURRING_MONTHLY:
            case RECURRING_YEARLY:
                if (recurrence == null) {
                    recurrence = option;
                }

                break;
            default:
                break;
            }
        }
    }

    /**
     * Constructs a Deadline instance with the name of
     * task, time of deadline and the done state of the task.
     * @param name the name of task.
     * @param recurrenceAlias the alias of the recurrence.
     * @param time the deadline of task.
     * @param isDone the done state of the task.
     * @param tags the tags associated with the task.
     */
    public Deadline(String name, String recurrenceAlias, String time,
                    boolean isDone, String[] tags) throws InvalidOptionException {
        super(name, tags);
        LocalDateTime parsedTime = LocalDateTime.parse(time);
        recurrence = recurrenceAlias.length() == 0
                ? null : Option.getOptionFromShortAlias(recurrenceAlias);

        LocalDateTime now = LocalDateTime.now();
        boolean hasDeadlinePassed = parsedTime.isBefore(now);
        boolean hasRecurrence = recurrence != null;
        boolean isToBeReset = hasDeadlinePassed && hasRecurrence;
        long addedTime;

        if (isToBeReset) {
            switch (recurrence) {
            case RECURRING_DAILY:
                addedTime = parsedTime.until(now, ChronoUnit.DAYS) + 1;
                this.time = parsedTime.plusDays(addedTime);
                break;
            case RECURRING_WEEKLY:
                addedTime = parsedTime.until(now, ChronoUnit.WEEKS) + 1;
                this.time = parsedTime.plusWeeks(addedTime);
                break;
            case RECURRING_MONTHLY:
                addedTime = parsedTime.until(now, ChronoUnit.MONTHS) + 1;
                this.time = parsedTime.plusMonths(addedTime);
                break;
            case RECURRING_YEARLY:
                addedTime = parsedTime.until(now, ChronoUnit.YEARS) + 1;
                this.time = parsedTime.plusYears(addedTime);
                break;
            default:
                this.time = parsedTime;
            }
        } else {
            this.time = parsedTime;
        }

        if (isDone && !isToBeReset) {
            setDone();
        }
    }

    /**
     * Returns the string meant for writing to disk.
     * @return the string meant for writing to disk.
     */
    @Override
    public String write() {
        String recurrence = this.recurrence == null
                ? "" : Option.getAlias(this.recurrence);

        return String.format("D,%s,%s%s", recurrence, time, super.write());
    }

    @Override
    public String toString() {
        return String.format("[D]%s\n(by: %s)", super.toString(),
                time.format(FORMATTER));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline o = (Deadline) obj;
            boolean isSameRecurrence = recurrence == o.recurrence;
            boolean isSameTime = time.equals(o.time);

            return super.equals(o) && isSameTime && isSameRecurrence;
        }
        return false;
    }
}
