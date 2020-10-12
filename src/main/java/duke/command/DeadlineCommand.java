package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.parser.DeadlineParser;

public class DeadlineCommand implements Command {
    private TaskList lines;
    private DeadlineParser deadlineParser;

    /**
     * The constructor method for the DeadlineCommand object.
     * @param lines the TaskList to be manipulated.
     * @param deadlineParser the parser that parses the deadline command.
     */
    public DeadlineCommand(TaskList lines, DeadlineParser deadlineParser) {
        this.lines = lines;
        this.deadlineParser = deadlineParser;
    }

    /**
     * Executes the deadline command
     * @return the string that represents the outcome of the execution of the command.
     */
    public String execute() {
        String message;
        try {
            String task = deadlineParser.checkIfValid();
            lines.addTask(task);
            message = Ui.addedTask(task, lines.getNumberOfItems());
        } catch (DukeException e) {
            message = Ui.handleDukeException(e);
        }
        return message;
    }
}
