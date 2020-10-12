package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.parser.DoneParser;

public class DoneCommand implements Command {
    private TaskList lines;
    private DoneParser doneParser;

    /**
     * The constructor for the DoneCommand object.
     * @param lines The TaskList to be manipulated
     * @param doneParser the parser that parses the done command
     */
    public DoneCommand(TaskList lines, DoneParser doneParser) {
        this.lines = lines;
        this.doneParser = doneParser;
    }

    /**
     * Executes the done command and returns a string to be output to the user.
     * @return the String representing the output to the user.
     */
    public String execute() {
        String message;
        try {
            int itemNumber = doneParser.checkIfValid();
            String completedTask = lines.getTask(itemNumber - 1);
            message = Ui.done(completedTask);
            lines.updateTask(Ui.updateDoneTask(completedTask), itemNumber - 1);
            return message;
        } catch (DukeException e) {
            message = Ui.handleDukeException(e);
            return message;
        }
    }
}
