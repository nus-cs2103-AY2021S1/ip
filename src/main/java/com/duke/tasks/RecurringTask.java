package com.duke.tasks;

public abstract class RecurringTask extends Task {
    public static final String RECURRING_SYMBOL = "[R]";
    protected String recurringPeriod;
    /**
     * Default Constructor for Deadlines.
     * Nothing is initialized in this constructor.
     */
    public RecurringTask() {
        super();
        this.recurringPeriod = "none";
    }

    public RecurringTask(String task) {
        super(task);
    }

    public RecurringTask(String task, boolean isDone) {
        super(task, isDone);
    }
}
