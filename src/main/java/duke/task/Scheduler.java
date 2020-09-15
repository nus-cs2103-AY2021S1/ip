package duke.task;

import java.time.LocalDate;

public class Scheduler {
    private static LocalDate dateNow = LocalDate.now();

    private static boolean hasRepeated(LocalDate dateCreated, String timeUnit) {
        LocalDate dateToPass;
        if (timeUnit == "day") {
            dateToPass = dateNow.minusDays(1);
        } else if (timeUnit == "week") {
            dateToPass = dateNow.minusWeeks(1);
        } else {
            dateToPass = dateNow.minusMonths(1);
        }

        return dateToPass.equals(dateCreated) || dateToPass.isAfter(dateCreated);
    }

    /**
     * Schedules a task if it's past the date to be repeated.
     *
     * @param task The task to be repeated.
     */
    public static void scheduleTask(Task task) {
        if (hasRepeated(task.getDateRepeated(), task.getRecurrence())) {
            task.repeatTask();
        }
    }
}
