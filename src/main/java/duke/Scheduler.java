package duke;

import java.time.LocalDate;
import java.time.LocalTime;

public class Scheduler {
    /**
     * Returns a boolean to show whether the events are clashing.
     * Solution adapted from https://stackoverflow.com/questions/2309558/time-comparison.
     *
     * @param scheduled event already scheduled previously.
     * @param toBeScheduled event attempting to be scheduled by user.
     * @return boolean whether events are clashing.
     */
    public boolean isClashing(Event scheduled, Event toBeScheduled) {
        LocalDate scheduledDate = scheduled.getDate();
        LocalDate toBeScheduledDate = toBeScheduled.getDate();

        if (!scheduledDate.equals(toBeScheduledDate)) {
            return false;
        }

        assert (scheduledDate.equals(toBeScheduledDate));
        LocalTime scheduledStartTime = scheduled.getStartTime();
        LocalTime scheduledEndTime = scheduled.getEndTime();
        LocalTime toBeScheduledStartTime = toBeScheduled.getStartTime();
        LocalTime toBeScheduledEndTime = toBeScheduled.getEndTime();

        if (scheduledStartTime.equals(toBeScheduledStartTime)) {
            return true;
        } else if (scheduledEndTime.equals(toBeScheduledEndTime)) {
            return true;
        } else if (scheduledStartTime.isBefore(toBeScheduledStartTime)) {
            if (scheduledEndTime.isAfter(toBeScheduledStartTime)) {
                return true;
            } else if (scheduledEndTime.isBefore(toBeScheduledStartTime)) {
                return false;
            } else if (scheduledEndTime.equals(toBeScheduledStartTime)) {
                return false;
            }
        } else if (scheduledStartTime.isAfter(toBeScheduledStartTime)) {
            if (scheduledStartTime.isBefore(toBeScheduledEndTime)) {
                return true;
            } else if (scheduledStartTime.isAfter(toBeScheduledEndTime)) {
                return false;
            } else if (scheduledStartTime.equals(toBeScheduledEndTime)) {
                return false;
            }
        }
        return false;
    }
}
