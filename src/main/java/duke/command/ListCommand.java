package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {
    private TaskList lines;

    /**
     * The constructor for the ListCommand object.
     * @param lines the TaskList to be manipulated.
     */
    public ListCommand(TaskList lines) {
        this.lines = lines;
    }

    /**
     * Executes the list command by returning the String that represents the listed tasks.
     * @return the String that represents the listed tasks.
     */
    public String execute() {
        return Ui.listTasks(lines.getList());
    }
}
