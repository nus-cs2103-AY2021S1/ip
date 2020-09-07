package com.duke.tasks;

public class RecurringToDos extends RecurringTask {
    public static final String TODO_SYMBOL = "[T]";

    /**
     * Constructor for RecurringToDos.
     * dateAndTime is of YYYY-MM-DD 10pm format.
     *
     * @param task Task description.
     * @param recurringPeriod the period of recurrence in days.
     */
    public RecurringToDos(String task, String recurringPeriod) {
        super(task);
        this.recurringPeriod = recurringPeriod;
    }

    /**
     * Constructor for RecurringToDos.
     * dateAndTime is of YYYY-MM-DD 10pm format.
     *
     * @param task Task description.
     * @param isDone Whether task is done or not.
     * @param recurringPeriod the period of recurrence in days.
     */
    public RecurringToDos(String task, boolean isDone, String recurringPeriod) {
        super(task, isDone);
        this.recurringPeriod = recurringPeriod;
    }

    /**
     * Returns a string representation of the RecurringToDos object to be saved in persistent file.
     *
     * @return String Returns a string representation of the RecurringToDos object to be saved in persistent file.
     */
    @Override
    public String parseToSaveFormat() {
        String isDoneStr = this.isDone ? "1" : "0";
        String res = "T - " + isDoneStr + " - " + this.task;
        return res;
    }

    /**
     * Returns a string representation of the contents of the specified array.
     *
     * @return String Returns a string representation of the contents of the specified array.
     */
    @Override
    public String toString() {
        String doneIndicator = getDoneIndicator();
        return RECURRING_SYMBOL + " " + TODO_SYMBOL + doneIndicator + " " + this.task;
    }
}
