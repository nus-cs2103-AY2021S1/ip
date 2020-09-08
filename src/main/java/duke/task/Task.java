package duke.task;

import duke.Exception.InvalidPriorityLevel;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a task.
 * @author Tee Kok Siang
 */
public abstract class Task {
    /** Description of the task */
    protected String description;
    /** Indicates if task is done */
    protected boolean isDone;
    /** Priority of the task */
    protected Priority priority;
    /** Indicates low priority level */
    public static final int PRIORITY_LOW = 1;
    /** Indicates medium priority level */
    public static final int PRIORITY_MEDIUM = 2;
    /** Indicates high priority level */
    public static final int PRIORITY_HIGH = 3;
    /** Date format */
    public static final String DATE_FORMAT = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";

    /**
     * Constructs a Task object.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.MEDIUM;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks task with priority level
     *
     * @param level Task priority level, 1 - LOW, 2 - MEDIUM, 3 - HIGH
     */
    public void markPriority(int level) {
        priority = Priority.get(level);
    }

    /**
     * Marks task with priority level
     *
     * @param priority Task priority LOW, MEDIUM, HIGH
     */
    public void markPriority(Priority priority) {
        this.priority = priority;
    }


    /**
     * Returns formatted task information.
     * It will be used to write into the file.
     * @return Formatted task information.
     */
    public abstract String toFileString();

    /**
     * Returns task description
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if task is done
     * @return task isDone
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public enum Priority {
        LOW(1),
        MEDIUM(2),
        HIGH(3);

        private static final Map<Integer,Priority> lookup
                = new HashMap<Integer,Priority>();

        static {
            for(Priority s : EnumSet.allOf(Priority.class))
                lookup.put(s.getCode(), s);
        }

        private int code;

        private Priority(int code) {
            this.code = code;
        }

        public int getCode() { return code; }

        public static Priority get(int code) {
            return lookup.get(code);
        }

    }
}