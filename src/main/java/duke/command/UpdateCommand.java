package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.DukeFileEditor;
import duke.task.Task;
import duke.tools.Format;

/**
 * Represents a update command which can update time or
 * detail of one task in the task list.
 */
public class UpdateCommand implements Command {
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
        return new Response(new Format<>(task).updateFormat());
    }
}
