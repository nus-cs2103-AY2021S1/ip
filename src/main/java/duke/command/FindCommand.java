package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class FindCommand implements Command {
    String command;

    public FindCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("Find cannot be empty.");
        }
        String keyword = command.substring(5);
        taskList.find(keyword, ui);
    }

    public boolean isDone() {
        return false;
    }
}
