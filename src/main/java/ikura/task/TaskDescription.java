// TaskDescription.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import java.util.Optional;
import java.time.LocalDate;

/**
 * A container class to hold the results of parsing a task-related command. Contains
 * a title, a description, and a date -- all of which are optional.
 */
public class TaskDescription {

    private final Optional<String> title;
    private final Optional<String> description;
    private final Optional<LocalDate> date;

    private TaskDescription(Optional<String> title, Optional<String> description,
        Optional<LocalDate> date) {

        this.date           = date;
        this.title          = title;
        this.description    = description;
    }

    /**
     * Get the title, if any.
     *
     * @return the title, optionally
     */
    public Optional<String> getTitle() {
        return this.title;
    }

    /**
     * Get the description, if any.
     *
     * @return the description, optionally
     */
    public Optional<String> getDescription() {
        return this.description;
    }

    /**
     * Get the date, if any.
     *
     * @return the date, optionally
     */
    public Optional<LocalDate> getDate() {
        return this.date;
    }

    /**
     * Checks whether a title is present.
     *
     * @return true if a title is present
     */
    public boolean hasTitle() {
        return this.title.isPresent();
    }

    /**
     * Checks whether a description is present.
     *
     * @return true if a description is present
     */
    public boolean hasDescription() {
        return this.description.isPresent();
    }

    /**
     * Checks whether a date is present.
     *
     * @return true if a date is present
     */
    public boolean hasDate() {
        return this.date.isPresent();
    }




    /**
     * Create a task description with none of the components present.
     *
     * @return an empty task description
     */
    public static TaskDescription withNothing() {
        return new TaskDescription(Optional.empty(), Optional.empty(), Optional.empty());
    }

    /**
     * Create a task description with only a title.
     *
     * @param title the task title
     * @return a task description
     */
    public static TaskDescription withTitle(String title) {
        return new TaskDescription(Optional.of(title), Optional.empty(), Optional.empty());
    }

    /**
     * Create a task description with only a description.
     *
     * @param description the task description
     * @return a task description
     */
    public static TaskDescription withDescription(String description) {
        return new TaskDescription(Optional.empty(), Optional.of(description), Optional.empty());
    }

    /**
     * Create a task description with a title and a description.
     *
     * @param title the task title
     * @param description the task description
     * @return a task description
     */
    public static TaskDescription withTitleAndDescription(String title, String description) {
        return new TaskDescription(Optional.of(title), Optional.of(description), Optional.empty());
    }

    /**
     * Create a task description with a title and a date.
     *
     * @param title the task title
     * @param date  the task date
     * @return a task description
     */
    public static TaskDescription withTitleAndDate(String title, LocalDate date) {
        return new TaskDescription(Optional.of(title), Optional.empty(), Optional.of(date));
    }

    /**
     * Create a task description with a title, a description, and a date.
     *
     * @param title         the task title
     * @param description   the task description
     * @param date          the task date
     * @return a task description
     */
    public static TaskDescription of(String title, String description, LocalDate date) {
        return new TaskDescription(Optional.of(title), Optional.of(description), Optional.of(date));
    }
}
