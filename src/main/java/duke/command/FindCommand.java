package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/** A subclass of Command which find a task via a keyword */
public class FindCommand extends Command {
    protected static final String FIND_REQUEST = "search";
    private static final int KEYWORD_LENGTH = 1;
    private final String keyword;

    /**
     * Creates a FindCommand.
     *
     * @param content The keyword supplied by the user.
     * @throws DukeException If no keyword is supplied or more than 1 keyword is supplied.
     */
    public FindCommand(String content) throws DukeException {
        if (content.isEmpty()) {
            throw new DukeException("OOPS!!! Please enter some keyword for me to find");
        }

        // The expected content part is task's index in the task list.
        String[] contentParts = content.split(" ");

        if (contentParts[0].equals("")) {
            throw new DukeException("OOPS!!! Please enter a keyword");
        } else if (contentParts.length > KEYWORD_LENGTH) {
            throw new DukeException("OOPS!!! Please enter one keyword at a time");
        }

        this.keyword = contentParts[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sendRequest() {
        return FIND_REQUEST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add(this.keyword);
        return contentList;
    }
}
