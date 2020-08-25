package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/** A subclass of Command which find a task via a keyword */
public class FindCommand extends Command {
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
            throw new DukeException("☹ OOPS!!! Please enter some keyword for me to find");
        } else {
            String[] contentParts = content.split(" ");
            if (contentParts.length > KEYWORD_LENGTH | contentParts[0].equals("")) {
                throw new DukeException("☹ OOPS!!! Please enter one keyword at a time");
            } else {
                this.keyword = contentParts[0];
            }
        }
    }

    @Override
    public String sendRequest() {
        return "search";
    }

    @Override
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add(this.keyword);
        return contentList;
    }
}
