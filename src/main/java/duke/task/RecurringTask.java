package duke.task;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import duke.exception.DukeFrequencyNotFoundException;

public class RecurringTask extends Task {
    enum Frequency {
        DAILY, WEEKLY, MONTHLY;
    }

    private String date;
    private Timer timer = new Timer();

    private RecurringTask(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * @param description
     * @param date
     * @return
     * @throws DukeFrequencyNotFoundException
     */
    public static RecurringTask createRecurringTask(String description, String date)
            throws DukeFrequencyNotFoundException {
        RecurringTask recurringTask = new RecurringTask(description, date);
        recurringTask.repeatTask(date);
        return recurringTask;
    }

    /**
     * @param date
     * @return
     * @throws DukeFrequencyNotFoundException
     */
    private static long getTimeDelay(String date) throws DukeFrequencyNotFoundException {
        Calendar calendar = Calendar.getInstance();
        if (!hasInputs(date)) {
            throw new DukeFrequencyNotFoundException("Frequency not found. "
                    + "Please only enter Daily, Weekly or Monthly");
        }
        switch (Frequency.valueOf(date.toUpperCase())) {
        case DAILY:
            calendar.add(Calendar.DATE, 1);
            return calendar.getTimeInMillis() - System.currentTimeMillis();
        case WEEKLY:
            calendar.add(Calendar.DATE, 7);
            return calendar.getTimeInMillis() - System.currentTimeMillis();
        case MONTHLY:
            calendar.add(Calendar.MONTH, 1);
            return calendar.getTimeInMillis() - System.currentTimeMillis();
        default:
            throw new DukeFrequencyNotFoundException("Frequency not found. "
                   + "Please only enter Daily, Weekly or Monthly");
        }
    }

    /**
     * Checks if string is inside enum Frequency.
     *
     * @param input User input.
     * @return Boolean value.
     */
    private static boolean hasInputs(String input) {
        for (Frequency i : Frequency.values()) {
            if (input.toUpperCase().equals(i.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param date
     * @throws DukeFrequencyNotFoundException
     */
    public void repeatTask(String date) throws DukeFrequencyNotFoundException {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                RecurringTask.super.undoTask();
            }
        };
        long delay = 0L;
        timer.scheduleAtFixedRate(timerTask, delay, getTimeDelay(date));
    }

    /**
     *
     */
    public void cancelRepeat() {
        timer.cancel();
    }

    /**
     * Converts a Deadline object to a string.
     *
     * @return A string displaying the task and its status.
     */
    @Override
    public String toString() {
        return "[R]" + super.toString() + date;
    }

}
