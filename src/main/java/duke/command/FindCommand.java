package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/** A subclass of Command which find a task via a keyword */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String content) throws DukeException {
        if (content.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please enter some keyword for me to find");
        } else {
            String[] contentParts = content.split(" ");
            if (contentParts.length > 1 | contentParts[0].equals("")) {
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
