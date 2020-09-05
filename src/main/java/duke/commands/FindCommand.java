package duke.commands;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.DukeException;
import duke.Storage;
import duke.tasks.TaskList;


/**
 * Command to find words in list.
 */
public class FindCommand extends Command {
    private String description;

    /**
     * Constructor to create FindCommand object.
     *
     * @param description description of task being searched for
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Find tasks in TaskList which contains word being searched.
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException when no words are found matching in list.
     */
    public String execute(TaskList tasklist, Storage storage) throws DukeException {
        assert tasklist != null : "Tasklist cannot be null.";
        assert storage != null : "Storage cannot be null.";
        assert description != null : "Description cannot be null.";
        String response = IntStream.range(0, tasklist.getSize())
                .filter(index -> tasklist.get(index).toString().contains(description))
                .mapToObj(index -> String.format("%d. %s", index + 1, tasklist.get(index)))
                .collect(Collectors.joining("\n"))
                .trim();
        if (response.length() == 0) {
            throw new DukeException("word does not exist in TaskList!");
        }
        return response;
    }
}
