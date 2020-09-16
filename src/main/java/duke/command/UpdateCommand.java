package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.DukeFileEditor;
import duke.task.Task;

public class UpdateCommand implements Command {
    protected static final String FUNCTION = "[" + CommandString.UPDATE
            + "] <task index> <detail/time> /to <content you want to change>";

    private final int lineNum;
    private final String detailOrTime;
    private final String input;

    /**
     * Constructs a update command.
     *
     * @param lineNum the index of the task.
     * @param command command to change time or detail.
     * @param input the updated information.
     */
    public UpdateCommand(int lineNum, String command, String input) {
        this.lineNum = lineNum;
        this.detailOrTime = command;
        this.input = input;
    }

    @Override
    public Response process() throws DukeException {
        DukeFileEditor editor = new DukeFileEditor(Directory.FILEDIRECTORY);
        Task task = editor.update(lineNum, detailOrTime, input);
        return new Response(Statement.UPDATE.toString() + task);
    }
}
