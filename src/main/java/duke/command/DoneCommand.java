package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.DukeFileEditor;
import duke.task.Task;
import duke.task.TaskList;
import duke.tools.Parser;

/**
 * Represents a done command.
 */
public class DoneCommand implements Command {
    private final int num;

    /**
     * Constructs a <code>DoneCommand</code>.
     * @param num the index of the task to be marked as done.
     */
    public DoneCommand(int num) {
        this.num = num;
    }

    @Override
    public Response process() throws DukeException {
        TaskList<Task> taskList = Parser.getTaskList();
        Task task = taskList.getTaskList().get(num - 1);
        task.setDone();
        DukeFileEditor dukeFileEditor = new DukeFileEditor(Directory.FILEDIRECTORY);
        dukeFileEditor.setTaskDone(num);
        return new Response(Statement.DONE.toString() + task);
    }
}
