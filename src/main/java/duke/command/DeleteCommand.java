package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.DukeFileEditor;
import duke.task.Task;
import duke.task.TaskList;
import duke.tools.FormatString;
import duke.tools.Parser;

/**
 * Represents a delete command that deletes respective
 * in Duke program.
 */
public class DeleteCommand implements Command {
    private final int num;

    /**
     * Constructs a <code>DeleteCommand</code>.
     *
     * @param num the index of the task to be deleted.
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    @Override
    public Response process() throws DukeException {
        TaskList<Task> taskList = Parser.getTaskList();
        Task task = taskList.getTaskList().get(num - 1);
        DukeFileEditor dukeFileEditor = new DukeFileEditor(Directory.FILEDIRECTORY);
        dukeFileEditor.deleteLine(num);

        Parser.reloadTaskList();
        taskList = Parser.getTaskList();
        String response =
                Statement.DELETE.toString()
                        + task
                        + FormatString.NEXT_LINE.toString()
                        + String.format
                        (Statement.REPORT.toString(), taskList.getTaskList().size());

        return new Response(response);
    }
}
