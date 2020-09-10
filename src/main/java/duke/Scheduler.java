package duke;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.stream.Collectors;
import java.util.List;

/**
 * Scheduler class deals with event schedules to detect any anomalies
 * in scheduled events.
 */
public class Scheduler {

    /**
     * Returns a boolean to indicate an event being added clashes with
     * existing schedules.
     *
     * @param userTasks list of tasks from user.
     * @param toBeScheduled event attempting to be scheduled by user.
     * @return boolean whether event clashes with schedule.
     */
    public boolean isEventClashingSchedule(TaskList userTasks, Event toBeScheduled) {
        boolean isEventClashing = false;

        List<Task> events = userTasks.getTaskList()
                .stream()
                .filter(task -> task instanceof Event)
                .collect(Collectors.toList());

        for (Task task : events) {
            isEventClashing = isEventClashingAnotherEvent(((Event) task), toBeScheduled);
            if (isEventClashing) {
                break;
            }
        }

        return isEventClashing;
    }

    /**
     * Returns a boolean to indicate whether two events are clashing.
     * Solution adapted from https://stackoverflow.com/questions/2309558/time-comparison.
     *
     * @param scheduled event already scheduled previously.
     * @param toBeScheduled event attempting to be scheduled by user.
     * @return boolean whether events are clashing.
     */
    public boolean isEventClashingAnotherEvent(Event scheduled, Event toBeScheduled) {
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
        } else if (scheduledStartTime.isBefore(toBeScheduledStartTime)
                && scheduledEndTime.isAfter(toBeScheduledStartTime)) {
            return true;
        } else if (scheduledStartTime.isAfter(toBeScheduledStartTime)
                && scheduledStartTime.isBefore(toBeScheduledEndTime)) {
            return true;
        }

        return false;
    }
}
