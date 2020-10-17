package duke.command;

import duke.DukeException;
import duke.storage.TaskList;
import duke.ui.Ui;
import duke.parser.UntagParser;

public class UntagCommand implements Command {
    private TaskList lines;
    private UntagParser untagParser;

    /**
     * The constructor method for the UntagCommand object.
     * @param lines the TaskList to be manipulated.
     * @param untagParser the parser that parses the untag command.
     */
    public UntagCommand(TaskList lines, UntagParser untagParser) {
        this.lines = lines;
        this.untagParser = untagParser;
    }

    /**
     * Executes the untag command.
     * @return the output message for the user upon executing the untag command.
     */
    public String execute() {
        String message;
        try {
            int index = untagParser.checkIfValid();
            boolean tagRemoved = false;
            if (lines.removeTag(index - 1)) {
                tagRemoved = true;
            }
            message = Ui.untaggedTask(lines.getTask(index - 1), tagRemoved);
        } catch (DukeException e) {
            message = Ui.handleDukeException(e);
        }
        return message;
    }
}
