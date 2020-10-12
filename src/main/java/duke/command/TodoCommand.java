package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.parser.TodoParser;

public class TodoCommand implements Command {
    private TaskList lines;
    private TodoParser todoParser;

    /**
     * The constructor of the TodoCommand object.
     * @param lines the TaskList to be manipulated.
     * @param todoParser the parser that parses the task command.
     */
    public TodoCommand(TaskList lines, TodoParser todoParser) {
        this.lines = lines;
        this.todoParser = todoParser;
    }

    /**
     * Executes the task command.
     * @return a output message for the user upon executing the task command.
     */
    public String execute() {
        String message;
        try {
            String createdTask = todoParser.checkIfValid();
            lines.addTask(createdTask);
            message = Ui.addedTask(createdTask, lines.getNumberOfItems());
        } catch (DukeException e) {
            message = Ui.handleDukeException(e);
        }
        return message;
    }
}
