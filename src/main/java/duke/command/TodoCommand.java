package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/**
 * A subclass of Command which adds a todo to the taskList
 */
public class TodoCommand extends AddCommand {
    private final String name;

    /**
     * Creates a TodoCommand.
     *
     * @param content the task information supplied by the user.
     * @throws DukeException if the content is missing.
     */
    public TodoCommand(String content) throws DukeException {
        if (content.equals("")) {
            throw new DukeException("☹ OOPS!!! The name of a todo cannot be empty.");
        } else {
            this.name = content;
        }
    }

    @Override
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add("T");
        contentList.add(this.name);
        return contentList;
    }
}
