package utils;

import java.time.LocalDateTime;

public interface Schedulable extends Comparable<Schedulable> {
    /** Returns the datetime of the {@code Schedulable} object. */
    LocalDateTime getDateTime();
    /** Returns whether the datetime has elapsed. */
    boolean hasDateTimeElapsed();
}
