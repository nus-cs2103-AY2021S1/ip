package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.DukeFileReader;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a find command.
 */
public class FindCommand implements Command {
    private final String[] content;

    /**
     * Constructs a <code>FindCommand</code>.
     * @param content a string of keywords to be matched with tasks.
     */
    public FindCommand(String[] content) {
        this.content = content;
    }

    @Override
    public Response process() throws DukeException {
        TaskList<Task> taskList = new TaskList<>();
        DukeFileReader dukeFileReader = new DukeFileReader(Directory.FILEDIRECTORY);
        taskList = dukeFileReader.matchContent(content);

        return new Response(Statement.FIND.toString() + taskList.toString());
    }
}
