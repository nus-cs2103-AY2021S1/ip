package data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private boolean completion;
    private String name;
    private Type type;
    private LocalDate date;

    enum Type {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        private String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * creates a task
     * @param type type of the task, like event, deadline
     * @param name name of the task
     */
    public Task(String type, String name) {
        this.name = name;
        int dateLocation = containsDate(name);
        if (dateLocation >= 0) {
            try {
                this.date = LocalDate.parse(name.substring(dateLocation));
            } catch (Exception e) {
                // can't be parsed
                System.out.println("Date could not be parsed, task added without date.");
            }
            this.name = name.substring(0, dateLocation - 5);
        }
        this.completion = false;
        this.type = Type.valueOf(type.toUpperCase());
    }

    /**
     * overloaded task creation
     * @param type type of the task, like event, deadline
     * @param name name of the task
     * @param completed whether the task is completed
     * @param date date of the task
     */
    public Task(String type, String name, Boolean completed, String date) {
        this(type, name);
        this.completion = completed;
        if (date != null) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            this.date = LocalDate.parse(date, dateFormat);
        }
    }

    public void complete() {
        completion = true;
    }

    public String getName() {
        return name;
    }

    public String getCompletion() {
        if (completion) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }

    public String getType() {
        switch (type) {
        case TODO:
            return "[T]";
        case EVENT:
            return "[E]";
        case DEADLINE:
            return "[D]";
        default:
            // this should not happen?
            return "something went wrong";
        }
    }

    public String getDate() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dateFormat);
    }

    /**
     * checks whether the string contains an indicator for date
     * @param s the string to be checked
     * @return index for when the date starts
     */
    public static int containsDate(String s) {
        int eitherIndex = Math.max(s.indexOf(" /by "), s.indexOf(" /at "));
        int anyIndex = Math.max(eitherIndex, s.indexOf("  @  "));
        return anyIndex == -1 ? -1 : anyIndex + 5;
    }

    public boolean hasDate() {
        return date != null;
    }

    @Override
    public String toString() {
        if (date != null) {
            return getType() + getCompletion() + " " + getName() + "  @  " + getDate();
        } else {
            return getType() + getCompletion() + " " + getName();
        }
    }
}
