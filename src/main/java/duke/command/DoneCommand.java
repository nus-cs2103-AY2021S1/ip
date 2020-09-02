package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.enums.Message;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Marks a specific task from the TaskList as completed and invokes appropriate UI messages about it
 */
public class DoneCommand implements Command {
    private final String[] parsedInput;
    public DoneCommand(String[] parsedInput) {
        this.parsedInput = parsedInput;
    }
    /**
     * Prints out a done message and displays the newly done task
     *
     * @param tasks Current TaskList
     * @param ui    Where the User shall receive messages about the command
     *
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws DukeException, IOException {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(Message.DONE_MSG.getMsg());
        lines.add(tasks.completeTask(Integer.parseInt(this.parsedInput[1])));
        ui.display(lines);
        Storage.save(tasks);
        return Command.listLinesToString(lines);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
