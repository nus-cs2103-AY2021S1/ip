package duke.task;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import duke.exception.DukeFrequencyNotFoundException;
import duke.exception.DukeInputNotRecognizedException;

/**
 * Represents a RecurringTask.
 */
public class RecurringTask extends Task {
    enum Frequency {
        DAILY, WEEKLY, MONTHLY, TEST;
    }

    private String date;
    private String time;
    private Timer timer = new Timer();


    /**
     * Constructs a RecurringTask object
     *
     * @param description Description of task.
     * @param date Either Daily, weekly or monthly.
     */
    private RecurringTask(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    private void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }


    /**
     * Creates an RecurringTask object.
     *
     * @param description Description of task.
     * @param date Either Daily, weekly or monthly.
     * @param time A string that contains the time for the task.
     * @return RecurringTask.
     * @throws DukeInputNotRecognizedException Input not recognized as time is not in correct format.
     */
    public static RecurringTask createRecurringTask(String description, String date, String time)
            throws DukeInputNotRecognizedException {
        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(1, 4));
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, min);
            RecurringTask recurringTask = new RecurringTask(description, date);
            recurringTask.setTime(time);
            recurringTask.repeatTask(date, calendar);
            return recurringTask;
        } catch (Exception e) {
            throw new DukeInputNotRecognizedException("Please input a time in the 24 hour format. Eg. 0600");
        }
    }

    /**
     * Calculates how long before the recurring task will undo itself.
     *
     * @param date Either daily, weekly or monthly.
     * @return A long that contains the time for the next undo.
     * @throws DukeFrequencyNotFoundException User input is not daily, weekly or monthly.
     */
    protected static long getTimeDelay(String date) throws DukeFrequencyNotFoundException {
        Calendar calendar = Calendar.getInstance();
        if (!hasInputs(date)) {
            throw new DukeFrequencyNotFoundException("Frequency not found. "
                    + "Please only enter Daily, Weekly or Monthly");
        }
        switch (Frequency.valueOf(date.toUpperCase())) {
        case TEST:
            calendar.add(Calendar.MINUTE, 1);
            return calendar.getTimeInMillis() - System.currentTimeMillis();
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
            throw new DukeFrequencyNotFoundException(" Frequency not found. "
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
     * Schedules the task to be repeated.
     *
     * @param date Either daily, weekly or monthly.
     * @param calendar Calender object.
     * @throws DukeFrequencyNotFoundException
     */
    public void repeatTask(String date, Calendar calendar) throws DukeFrequencyNotFoundException {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                RecurringTask.super.undoTask();
            }
        };
        timer.schedule(timerTask, calendar.getTime(), getTimeDelay(date));
    }

    /**
     * Cancels the timer.
     */
    public void cancelRepeat() {
        timer.cancel();
    }

    /**
     * Converts a RecurringTask object to a string.
     *
     * @return A string displaying the task and its status.
     */
    @Override
    public String toString() {
        return "[R]" + super.toString() + " " + date + " at " + time;
    }

}
