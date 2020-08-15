package command;

import java.util.ArrayList;
import java.util.List;

import exception.DukeException;

/**
 * A subclass of Command which adds a deadline to the taskList
 */
public class DeadlineCommand extends AddCommand {
    private final String name;
    private final String schedule;

    /**
     * Creates a DeadlineCommand.
     *
     * @param content the arguments supplied by the user.
     * @throws DukeException if the content is has missing name or schedule.
     */
    public DeadlineCommand(String content) throws DukeException {
        String[] contentParts = content.split(" /by ");
        if (contentParts[0].equals("") || contentParts[1].equals("")) {
            throw new DukeException("The description of a deadline must not be empty");
        } else {
            this.name = contentParts[0];
            this.schedule = contentParts[1];
        }
    }

    @Override
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add("D");
        contentList.add(this.name);
        contentList.add(this.schedule);
        return contentList;
    }
}
