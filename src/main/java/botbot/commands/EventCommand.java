package botbot.commands;

import java.time.LocalDateTime;

import botbot.tasks.Event;

/**
 * Adds an event to the task list.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_KEYWORD = "event";

    /**
     * Creates an event command to add an event.
     *
     * @param description Description of event.
     * @param at Time of event.
     */
    public EventCommand(String description, LocalDateTime at) {
        super(new Event(description, at));
    }
}
