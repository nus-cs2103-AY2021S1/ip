package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.DukeFileReader;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand implements Command {
    protected static final String FUNCTION = "[" + CommandString.FIND + "] <keyword(s)>";

    private final String[] content;

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
