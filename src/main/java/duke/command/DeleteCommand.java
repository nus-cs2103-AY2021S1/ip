package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.DukeFileEditor;
import duke.task.Task;
import duke.task.TaskList;
import duke.tools.FormatString;
import duke.tools.Parser;

public class DeleteCommand implements Command {
    protected static final String FUNCTION = "[" + CommandString.DELETE + "] <task index>";

    private final int num;

    public DeleteCommand(int num) {
        this.num = num;
    }

    @Override
    public Response process() throws DukeException {
        TaskList<Task> taskList = Parser.getTaskList();
        Task task = taskList.getTaskList().get(num - 1);
        DukeFileEditor dukeFileEditor = new DukeFileEditor(Directory.FILEDIRECTORY);
        dukeFileEditor.deleteLine(num);

        String response =
                Statement.DELETE.toString()
                        + task
                        + FormatString.NEXTLINE.toString()
                        + String.format
                        (Statement.REPORT.toString(), taskList.getTaskList().size());

        return new Response(response);
    }
}
