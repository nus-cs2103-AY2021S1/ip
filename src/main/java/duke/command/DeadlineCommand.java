package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/** A subclass of Command which adds a deadline to the taskList */
public class DeadlineCommand extends AddCommand {
    private final String name;
    private final String schedule;

    /**
     * Creates a DeadlineCommand.
     *
     * @param content The task information supplied by the user.
     * @throws DukeException If the content has missing name or schedule or the format does not contain /by.
     */
    public DeadlineCommand(String content) throws DukeException {
        if (!content.contains("/by")) {
            throw new DukeException("OOPS!!! The information contains invalid delimiter");
        }

        // The expected content parts are task's name and schedule.
        String[] contentParts = content.split(" /by ");

        if (contentParts[0].equals("") || contentParts[1].equals("")) {
            throw new DukeException("OOPS!!! The information of a deadline cannot be empty.");
        }

        this.name = contentParts[0];
        this.schedule = contentParts[1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add("D");
        contentList.add(this.name);
        contentList.add(this.schedule);
        return contentList;
    }
}
