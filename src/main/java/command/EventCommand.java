package command;

import java.util.ArrayList;
import java.util.List;

import exception.DukeException;

/**
 * A subclass of Command which adds an event to the taskList
 */
public class EventCommand extends AddCommand {
    private final String name;
    private final String schedule;

    /**
     * Creates a EventCommand.
     *
     * @param content the task information supplied by the user.
     * @throws DukeException if the content has missing name or schedule.
     * or the format does not contain /at.
     */
    public EventCommand(String content) throws DukeException {
        if (!content.contains("/at")) {
            throw new DukeException("☹ OOPS!!! The information contains invalid delimiter");
        } else {
            String[] contentParts = content.split(" /at ");
            if (contentParts[0].equals("") || contentParts[1].equals("")) {
                throw new DukeException("☹ OOPS!!! The information of a event cannot be empty.");
            } else {
                this.name = contentParts[0];
                this.schedule = contentParts[1];
            }
        }
    }

    @Override
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add("E");
        contentList.add(this.name);
        contentList.add(this.schedule);
        return contentList;
    }
}
